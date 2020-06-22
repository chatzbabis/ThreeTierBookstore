package com.project.bookstore.repository;

import com.project.bookstore.models.Wishlist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RepositoryWishlists extends CrudRepository<Wishlist, Integer> {

    @Query(value = "SELECT * from wishlists WHERE user_id=?1", nativeQuery = true)
    Wishlist findWishlistWithUserId(int id);
}
