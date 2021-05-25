package com.letslearn.eventify.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.letslearn.eventify.dto.LoginDTO;
import com.letslearn.eventify.dto.LoginDTOResponse;
import com.letslearn.eventify.dto.UserDTO;
import com.letslearn.eventify.dto.UserDTORequest;
import com.letslearn.eventify.exception.UserExists;
import com.letslearn.eventify.model.User;
import com.letslearn.eventify.repository.UserRepository;
import com.letslearn.eventify.service.AuthService;
import com.letslearn.eventify.util.JwtTokenUtil;
import com.letslearn.eventify.util.ObjectMapperUtils;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;


	@Override
	public UserDTO registerUser(UserDTORequest userDTORequest) {

		if (userDTORequest != null && userDTORequest.getId() != null
				&& userRepository.findById(userDTORequest.getId()).isPresent()) {

			throw new UserExists("Duplicate Id");

		}

		if (userRepository.findByEmail(userDTORequest.getEmail()).isPresent()) {

			throw new UserExists("Duplicate Email");

		}

		if (userRepository.findByUserName(userDTORequest.getUserName()).isPresent()) {

			throw new UserExists("Duplicate User Name");

		}

		if (userRepository.findByMobileNumber(userDTORequest.getMobileNumber()).isPresent()) {

			throw new UserExists("Duplicate Mobile Number");

		}

		User user = ObjectMapperUtils.map(userDTORequest, User.class);
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		UserDTO userDTO = ObjectMapperUtils.map(userRepository.save(user), UserDTO.class);

		return userDTO;
	}

	@Override
	public LoginDTOResponse login(LoginDTO loginDTO) {

		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
				loginDTO.getUserName(), loginDTO.getPassword());

		Authentication authentication = authenticationManager.authenticate(authRequest);

		SecurityContext securityContext = SecurityContextHolder.getContext();

		securityContext.setAuthentication(authentication);

		UserDTO userDTO = ObjectMapperUtils.map(authentication.getPrincipal(), UserDTO.class);
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(loginDTO.getUserName());
		
		String jwtToken = jwtTokenUtil.generateToken(userDetails);
		
		LoginDTOResponse loginDTOResponse = new LoginDTOResponse();
		loginDTOResponse.setJwt(jwtToken);
		loginDTOResponse.setUserDetails(userDTO);
		
		return loginDTOResponse;
	}
	
	@Override
	public UserDetails validateJwtToken(String jwtToken) {
		
		String username = null;
		
		if(jwtToken !=null) {
			username = jwtTokenUtil.extractUserNameFromToken(jwtToken);
		}
		
		// If the request already authenticated then skip
		if(username !=null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			// check whether user exists in the database
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			
			if(jwtTokenUtil.validateToken(jwtToken, userDetails)) {
				
				return userDetails;
				
			}
			
		}
		
		return null;
		
	}

}
