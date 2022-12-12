package com.bloggingApp.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bloggingApp.blog.model.Category;
import com.bloggingApp.blog.model.Post;
import com.bloggingApp.blog.model.User;

public interface PostRepo extends JpaRepository<Post, Integer>{
	
	
	//derived query methods
	
	//find all posts by a single user
	List<Post> findAllByUser(User user);
	
	//find all posts of a signle category
	List<Post> findAllByCategory(Category category);
	
}
