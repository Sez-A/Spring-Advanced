package com.example.books.service.impl;

import com.example.books.model.dto.BookDTO;
import com.example.books.model.entity.AuthorEntity;
import com.example.books.model.entity.BookEntity;
import com.example.books.repository.BookRepository;
import com.example.books.service.AuthorService;
import com.example.books.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    private final AuthorService authorService;

    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
        this.authorService = authorService;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return this.bookRepository.findAll()
                .stream()
                .map(this::asBook)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BookDTO> findBookById(Long id) {
        return this.bookRepository.findById(id)
                .map(this::asBook);
    }

    @Override
    public void deleteBookById(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public long createBook(BookDTO bookDTO) {
        BookEntity book = this.modelMapper.map(bookDTO, BookEntity.class);
        Optional<AuthorEntity> authorByName =
                this.authorService.findAuthorByName(bookDTO.getAuthor().getName());
        if (authorByName.isPresent()) {
            book.setAuthor(authorByName.get());
        } else {
            book.setAuthor(this.authorService.randomAuthor());
        }
        return this.bookRepository.save(book).getId();
    }

    @Override
    public boolean updateBookById(Long id, BookDTO bookDTO) {
        Optional<BookEntity> bookForUpdate = this.bookRepository.findById(id);
        if(bookForUpdate.isEmpty()) {
            return false;
        }

        BookEntity book = bookForUpdate.get();
        book.setAuthor(book.getAuthor())
                .setTitle(bookDTO.getTitle())
                .setIsbn(bookDTO.getIsbn());
        this.bookRepository.save(book);

        return true;
    }

    private BookDTO asBook(BookEntity book) {
        return this.modelMapper.map(book, BookDTO.class);
    }
}
