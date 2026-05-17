package e_commerce.University.controller;


import e_commerce.University.dto.LoginRequest;
import e_commerce.University.entity.User;
import e_commerce.University.repository.UserRepository;
import e_commerce.University.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtService jwtService,
                          UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            User user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            String token = jwtService.generateToken(
                    user.getEmail(),
                    user.getRole().name()
            );

            return ResponseEntity.ok(token);

        } catch (BadCredentialsException e) {
            return ResponseEntity
                    .status(401)
                    .body("Invalid email or password");

        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(400)
                    .body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity
                    .status(500)
                    .body("Something went wrong");
        }
    }
}