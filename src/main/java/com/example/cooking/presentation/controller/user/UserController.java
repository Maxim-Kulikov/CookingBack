package com.example.cooking.presentation.controller.user;

import com.example.cooking.buisness.service.user.UserService;
import com.example.cooking.presentation.dto.Query;
import com.example.cooking.presentation.dto.dish.resp.DishResp;
import com.example.cooking.presentation.dto.user.UpdateUserReq;
import com.example.cooking.presentation.dto.user.UserCredentialsReq;
import com.example.cooking.presentation.dto.user.UserResp;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/save")
    public UserResp save(@RequestBody UserCredentialsReq req) {
        return userService.save(req);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        userService.delete(id);
    }

    @GetMapping("/{id}/recipes")
    public List<DishResp> findDishesByUserId(@PathVariable Integer id) {
        return userService.getDishesByUserId(id);
    }

    @PatchMapping("/update/{id}")
    public UserResp update(@RequestBody UpdateUserReq req, @PathVariable int id) {
        return userService.update(id, req);
    }

    @PatchMapping("/{id}/set-role/{id-role}")
    public UserResp setRole(@PathVariable int id, @PathVariable("id-role") int idRole) {
        return userService.setRole(id, idRole);
    }

    @GetMapping("/{id}")
    public UserResp findById(@PathVariable int id) {
        return userService.findById(id);
    }

    @PostMapping("")
    public List<UserResp> findAllByQuery(@RequestBody Query query) {
        return userService.findAllByQuery(query);
    }
}
