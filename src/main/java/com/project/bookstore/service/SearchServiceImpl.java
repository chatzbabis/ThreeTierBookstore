package com.project.bookstore.service;

import com.project.bookstore.models.Author;
import com.project.bookstore.models.Book;
import com.project.bookstore.repository.RepositoryAuthors;
import com.project.bookstore.repository.RepositoryBooks;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("searchService")
public class SearchServiceImpl implements ISearchService {

    @Autowired
    private RepositoryBooks repositoryBooks;

    @Autowired
    private RepositoryAuthors repositoryAuthors;

    @Override
    public Set<Book> searchBooks(String keyphrase) {
        Set<Book> resultBooks = new HashSet();
        resultBooks.addAll(repositoryBooks.searchByTitle(keyphrase));
        resultBooks.addAll(searchBooksByAuthorName(keyphrase));
        return resultBooks;
    }

    private Set<Book> searchBooksByAuthorName(String keyphrase) {
        Set<String> keywords = splitKeyphrase(keyphrase);
        Set<Book> resultBooks = new HashSet();

        List<Author> resultAuthors = new ArrayList();
        for (String keyword : keywords) {
            resultAuthors.addAll(repositoryAuthors.searchByLastName(keyword));
        }

        for (Author author : resultAuthors) {
            resultBooks.addAll(repositoryBooks.searchByAuthor(author.getId()));
        }
        return resultBooks;
    }

    private Set<String> splitKeyphrase(String keyphrase) {
        String[] words = keyphrase.split(" ");
        Set<String> keywords = new HashSet<>(Arrays.asList(words));
        keywords.remove("and");
        keywords.remove("is");
        keywords.remove("or");
        return keywords;
    }

}
