package com.project.bookstore.service;

import com.project.bookstore.models.User;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {

    User findByUsername(String username);

    List<User> findAllUsers();

    boolean isUserExist(User user);

    void saveUser(User user);

    User findUserWithUsername(String username);

}
