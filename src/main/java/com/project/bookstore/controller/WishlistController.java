package com.project.bookstore.controller;

import com.project.bookstore.models.Basket;
import com.project.bookstore.models.Wishlist;
import com.project.bookstore.models.WishlistItem;
import com.project.bookstore.service.WishlistServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tier3")
public class WishlistController {

    @Autowired
    WishlistServiceImpl wishlistService;

    /**
     * GET /wishlistItems:userId : Get wishlistItems of user.
     *
     * @param userId the id of the user whose items to retrieve.
     * @return the ResponseEntity with status 200 (OK) and the wishlistItems
     * items of the user in the body, or with status 204 (NO CONTENT) if there
     * are no wishlistItems items for this user in the database.
     */
    @GetMapping("/wishlistItems/{userId}")
    public ResponseEntity<List<WishlistItem>> getAllUserwishlistItems(@PathVariable("userId") int userId) {
        List<WishlistItem> wishlistItems = wishlistService.findWishlistItemsByUserId(userId);
        if (wishlistItems.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(wishlistItems, HttpStatus.OK);
    }

    /**
     * POST /wishlistItems : Create a wishlist item for a wishlist.
     *
     * @param wishlistItem the wishlistItem to create
     * @return the ResponseEntity with status 201 (Created), or with status 409
     * (Conflict) if the basketItem already exists in the basket.
     */
    @PostMapping(value = "/wishlistItems", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> addWishlistItem(@RequestBody WishlistItem wishlistItem) {

        if (wishlistService.isWishlistItemExist(wishlistItem)) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        wishlistService.saveWishlistItem(wishlistItem);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * DELETE /wishlistItems/:wishlistItemId : Delete wishlist item with id.
     *
     * @param wishlistItemId the id of the wishlist item to delete
     * @return the ResponseEntity with status 200 (OK) if wishlist item is
     * deleted or status 404 (not found) if wishlist item does not exist.
     */
    @DeleteMapping("/wishlistItems/{wishlistItemId}")
    public ResponseEntity<String> deleteWishlistItem(@PathVariable("wishlistItemId") int wishlistItemId) {

        WishlistItem wishlistItem = wishlistService.findWishlistItemById(wishlistItemId);
        if (wishlistItem == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        wishlistService.deleteWishlistItemById(wishlistItemId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * GET /wishlist:userId : Get wishlist id of user.
     *
     * @param userId the id of the user whose basket id to retrieve.
     * @return the ResponseEntity with status 200 (OK) and the wishlist of the
     * user in the body, or with status 204 (NO CONTENT) if there is no wishlist
     * for this user in the database.
     */
    @GetMapping("/wishlist/{userId}")
    public ResponseEntity<Wishlist> getUserWishlistId(@PathVariable("userId") int userId) {
        Wishlist wishlist = wishlistService.findWishlistWithUserId(userId);
        if (wishlist == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(wishlist, HttpStatus.OK);
    }
}
