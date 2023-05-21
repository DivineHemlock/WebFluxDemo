package com.example.webflux.services;

import com.example.webflux.models.Student;
import com.example.webflux.repos.StudentRepo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class StudentService {
    private final StudentRepo repo;

    public StudentService(StudentRepo repo) {
        this.repo = repo;
    }

    public Mono<Student> save(Student student) {
        return repo.save(student);
    }
}
