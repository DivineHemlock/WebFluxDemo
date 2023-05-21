package com.example.webflux.controllers;

import com.example.webflux.models.Student;
import com.example.webflux.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Student> makeStudent(@RequestBody Student student) {
        return service.save(student);
    }
}
