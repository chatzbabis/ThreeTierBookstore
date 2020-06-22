package com.project.bookstore.service;

import com.project.bookstore.models.Basket;
import com.project.bookstore.models.BasketItem;
import java.util.List;

public interface IBasketService {

    List<BasketItem> findBasketItemsWithUserId(int userId);

    BasketItem findBasketItemById(int basketItemId);

    Basket findBasketWithUserId(int userId);

    void saveBasketItem(BasketItem basketItem);

    boolean isBasketItemExist(BasketItem basketItem);

    void deleteBasketItemById(int basketItemId);

    void updateQuantityBasketItemWithId(BasketItem currentBasketItem);
}
