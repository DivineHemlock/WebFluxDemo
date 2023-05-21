package com.example.webflux.repos;

import com.example.webflux.models.StudentCourse;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCourseRepo extends R2dbcRepository<StudentCourse, Integer> {
}
