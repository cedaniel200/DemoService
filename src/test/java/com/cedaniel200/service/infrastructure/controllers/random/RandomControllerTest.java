package com.cedaniel200.service.infrastructure.controllers.random;

import com.cedaniel200.service.application.random.GetRandomNumberUseCase;
import com.cedaniel200.service.application.random.InvalidBoundException;
import com.cedaniel200.service.application.random.RandomNumberResult;
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
    private GetRandomNumberUseCase getRandomNumberUseCase;

    @Test
    void returnsGeneratedValue() throws Exception {
        given(getRandomNumberUseCase.getRandomNumber(10)).willReturn(new RandomNumberResult(7));

        mockMvc.perform(get("/random").param("bound", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value(7));
    }

    @Test
    void rejectsMissingBound() throws Exception {
        mockMvc.perform(get("/random"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400));
    }

    @Test
    void rejectsNegativeBound() throws Exception {
        given(getRandomNumberUseCase.getRandomNumber(-1))
                .willThrow(new InvalidBoundException("bound debe ser mayor o igual que 0"));

        mockMvc.perform(get("/random").param("bound", "-1"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.detail").value("bound debe ser mayor o igual que 0"));
    }
}
