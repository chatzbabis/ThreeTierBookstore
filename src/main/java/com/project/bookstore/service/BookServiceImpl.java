package com.project.bookstore.service;

import com.project.bookstore.models.Book;
import com.project.bookstore.repository.RepositoryBooks;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("bookService")
public class BookServiceImpl implements IBookService {

    @Autowired
    private RepositoryBooks repositoryBooks;

    @Override
    public List<Book> findAllBooks() {
        List<Book> books = new ArrayList<>();
        repositoryBooks.findAll()
                .forEach(books::add);
        return books;
    }

    @Override
    public Book findById(int id) {
        return repositoryBooks.findBookById(id);
    }

    @Override
    public List<Book> findByGenre(String genre) {
        return repositoryBooks.findBookByGenre(genre);
    }

    @Override
    public List<Book> findByLanguage(String language) {
        return repositoryBooks.findBookByLanguage(language);
    }

    @Override
    public List<Book> findNRecentBooks(int n) {
        return repositoryBooks.findNRecentBooks(n);
    }

    @Override
    public List<Book> findNBestsellerBooks(int n) {
        return repositoryBooks.findNBestsellerBooks(n);
    }

    @Override
    public boolean isBookExist(Book book) {
        if (repositoryBooks.findByISBN(book.getIsbn()) == null
                && repositoryBooks.findByTitle(book.getTitle()) == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void saveBook(Book book) {
        repositoryBooks.save(book);
    }
//
//    @Override
//    public void updateBook(Book book) {
//        int index = books.indexOf(book);
//        books.set(index, book);
//    }
//
//    @Override
//    public void deleteBookById(long id) {
//        for (Iterator<Book> iterator = books.iterator(); iterator.hasNext();) {
//            Book user = iterator.next();
//            if (user.getId() == id) {
//                iterator.remove();
//            }
//        }
//    }
}
