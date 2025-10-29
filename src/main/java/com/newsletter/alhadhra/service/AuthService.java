package com.newsletter.alhadhra.service;

import com.newsletter.alhadhra.entity.UserEntity;
import com.newsletter.alhadhra.enums.Role;
import com.newsletter.alhadhra.exception.BadRequestException;
import com.newsletter.alhadhra.exception.ResourceNotFoundException;
import com.newsletter.alhadhra.payload.ApiResponse;
import com.newsletter.alhadhra.payload.LoginRequest;
import com.newsletter.alhadhra.payload.LoginResponse;
import com.newsletter.alhadhra.payload.SignupRequest;
import com.newsletter.alhadhra.repository.UserRepository;
import com.newsletter.alhadhra.security.TokenProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    public LoginResponse login(@Valid LoginRequest loginRequest) {

        UserEntity user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", loginRequest.getEmail()));


        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);
        return new LoginResponse(token);
    }

    public ApiResponse signup(@Valid SignupRequest signUpRequest) {
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Email address already in use. Try another address.");
        }

        // Creating user's account
        UserEntity user = new UserEntity();
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setRole(Role.ADMIN);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity result = userRepository.save(user);

        return new ApiResponse(
                true,
                "User registered successfully."
        );
    }



}
