package com.bloggingApp.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bloggingApp.blog.model.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
