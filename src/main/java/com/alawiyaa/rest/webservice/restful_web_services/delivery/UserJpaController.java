package com.alawiyaa.rest.webservice.restful_web_services.delivery;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.alawiyaa.rest.webservice.restful_web_services.exception.UserNotFoundException;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alawiyaa.rest.webservice.restful_web_services.model.users.User;
import com.alawiyaa.rest.webservice.restful_web_services.repository.UserRepository;
import com.alawiyaa.rest.webservice.restful_web_services.services.UserDaoService;

import jakarta.validation.Valid;

@RestController
public class UserJpaController {

    private UserDaoService userDaoService;
    private UserRepository userRepository;

    public UserJpaController(UserDaoService userDaoService, UserRepository userRepository) {
        this.userDaoService = userDaoService;
        this.userRepository = userRepository;
    }

    @GetMapping("/jpa/users")
    public List<User> retriveAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> findOne(@Valid @PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("id =" + id);
        }

        EntityModel<User> entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder link = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retriveAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<?> addUser(@Valid @RequestBody User user) {
        User data = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(data.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }

}
