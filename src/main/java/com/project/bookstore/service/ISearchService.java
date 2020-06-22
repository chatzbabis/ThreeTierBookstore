package com.project.bookstore.service;

import com.project.bookstore.models.Book;
import java.util.Set;

public interface ISearchService {

    Set<Book> searchBooks(String keyphrase);

}
