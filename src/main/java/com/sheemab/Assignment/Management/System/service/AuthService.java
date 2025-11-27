package com.sheemab.Assignment.Management.System.service;

import com.sheemab.Assignment.Management.System.dto.AuthResponse;
import com.sheemab.Assignment.Management.System.dto.ChangePasswordRequest;
import com.sheemab.Assignment.Management.System.dto.LoginRequest;
import com.sheemab.Assignment.Management.System.entity.User;
import com.sheemab.Assignment.Management.System.exception.InvalidCredentialsException;
import com.sheemab.Assignment.Management.System.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    /**
     * Authenticate a user and return a JWT. Throws InvalidCredentialsException if auth fails.
     */
    public AuthResponse login(LoginRequest request) {
        log.info("Attempting login for email: {}", request.getEmail());
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));
        // Verify password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            log.warn("Invalid password for user {}", request.getEmail());
            throw new InvalidCredentialsException("Invalid email or password");
        }
        // Generate JWT token
        String token = jwtService.generateToken(user.getEmail());
        log.info("User {} authenticated successfully", user.getEmail());
        return new AuthResponse(token, "Bearer");
    }

    public void changePassword(String loggedInEmail, ChangePasswordRequest request) {

        log.info("Password change request received for user: {}", loggedInEmail);

        User user = userRepository.findByEmail(loggedInEmail)
                .orElseThrow(() -> {
                    log.warn("Password change failed: User not found for email {}", loggedInEmail);
                    return new RuntimeException("User not found");
                });

        // 1️⃣ Check old password
        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {

            log.warn("Password change failed: Incorrect old password for user {}", loggedInEmail);
            throw new RuntimeException("Old password is incorrect");
        }

        // 2️⃣ Encode and update password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        log.info("Password successfully updated for user: {}", loggedInEmail);
    }

}
