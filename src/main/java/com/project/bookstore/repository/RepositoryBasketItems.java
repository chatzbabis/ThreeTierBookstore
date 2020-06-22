package com.project.bookstore.repository;

import com.project.bookstore.models.BasketItem;
import com.project.bookstore.models.Book;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RepositoryBasketItems extends CrudRepository<BasketItem, Integer> {

    @Query(value = "SELECT * from basket_items WHERE basket_id=?1", nativeQuery = true)
    List<BasketItem> findBasketItemsWithBasketId(int basketId);

    @Query(value = "SELECT * from basket_items WHERE book_id=?1", nativeQuery = true)
    Book findBasketItemWithBookId(int id);

    @Query(value = "SELECT * from basket_items WHERE basket_items.id=?1", nativeQuery = true)
    BasketItem findBasketItemById(int basketItemId);

    @Query(value = "SELECT basket_items.* from basket_items "
            + "JOIN baskets ON baskets.id = basket_items.basket_id "
            + "WHERE user_id = ?1", nativeQuery = true)
    List<BasketItem> findBasketItemsByUserId(int userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE basket_items "
            + "SET quantity =?2 "
            + "WHERE id=?1", nativeQuery = true)
    void updateQuantityBasketItemWithId(int currentBasketItemId, int quantity);

    @Query(value = "SELECT sum(books.price*basket_items.quantity)/100 FROM basket_items,books "
            + "WHERE books.id=basket_items.book_id "
            + "GROUP BY basket_id "
            + "HAVING basket_id = ?1", nativeQuery = true)
    double computeTotaPrice(int basketId);
}
