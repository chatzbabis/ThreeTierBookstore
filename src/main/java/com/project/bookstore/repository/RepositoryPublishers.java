package com.project.bookstore.repository;

import com.project.bookstore.models.Publisher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RepositoryPublishers extends CrudRepository<Publisher, Integer> {

    @Query(value = "SELECT * FROM publishers WHERE name= ?1", nativeQuery = true)
    Publisher findByName(String name);
    
    

}
