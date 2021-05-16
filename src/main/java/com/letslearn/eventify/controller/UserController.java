package com.letslearn.eventify.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.letslearn.eventify.dto.UserDTO;
import com.letslearn.eventify.model.User;
import com.letslearn.eventify.service.UserService;
import com.letslearn.eventify.util.ObjectMapperUtils;

@RestController
@RequestMapping("/user")
public class UserController {
	
    Logger log = LoggerFactory.getLogger(UserController.class);

	
	@Autowired
	private UserService userService;
		
	
	@RequestMapping(method = RequestMethod.GET)
	public List<UserDTO> getAllUsers() {
				
		List<UserDTO> users = userService.getAllUsers();
				
		return users;

	}
	
	@RequestMapping(method = RequestMethod.POST)
	public UserDTO createUser(@Valid @RequestBody UserDTO userDTO) {
				
		userDTO = userService.createUser(userDTO);
		
		return userDTO;
		
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public UserDTO getUserDetails(@PathVariable(name = "id") UUID id) {
		
		
		UserDTO userDTO = userService.getUserDetailsById(id);
		
		return userDTO;

	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	public UserDTO updateUserDetails(@Valid @RequestBody UserDTO userDto, @PathVariable(name = "id") UUID id) {
				
		UserDTO userDTO = userService.updateUserDetailsById(id, userDto);
				
		return userDTO;

	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable(name = "id") UUID id) {
				
		userService.deleteUser(id);

	}
	
	

}
