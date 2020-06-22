package com.project.bookstore.repository;

import com.project.bookstore.models.OrderItem;
import org.springframework.data.repository.CrudRepository;

public interface RepositoryOrderItems extends CrudRepository<OrderItem, Integer> {

}
