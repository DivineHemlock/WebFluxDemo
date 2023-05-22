package com.example.webflux.controllers;

import com.example.webflux.models.Student;
import com.example.webflux.models.StudentCourse;
import com.example.webflux.repos.StudentCourseRepo;
import com.example.webflux.services.CourseService;
import com.example.webflux.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService service;
    private final StudentCourseRepo studentCourseRepo;
    private final CourseService courseService;

    public StudentController(StudentService service, StudentCourseRepo studentCourseRepo, CourseService courseService) {
        this.service = service;
        this.studentCourseRepo = studentCourseRepo;
        this.courseService = courseService;
    }

    @GetMapping("/get_courses/{studentId}")
    @ResponseStatus(HttpStatus.OK)
    public Flux<StudentCourse> getCourses(@PathVariable("studentId") Integer studentId) {
        Flux<StudentCourse> studentCourseFlux = studentCourseRepo.getStudentCoursesByStudentId(studentId);
        return studentCourseFlux.doOnNext(studentCourse -> courseService.getOne(studentCourse.getCourseId()));
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Student> getAllStudents() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Student> getStudentById(@PathVariable("id") int id) {
        return service.getOne(id);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Student> createStudent(@RequestBody Student student) {
        return service.save(student);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Student> updateStudent(@PathVariable("id") int id, @RequestBody Student student) {
        return service.update(id, student);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteStudent(@PathVariable("id") int id) {
        return service.delete(id);
    }

}
