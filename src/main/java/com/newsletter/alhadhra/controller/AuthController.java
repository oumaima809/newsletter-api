package com.newsletter.alhadhra.controller;

import com.newsletter.alhadhra.payload.ApiResponse;
import com.newsletter.alhadhra.payload.LoginRequest;
import com.newsletter.alhadhra.payload.LoginResponse;
import com.newsletter.alhadhra.payload.SignupRequest;
import com.newsletter.alhadhra.service.AuthService;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse response = authService.login(loginRequest);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest signUpRequest) {
        ApiResponse apiResponse = authService.signup(signUpRequest);
        return ResponseEntity.ok(apiResponse);
    }
}