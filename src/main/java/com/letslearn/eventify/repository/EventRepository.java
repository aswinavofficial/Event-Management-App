package com.letslearn.eventify.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.letslearn.eventify.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID>{
	
	
	public List<Event> findByCreatedUser(UUID createdUser);

}
