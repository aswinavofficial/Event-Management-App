package com.letslearn.eventify.service;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import com.letslearn.eventify.dto.EventDTO;

public interface EventService {

	public List<EventDTO> getAllEvents();


	public EventDTO getEventDetailsById(UUID id);

	public EventDTO updateEventDetailsById(UUID id, EventDTO eventDTO);

	public void deleteEvent(UUID id);

	public List<EventDTO> getAllEventsCreatedByUser(UUID userId);

	public EventDTO createEvent(EventDTO eventDTO, UUID userId);

}
