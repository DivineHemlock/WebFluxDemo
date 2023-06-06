package com.example.webflux.controllers;

import com.example.webflux.models.StudentCourse;
import com.example.webflux.services.CourseService;
import com.example.webflux.services.ReserveService;
import com.example.webflux.services.StudentService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reserve")
public class ReserveController {

    private final StudentService studentService;
    private final CourseService courseService;
    private final ReserveService reserveService;

    public ReserveController(StudentService studentService, CourseService courseService, ReserveService reserveService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.reserveService = reserveService;
    }

    @PostMapping("/{studentId}/{courseId}")
    public Mono<StudentCourse> reserve(@PathVariable("studentId") int studentId, @PathVariable("courseId") int courseId) throws Exception {
        return reserveService.reserve(studentId, courseId);
    }
}
