package com.project.bookstore.repository;

import com.project.bookstore.models.Order;
import org.springframework.data.repository.CrudRepository;

public interface RepositoryOrders extends CrudRepository<Order, Integer> {

}
