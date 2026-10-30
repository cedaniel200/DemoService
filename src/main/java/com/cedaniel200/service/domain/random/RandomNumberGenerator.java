package com.cedaniel200.service.domain.random;

import com.cedaniel200.service.domain.model.ValueRandom;

public interface RandomNumberGenerator {

    /**
     * Devuelve un número aleatorio entre 0 y {@code bound}, ambos inclusive.
     *
     * @param bound límite superior inclusivo; debe ser mayor o igual que 0
     * @return el número generado
     * @throws com.cedaniel200.service.domain.exception.InvalidArgumentException
     *         si {@code bound} es negativo
     */
    ValueRandom getNumberRandom(int bound);
}
