package com.example.service.random;

import com.example.service.domain.RandomNumberGenerator;
import com.example.service.model.ValueRandom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RandomController.class)
class RandomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RandomNumberGenerator generator;

    @Test
    void returnsGeneratedValue() throws Exception {
        given(generator.getNumberRandom(10)).willReturn(new ValueRandom(7));

        mockMvc.perform(get("/random").param("bound", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value(7));
    }

    @Test
    void rejectsMissingBound() throws Exception {
        mockMvc.perform(get("/random"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void rejectsNegativeBound() throws Exception {
        mockMvc.perform(get("/random").param("bound", "-1"))
                .andExpect(status().isBadRequest());
    }
}
