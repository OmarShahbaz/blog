package com.bloggingApp.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bloggingApp.blog.exception.ResourceNotFoundException;
import com.bloggingApp.blog.model.Category;
import com.bloggingApp.blog.payload.CategoryDto;
import com.bloggingApp.blog.repository.CategoryRepo;
import com.bloggingApp.blog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = dtoToCategory(categoryDto);
		Category savedCategory = categoryRepo.save(category);
		return categoryToDto(savedCategory);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, int cid) {
		Category category = categoryRepo.findById(cid).orElseThrow(()->
				new ResourceNotFoundException("Category", "cId", cid));
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		Category updatedCategory = categoryRepo.save(category);
		CategoryDto updatedCategoryDto = categoryToDto(updatedCategory);
		return updatedCategoryDto;
	}

	@Override
	public void removeCategoryById(int cid) {
		Category category= categoryRepo.findById(cid).orElseThrow(() ->
				new ResourceNotFoundException("Category", "cid", cid));
		categoryRepo.delete(category);
		
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> list = categoryRepo.findAll();
		List<CategoryDto> categories = list.stream().map(category -> categoryToDto(category)).collect(Collectors.toList());
		return categories;
	}

	@Override
	public CategoryDto getCategoryById(int cid) {
		Category category = categoryRepo.findById(cid).orElseThrow(()->
				new ResourceNotFoundException("Category", "cId", cid));
		return categoryToDto(category);
	}
	
	private CategoryDto categoryToDto(Category category) {
		CategoryDto  categoryDto= modelMapper.map(category, CategoryDto.class);
		return categoryDto;
	}
	
	private Category dtoToCategory(CategoryDto categoryDto) {
		Category category = modelMapper.map(categoryDto, Category.class);
		return category;
	}

}
