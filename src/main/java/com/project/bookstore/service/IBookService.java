package com.project.bookstore.service;

import com.project.bookstore.models.Book;
import java.util.List;

public interface IBookService {

    List<Book> findAllBooks();

    List<Book> findByGenre(String genre);

    List<Book> findByLanguage(String language);

    Book findById(int id);

    List<Book> findNRecentBooks(int n);

    List<Book> findNBestsellerBooks(int n);

    boolean isBookExist(Book book);

    void saveBook(Book book);
//
//    void updateBook(Book book);
//
//    void deleteBookById(long id);
}
