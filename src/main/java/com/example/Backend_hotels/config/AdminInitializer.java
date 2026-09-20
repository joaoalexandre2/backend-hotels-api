package com.example.Backend_hotels.config;

import com.example.Backend_hotels.repository.UserRepository;
import com.example.Backend_hotels.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Cria o usuário administrador na primeira execução, a partir de variáveis de
 * ambiente (ADMIN_USERNAME e ADMIN_PASSWORD). A senha nunca fica no código.
 */
@Component
public class AdminInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(AdminInitializer.class);

    private final UserRepository userRepository;
    private final AuthService authService;

    @Value("${app.admin.username:}")
    private String username;

    @Value("${app.admin.password:}")
    private String password;

    public AdminInitializer(UserRepository userRepository, AuthService authService) {
        this.userRepository = userRepository;
        this.authService = authService;
    }

    @Override
    public void run(String... args) {
        if (username.isBlank() || password.isBlank()) {
            log.info("ADMIN_USERNAME/ADMIN_PASSWORD não definidos: nenhum administrador será criado.");
            return;
        }

        if (userRepository.findByUsername(username).isPresent()) {
            return;
        }

        authService.register(username, password);
        log.info("Usuário administrador '{}' criado.", username);
    }
}
