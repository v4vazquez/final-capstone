package com.techelevator.dao;

import com.techelevator.model.Event;
import com.techelevator.model.User;

import java.util.List;

public interface EventDao {

    List<Event> getEventsForCurrentUser(User user);

    Event getEventById(int eventId);

    void createEvent(User user, Event event, int djId);

    void updateEvent(int id, Event event);

    void addDjToEvent(Event event, User dj);

    void deleteEvent(int eventId);
}
