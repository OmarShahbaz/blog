package com.bloggingApp.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloggingApp.blog.payload.ApiResponse;
import com.bloggingApp.blog.payload.CategoryDto;
import com.bloggingApp.blog.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/create")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		return new ResponseEntity<>(categoryService.createCategory(categoryDto),HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{cId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable int cId) {
		CategoryDto getCategory = categoryService.updateCategory(categoryDto, cId);
		return new ResponseEntity<>(getCategory, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public List<CategoryDto> getAllCategory(){
		return categoryService.getAllCategories();
	}
	
	@GetMapping("/by-id/{cId}")
	public CategoryDto getCategoryById(@PathVariable int cId) {
		return categoryService.getCategoryById(cId);
		
	}
	
	@DeleteMapping("/delete/{cId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable int cId) {
		categoryService.removeCategoryById(cId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted",true),HttpStatus.OK);
		
	}
}
