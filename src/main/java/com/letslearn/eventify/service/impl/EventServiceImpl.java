package com.letslearn.eventify.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letslearn.eventify.dto.EventDTO;
import com.letslearn.eventify.dto.UserDTO;
import com.letslearn.eventify.exception.EventNotFoundException;
import com.letslearn.eventify.exception.UserNotFoundException;
import com.letslearn.eventify.model.Event;
import com.letslearn.eventify.model.User;
import com.letslearn.eventify.repository.EventRepository;
import com.letslearn.eventify.service.EventService;
import com.letslearn.eventify.util.ObjectMapperUtils;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	EventRepository eventRepository;

	@Override
	public List<EventDTO> getAllEvents() {

		List<Event> events = eventRepository.findAll();

		List<EventDTO> eventDTOList = ObjectMapperUtils.mapAll(events, EventDTO.class);

		return eventDTOList;
	}

	@Override
	public List<EventDTO> getAllEventsCreatedByUser(UUID userId) {

		List<Event> events = eventRepository.findByCreatedUser(userId);

		List<EventDTO> eventDTOList = ObjectMapperUtils.mapAll(events, EventDTO.class);

		return eventDTOList;

	}

	@Override
	public EventDTO createEvent(EventDTO eventDTO, UUID userId) {

		Event event = ObjectMapperUtils.map(eventDTO, Event.class);
		
		event.setCreatedUser(userId);

		eventDTO = ObjectMapperUtils.map(eventRepository.save(event), EventDTO.class);

		return eventDTO;

	}

	@Override
	public EventDTO getEventDetailsById(UUID id) {

		Optional<Event> event = eventRepository.findById(id);
		
		if (event.isPresent()) {

			return  ObjectMapperUtils.map(event.get(), EventDTO.class);
			
		} else {

			throw new EventNotFoundException(id);
		}
		
	}

	@Override
	public EventDTO updateEventDetailsById(UUID id, EventDTO eventDTO) {

		Event event = ObjectMapperUtils.map(eventDTO, Event.class);		
		
		return ObjectMapperUtils.map(eventRepository.save(event), EventDTO.class);
	}

	@Override
	public void deleteEvent(UUID id) {
		

		Optional<Event> eventWrapper = eventRepository.findById(id);

		if (eventWrapper.isPresent()) {

			Event event = eventWrapper.get();
			eventRepository.delete(event);

		}

		else {

			throw new UserNotFoundException(id);
		}

		
	}

}
