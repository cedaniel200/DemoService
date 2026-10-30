package com.cedaniel200.service.infrastructure.controllers.random;

import com.cedaniel200.service.application.random.RandomApplicationService;
import com.cedaniel200.service.application.random.RandomNumberResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class RandomController {

    private final RandomApplicationService randomApplicationService;

    public RandomController(RandomApplicationService randomApplicationService) {
        this.randomApplicationService = randomApplicationService;
    }

    @GetMapping("/random")
    public RandomNumberResponse getNumberRandom(@RequestParam int bound) {
        if (bound < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "bound debe ser mayor o igual que 0");
        }
        return randomApplicationService.getRandomNumber(bound);
    }
}
