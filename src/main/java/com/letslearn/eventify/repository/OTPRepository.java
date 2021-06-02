package com.letslearn.eventify.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.letslearn.eventify.model.Otp;

@Repository
public interface OTPRepository extends JpaRepository<Otp, UUID> {
	
	Otp findByUserId(UUID userId);

}
