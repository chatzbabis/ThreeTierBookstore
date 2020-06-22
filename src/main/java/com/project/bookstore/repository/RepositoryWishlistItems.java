package com.project.bookstore.repository;

import com.project.bookstore.models.Book;
import com.project.bookstore.models.WishlistItem;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RepositoryWishlistItems extends CrudRepository<WishlistItem, Integer> {

    @Query(value = "SELECT * from wishlist_items WHERE wishlist_id=?1", nativeQuery = true)
    List<WishlistItem> findWishlistItemsWithWishlistId(int wishlistId);

    @Query(value = "SELECT * from wishlist_items WHERE book_id=?1", nativeQuery = true)
    Book findWishlistItemWithBookId(int id);

    @Query(value = "SELECT wishlist_items.* from wishlist_items "
            + "JOIN wishlists ON wishlist_id =wishlist_items.wishlist_id "
            + "WHERE user_id = ?1", nativeQuery = true)
    List<WishlistItem> findWishlistItemsByUserId(int userId);

    @Query(value = "SELECT * from wishlist_items WHERE wishlist_items.id=?1", nativeQuery = true)
    WishlistItem findWishlistItemById(int wishlistItemId);
}
