package com.cedaniel200.service.application.random;

import com.cedaniel200.service.domain.random.RandomNumberGenerator;

public class RandomApplicationService {

    private final RandomNumberGenerator randomNumberGenerator;

    public RandomApplicationService(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public RandomNumberResponse getRandomNumber(int bound) {
        if (bound < 0) {
            throw new IllegalArgumentException("bound debe ser mayor o igual que 0");
        }
        return RandomNumberResponse.from(randomNumberGenerator.getNumberRandom(bound));
    }
}
