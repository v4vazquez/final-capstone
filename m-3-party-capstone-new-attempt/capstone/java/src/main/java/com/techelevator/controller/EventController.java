package com.techelevator.controller;

import com.techelevator.dao.EventDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Event;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/events")
@CrossOrigin
public class EventController {
    private final EventDao eventDao;
    private final UserDao userDao;


    public EventController(UserDao userDao, EventDao eventDao) {
        this.eventDao = eventDao;
        this.userDao = userDao;
    }

    @RequestMapping(path = "/all-events", method = RequestMethod.GET)
    public List<Event> getEvents(Principal principal) {
        int userId = userDao.findIdByUsername(principal.getName());
        User user = userDao.getUserById(userId);
        return eventDao.getEventsForCurrentUser(user);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Event getEventById(@PathVariable int id) {
        return eventDao.getEventById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/newEvent/{djId}", method = RequestMethod.POST)
    public void createEvent(Principal principal, @RequestBody Event event, @PathVariable int djId) {
        User user = userDao.findByUsername(principal.getName());
        eventDao.createEvent(user, event, djId);
    }

    @RequestMapping(path = "/update-event/{id}", method = RequestMethod.PUT)
    public void updateEvent(@RequestBody Event event, @PathVariable int id) {
        eventDao.updateEvent(id , event);
    }

    @RequestMapping(path = "/delete-event/{id}", method = RequestMethod.DELETE)
    public void deleteEvent(@PathVariable int id) {
        eventDao.deleteEvent(id);
    }

    @RequestMapping(path = "/addDj/{eventId}/{id}", method = RequestMethod.PUT)
    public void addDjToEvent(@PathVariable int eventId, @PathVariable int id) {
        Event event = eventDao.getEventById(eventId);
        User dj = userDao.findDjById(id);
        eventDao.addDjToEvent(event, dj);
    }

    @RequestMapping(path = "eventId", method = RequestMethod.GET)
    public int getInt(Principal principal) {
        User user = userDao.findByUsername(principal.getName());
        return userDao.getEventIdFromDj(user);
    }




}
