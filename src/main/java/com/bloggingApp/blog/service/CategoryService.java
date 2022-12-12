package com.bloggingApp.blog.service;

import java.util.List;

import com.bloggingApp.blog.payload.CategoryDto;

public interface CategoryService {

	CategoryDto createCategory(CategoryDto categoryDto);
	
	CategoryDto updateCategory(CategoryDto categoryDto, int cid);
	
	void removeCategoryById(int cid);
	
	List<CategoryDto> getAllCategories();
	
	CategoryDto getCategoryById(int id);
}
