package com.letslearn.eventify.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	
		@Bean
		public PasswordEncoder passwordEncoder() {
			
			return NoOpPasswordEncoder.getInstance();
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
	 		.inMemoryAuthentication().withUser("user").password("password").roles("USER")
	 				.and().withUser("admin").password("password").roles("USER", "ADMIN");
	 	}


	 
}
