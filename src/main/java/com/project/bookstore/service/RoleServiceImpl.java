package com.project.bookstore.service;

import com.project.bookstore.models.Role;
import com.project.bookstore.repository.RepositoryRoles;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RepositoryRoles repositoryRoles;

    @Override
    public List<Role> findAllRoles() {
        List<Role> roles = new ArrayList<>();
        repositoryRoles.findAll()
                .forEach(roles::add);
        return roles;
    }

}
