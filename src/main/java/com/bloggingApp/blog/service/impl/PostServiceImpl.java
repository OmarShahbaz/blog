package com.bloggingApp.blog.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bloggingApp.blog.exception.ResourceNotFoundException;
import com.bloggingApp.blog.model.Category;
import com.bloggingApp.blog.model.Post;
import com.bloggingApp.blog.model.User;
import com.bloggingApp.blog.payload.PostDto;
import com.bloggingApp.blog.repository.CategoryRepo;
import com.bloggingApp.blog.repository.PostRepo;
import com.bloggingApp.blog.repository.UserRepo;
import com.bloggingApp.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, int uId, int cId) {
		
		User user = userRepo.findById(uId).orElseThrow(() ->
				new ResourceNotFoundException("User", "user id", uId));
		
		Category category = categoryRepo.findById(cId).orElseThrow(()->
				new ResourceNotFoundException("Category", "category id", cId));
		
		Post post = modelMapper.map(postDto, Post.class);
		post.setPostImage("default.png");
		post.setDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post newPost = postRepo.save(post);
		
		PostDto postToDto = modelMapper.map(newPost, PostDto.class);
		
		return postToDto;
		
		
	}

	@Override
	public List<PostDto> retrievePost(int page, int size) {
		
		Pageable p = PageRequest.of(page, size);

		Page<Post> pagePost = postRepo.findAll(p);
		
		List<Post> allPosts = pagePost.getContent();
		
		List<PostDto> postsDto = allPosts.stream().map(post ->
			modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postsDto;
	}

	@Override
	public PostDto updatePost(PostDto postDto, int pId) {
		Post post = postRepo.findById(pId).orElseThrow(()->
				new ResourceNotFoundException("Post", "post id", pId));

		post.setPostTitle(postDto.getPostTitle());
		post.setPostContent(postDto.getPostContent());
		post.setPostImage(postDto.getPostImage());
		System.out.println(postDto.getCategory());
		System.out.println(postDto.getUser());
		Post newPost = postRepo.save(post);
		
		PostDto postToDto = modelMapper.map(newPost, PostDto.class);
		return postToDto;
		
	}

	@Override
	public void deletePost(int pId) {
		Post post = postRepo.findById(pId).orElseThrow(()->
				new ResourceNotFoundException("ID", "post Id", pId));
		postRepo.delete(post);
	}

	@Override
	public PostDto findPostById(int pId) {
		Post post = postRepo.findById(pId).orElseThrow(()->
				new ResourceNotFoundException("Post", "post id", pId));
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> findAllPostsByUser(int uId) {
		User user = userRepo.findById(uId).orElseThrow(()->
				new ResourceNotFoundException("User", "user id", uId));
		List<Post> posts = postRepo.findAllByUser(user);
		List<PostDto> postDtos = posts.stream().map(post->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> findAllPostsByCategory(int cId) {
		Category category = categoryRepo.findById(cId).orElseThrow(()->
				new ResourceNotFoundException("Category", "category id", cId));
		
		List<Post> posts = postRepo.findAllByCategory(category);
		List<PostDto> postDto = posts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDto;
	}

	@Override
	public List<Post> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
