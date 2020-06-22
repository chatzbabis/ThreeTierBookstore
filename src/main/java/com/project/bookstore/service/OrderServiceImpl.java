package com.project.bookstore.service;

import com.project.bookstore.models.BasketItem;
import com.project.bookstore.models.Order;
import com.project.bookstore.models.OrderItem;
import com.project.bookstore.repository.RepositoryBasketItems;
import com.project.bookstore.repository.RepositoryBaskets;
import com.project.bookstore.repository.RepositoryOrderItems;
import com.project.bookstore.repository.RepositoryOrders;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("orderService")
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private RepositoryOrders repositoryOrders;

    @Autowired
    private RepositoryOrderItems repositoryOrderItems;

    @Autowired
    private RepositoryBasketItems repositoryBasketItems;

    @Autowired
    private RepositoryBaskets repositoryBaskets;

    @Override
    public void saveOrder(Order order) {
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date currentDate = new Date();
        order.setOrderDate(currentDate);
        repositoryOrders.save(order);
        moveBasketItemsToOrderItems(order);

    }

    private void moveBasketItemsToOrderItems(Order order) {
        List<BasketItem> userBasketItems = repositoryBasketItems.findBasketItemsByUserId(order.getUserId().getId());

        userBasketItems.forEach((basketItem) -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setBookId(basketItem.getBook());
            orderItem.setQuantity(basketItem.getQuantity());
            orderItem.setOrderId(order);
            repositoryOrderItems.save(orderItem);
            repositoryBasketItems.delete(basketItem);
        });

    }

}
