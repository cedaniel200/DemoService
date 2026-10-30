package com.cedaniel200.service.infrastructure.random;

import com.cedaniel200.service.domain.exception.InvalidArgumentException;
import com.cedaniel200.service.domain.vo.ValueRandom;
import com.cedaniel200.service.domain.random.RandomNumberGenerator;

import java.util.concurrent.ThreadLocalRandom;

public class RandomNumberGeneratorDefault implements RandomNumberGenerator {

    @Override
    public ValueRandom getNumberRandom(int bound) {
        if (bound < 0) {
            throw new InvalidArgumentException("bound debe ser mayor o igual que 0");
        }
        return new ValueRandom(ThreadLocalRandom.current().nextInt(bound + 1));
    }
}
