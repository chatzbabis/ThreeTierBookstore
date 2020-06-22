package com.project.bookstore.repository;

import com.project.bookstore.models.Basket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RepositoryBaskets extends CrudRepository<Basket, Integer> {

    @Query(value = "SELECT * from baskets WHERE user_id=?1", nativeQuery = true)
    Basket findBasketWithUserId(int userId);

}
