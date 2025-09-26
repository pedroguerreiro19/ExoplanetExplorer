package com.exoplanetexplorer.ExoplanetExplorer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ExoplanetExplorerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnJsonAndNotEmpty() throws Exception {
        mockMvc.perform(get("/planets/all"))
                .andExpect(status().isOk()) 
                .andExpect(content().contentType("application/json"));
    }
}