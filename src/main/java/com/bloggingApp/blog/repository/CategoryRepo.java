package com.bloggingApp.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bloggingApp.blog.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{
	
}
