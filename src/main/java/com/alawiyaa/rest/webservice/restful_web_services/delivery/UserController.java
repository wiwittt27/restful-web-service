package com.alawiyaa.rest.webservice.restful_web_services.delivery;

import java.net.URI;
import java.util.List;

import com.alawiyaa.rest.webservice.restful_web_services.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alawiyaa.rest.webservice.restful_web_services.model.users.User;
import com.alawiyaa.rest.webservice.restful_web_services.services.UserDaoService;

@RestController
public class UserController {

    private UserDaoService userDaoService;

    public UserController(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    @GetMapping("/users")
    public List<User> retriveAllUsers() {
        return userDaoService.findAll();
    }

    @GetMapping("/users/{id}")
    public User findOne(@PathVariable int id) {
        User user = userDaoService.findOne(id);
        if (user == null) {
            throw new UserNotFoundException("id =" + id);
        }

        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        User data = userDaoService.userSave(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(data.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userDaoService.deleteById(id);

    }

}
