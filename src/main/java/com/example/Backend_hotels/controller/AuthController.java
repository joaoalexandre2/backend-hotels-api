package com.example.Backend_hotels.controller;

import com.example.Backend_hotels.domain.User;
import com.example.Backend_hotels.dto.hotel.LoginRequest;
import com.example.Backend_hotels.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final HttpSessionSecurityContextRepository contextRepository = new HttpSessionSecurityContextRepository();

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request,
                                   HttpServletRequest req,
                                   HttpServletResponse res) {

        User user = request.getUsername() == null ? null
                : userRepository.findByUsername(request.getUsername()).orElse(null);

        if (user == null
                || request.getPassword() == null
                || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body(Map.of("message", "Usuário ou senha inválidos"));
        }

        // Evita fixação de sessão: descarta a sessão antiga antes de criar a nova
        HttpSession antiga = req.getSession(false);
        if (antiga != null) {
            antiga.invalidate();
        }

        Authentication auth = UsernamePasswordAuthenticationToken.authenticated(
                user.getUsername(), null, AuthorityUtils.createAuthorityList("ROLE_ADMIN"));

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(auth);
        SecurityContextHolder.setContext(context);
        req.getSession(true);
        contextRepository.saveContext(context, req, res);

        return ResponseEntity.ok(Map.of(
                "message", "Login realizado com sucesso",
                "user", Map.of("id", user.getId(), "username", user.getUsername())
        ));
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).body(Map.of("message", "Não autenticado"));
        }
        return ResponseEntity.ok(Map.of("user", Map.of("username", authentication.getName())));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest req, HttpServletResponse res, Authentication authentication) {
        new SecurityContextLogoutHandler().logout(req, res, authentication);
        return ResponseEntity.ok(Map.of("message", "Logout realizado com sucesso"));
    }
}
