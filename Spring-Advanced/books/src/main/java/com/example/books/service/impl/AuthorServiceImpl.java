package com.example.books.service.impl;

import com.example.books.model.entity.AuthorEntity;
import com.example.books.repository.AuthorRepository;
import com.example.books.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Optional<AuthorEntity> findAuthorByName(String name) {
        return this.authorRepository.findByName(name);
    }

    @Override
    public AuthorEntity randomAuthor() {
        long id = ThreadLocalRandom.current().nextLong(0, authorRepository.count());
        return this.authorRepository.findById(id).get();
    }

    @Override
    public void createAuthor(String name) {
        // TODO: 12.03.22
    }
}
