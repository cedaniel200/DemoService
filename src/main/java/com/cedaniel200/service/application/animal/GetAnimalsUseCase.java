package com.cedaniel200.service.application.animal;

import com.cedaniel200.service.application.dto.AnimalResult;

import java.util.List;

public interface GetAnimalsUseCase {
    List<AnimalResult> getAnimals();
}
