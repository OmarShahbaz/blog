package com.bloggingApp.blog.service;

import java.util.List;

import com.bloggingApp.blog.model.Post;
import com.bloggingApp.blog.payload.PostDto;

public interface PostService {

	PostDto createPost(PostDto postDto, int uId, int cId);
	
	List<PostDto> retrievePost(int pageSize, int pageNumber);
	
	PostDto updatePost(PostDto postDto, int pId);
	
	void deletePost(int id);
	
	PostDto findPostById(int pId);
	
	List<PostDto> findAllPostsByUser(int uId);
	
	List<PostDto> findAllPostsByCategory(int cId);
	
	List<Post> searchPosts(String keyword);
}
