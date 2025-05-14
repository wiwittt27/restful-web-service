package com.alawiyaa.rest.webservice.restful_web_services.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.alawiyaa.rest.webservice.restful_web_services.model.users.User;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    private static int userCount =1;

    static {
        users.add(new User(userCount++, "adam", LocalDate.now().minusYears(30)));
        users.add(new User(userCount++, "Yanto", LocalDate.now().minusYears(30)));
        users.add(new User(userCount++, "James", LocalDate.now().minusYears(30)));
    }

    public List<User> findAll() {
        return users;
    }

    public User findOne(int id) {
        return users.stream().filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public User userSave(User user){
        user.setId(userCount++);
        users.add(user);
        return user;
    }

//    public User deleteById(int id){
//
//    }
}
