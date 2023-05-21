package com.example.webflux.services;

import com.example.webflux.models.StudentCourse;
import com.example.webflux.repos.StudentCourseRepo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class StudentCourseService extends ParentService<StudentCourse, StudentCourseRepo> {
    public StudentCourseService(StudentCourseRepo repository) {
        super(repository);
    }

    @Override
    public Mono<StudentCourse> update(int id, StudentCourse studentCourse) {
        return repository.findById(id).map(Optional::of).defaultIfEmpty(Optional.empty())
                .flatMap(optionalStudentCourse -> {
                    if (optionalStudentCourse.isPresent()) {
                        studentCourse.setId(id);
                        return repository.save(studentCourse);
                    }

                    return Mono.empty();
                });
    }
}
