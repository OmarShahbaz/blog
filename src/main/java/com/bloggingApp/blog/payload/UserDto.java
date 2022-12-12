package com.bloggingApp.blog.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int id;
	
	@NotEmpty
	@Size(min = 4,message = "name must be of atleast 4 character long")
	private String name;
	
	@Email(message = "email is not valid")
	private String email;
	
	@NotEmpty
	@Size(min = 6, max = 10, message = "password must be atleast 6 characrer long")
	private String password;
	
	@NotEmpty
	private String about;
}
