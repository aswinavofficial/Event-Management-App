package com.letslearn.eventify.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.letslearn.eventify.dto.EventDTO;
import com.letslearn.eventify.dto.EventDTO;
import com.letslearn.eventify.service.EventService;

@RestController
@RequestMapping("/event")
public class EventController {
	
    Logger log = LoggerFactory.getLogger(EventController.class);
    
    @Autowired
    EventService eventService;
    
    

	@RequestMapping(method = RequestMethod.GET)
	public List<EventDTO> getAllEvents() {
				
		List<EventDTO> events = eventService.getAllEvents();
				
		return events;

	}
	
	@RequestMapping(method = RequestMethod.POST)
	public EventDTO createEvent(@Valid @RequestBody EventDTO eventDTO) {
		
		log.info("PUT /event");
		log.info(eventDTO.toString());
				
		eventDTO = eventService.createEvent(eventDTO,UUID.randomUUID());
		
		return eventDTO;
		
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public EventDTO getEventDetails(@PathVariable(name = "id") UUID id) {
		
		
		EventDTO EventDTO = eventService.getEventDetailsById(id);
		
		return EventDTO;

	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	public EventDTO updateEventDetails(@Valid @RequestBody EventDTO EventDTO, @PathVariable(name = "id") UUID id) {
				
		EventDTO eventDTO = eventService.updateEventDetailsById(id, EventDTO);
				
		return eventDTO;

	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	public void deleteEvent(@PathVariable(name = "id") UUID id) {
				
		eventService.deleteEvent(id);

	}
	

}
