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
import com.letslearn.eventify.dto.UserDTO;
import com.letslearn.eventify.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
    Logger log = LoggerFactory.getLogger(AuthController.class);

	
	@Autowired
	private UserService userService;
	

	
	@Autowired
	AuthenticationManager authenticationManager;
	
	
	@RequestMapping(value="login",method = RequestMethod.GET)
	public Object login(@RequestBody LoginDTO loginDTO) {
		
	    UsernamePasswordAuthenticationToken authRequest 
	    = new UsernamePasswordAuthenticationToken(loginDTO.getUserName(), loginDTO.getPassword());
	    
	    
	    Authentication authentication = authenticationManager.authenticate(authRequest);
	    
	    SecurityContext securityContext = SecurityContextHolder.getContext();
	    
	    securityContext.setAuthentication(authentication);


	   return authentication.getPrincipal();

			}

	
	@RequestMapping(value="register",method = RequestMethod.POST)
	public UserDTO registerUser(@Valid @RequestBody UserDTO userDTO) {
		
		log.info("PUT /auth/register");
		log.info(userDTO.toString());
		
				
		userDTO = userService.registerUser(userDTO);
		
		return userDTO;
		
	}
}
