import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        // 🔥 EXEMPLO SIMPLES (depois troca por banco)
        if (!request.getUsername().equals("admin") || !request.getPassword().equals("123")) {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }

        String token = jwtService.generateToken(request.getUsername());

        return ResponseEntity.ok(
                Map.of("token", token)
        );
    }
}