package com.alawiyaa.rest.webservice.restful_web_services.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alawiyaa.rest.webservice.restful_web_services.model.db.posts.Post;

public interface PostRepository extends JpaRepository<Post,Integer>{

}
