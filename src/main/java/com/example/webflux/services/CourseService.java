package com.example.webflux.services;

import com.example.webflux.models.Course;
import com.example.webflux.repos.CourseRepo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class CourseService extends ParentService<Course, CourseRepo> {
    public CourseService(CourseRepo repository) {
        super(repository);
    }

    @Override
    public Mono<Course> update(int id, Course course) {
        return repository.findById(id).map(Optional::of).defaultIfEmpty(Optional.empty())
                .flatMap(optionalCourse -> {
                    if (optionalCourse.isPresent()) {
                        course.setId(id);
                        return repository.save(course);
                    }

                    return Mono.empty();
                });
    }
}
