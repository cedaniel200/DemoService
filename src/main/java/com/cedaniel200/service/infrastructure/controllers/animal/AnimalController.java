package com.cedaniel200.service.infrastructure.controllers.animal;

import com.cedaniel200.service.application.animal.CreateAnimalUseCase;
import com.cedaniel200.service.application.animal.GetAnimalsUseCase;
import com.cedaniel200.service.infrastructure.controllers.dto.AnimalResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnimalController {

    private final CreateAnimalUseCase createAnimalUseCase;
    private final GetAnimalsUseCase getAnimalsUseCase;

    public AnimalController(CreateAnimalUseCase createAnimalUseCase, GetAnimalsUseCase getAnimalsUseCase) {
        this.createAnimalUseCase = createAnimalUseCase;
        this.getAnimalsUseCase = getAnimalsUseCase;
    }

    @PostMapping("/animal")
    @ResponseStatus(HttpStatus.CREATED)
    public AnimalResponse createAnimal() {
        return AnimalResponse.from(createAnimalUseCase.createAnimal());
    }

    @GetMapping("/animals")
    public List<AnimalResponse> getAnimals() {
        return getAnimalsUseCase.getAnimals().stream()
                .map(AnimalResponse::from)
                .toList();
    }
}
