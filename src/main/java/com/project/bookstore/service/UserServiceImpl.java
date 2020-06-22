package com.project.bookstore.service;

import com.project.bookstore.models.Basket;
import com.project.bookstore.models.Role;
import com.project.bookstore.models.User;
import com.project.bookstore.models.Wishlist;
import com.project.bookstore.repository.RepositoryBaskets;
import com.project.bookstore.repository.RepositoryUsers;
import com.project.bookstore.repository.RepositoryWishlists;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private RepositoryUsers repositoryUsers;

//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private RepositoryBaskets repositoryBaskets;

    @Autowired
    private RepositoryWishlists repositoryWishlists;

    @Override
    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        repositoryUsers.findAll()
                .forEach(users::add);
        return users;
    }

    @Override
    public boolean isUserExist(User user) {
        if (repositoryUsers.findByUsername(user.getUsername()) == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void saveUser(User user) {
        repositoryUsers.save(user);
        Basket basket = new Basket(user);
        Wishlist wishlist = new Wishlist(user);
        repositoryBaskets.save(basket);
        repositoryWishlists.save(wishlist);
    }

    @Override
    public User findByUsername(String username) {
        return repositoryUsers.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = repositoryUsers.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Collection< Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole()))
                .collect(Collectors.toList());
    }

    @Override
    public User findUserWithUsername(String username) {
        return repositoryUsers.findByUsername(username);
    }
}
