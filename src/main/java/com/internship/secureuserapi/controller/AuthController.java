package com.internship.secureuserapi.controller;

import com.internship.secureuserapi.dto.AuthResponse;
import com.internship.secureuserapi.dto.LoginRequest;
import com.internship.secureuserapi.dto.RegisterRequest;
import com.internship.secureuserapi.model.User;
import com.internship.secureuserapi.repository.UserRepository;
import com.internship.secureuserapi.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthController(UserRepository repo,
                          PasswordEncoder encoder,
                          JwtService jwtService,
                          AuthenticationManager authenticationManager) {
        this.repo = repo;
        this.encoder = encoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest req) {

        User user = new User();
        user.setName(req.name);
        user.setEmail(req.email);
        user.setAge(req.age);
        user.setPassword(encoder.encode(req.password));
        user.setRole("ROLE_USER");

        repo.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest req) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.email, req.password));

        User user = repo.findByEmail(req.email).orElseThrow();
        String token = jwtService.generateToken(user);

        return ResponseEntity.ok(new AuthResponse(token, user.getRole()));
    }
}
