package com.cedaniel200.service.infrastructure.controllers.random;

import com.cedaniel200.service.domain.model.ValueRandom;
import com.cedaniel200.service.domain.random.RandomNumberGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class RandomController {

    private final RandomNumberGenerator randomNumberGenerator;

    public RandomController(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    @GetMapping("/random")
    public ValueRandom getNumberRandom(@RequestParam int bound) {
        if (bound < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "bound debe ser mayor o igual que 0");
        }
        return randomNumberGenerator.getNumberRandom(bound);
    }
}
