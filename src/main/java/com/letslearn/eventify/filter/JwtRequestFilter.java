package com.letslearn.eventify.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.letslearn.eventify.controller.AuthController;
import com.letslearn.eventify.exception.UserAuthenticationException;
import com.letslearn.eventify.service.AuthService;
import com.letslearn.eventify.util.JwtTokenUtil;

@Component
public class JwtRequestFilter  extends OncePerRequestFilter{
	
	Logger log = LoggerFactory.getLogger(JwtRequestFilter.class);

	
	@Autowired
	AuthService authService;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		String jwtToken = retreiveJwtTokenFromHeader(request);
		
		UserDetails userDetails = authService.validateJwtToken(jwtToken);
		
		if(userDetails !=null) {
			
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
			= new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			
			usernamePasswordAuthenticationToken
			.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			
			
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			
		}
		
		filterChain.doFilter(request, response);

	}
	
	
	private String retreiveJwtTokenFromHeader(HttpServletRequest request) {
				
		final String requestTokenHeader = request.getHeader("Authorization");
		
		String jwtToken = null;

		if(requestTokenHeader !=null && requestTokenHeader.startsWith("Bearer ")) {
			
			jwtToken = requestTokenHeader.substring(7);
			
		}
		
		else {
			
			log.info("JWT Token does not begin with Bearer String");
		}
		
		return jwtToken;
	}

}
