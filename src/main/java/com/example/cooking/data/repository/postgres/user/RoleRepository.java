package com.example.cooking.data.repository.postgres.user;

import com.example.cooking.data.model.postgres.user.Role;
import com.example.cooking.data.model.postgres.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Optional<Role> findRoleByRoleIgnoreCase(String role);
}
