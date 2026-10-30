package com.cedaniel200.service.infrastructure.controllers.random;

import com.cedaniel200.service.application.random.GetRandomNumberUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RandomController {

    private final GetRandomNumberUseCase getRandomNumberUseCase;

    public RandomController(GetRandomNumberUseCase getRandomNumberUseCase) {
        this.getRandomNumberUseCase = getRandomNumberUseCase;
    }

    @GetMapping("/random")
    public RandomNumberResponse getNumberRandom(@RequestParam int bound) {
        return RandomNumberResponse.from(getRandomNumberUseCase.getRandomNumber(bound));
    }
}
