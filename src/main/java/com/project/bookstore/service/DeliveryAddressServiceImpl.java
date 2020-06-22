package com.project.bookstore.service;

import com.project.bookstore.models.DeliveryAddress;
import com.project.bookstore.repository.RepositoryDeliveryAddress;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("deliveryAddressService")
public class DeliveryAddressServiceImpl implements IDeliveryAddressService {

    @Autowired
    private RepositoryDeliveryAddress repositoryDeliveryAddress;

    @Override
    public List<DeliveryAddress> findDeliveryAddressesWithUserId(int userId) {
        return repositoryDeliveryAddress.findDeliveryAddressesWithUserId(userId);
    }

    @Override
    public boolean isDeliveryAddressExist(DeliveryAddress deliveryAddress) {
        String recipientFirstName = deliveryAddress.getFirstName();
        String recipientLastName = deliveryAddress.getLastName();
        String streetName = deliveryAddress.getStreet();
        int streetNr = deliveryAddress.getStreetNumber();
        int postalCode = deliveryAddress.getPostalCode();
        String province = deliveryAddress.getProvince();
        if (repositoryDeliveryAddress.findDeliveryAddressesWithAllFields(recipientFirstName, recipientLastName, streetName, streetNr, postalCode, province) == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void saveDeliveryAddress(DeliveryAddress deliveryAddress) {
        repositoryDeliveryAddress.save(deliveryAddress);
    }

}
