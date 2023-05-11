package it.model;

import java.io.Serializable;

public class EventsArtistsBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private EventBean event;
	private ArtistBean artist;
	private String role;
	
	public EventsArtistsBean() {
		this.event = null;
		this.artist = null;
		this.role = "";
	}

	public EventBean getEvent() {
		return event;
	}

	public void setEvent(EventBean event) {
		this.event = event;
	}

	public ArtistBean getArtist() {
		return artist;
	}

	public void setArtist(ArtistBean artist) {
		this.artist = artist;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}
