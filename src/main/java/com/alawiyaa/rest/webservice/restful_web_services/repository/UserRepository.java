package com.alawiyaa.rest.webservice.restful_web_services.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alawiyaa.rest.webservice.restful_web_services.model.users.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

}
