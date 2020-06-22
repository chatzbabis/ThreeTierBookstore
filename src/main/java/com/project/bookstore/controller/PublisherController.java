package com.project.bookstore.controller;

import com.project.bookstore.models.Publisher;
import com.project.bookstore.service.PublisherServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tier3")
public class PublisherController {

    @Autowired
    PublisherServiceImpl publisherService;

    /**
     * GET /publishers : Get all publishers.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of
     * publishers in the body, or with status 204 (NO CONTENT) if there are no
     * publishers in database.
     */
    @GetMapping("/publishers")
    public ResponseEntity<List<Publisher>> getAllPublishers() {
        List<Publisher> publishers = publisherService.findAllPublishers();
        if (publishers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(publishers, HttpStatus.OK);
    }

    /**
     * POST /publishers:publisher : Create a new publisher.
     *
     * @param publisher the publishers name to create
     * @return the ResponseEntity with status 201 (Created), or with status 409
     * (Conflict) if the publishers already exists.
     */
    @PostMapping(value = "/publishers", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createPublisher(@RequestBody Publisher publisher) {

        if (publisherService.isPublisherExist(publisher)) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        publisherService.savePublisher(publisher);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
