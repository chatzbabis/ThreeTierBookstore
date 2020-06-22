package com.project.bookstore.controller;

import com.project.bookstore.models.Book;
import com.project.bookstore.models.Language;
import com.project.bookstore.service.BookServiceImpl;
import com.project.bookstore.service.LanguageServiceImpl;
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
public class LanguageController {

    @Autowired
    BookServiceImpl bookService;

    @Autowired
    LanguageServiceImpl languageService;

    /**
     * GET /languages : Get all languages.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of languages
     * in the body, or with status 204 (NO CONTENT) if there are no languages in
     * database.
     */
    @GetMapping("/languages")
    public ResponseEntity<List<Language>> getAllLanguages() {
        List<Language> languages = languageService.findAllLanguages();
        if (languages.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(languages, HttpStatus.OK);
    }

    /**
     * POST /languages : Create a new book language.
     *
     * @param language the language to create
     * @return the ResponseEntity with status 201 (Created), or with status 409
     * (Conflict) if the language already exists.
     */
    @PostMapping(value = "/languages", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createLanguage(@RequestBody Language language) {

        if (languageService.isLanguageExist(language)) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        languageService.saveLanguage(language);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * GET /language:language : Get books by language
     *
     * @param language the language of the books to retrieve
     * @return the ResponseEntity with status 200 (OK) and a list of books by
     * language in the body, or with status 204 (NO CONTENT) if there are no
     * books of this language.
     */
    @GetMapping("/language/{language}")
    public ResponseEntity<List<Book>> getBooksByLanguage(@PathVariable("language") String language) {
        List<Book> books = bookService.findByLanguage(language);
        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

}
