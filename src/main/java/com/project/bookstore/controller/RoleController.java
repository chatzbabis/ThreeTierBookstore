package com.project.bookstore.controller;

import com.project.bookstore.models.Role;
import com.project.bookstore.service.RoleServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tier3")
public class RoleController {

    @Autowired
    RoleServiceImpl roleService;

    /**
     * GET /roles : Get all roles
     *
     * @return the ResponseEntity with status 200 (OK) and the list of roles in
     * the body, or with status 204 (NO CONTENT) if there are no roles in the
     * database.
     */
    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.findAllRoles();
        if (roles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

}
