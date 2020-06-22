package com.project.bookstore.controller;

import com.project.bookstore.models.DeliveryAddress;
import com.project.bookstore.service.DeliveryAddressServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/tier3")
public class DeliveryAddressController {

    @Autowired
    DeliveryAddressServiceImpl deliveryAddressService;

    /**
     * GET /deliveryAddresses/{userId} : Get all delivery addresses of user.
     *
     * @param userId the user whose delivery addresses to retrieve
     * @return the ResponseEntity with status 200 (OK) and the list of books in
     * the body, or with status 204 (NO CONTENT) if there are no books in
     * database.
     */
    @GetMapping("/deliveryAddresses/{userId}")
    public ResponseEntity<List<DeliveryAddress>> getAllBooks(@PathVariable("userId") int userId) {
        List<DeliveryAddress> deliveryAddress = deliveryAddressService.findDeliveryAddressesWithUserId(userId);
        if (deliveryAddress.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(deliveryAddress, HttpStatus.OK);
    }

    /**
     * POST /deliveryAddress : Create a new deliveryAddress for user.
     *
     * @param deliveryAddress the delivery address to create
     * @return the ResponseEntity with status 201 (Created) and with body the
     * newly created delivery address, or with status 409 (Conflict) if the
     * delivery address already exists.
     */
    @PostMapping(value = "/deliveryAddress", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DeliveryAddress> createDeliveryAddress(@RequestBody DeliveryAddress deliveryAddress) {

        if (deliveryAddressService.isDeliveryAddressExist(deliveryAddress)) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        deliveryAddressService.saveDeliveryAddress(deliveryAddress);

        return new ResponseEntity<>(deliveryAddress, HttpStatus.CREATED);
    }
}
