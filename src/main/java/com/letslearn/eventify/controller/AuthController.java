package com.letslearn.eventify.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.letslearn.eventify.dto.LoginDTO;
import com.letslearn.eventify.dto.LoginDTOResponse;
import com.letslearn.eventify.dto.UserDTO;
import com.letslearn.eventify.dto.UserDTORequest;
import com.letslearn.eventify.service.AuthService;
import com.letslearn.eventify.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	Logger log = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private AuthService authService;

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public LoginDTOResponse login(@RequestBody LoginDTO loginDTO) {
		
		log.info("POST /auth/login");

		return authService.login(loginDTO);

	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public UserDTO registerUser(@Valid @RequestBody UserDTORequest UserDTORequest) {

		log.info("POST /auth/register");
		log.info(UserDTORequest.toString());

		UserDTO userDTO = authService.registerUser(UserDTORequest);

		return userDTO;

	}
}
