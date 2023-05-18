package it.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

public class EventBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id_event;
	private Date date;
	private String name;
	private String description;
	private double price;
	private int available_tickets;
	private int max_tickets;
	private Date cancellation;
	private Collection<TagBean> tags;
	private Map<ArtistBean, String> artists;
	
	
	public EventBean() {
		this.id_event = -1;
		this.date = null;
		this.name = "";
		this.description = "";
		this.price = 0.0;
		this.available_tickets = 0;
		this.max_tickets = 0;
		this.cancellation = null;
		this.tags = new LinkedList<TagBean>();
		this.artists = new HashMap<ArtistBean, String>();
	}


	public int getEventId() {
		return id_event;
	}


	public void setEventId(int id_event) {
		this.id_event = id_event;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public int getAvailable_tickets() {
		return available_tickets;
	}


	public void setAvailable_tickets(int available_ticket) {
		this.available_tickets = available_ticket;
	}


	public int getMax_tickets() {
		return max_tickets;
	}


	public void setMax_tickets(int max_tickets) {
		this.max_tickets = max_tickets;
	}
	
	public Collection<TagBean> getTags() {
		return tags;
	}


	public void setTags(Collection<TagBean> tags) {
		this.tags = tags;
	}


	public Map<ArtistBean, String> getArtists() {
		return artists;
	}


	public void setArtists(Map<ArtistBean, String> artists) {
		this.artists = artists;
	}


	public Date getCancellation() {
		return cancellation;
	}


	public void setCancellation(Date cancellation) {
		this.cancellation = cancellation;
	}
	
}
