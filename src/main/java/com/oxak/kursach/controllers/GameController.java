package com.oxak.kursach.controllers;

import com.oxak.kursach.models.Game;
import com.oxak.kursach.repo.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @GetMapping("/getAll")
    public Iterable<Game> getAll() {
        return gameRepository.findAll();
    }

    @GetMapping("/getById")
    public Optional<Game> getById(@RequestParam Long id) {
        return gameRepository.findById(id);
    }

    @GetMapping("/filter")
    public List<Game> filter(@RequestParam String title) {
        return gameRepository.findByTitleContainingIgnoreCase(title);
    }
}
