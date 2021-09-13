package com.oxak.kursach.controllers;

import com.oxak.kursach.models.Product;
import com.oxak.kursach.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/getAll")
    public Iterable<Product> getAll() {
        return productRepository.findAll();
    }

    @GetMapping("/getById")
    public Optional<Product> getById(@RequestParam Long id) {
        return productRepository.findById(id);
    }

    @GetMapping("/filter")
    public List<Product> filter(@RequestParam String title) {
        System.out.println("search = " + title);
        List<Product> result = productRepository.findByTitleContainingIgnoreCase(title);
        System.out.println("result = " + result);
        return result;
    }
}
