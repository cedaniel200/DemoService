package com.example.service.random;

import com.example.service.domain.RandomNumberGenerator;
import com.example.service.model.ValueRandom;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class RandomController {

    private final RandomNumberGenerator randomNumberGenerator;

    public RandomController(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    @GetMapping("/random")
    @ResponseBody
    public ValueRandom getNumberRandom(@RequestParam int bound) {
        if (bound < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "bound debe ser mayor o igual que 0");
        }
        return randomNumberGenerator.getNumberRandom(bound);
    }
}
