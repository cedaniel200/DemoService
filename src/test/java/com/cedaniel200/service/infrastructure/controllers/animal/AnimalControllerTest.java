package com.cedaniel200.service.infrastructure.controllers.animal;

import com.cedaniel200.service.application.animal.AnimalResult;
import com.cedaniel200.service.application.animal.CreateAnimalUseCase;
import com.cedaniel200.service.application.animal.GetAnimalsUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AnimalController.class)
class AnimalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CreateAnimalUseCase createAnimalUseCase;

    @MockitoBean
    private GetAnimalsUseCase getAnimalsUseCase;

    @Test
    void createsAnimal() throws Exception {
        given(createAnimalUseCase.createAnimal()).willReturn(new AnimalResult("Perro", "Luis"));

        mockMvc.perform(post("/animal"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.type").value("Perro"))
                .andExpect(jsonPath("$.name").value("Luis"));
    }

    @Test
    void listsAnimals() throws Exception {
        given(getAnimalsUseCase.getAnimals()).willReturn(List.of(new AnimalResult("Perro", "Luis")));

        mockMvc.perform(get("/animals"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].type").value("Perro"))
                .andExpect(jsonPath("$[0].name").value("Luis"));
    }

    @Test
    void returnsServiceUnavailableWhenDatabaseFails() throws Exception {
        given(createAnimalUseCase.createAnimal())
                .willThrow(new DataAccessResourceFailureException("mongo no disponible"));

        mockMvc.perform(post("/animal"))
                .andExpect(status().isServiceUnavailable())
                .andExpect(jsonPath("$.status").value(503))
                .andExpect(jsonPath("$.detail").value("No se pudo acceder a la base de datos"));
    }
}
