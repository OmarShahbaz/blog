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
import com.bloggingApp.blog.payload.UserDto;
import com.bloggingApp.blog.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/create")
	public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto userDto) {
		return new ResponseEntity<UserDto> (userService.createUser(userDto),HttpStatus.CREATED);
	}
	
	@GetMapping("/retrieve-all")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		return new ResponseEntity<List<UserDto>> (userService.getAllUsers(),HttpStatus.OK);
	}
	
	@PutMapping("/update/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") int uId){
		return new ResponseEntity<UserDto>(userService.updateUser(userDto, uId),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<ApiResponse> delete(@PathVariable("userId") int uId) {
		userService.deleteUser(uId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfuly", true),HttpStatus.OK);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto>  getUserById(@PathVariable("userId") int uId) {
		return new ResponseEntity<UserDto> (userService.getUserById(uId),HttpStatus.OK);
	}

}
