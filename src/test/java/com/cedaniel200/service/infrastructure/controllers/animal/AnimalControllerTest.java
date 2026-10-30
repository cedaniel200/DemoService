package com.cedaniel200.service.infrastructure.controllers.animal;

import com.cedaniel200.service.domain.animal.AnimalDomain;
import com.cedaniel200.service.domain.model.Animal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
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
    private AnimalDomain animalDomain;

    @Test
    void createsAnimal() throws Exception {
        given(animalDomain.createAnimal()).willReturn(new Animal("Perro", "Luis"));

        mockMvc.perform(post("/animal"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.type").value("Perro"))
                .andExpect(jsonPath("$.name").value("Luis"));
    }

    @Test
    void listsAnimals() throws Exception {
        given(animalDomain.getAnimals()).willReturn(List.of(new Animal("Perro", "Luis")));

        mockMvc.perform(get("/animals"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].type").value("Perro"))
                .andExpect(jsonPath("$[0].name").value("Luis"));
    }
}
