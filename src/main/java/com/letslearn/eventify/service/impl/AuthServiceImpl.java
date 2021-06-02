package com.letslearn.eventify.service.impl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.letslearn.eventify.controller.AuthController;
import com.letslearn.eventify.dto.LoginDTO;
import com.letslearn.eventify.dto.LoginDTOResponse;
import com.letslearn.eventify.dto.UserDTO;
import com.letslearn.eventify.dto.UserDTORequest;
import com.letslearn.eventify.exception.UserExists;
import com.letslearn.eventify.model.Otp;
import com.letslearn.eventify.model.User;
import com.letslearn.eventify.repository.OTPRepository;
import com.letslearn.eventify.repository.UserRepository;
import com.letslearn.eventify.service.AuthService;
import com.letslearn.eventify.service.MailService;
import com.letslearn.eventify.util.JwtTokenUtil;
import com.letslearn.eventify.util.OTPGenerator;
import com.letslearn.eventify.util.ObjectMapperUtils;

@Service
public class AuthServiceImpl implements AuthService {
	
	Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);


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

	@Autowired
	private MailService mailService;
	
	@Autowired
	private OTPRepository otpRepository;
	
	@Autowired
	private OTPGenerator otpGenerator;

	@Override
	@Transactional
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

		User user = new User();
		user = ObjectMapperUtils.map(userDTORequest, User.class);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setEnabled(false);
		
		user = userRepository.save(user);
		
		Otp otp = new Otp();
		otp.setOtp(otpGenerator.random(6));
		otp.setUserId(user.getId());
		otpRepository.save(otp);
		
		String link = "http://localhost:8027/auth/verifyOtp?userId=" + user.getId() + "&otp=" + otp.getOtp();
		
		mailService.sendSimpleMail("noreply@mail.aswin.tech", user.getEmail(), "Eventify - OTP", "OTP is " + otp.getOtp() +
				"\nVisit this link to verify " + link);

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
	
	@Override
	public boolean verifyOtp(UUID userId, String otp) {
		
		Otp otpEntity = otpRepository.findByUserId(userId);
		Optional<User> userContainer = userRepository.findById(userId);

		
		//15 minutes
		long validity = 15 * 60 * 1000;
		
//		LocalDateTime ldt = Instant.now();
		
//		Date expirationTime = new Date(otpEntity.getCreatedTimeStamp().getNano() + validity);
		
//		log.info("Expiration time : " + expirationTime);
		
		if(otpEntity !=null && otp.equals(otpEntity.getOtp()) && userContainer.isPresent()) {
			
			User user = userContainer.get();
			user.setEnabled(true);
			userRepository.save(user);
			otpRepository.delete(otpEntity);
						
			return true;
		}
		
		return false;
	}

}
