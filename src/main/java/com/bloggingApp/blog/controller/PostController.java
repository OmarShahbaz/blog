package com.bloggingApp.blog.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bloggingApp.blog.payload.ApiResponse;
import com.bloggingApp.blog.payload.PostDto;
import com.bloggingApp.blog.service.PostService;

@RestController
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@PostMapping("/user/{uId}/category/{cId}/create")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
			@PathVariable int uId, @PathVariable int cId) {
		return new ResponseEntity<PostDto> (postService.createPost(postDto, uId, cId),HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<PostDto>> getAllPosts(
			@RequestParam(value = "page",defaultValue = "0", required = false) int page,
			@RequestParam(value = "size", defaultValue = "5", required = false) int size
			){
		return new ResponseEntity<List<PostDto>>(postService.retrievePost(page, size), HttpStatus.OK);
	}
	
	@PutMapping("/update/{pId}")
	public ResponseEntity<PostDto> updatePost(@PathVariable int pId, @RequestBody PostDto postDto){
		return new ResponseEntity<PostDto>(postService.updatePost(postDto, pId),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{pId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable int pId){
		postService.deletePost(pId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleted.", true), HttpStatus.OK);
	}
	
	@GetMapping("/id/{pId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable int pId){
		PostDto postDto= postService.findPostById(pId);
		return new ResponseEntity<>(postDto,HttpStatus.OK);
	}
	
	@GetMapping("by-user/{uId}")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable int uId){
		List<PostDto> postDto = postService.findAllPostsByUser(uId);
		return new ResponseEntity<List<PostDto>>(postDto, HttpStatus.OK);
	}
	
	@GetMapping("/by-category/{cId}")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable int cId){
		List<PostDto> postsDto = postService.findAllPostsByCategory(cId);
		return new ResponseEntity<List<PostDto>>(postsDto,HttpStatus.OK);
	}
}
