package com.oxak.kursach.controllers;

import com.oxak.kursach.models.Platform;
import com.oxak.kursach.repo.PlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/platforms")
public class PlatformController {

    @Autowired
    private PlatformRepository platformRepository;

    @GetMapping("/getAll")
    public Iterable<Platform> getAll(Model model) {
        Iterable<Platform> platforms = platformRepository.findAll();
        return platforms;
//        model.addAttribute("platforms", platforms);
//        return "SukaSuka";
    }

    @GetMapping("/getById")
    public Optional<Platform> getById(@RequestParam Long id) {
        return platformRepository.findById(id);
    }

    // TODO разобраться а шо по скобочкам в урле
    @GetMapping("/filter")
    public List<Platform> filter(@RequestParam String title) {
        System.out.println("search = " + title);
        List<Platform> result = platformRepository.findByTitleContainingIgnoreCase(title);
        System.out.println("result = " + result);
        return result;
    }
}
