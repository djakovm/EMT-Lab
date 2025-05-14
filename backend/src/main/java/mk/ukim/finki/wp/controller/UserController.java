package mk.ukim.finki.wp.controller;

import io.swagger.v3.oas.annotations.Operation;
import mk.ukim.finki.wp.application.dto.RegisterDto;
import mk.ukim.finki.wp.domain.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:5173")

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Operation(summary = "Register a new user", description = "Creates a new user account with encoded password and assigned role.")
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto dto) {
        userService.register(dto.username(), passwordEncoder.encode(dto.password()), dto.name(), dto.surname(), dto.role());
        return ResponseEntity.ok("User registered");
    }

    @Operation(summary = "Login endpoint", description = "This endpoint is used for login via Spring Security form login.")
    @GetMapping("/login")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok("Login successful");
    }

    @Operation(summary = "Logs the user out of the application")
    @GetMapping("/logout/success")
    public ResponseEntity<String> logoutSuccess() {
        return ResponseEntity.ok("Logout successful");
    }

}
