package com.example.webflux.repos;

import com.example.webflux.models.Course;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepo extends R2dbcRepository<Course, Integer> {
}
