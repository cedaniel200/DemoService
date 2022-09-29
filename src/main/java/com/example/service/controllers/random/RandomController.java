package com.example.service.controllers.random;

import com.example.service.domain.random.RandomNumberGenerator;
import com.example.service.model.ValueRandom;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RandomController {

    private final RandomNumberGenerator randomNumberGenerator;

    public RandomController(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    @GetMapping("/random")
    @ResponseBody
    public ValueRandom getNumberRandom(@RequestParam int bound) {
        return randomNumberGenerator.getNumberRandom(bound);
    }

}
