package com.letslearn.eventify.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letslearn.eventify.dto.UserDTO;
import com.letslearn.eventify.exception.UserExists;
import com.letslearn.eventify.exception.UserNotFoundException;
import com.letslearn.eventify.model.User;
import com.letslearn.eventify.repository.UserRepository;
import com.letslearn.eventify.service.UserService;
import com.letslearn.eventify.util.ObjectMapperUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	

	@Override
	public List<UserDTO> getAllUsers() {
		
		List<User> users = userRepository.findAll();
		
		//removing password from output
		users.stream().forEach(user -> user.setPassword(null));
		
		List<UserDTO> userDtoList = ObjectMapperUtils.mapAll(users, UserDTO.class);
		
		return userDtoList;
	}


	@Override
	public UserDTO createUser(UserDTO userDTO) {
				

		if (userDTO !=null && userDTO.getId() !=null &&
				userRepository.findById(userDTO.getId()).isPresent()) {
			
			throw new UserExists("Duplicate Id");
			
		}
		
		if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
			
			throw new UserExists("Duplicate Email");
			
		}
		
		if (userRepository.findByUserName(userDTO.getUserName()).isPresent()) {
			
			throw new UserExists("Duplicate User Name");
			
		}
		
		if (userRepository.findByMobileNumber(userDTO.getMobileNumber()).isPresent()) {
			
			throw new UserExists("Duplicate Mobile Number");
			
		}
		

		User user = ObjectMapperUtils.map(userDTO, User.class);

		user.setId(UUID.randomUUID());
		
		userDTO = ObjectMapperUtils.map(userRepository.save(user), UserDTO.class);

		return userDTO;
	}

	@Override
	public UserDTO getUserDetailsById(UUID id) {

		Optional<User> user = userRepository.findById(id);

		if (user.isPresent()) {

			return  ObjectMapperUtils.map(user.get(), UserDTO.class);
			
		} else {

			throw new UserNotFoundException(id);
		}

	}

	@Override
	public UserDTO updateUserDetailsById(UUID id, UserDTO userDto) {
		
		User user = ObjectMapperUtils.map(userDto, User.class);		
		
		return ObjectMapperUtils.map(userRepository.save(user), UserDTO.class);
	}

	@Override
	public void deleteUser(UUID id) {

		Optional<User> userWrapper = userRepository.findById(id);

		if (userWrapper.isPresent()) {

			User user = userWrapper.get();
			userRepository.delete(user);

		}

		else {

			throw new UserNotFoundException(id);
		}
	}


}
