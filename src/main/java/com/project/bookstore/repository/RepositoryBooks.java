package com.project.bookstore.repository;

import com.project.bookstore.models.Book;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RepositoryBooks extends CrudRepository<Book, Integer> {

    Book findBookById(Integer id);

    @Query(value = "SELECT * FROM books JOIN genre ON books.genre_id = genre.id\n"
            + "HAVING genre.name = ?1", nativeQuery = true)
    List<Book> findBookByGenre(String genre);

    @Query(value = "SELECT * FROM books JOIN languages ON books.language_id = languages.id\n"
            + "HAVING languages.name = ?1", nativeQuery = true)
    List<Book> findBookByLanguage(String language);

    @Query(value = "SELECT * from books ORDER BY id DESC LIMIT ?1", nativeQuery = true)
    List<Book> findNRecentBooks(int n);

    @Query(value = "SELECT books.* FROM books JOIN order_items on books.id = order_items.book_id "
            + "GROUP BY book_id ORDER BY sum(quantity) DESC LIMIT ?1", nativeQuery = true)
    List<Book> findNBestsellerBooks(int n);

    @Query(value = "SELECT * FROM books\n"
            + "WHERE title LIKE %?1%", nativeQuery = true)
    List<Book> searchByTitle(String title);

    @Query(value = "SELECT books.* from books \n"
            + "JOIN books_authors on books.id=books_authors.book_id\n"
            + "WHERE author_id=?1", nativeQuery = true)
    List<Book> searchByAuthor(int author_id);

   @Query(value = "SELECT * FROM books\n"
            + "WHERE isbn=?1",nativeQuery = true)
    Book findByISBN(String isbn);

    @Query(value = "SELECT * FROM books\n"
            + "WHERE title=?1",nativeQuery = true)
    Book findByTitle(String title);
}
