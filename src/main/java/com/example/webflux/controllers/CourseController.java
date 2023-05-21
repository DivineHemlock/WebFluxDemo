package com.example.webflux.controllers;

import com.example.webflux.models.Course;
import com.example.webflux.models.Student;
import com.example.webflux.models.StudentCourse;
import com.example.webflux.repos.StudentCourseRepo;
import com.example.webflux.services.CourseService;
import com.example.webflux.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    private final CourseService service;
    private final StudentService studentService;
    private final StudentCourseRepo studentCourseRepo;

    public CourseController(CourseService service, StudentService studentService, StudentCourseRepo studentCourseRepo) {
        this.service = service;
        this.studentService = studentService;
        this.studentCourseRepo = studentCourseRepo;
    }

    @GetMapping("/get_students/{courseId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Mono<Student>> getStudents(@PathVariable("courseId") Integer courseId) {
        Flux<StudentCourse> studentCourseFlux = studentCourseRepo.getStudentCoursesByCourseId(courseId);
        List<Mono<Student>> studentlist = new ArrayList<>();
        studentCourseFlux.subscribe(studentCourse -> studentlist.add(studentService.getOne(studentCourse.getCourseId())));
        return studentlist;
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
