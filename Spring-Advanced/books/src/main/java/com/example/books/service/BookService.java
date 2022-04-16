package com.example.books.service;

import com.example.books.model.dto.BookDTO;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<BookDTO> getAllBooks();

    Optional<BookDTO> findBookById(Long id);

    void deleteBookById(Long id);

    long createBook(BookDTO bookDTO);

    boolean updateBookById(Long id, BookDTO bookDTO);
}
