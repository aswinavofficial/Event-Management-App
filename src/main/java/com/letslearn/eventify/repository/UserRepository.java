package com.letslearn.eventify.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.letslearn.eventify.model.User;

@Repository
public interface UserRepository  extends JpaRepository<User, UUID>{
	
	Optional<User> findById(UUID id);
	Optional<User> findByEmail(String email);
	Optional<User> findByUserName(String userName);
	Optional<User> findByMobileNumber(String mobileNumber);
}
