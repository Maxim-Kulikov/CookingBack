package com.example.cooking.buisness.service.user.impl;

import com.example.cooking.buisness.service.dish.impl.DishServiceImpl;
import com.example.cooking.buisness.service.user.UserService;
import com.example.cooking.data.model.postgres.user.User;
import com.example.cooking.data.repository.postgres.user.RoleRepository;
import com.example.cooking.data.repository.postgres.user.UserRepository;
import com.example.cooking.exception.user.RoleNotFoundException;
import com.example.cooking.exception.user.UserByLoginIsExistedException;
import com.example.cooking.exception.user.UserNotFoundException;
import com.example.cooking.presentation.dto.Query;
import com.example.cooking.presentation.dto.dish.resp.DishResp;
import com.example.cooking.presentation.dto.user.UpdateUserReq;
import com.example.cooking.presentation.dto.user.UserCredentialsReq;
import com.example.cooking.presentation.dto.user.UserResp;
import com.example.cooking.presentation.mapper.user.UserMapper;
import com.example.cooking.util.DataValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final DishServiceImpl dishService;

    @Transactional
    @Override
    public UserResp save(UserCredentialsReq req) {
        ifUserIsExistedThenThrowException(req.getLogin());

        User user = userMapper.toUser(req);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        setRole(user, 2);
        user.setUserBlocked(false);

        userRepository.save(user);
        return userMapper.toUserResp(user);
    }

    @Transactional
    @Override
    public UserResp update(Integer id, UpdateUserReq req) {
        User user = getUserOrThrowException(id);

        if (!DataValidator.isNullOrEmpty(req.getLogin())) {
            ifUserIsExistedThenThrowException(req.getLogin());
            user.setLogin(req.getLogin());
        }

        if (!DataValidator.isNullOrEmpty(req.getPassword())) {
            user.setPassword(passwordEncoder.encode(req.getPassword()));
        }
        user.setName(req.getName());
        user.setLastname(req.getLastname());

        userRepository.save(user);
        return userMapper.toUserResp(user);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        dishService.deleteByUser(getUserOrThrowException(id));
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public UserResp findById(Integer id) {
        return userMapper.toUserResp(getUserOrThrowException(id));
    }

    @Transactional
    @Override
    public List<UserResp> findAllByQuery(Query query) {
        if (DataValidator.isObjectOrFieldNull(query)) {
            return userMapper.toUserResps((List<User>) userRepository.findAll());
        }
        return userMapper.toUserResps(
                userRepository
                        .findAllByLoginContainingIgnoreCaseOrNameContainingIgnoreCaseOrLastnameContainingIgnoreCase(
                                query.getQuery(), query.getQuery(), query.getQuery()
                        ));
    }

    @Transactional
    @Override
    public UserResp setRole(int idUser, int idRole) {
        User user = getUserOrThrowException(idUser);
        setRole(user, idRole);
        return userMapper.toUserResp(user);
    }

    @Transactional
    @Override
    public List<DishResp> getDishesByUserId(int idUser) {
        return getUserOrThrowException(idUser).getDishes()
                .stream()
                .map(dishService::getDishResp)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findUserByLoginIgnoreCase(username);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException(username);
        }
        return userOptional.map(user -> org.springframework.security.core.userdetails.User.builder()
                        .username(user.getLogin())
                        .password(user.getPassword())
                        .authorities(user.getRole().getRole())
                        .build())
                .get();
    }

    public User getUserOrThrowException(int id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    private void ifUserIsExistedThenThrowException(String login) {
        if (userRepository.existsByLoginIgnoreCase(login)) {
            throw new UserByLoginIsExistedException(login);
        }
    }

    private void setRole(User user, int idRole) {
        user.setRole(roleRepository.findById(idRole).orElseThrow(() -> new RoleNotFoundException(idRole)));
    }

}
