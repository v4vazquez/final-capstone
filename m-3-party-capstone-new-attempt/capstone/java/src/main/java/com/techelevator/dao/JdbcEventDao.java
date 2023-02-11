package com.techelevator.dao;

import com.techelevator.model.Event;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcEventDao implements EventDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcEventDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Event> getEventsForCurrentUser(User user) {
        List<Event> list = new ArrayList<>();
        String sql = "SELECT * FROM events WHERE host_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, user.getId());
        while (results.next()) {
            list.add(mapRowToEvent(results));
        }
        return list;
    }

    public Event getEventById(int eventId) {
        String sql = "SELECT * FROM events WHERE event_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, eventId);
        if (results.next()) {
            return mapRowToEvent(results);
        }
        return null;
    }

    public void createEvent(User user, Event event, int djId) {
        String sql = "INSERT INTO events(event_name, description, host_id, dj_id) " +
                "VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, event.getEventName(), event.getDescription(), user.getId(), djId);
    }

    public void updateEvent(int eventId, Event event) {
        String sql = "UPDATE events SET event_name = ?, description = ? WHERE event_id = ?";
        jdbcTemplate.update(sql, event.getEventName(), event.getDescription(), eventId);
    }

    public void addDjToEvent(Event event, User dj) {
        String sql = "UPDATE events SET dj_id = ? WHERE event_id = ?";
        jdbcTemplate.update(sql, dj.getId(), event.getEventId());
    }

    public void deleteEvent(int id) {
        String sql = "DELETE FROM events WHERE event_id = ?";
        String sql2 = "DELETE FROM event_songs WHERE event_id = ?";
        jdbcTemplate.update(sql2, id);
        jdbcTemplate.update(sql, id);
    }

    public Event mapRowToEvent(SqlRowSet results) {
        Event event = new Event();
        event.setDescription(results.getString("description"));
        event.setEventName(results.getString("event_name"));
        event.setEventId(results.getInt("event_id"));
        event.setHostId(results.getInt("host_id"));
        event.setDjId(results.getInt("dj_id"));
        return event;
    }

}
