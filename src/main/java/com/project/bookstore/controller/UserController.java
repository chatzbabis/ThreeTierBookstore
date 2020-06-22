package com.project.bookstore.controller;

import com.project.bookstore.models.User;
import com.project.bookstore.service.UserServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tier3")
public class UserController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserServiceImpl userService;

    /**
     * GET /users : Get all users
     *
     * @return the ResponseEntity with status 200 (OK) and the list of users in
     * the body, or with status 204 (NO CONTENT) if there are no users in the
     * database.
     */
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * GET /user/{username} : Get user with username
     *
     * @param username the username of the user to retrieve
     * @return the ResponseEntity with status 200 (OK) and the user in the body,
     * or with status 204 (NO CONTENT) if there is no user with username in the
     * database.
     */
    @GetMapping("/user/{username}")
    public ResponseEntity<User> getUserWithUsername(@PathVariable("username") String username) {
        User user = userService.findUserWithUsername(username);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * POST /users : Create a new user.
     *
     * @param user the user to create
     * @return the ResponseEntity with status 201 (Created), or with status 409
     * (Conflict) if the user with same username already exists.
     */
    @PostMapping(value = "/users", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> createUser(@RequestBody User user) {

        if (userService.isUserExist(user)) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        User user2 = new User();
        user2.setEmail(user.getEmail());
        user2.setFirstName(user.getFirstName());
        user2.setLastName(user.getLastName());
        user2.setUsername(user.getUsername());
        user2.setPassword(passwordEncoder.encode(user.getPassword()));
        user2.setRoles(user.getRoles());
        userService.saveUser(user2);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/userdetails")
    public ResponseEntity<Object> getUserCredentials() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(principal, HttpStatus.OK);

    }
}
