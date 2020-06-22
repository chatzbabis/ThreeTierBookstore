package com.project.bookstore.repository;

import com.project.bookstore.models.DeliveryAddress;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RepositoryDeliveryAddress extends CrudRepository<DeliveryAddress, Integer> {

    @Query(value = "SELECT * FROM delivery_address WHERE user_id = ?1", nativeQuery = true)
    List<DeliveryAddress> findDeliveryAddressesWithUserId(int userId);

    @Query(value = "SELECT * FROM delivery_address "
            + "WHERE first_name = ?1 AND last_name = ?2 "
            + "AND street = ?3 AND street_number = ?4 "
            + "AND postal_code = ?5 AND province = ?6", nativeQuery = true)
    DeliveryAddress findDeliveryAddressesWithAllFields(String firstName, String lastName, String street, int streetNumber, int postalCode, String province);

}
