/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.bookstore.web;

import com.project.bookstore.models.User;
import com.project.bookstore.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author chatz
 */

    
    
@Controller
@RequestMapping("/tier3")
public class UserRegistrationController {
    
    


    @Autowired
    private UserServiceImpl userService;

//    @ModelAttribute("user")
//    public UserRegistrationDto userRegistrationDto() {
//        return new UserRegistrationDto();
//    }

//    @GetMapping("/users")
//    public String showRegistrationForm(Model model) {
//        return "registration";
//    }

    
    
    @PostMapping(value="/createUser", consumes = {MediaType.APPLICATION_JSON_VALUE})    
    public ResponseEntity<String> registerUserAccount(@RequestBody User user /*@Valid UserRegistrationDto userDto,
        BindingResult result*/) {

        Boolean userExists = userService.isUserExist(user);
         if (userExists) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        userService.saveUser(user);

        return new ResponseEntity<>(HttpStatus.CREATED);
}
    
}

