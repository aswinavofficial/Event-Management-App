package com.letslearn.eventify.service;

import java.util.List;
import java.util.UUID;

import com.letslearn.eventify.dto.UserDTO;
import com.letslearn.eventify.dto.UserDTORequest;
import com.letslearn.eventify.model.User;

public interface UserService {
	
	public List<UserDTO> getAllUsers();
	
	public UserDTO createUser(UserDTORequest userDTORequest);
	
	public UserDTO getUserDetailsById(UUID id);
	
	public UserDTO updateUserDetailsById(UUID id, UserDTORequest userDTORequest);
	
	public void deleteUser(UUID id);


}
