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

    @Test 
    void shouldReturnFilteredPlanets() throws Exception {
        mockMvc.perform(get("/planets/filter")
                .param("pl_bmasse_min", "1")
                .param("pl_bmasse_max", "10")
                .param("disc_year", "2015"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"));
    }

    @Test
    void shouldReturnPlanetByName() throws Exception {
        mockMvc.perform(get("/planets/search")
                .param("name", "Kepler-22 b"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"));
    }
}