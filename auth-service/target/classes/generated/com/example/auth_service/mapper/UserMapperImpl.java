package com.example.auth_service.mapper;

import com.example.auth_service.dto.RegisterRequest;
import com.example.auth_service.dto.UserDTO;
import com.example.auth_service.model.User;
import java.sql.Timestamp;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-29T14:46:47+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (Microsoft)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(RegisterRequest registerRequest) {
        if ( registerRequest == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.username( registerRequest.username() );
        user.password( registerRequest.password() );
        user.email( registerRequest.email() );

        return user.build();
    }

    @Override
    public UserDTO toDto(User user) {
        if ( user == null ) {
            return null;
        }

        String username = null;
        String email = null;
        Timestamp createdAt = null;

        username = user.getUsername();
        email = user.getEmail();
        createdAt = user.getCreatedAt();

        UserDTO userDTO = new UserDTO( username, email, createdAt );

        return userDTO;
    }
}
