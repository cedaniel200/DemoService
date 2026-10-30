package com.cedaniel200.service.application.random;

import com.cedaniel200.service.domain.exception.InvalidArgumentException;

public class InvalidBoundException extends InvalidArgumentException {

    public InvalidBoundException(String message) {
        super(message);
    }
}
