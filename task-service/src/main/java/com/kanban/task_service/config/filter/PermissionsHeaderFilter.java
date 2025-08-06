package com.kanban.task_service.config.filter;

import com.kanban.task_service.service.AccountsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PermissionsHeaderFilter extends OncePerRequestFilter {

    private final AccountsService accountsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {


        String userIdHeader = request.getHeader("X-User-Id");
        String permissionsHeader = request.getHeader("X-User-Permissions");
        String userEmail = request.getHeader("X-User-Email");

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (permissionsHeader != null) {
            authorities = Arrays.stream(permissionsHeader.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        }

        if (userIdHeader != null) {
            UUID user_id = UUID.fromString(userIdHeader);

            accountsService.existAccount(user_id,userEmail);

            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(user_id, null, authorities);

            SecurityContextHolder.clearContext();
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
