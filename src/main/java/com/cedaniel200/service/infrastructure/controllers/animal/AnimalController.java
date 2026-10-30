package com.cedaniel200.service.infrastructure.controllers.animal;

import com.cedaniel200.service.application.animal.AnimalApplicationService;
import com.cedaniel200.service.application.animal.AnimalResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnimalController {

    private final AnimalApplicationService animalApplicationService;

    public AnimalController(AnimalApplicationService animalApplicationService) {
        this.animalApplicationService = animalApplicationService;
    }

    @PostMapping("/animal")
    @ResponseStatus(HttpStatus.CREATED)
    public AnimalResponse createAnimal() {
        return animalApplicationService.createAnimal();
    }

    @GetMapping("/animals")
    public List<AnimalResponse> getAnimals() {
        return animalApplicationService.getAnimals();
    }
}
