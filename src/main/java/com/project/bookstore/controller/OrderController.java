package com.project.bookstore.controller;

import com.project.bookstore.models.Order;
import com.project.bookstore.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tier3")
public class OrderController {

    @Autowired
    OrderServiceImpl orderService;

    /**
     * POST /order : Create an order for a user.
     *
     * @param order the order to create
     * @return the ResponseEntity with status 201 (Created), or with status 409
     * (Conflict) if the order already exists in the basket.
     */
    @PostMapping(value = "/order", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> addOrder(@RequestBody Order order) {

        orderService.saveOrder(order);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
