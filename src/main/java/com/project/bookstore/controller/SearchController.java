package com.project.bookstore.controller;

import com.project.bookstore.models.Book;
import com.project.bookstore.service.SearchServiceImpl;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tier3")
public class SearchController {

    @Autowired
    SearchServiceImpl searchService;

    /**
     * GET /search/:keyphrase : Search books with keyphrase
     *
     * @param keyphrase the keyphrase of the search
     * @return the ResponseEntity with status 200 (OK) and with body the books
     * returned by the search, or with status 404 (NOT FOUND) if the search
     * returns no books.
     */
    @GetMapping("/search/{keyphrase}")
    public ResponseEntity<Set<Book>> getBook(@PathVariable("keyphrase") String keyphrase) {
        Set<Book> books = searchService.searchBooks(keyphrase);
        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}
