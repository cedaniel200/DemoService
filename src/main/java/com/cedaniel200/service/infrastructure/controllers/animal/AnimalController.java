package com.cedaniel200.service.infrastructure.controllers.animal;

import com.cedaniel200.service.domain.animal.AnimalDomain;
import com.cedaniel200.service.domain.model.Animal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnimalController {

    private final AnimalDomain animalDomain;

    public AnimalController(AnimalDomain animalDomain) {
        this.animalDomain = animalDomain;
    }

    @PostMapping("/animal")
    @ResponseBody
    public Animal createAnimal() {
        return animalDomain.createAnimal();
    }

    @GetMapping("/animals")
    @ResponseBody
    public List<Animal> getAnimals() {
        return animalDomain.getAnimals();
    }
}
