package com.example.auth_service.service.implementation;

import com.example.auth_service.dto.AssignAuthorityRequest;
import com.example.auth_service.dto.LoginRequest;
import com.example.auth_service.dto.RegisterRequest;
import com.example.auth_service.exception.InvalidCredentialsException;
import com.example.auth_service.jwt.JwtUtil;
import com.example.auth_service.model.Authorities;
import com.example.auth_service.model.Role;
import com.example.auth_service.model.User;
import com.example.auth_service.repository.AuthoritiesRepository;
import com.example.auth_service.repository.RoleRepository;
import com.example.auth_service.repository.UserRepository;
import com.example.auth_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthoritiesRepository authoritiesRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;


    @Override
    public User register(RegisterRequest registerRequest) {
        if (userRepository.findByEmail(registerRequest.email()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }
        if (userRepository.findByUsername(registerRequest.username()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
        }

        User user = User.builder()
                .username(registerRequest.username())
                .email(registerRequest.email())
                .password(passwordEncoder.encode(registerRequest.password()))
                .build();

        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found"));
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        user.setRoles(roles);

        return userRepository.save(user);
    }

    @Override
    public String login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.email())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));
        if (!passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid password or username");
        }
        List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .flatMap(r->r.getAuthorities().stream()
                        .map(auth -> new SimpleGrantedAuthority(auth.getName())))
                .toList();
        return jwtUtil.generateToken(loginRequest.email(), authorities);
    }

    @Override
    public void assignAuthorityToRole(AssignAuthorityRequest request) {
        Role role = roleRepository.findByName(request.roleName())
                .orElseThrow(() -> new RuntimeException("Role not found: " + request.roleName()));
        Authorities auth = authoritiesRepository.findByName(request.authority())
                .orElseThrow(() -> new RuntimeException("Authority not found: " + request.authority()));
        role.getAuthorities().add(auth);
        roleRepository.save(role);
    }
}
