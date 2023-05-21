package com.example.webflux.controllers;

import com.example.webflux.models.StudentCourse;
import com.example.webflux.services.StudentCourseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/student-course")
public class StudentCourseController {
    private final StudentCourseService service;

    public StudentCourseController(StudentCourseService service) {
        this.service = service;
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Flux<StudentCourse> getAllStudentCourses() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<StudentCourse> getStudentCourseById(@PathVariable("id") int id) {
        return service.getOne(id);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<StudentCourse> createStudentCourse(@RequestBody StudentCourse studentCourse) {
        return service.save(studentCourse);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<StudentCourse> updateStudentCourse(@PathVariable("id") int id, @RequestBody StudentCourse studentCourse) {
        return service.update(id, studentCourse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteStudentCourse(@PathVariable("id") int id) {
        return service.delete(id);
    }
}
