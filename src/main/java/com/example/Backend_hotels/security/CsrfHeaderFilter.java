package com.example.Backend_hotels.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;

/**
 * Defesa contra CSRF para autenticação por cookie: exige o cabeçalho
 * X-Requested-With em requisições que alteram dados. Um site de terceiros não
 * consegue enviar esse cabeçalho sem passar pelo CORS, que só libera as
 * origens configuradas.
 */
public class CsrfHeaderFilter extends OncePerRequestFilter {

    private static final Set<String> METODOS_SEGUROS = Set.of("GET", "HEAD", "OPTIONS", "TRACE");

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        boolean altera = !METODOS_SEGUROS.contains(request.getMethod());

        if (altera && request.getHeader("X-Requested-With") == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"message\":\"Cabeçalho X-Requested-With ausente\"}");
            return;
        }

        chain.doFilter(request, response);
    }
}
