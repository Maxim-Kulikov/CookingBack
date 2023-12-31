package com.example.cooking.data.repository.postgres.user;

import com.example.cooking.data.model.postgres.user.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

}
