package com.project.bookstore.service;

import com.project.bookstore.models.Publisher;
import java.util.List;

public interface IPublisherService {

    void savePublisher(Publisher publisher);

    boolean isPublisherExist(Publisher publisher);

    List<Publisher> findAllPublishers();
}
