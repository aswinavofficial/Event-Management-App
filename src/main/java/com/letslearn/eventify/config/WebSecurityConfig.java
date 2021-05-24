package com.letslearn.eventify.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;


@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	
		@Bean
		public PasswordEncoder passwordEncoder() {
			
			String idForEncode = "bcrypt";
			/**
			 * this would be used as the default encoding method when
			 * calling passwordEncoder().encode("password") method
			 */			
			Map<String,PasswordEncoder> idToPasswordEncoder = new HashMap<>();
			idToPasswordEncoder.put(idForEncode, new BCryptPasswordEncoder());
			idToPasswordEncoder.put("noop", NoOpPasswordEncoder.getInstance());
			idToPasswordEncoder.put("pbkdf2", new Pbkdf2PasswordEncoder());
			idToPasswordEncoder.put("scrypt", new SCryptPasswordEncoder());
			idToPasswordEncoder.put("sha256", new StandardPasswordEncoder());
			
			
			return new DelegatingPasswordEncoder(idForEncode, idToPasswordEncoder);
		}
	
	 	@Override
	 	public void configure(WebSecurity web) throws Exception {
	 		web.ignoring()
	 		// Spring Security should completely ignore URLs starting with /resources/
	 				.antMatchers("/resources/**");
	 	}

	 	@Override
	 	protected void configure(HttpSecurity http) throws Exception {
	 		http
	 		.authorizeRequests()
	 		.antMatchers("/public/**").permitAll()
	 		.anyRequest().hasRole("USER").and()
	 	    .formLogin().permitAll();
	 	}

	 	@Override
	 	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	 		auth
	 		.inMemoryAuthentication()
	 		.withUser("user").password("password").roles("USER")
	 				.and().withUser("admin").password(passwordEncoder().encode("password")).roles("USER", "ADMIN");
	 	}


	 
}
