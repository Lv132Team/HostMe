package com.softserve.edu.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.softserve.edu.dto.EventDto;
import com.softserve.edu.dto.SearchRequestDto;
import com.softserve.edu.model.City;
import com.softserve.edu.model.Event;
import com.softserve.edu.model.PriceCategory;

import org.springframework.data.jpa.domain.Specifications;

import com.softserve.edu.model.User;


public interface EventService {

	public boolean haveEvent(int id);

	public void addEvent(Event event);

	public void removeEvent(Event event);

	public List<EventDto> getAllEvents();

	public List<EventDto> getAllEventsPaging(Integer page, Integer size, String orderBy, String orderType);

	public List<EventDto> getEventsDtoList(List<Event> events);

	public EventDto getEvent(Integer id);

	public Event findOne(Integer id);

	public List<Event> searchEvent(Specifications<Event> specifications);

	public List<EventDto> getEventByStartDate(Date date);

	public List<Event> getEventsLike(String search);

	public List<EventDto> getEventByCity(City city);

	public List<EventDto> getEventByOwner(Integer page, Integer size, String orderBy, String orderType);
	
	public List<EventDto> getByAttendee(Integer page, Integer size, String orderBy, String orderType);

	public List<EventDto> getEventByPriceCategory(PriceCategory priceCategory);

	public List<EventDto> getEventByWebSite(String website);

	public void saveEvent(Event event);
	
	void addEvent(Event event, String priceCategory, String city);

	public Event convertEventDtoToEvent(EventDto eventDto);

	public void updateEvent(Event event, String city, String priceCategory);
	
	public void updateEventStatus(String status, Integer id);
	
	public boolean checkEventOwner(EventDto event, User user);
	
	public void addAttendee(User user, Integer id);
}

