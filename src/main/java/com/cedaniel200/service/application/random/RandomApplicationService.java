package com.cedaniel200.service.application.random;

import com.cedaniel200.service.domain.random.RandomNumberGenerator;

public class RandomApplicationService implements GetRandomNumberUseCase {

    private final RandomNumberGenerator randomNumberGenerator;

    public RandomApplicationService(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    @Override
    public RandomNumberResult getRandomNumber(int bound) {
        if (bound < 0) {
            throw new InvalidBoundException("bound debe ser mayor o igual que 0");
        }
        return RandomNumberResult.from(randomNumberGenerator.getNumberRandom(bound));
    }
}
