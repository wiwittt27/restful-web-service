package com.alawiyaa.rest.webservice.restful_web_services.delivery;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alawiyaa.rest.webservice.restful_web_services.model.users.User;
import com.alawiyaa.rest.webservice.restful_web_services.services.UserDaoService;
@RestController
public class UserController {

    private UserDaoService userDaoService;

    public UserController(UserDaoService userDaoService){
        this.userDaoService = userDaoService;
    }

    @GetMapping("/users")
    public List<User> retriveAllUsers(){
        return userDaoService.findAll();
    }

    @GetMapping("/users/{id}")
    public User findOne(@PathVariable int id){
        return userDaoService.findOne(id);
    }

    @PostMapping("/users")
    public ResponseEntity<?> addUser(@RequestBody User user){
      User data =  userDaoService.userSave(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(data.getId()).toUri();
        return ResponseEntity.created(location).build();
    }


}
