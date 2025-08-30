package com.example.bookstore_backend.Security;

import com.example.bookstore_backend.Dto.AuthResponse;
import com.example.bookstore_backend.Dto.LoginRequest;
import com.example.bookstore_backend.Dto.RegisterRequest;
import com.example.bookstore_backend.Entity.User;
import com.example.bookstore_backend.Repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController @RequestMapping("/api/auth") @CrossOrigin
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) throws Exception {
        if(userRepository.existsByEmail(request.getEmail())) {
            return "Email is already in use";
        }
        User u =  User.builder().email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .roles((Set.of("ROLE_USER"))).build();
        userRepository.save(u);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest loginRequest) throws Exception {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
            String token = jwtService.generateToken(loginRequest.getEmail(), Map.of("roles", "ROLE_USER"));
            return new AuthResponse(token);
        }
        catch (AuthenticationException e){
            throw new RuntimeException("Invalid email or password");
        }
    }
}
