package com.project.bookstore.service;

import com.project.bookstore.models.Author;
import java.util.List;

public interface IAuthorInterface {

    Author getRandomAuthor();

    void saveAuthor(Author author);

    boolean isAuthorExist(Author author);

    List<Author> findAllAuthors();

}
