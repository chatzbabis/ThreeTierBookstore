package com.project.bookstore.service;

import com.project.bookstore.models.Wishlist;
import com.project.bookstore.models.WishlistItem;
import java.util.List;

public interface IWishlistService {

    void saveWishlistItem(WishlistItem wishlistItem);

    boolean isWishlistItemExist(WishlistItem wishlistItem);

    List<WishlistItem> findWishlistItemsByUserId(int userId);

    Wishlist findWishlistWithUserId(int userId);

    void deleteWishlistItemById(int wishlistItemId);

    WishlistItem findWishlistItemById(int wishlistItemId);

}
