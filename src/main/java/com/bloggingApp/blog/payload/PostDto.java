package com.bloggingApp.blog.payload;


import java.util.Date;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	
	private int postId;

	private String postTitle;
	
	private String postContent;
	
	private String postImage;
	
	private Date date;
	
	private CategoryDto category;
	
	private UserDto user;
	
}
