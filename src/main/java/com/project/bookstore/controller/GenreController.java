package com.project.bookstore.controller;

import com.project.bookstore.models.Book;
import com.project.bookstore.models.Genre;
import com.project.bookstore.service.BookServiceImpl;
import com.project.bookstore.service.GenreServiceImpl;
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
public class GenreController {

    @Autowired
    BookServiceImpl bookService;

    @Autowired
    GenreServiceImpl genreService;

    /**
     * GET /genre : Get all book genre.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of genre in
     * the body, or with status 204 (NO CONTENT) if there are no genre in
     * database.
     */
    @GetMapping("/genre")
    public ResponseEntity<List<Genre>> getAllGenre() {
        List<Genre> genre = genreService.findAllGenre();
        if (genre.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(genre, HttpStatus.OK);
    }

    /**
     * GET /genre:genre : Get books by genre
     *
     * @param genre the genre of the books to retrieve
     * @return the ResponseEntity with status 200 (OK) and a list of books by
     * genre the body, or with status 204 (NO CONTENT) if there are no books of
     * this genre.
     */
    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<Book>> getBooksByGenre(@PathVariable("genre") String genre) {
        List<Book> books = bookService.findByGenre(genre);
        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    /**
     * POST /genre : Create a new book genre.
     *
     * @param genre the genre to create
     * @return the ResponseEntity with status 201 (Created), or with status 409
     * (Conflict) if the genre already exists.
     */
    @PostMapping(value = "/genre", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createGenre(@RequestBody Genre genre) {

        if (genreService.isGenreExist(genre)) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        genreService.saveGenre(genre);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
