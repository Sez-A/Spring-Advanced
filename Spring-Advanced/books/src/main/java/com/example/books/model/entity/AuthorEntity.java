package com.example.books.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "authors")
public class AuthorEntity extends BaseEntity {
   private String name;
   private List<BookEntity> books;

   @Column
    public String getName() {
        return name;
    }

    public AuthorEntity setName(String name) {
        this.name = name;
        return this;
    }

    @OneToMany(mappedBy = "author")
    public List<BookEntity> getBooks() {
        return books;
    }

    public AuthorEntity setBooks(List<BookEntity> books) {
        this.books = books;
        return this;
    }

    @Override
    public String toString() {
        return "AuthorEntity{" +
                "name='" + name + '\'' +
                '}';
    }
}
