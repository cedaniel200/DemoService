package com.cedaniel200.service.infrastructure.controllers.random;

import com.cedaniel200.service.application.random.RandomApplicationService;
import com.cedaniel200.service.application.random.RandomNumberResponse;
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
    private RandomApplicationService randomApplicationService;

    @Test
    void returnsGeneratedValue() throws Exception {
        given(randomApplicationService.getRandomNumber(10)).willReturn(new RandomNumberResponse(7));

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
