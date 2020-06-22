package com.project.bookstore.service;

import com.project.bookstore.models.Basket;
import com.project.bookstore.models.Wishlist;
import com.project.bookstore.models.WishlistItem;
import com.project.bookstore.repository.RepositoryWishlistItems;
import com.project.bookstore.repository.RepositoryWishlists;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("wishlistService")
public class WishlistServiceImpl implements IWishlistService {

    @Autowired
    private RepositoryWishlistItems repositoryWishlistItems;

    @Autowired
    private RepositoryWishlists repositoryWishlists;

    @Override
    public void saveWishlistItem(WishlistItem wishlistItem) {
        repositoryWishlistItems.save(wishlistItem);
    }

    @Override
    public boolean isWishlistItemExist(WishlistItem wishlistItem) {
        int wishlistItemId = wishlistItem.getBook().getId();
        if (repositoryWishlistItems.findWishlistItemWithBookId(wishlistItemId) == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public List<WishlistItem> findWishlistItemsByUserId(int userId) {
        return repositoryWishlistItems.findWishlistItemsByUserId(userId);
    }

    @Override
    public void deleteWishlistItemById(int wishlistItemId) {
        repositoryWishlistItems.deleteById(wishlistItemId);
    }

    @Override
    public WishlistItem findWishlistItemById(int wishlistItemId) {
        return repositoryWishlistItems.findWishlistItemById(wishlistItemId);
    }

    @Override
    public Wishlist findWishlistWithUserId(int userId) {
        return repositoryWishlists.findWishlistWithUserId(userId);
    }

}
