package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Event {
    private int eventId;
    @JsonProperty("eventName")
    private String eventName;
    private String description;
    private int hostId;
    private int djId;

    public Event() {

    }

    public Event(int eventId, String eventName, String description, int hostId) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.description = description;
        this.hostId = hostId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHostId() {
        return hostId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }

    public int getDjId() {
        return djId;
    }

    public void setDjId(int djId) {
        this.djId = djId;
    }
}
