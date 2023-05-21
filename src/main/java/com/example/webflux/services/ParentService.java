package com.example.webflux.services;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public abstract class ParentService<MODEL, REPOSITORY extends R2dbcRepository<MODEL, Integer>> {
    protected final REPOSITORY repository;

    public ParentService(REPOSITORY repository) {
        this.repository = repository;
    }

    public Mono<MODEL> save(MODEL model) {
        return repository.save(model);
    }

    public Mono<MODEL> getOne(Integer id) {
       return repository.findById(id);
    }

    public Flux<MODEL> getAll() {
        return repository.findAll();
    }

    public abstract Mono<MODEL> update(int id, MODEL model);

    public Mono<Void> delete(Integer id) {
        return repository.deleteById(id);
    }

}
