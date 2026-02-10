package com.mednex.hms.patient;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
@CrossOrigin
public class PatientController {

    private final PatientRepository repo;

    public PatientController(PatientRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public Patient create(@RequestBody Patient p) {
        return repo.save(p);
    }

    @GetMapping
    public List<Patient> getAll() {
        return repo.findAll();
    }
}
