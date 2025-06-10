package com.alawiyaa.rest.webservice.restful_web_services.delivery;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.alawiyaa.rest.webservice.restful_web_services.exception.UserNotFoundException;
import com.alawiyaa.rest.webservice.restful_web_services.model.db.posts.Post;
import com.alawiyaa.rest.webservice.restful_web_services.model.users.User;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alawiyaa.rest.webservice.restful_web_services.repository.PostRepository;
import com.alawiyaa.rest.webservice.restful_web_services.repository.UserRepository;
import com.alawiyaa.rest.webservice.restful_web_services.services.UserDaoService;

import io.swagger.v3.core.util.Json;
import jakarta.validation.Valid;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserJpaController {

    private UserRepository userRepository;
    private PostRepository postRepository;

    public UserJpaController(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
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
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        User data = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(data.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrivePostForUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("id =" + id);
        }

        return user.get().getPosts();
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<?> createPostForUser(@Valid @PathVariable int id, @RequestBody Post post) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) throw new UserNotFoundException("id =" + id);
        
        log.info("User :{}", user.get().getName());
        post.setUser(user.get());
        Post savePost = postRepository.save(post);
        log.info("save post id:{}", savePost.getId());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savePost.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

}
