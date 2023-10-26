package com.example.cooking.data.repository.postgres.user;

import com.example.cooking.data.model.postgres.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findUserByLoginIgnoreCase(String login);

    boolean existsByLoginIgnoreCase(String login);

    List<User> findAllByLoginContainingIgnoreCaseOrNameContainingIgnoreCaseOrLastnameContainingIgnoreCase(String login,
                                                                                                          String name,
                                                                                                          String lastname);
}
