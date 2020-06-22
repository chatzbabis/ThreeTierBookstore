package com.project.bookstore.service;

import com.project.bookstore.models.DeliveryAddress;
import java.util.List;

public interface IDeliveryAddressService {

    List<DeliveryAddress> findDeliveryAddressesWithUserId(int userId);

    boolean isDeliveryAddressExist(DeliveryAddress deliveryAddress);

    void saveDeliveryAddress(DeliveryAddress deliveryAddress);

}
