package com.letslearn.eventify.service;

import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;

import com.letslearn.eventify.dto.LoginDTO;
import com.letslearn.eventify.dto.LoginDTOResponse;
import com.letslearn.eventify.dto.UserDTO;
import com.letslearn.eventify.dto.UserDTORequest;

public interface AuthService {
	
	public UserDTO registerUser(UserDTORequest UserDTORequest);
	
	public LoginDTOResponse login(LoginDTO loginDTO);

	public UserDetails validateJwtToken(String jwtToken);

	public boolean verifyOtp(UUID userId, String otp);

}
