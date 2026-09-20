package com.example.Backend_hotels;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthIntegrationTest {

    private static final String LOGIN_OK = "{\"username\":\"admin-teste\",\"password\":\"senha-de-teste-123\"}";
    private static final String LOGIN_ERRADO = "{\"username\":\"admin-teste\",\"password\":\"errada\"}";

    @Autowired
    private MockMvc mvc;

    private MockHttpSession login() throws Exception {
        return (MockHttpSession) mvc.perform(post("/auth/login")
                        .header("X-Requested-With", "XMLHttpRequest")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(LOGIN_OK))
                .andExpect(status().isOk())
                .andReturn().getRequest().getSession(false);
    }

    @Test
    void loginComSenhaErradaRetorna401() throws Exception {
        mvc.perform(post("/auth/login")
                        .header("X-Requested-With", "XMLHttpRequest")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(LOGIN_ERRADO))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void leituraDeHoteisEPublica() throws Exception {
        mvc.perform(get("/api/v1/hotels")).andExpect(status().isOk());
    }

    @Test
    void criarHotelSemLoginRetorna401() throws Exception {
        mvc.perform(post("/api/v1/hotels")
                        .header("X-Requested-With", "XMLHttpRequest")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void excluirHotelSemLoginRetorna401() throws Exception {
        mvc.perform(delete("/api/v1/hotels/1")
                        .header("X-Requested-With", "XMLHttpRequest"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void requisicaoQueAlteraDadosSemCabecalhoRetorna403() throws Exception {
        mvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(LOGIN_OK))
                .andExpect(status().isForbidden());
    }

    @Test
    void meSemLoginRetorna401() throws Exception {
        mvc.perform(get("/auth/me")).andExpect(status().isUnauthorized());
    }

    @Test
    void loginPermiteAcessoAoMeEAsRotasProtegidas() throws Exception {
        MockHttpSession session = login();

        mvc.perform(get("/auth/me").session(session))
                .andExpect(status().isOk());

        // Autenticado: a requisição passa da segurança (corpo vazio é rejeitado pela validação, não por 401/403)
        int codigo = mvc.perform(delete("/api/v1/hotels/999999")
                        .header("X-Requested-With", "XMLHttpRequest")
                        .session(session))
                .andReturn().getResponse().getStatus();
        org.junit.jupiter.api.Assertions.assertNotEquals(401, codigo);
        org.junit.jupiter.api.Assertions.assertNotEquals(403, codigo);
    }

    @Test
    void logoutEncerraASessao() throws Exception {
        MockHttpSession session = login();

        mvc.perform(post("/auth/logout")
                        .header("X-Requested-With", "XMLHttpRequest")
                        .session(session))
                .andExpect(status().isOk());

        mvc.perform(get("/auth/me").session(session))
                .andExpect(status().isUnauthorized());
    }
}
