package com.project.bookstore.repository;

import com.project.bookstore.models.Language;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RepositoryLanguages extends CrudRepository<Language, Integer> {

    @Query(value = "SELECT * FROM languages WHERE name= ?1", nativeQuery = true)
    Language findByName(String name);

}
