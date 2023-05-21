package com.example.webflux.services;

import com.example.webflux.models.Student;
import com.example.webflux.repos.StudentRepo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class StudentService extends ParentService<Student, StudentRepo>{

    public StudentService(StudentRepo repository) {
        super(repository);
    }

    @Override
    public Mono<Student> update(int id, Student student) {
        return repository.findById(id).map(Optional::of).defaultIfEmpty(Optional.empty())
                .flatMap(optionalStudent -> {
                    if (optionalStudent.isPresent()) {
                        student.setId(id);
                        return repository.save(student);
                    }

                    return Mono.empty();
                });
    }
}
