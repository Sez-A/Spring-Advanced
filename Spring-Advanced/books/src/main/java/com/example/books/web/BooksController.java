package com.example.books.web;

import com.example.books.model.dto.BookDTO;
import com.example.books.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BooksController {
    private final BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> allBooks = this.bookService.getAllBooks();

        return ResponseEntity.ok(allBooks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        Optional<BookDTO> book = this.bookService.findBookById(id);

        if (book.isEmpty()) {
            return ResponseEntity
                    .notFound()
                    .build();
        } else {
            return ResponseEntity
                    .ok(book.get());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookDTO> deleteBook(@PathVariable("id") Long id) {
        this.bookService.deleteBookById(id);

        return ResponseEntity
                .noContent()
                .build();
    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO,
                                              UriComponentsBuilder builder) {
        long bookId = this.bookService.createBook(bookDTO);

        URI location = builder.path("/books/{id}")
                .buildAndExpand(bookId).toUri();

        return ResponseEntity.created(location)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id,
                                              @RequestBody BookDTO bookDTO,
                                              UriComponentsBuilder builder) {

        boolean successfullyUpdated = this.bookService.updateBookById(id, bookDTO);

        if (successfullyUpdated) {
           return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
