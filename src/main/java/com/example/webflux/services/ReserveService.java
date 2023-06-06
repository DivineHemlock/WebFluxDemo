package com.example.webflux.services;

import com.example.webflux.models.StudentCourse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReserveService {

    private final StudentCourseService studentCourseService;
    private final CourseService courseService;

    public ReserveService(StudentCourseService studentCourseService, CourseService courseService) {
        this.studentCourseService = studentCourseService;
        this.courseService = courseService;
    }

    public Flux<StudentCourse> reserve(int studentId, int courseId) throws Exception {
        return courseService.getOne(courseId).flux().flatMap(course ->
        {
            StudentCourse studentCourse = studentCourse = new StudentCourse();;
            if (course.getCurrentCapacity() < course.getMaxCapacity()) {
                studentCourse.setStudentId(studentId);
                studentCourse.setCourseId(courseId);
                // increment current capacity
                course.setCurrentCapacity(course.getCurrentCapacity() + 1);
                courseService.save(course);
                return studentCourseService.save(studentCourse);
            }
            return Mono.error(new Exception("class is full"));
        }, 1);
    }
}
