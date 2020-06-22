package com.project.bookstore.controller;

import com.project.bookstore.models.Basket;
import com.project.bookstore.models.BasketItem;
import com.project.bookstore.service.BasketServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tier3")
public class BasketController {

    @Autowired
    BasketServiceImpl basketService;

    /**
     * GET /basketItems:userId : Get basketItems of userId.
     *
     * @param userId the id of the basket whose items to retrieve.
     * @return the ResponseEntity with status 200 (OK) and the basket items of
     * the user in the body, or with status 204 (NO CONTENT) if there are no
     * basket items for this user in the database.
     */
    @GetMapping("/basketItems/{userId}")
    public ResponseEntity<List<BasketItem>> getAllBasketItemsOfUser(@PathVariable("userId") int userId) {
        List<BasketItem> basketItems = basketService.findBasketItemsWithUserId(userId);
        if (basketItems.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(basketItems, HttpStatus.OK);
    }

    /**
     * GET /basket:userId : Get basket id of user.
     *
     * @param userId the id of the user whose basket id to retrieve.
     * @return the ResponseEntity with status 200 (OK) and the basket of the
     * user in the body, or with status 204 (NO CONTENT) if there is no basket
     * for this user in the database.
     */
    @GetMapping("/basket/{userId}")
    public ResponseEntity<Basket> getUserBasketId(@PathVariable("userId") int userId) {
        Basket basket = basketService.findBasketWithUserId(userId);
        if (basket == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(basket, HttpStatus.OK);
    }

    /**
     * POST /basketItems : Create a basket item for a basket.
     *
     * @param basketItem the basketItem to create
     * @return the ResponseEntity with status 201 (Created), or with status 409
     * (Conflict) if the basketItem already exists in the basket.
     */
    @PostMapping(value = "/basketItems", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> addBasketItem(@RequestBody BasketItem basketItem) {

        if (basketService.isBasketItemExist(basketItem)) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        basketService.saveBasketItem(basketItem);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * DELETE /basketItems/:basketItemId : Delete basket item with id.
     *
     * @param basketItemId the id of the basket item to delete
     * @return the ResponseEntity with status 200 (OK) if basket item is deleted
     * or status 404 (not found) if basket item does not exist.
     */
    @DeleteMapping("/basketItems/{basketItemId}")
    public ResponseEntity<String> deleteBasketItem(@PathVariable("basketItemId") int basketItemId) {

        BasketItem basketItem = basketService.findBasketItemById(basketItemId);
        if (basketItem == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        basketService.deleteBasketItemById(basketItemId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * PUT /basketItems: Update the quantity of an existing basket item.
     *
     * @param basketItem the basket item to update
     * @return the ResponseEntity with state 200 (sK) if update is done, or
     * status 404 (Not found) if the basket item does not exist.
     */
    @PutMapping("/basketItems")
    public ResponseEntity<String> updateQuantityOfBasketItem(@RequestBody BasketItem basketItem) {

        BasketItem currentBasketItem = basketService.findBasketItemById(basketItem.getId());

        if (currentBasketItem == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        currentBasketItem.setQuantity(basketItem.getQuantity());

        basketService.updateQuantityBasketItemWithId(currentBasketItem);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
