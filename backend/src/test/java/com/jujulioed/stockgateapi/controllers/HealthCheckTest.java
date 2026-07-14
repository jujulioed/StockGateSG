package com.jujulioed.stockgateapi.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HealthCheck.class)
public class HealthCheckTest {

    // Permite simular uma requisição http sem iniciar o servidor
    // Autowired pede para o SPring colocar uma instancia do MockMvc nessa variável
    @Autowired
    private MockMvc mock;

    @Test
    void shouldReturnApiOnlineStatu() throws Exception {
        mock.perform(get("/api/status"))
                // "isOk()" verifica se a reposta foi ´200´
                .andExpect(status().isOk())
                .andExpect(
                        // Verifica se o conteúdo foi convertido para JSON
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                )
                .andExpect(
                        // Aqui o $ é a raíz do JSON que estamos analisando (ou seja, pegamos o "message" do json para analisar seu conteúdo)
                        jsonPath("$.message")
                                .value("Api Online e conectada")
                );
    }


}
