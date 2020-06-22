package com.project.bookstore.service;

import com.project.bookstore.models.Genre;
import java.util.List;

public interface IGenreService {

    void saveGenre(Genre genre);

    boolean isGenreExist(Genre genre);
    
    List<Genre> findAllGenre();

}
