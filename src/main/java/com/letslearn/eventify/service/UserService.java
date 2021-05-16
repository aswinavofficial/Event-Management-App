package com.letslearn.eventify.service;

import java.util.List;
import java.util.UUID;

import com.letslearn.eventify.dto.UserDTO;
import com.letslearn.eventify.model.User;

public interface UserService {
	
	public List<UserDTO> getAllUsers();
	
	public UserDTO createUser(UserDTO userDTO);
	
	public UserDTO getUserDetailsById(UUID id);
	
	public UserDTO updateUserDetailsById(UUID id, UserDTO userDto);
	
	public void deleteUser(UUID id);


}
