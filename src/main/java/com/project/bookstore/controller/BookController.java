package com.project.bookstore.controller;

import com.project.bookstore.models.Book;
import com.project.bookstore.service.BookServiceImpl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tier3")
public class BookController {

    @Autowired
    BookServiceImpl bookService;

    /**
     * GET /books : Get all books.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of books in
     * the body, or with status 204 (NO CONTENT) if there are no books in
     * database.
     */
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.findAllBooks();
        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    /**
     * GET /books/:id : Get book with id.
     *
     * @param id the id of the book to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the book,
     * or with status 404 (NOT FOUND) if the book does not exist.
     */
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") int id) {
        Book book = bookService.findById(id);
        if (book == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    /**
     * GET /books/recent:n : Get n most recent books added to database.
     *
     * @param n the number of books to retrieve.
     * @return the ResponseEntity with status 200 (OK) and the list of n recent
     * books in the body, or with status 204 (NO CONTENT) if there are no recent
     * books in database. If less than n books exist in the database, all books
     * will be retrieved.
     */
    @GetMapping("/books/recent/{n}")
    public ResponseEntity<List<Book>> getNRecentBooks(@PathVariable("n") int n) {
        List<Book> books = bookService.findNRecentBooks(n);
        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    /**
     * GET /books/bestseller:n : Get n most sold books in bookstore.
     *
     * @param n the number of books to retrieve.
     * @return the ResponseEntity with status 200 (OK) and the list of n
     * bestseller books in the body, or with status 204 (NO CONTENT) if there
     * are no sold books in database. If less than n sold books exist in the
     * database, all sold books will be retrieved.
     */
    @GetMapping("/books/bestseller/{n}")
    public ResponseEntity<List<Book>> getNBestsellerBooks(@PathVariable("n") int n) {
        List<Book> books = bookService.findNBestsellerBooks(n);
        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    /**
     * POST /books : Create a new book.
     *
     * @param book the book to create
     * @return the ResponseEntity with status 201 (Created), or with status 409
     * (Conflict) if the book already exists.
     */
    @PostMapping(value = "/books", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> createBook(@RequestBody Book book) {

        if (bookService.isBookExist(book)) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        bookService.saveBook(book);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
