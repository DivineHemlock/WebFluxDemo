package com.example.webflux.controllers;

import com.example.webflux.models.Course;
import com.example.webflux.services.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/course")
public class CourseController {
    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Course> getAllCourses() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Course> getCourseById(@PathVariable("id") int id) {
        return service.getOne(id);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Course> createCourse(@RequestBody Course course) {
        return service.save(course);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Course> updateCourse(@PathVariable("id") int id, @RequestBody Course course) {
        return service.update(id, course);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteStudent(@PathVariable("id") int id) {
        return service.delete(id);
    }
}
