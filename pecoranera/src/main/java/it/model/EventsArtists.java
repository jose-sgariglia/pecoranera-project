package it.model;

import java.io.Serializable;

public class EventsArtists implements Serializable {

	private static final long serialVersionUID = 1L;

	private Event event;
	private Artist artist;
	private String role;
	
	public EventsArtists() {
		this.event = null;
		this.artist = null;
		this.role = "";
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}
