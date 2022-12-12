package com.bloggingApp.blog.payload;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

	private int cid;
	
	@NotEmpty
	@Size(min = 4, message = "title is too short")
	private String categoryTitle;
	
	@NotEmpty
	@Size(min = 10, message = "description is too short")
	private String categoryDescription;
}
