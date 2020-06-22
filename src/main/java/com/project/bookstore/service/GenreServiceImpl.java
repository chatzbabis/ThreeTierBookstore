package com.project.bookstore.service;

import com.project.bookstore.models.Genre;
import com.project.bookstore.repository.RepositoryGenre;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("genreService")
public class GenreServiceImpl implements IGenreService {

    @Autowired
    private RepositoryGenre repositoryGenre;

    @Override
    public void saveGenre(Genre genre) {
        repositoryGenre.save(genre);
    }

    @Override
    public boolean isGenreExist(Genre genre) {
        if (repositoryGenre.findByName(genre.getName()) == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public List<Genre> findAllGenre() {
          List<Genre> genre = new ArrayList<>();
        repositoryGenre.findAll()
                .forEach(genre::add);
        return genre;
    }

}
