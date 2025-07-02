package com.example.animal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animals")
public class AnimalController {

    private final AnimalRepository repo;

    public AnimalController(AnimalRepository repo) {
        this.repo = repo;
    }

    /**
     * Create a new Animal.
     * POST /animals
     * Body JSON: { "name": "Animal", "description": "Animal Description" }
     */
    @PostMapping
    public ResponseEntity<Animal> create(@RequestBody Animal animal) {
        Animal saved = repo.save(animal);
        return ResponseEntity.ok(saved);
    }

    /**
     * Search animals by name keyword (case-insensitive).
     * GET /animals/search?kw=lion
     */
    @GetMapping("/search")
    public List<Animal> search(@RequestParam("kw") String keyword) {
        return repo.findByNameContaining(keyword);
    }
}
