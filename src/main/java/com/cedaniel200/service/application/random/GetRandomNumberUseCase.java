package com.cedaniel200.service.application.random;

import com.cedaniel200.service.application.dto.RandomNumberResult;

public interface GetRandomNumberUseCase {
    RandomNumberResult getRandomNumber(int bound);
}
