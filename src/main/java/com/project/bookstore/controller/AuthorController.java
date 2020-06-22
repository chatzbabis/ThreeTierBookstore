package com.project.bookstore.controller;

import com.project.bookstore.models.Author;

import com.project.bookstore.service.AuthorServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tier3")
public class AuthorController {

    @Autowired
    AuthorServiceImpl authorService;

    /**
     * GET /authors : Get all authors
     *
     * @return the ResponseEntity with status 200 (OK) and the list of authors
     * in the body, or with status 204 (NO CONTENT) if there are no authors in
     * database.
     */
    @GetMapping("/authors")
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = authorService.findAllAuthors();
        if (authors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    /**
     * GET /quote : Get a random author
     *
     * @return the ResponseEntity with status 200 (OK) and a random author in
     * the body, or with status 204 (NO CONTENT) if the random author is empty.
     */
    @GetMapping("/quote")
    public ResponseEntity<Author> getRandomAuthor() {
        Author author = authorService.getRandomAuthor();
        if (author.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    /**
     * POST /authors : Create a new book language.
     *
     * @param author the genre to create
     * @return the ResponseEntity with status 201 (Created), or with status 409
     * (Conflict) if the author already exists.
     */
    @PostMapping(value = "/authors", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createAuthor(@RequestBody Author author) {

        if (authorService.isAuthorExist(author)) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        authorService.saveAuthor(author);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
