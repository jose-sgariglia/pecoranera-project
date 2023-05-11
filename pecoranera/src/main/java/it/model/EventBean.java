package it.model;

import java.io.Serializable;
import java.util.Date;

public class EventBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id_event;
	private Date date;
	private String name;
	private String description;
	private double price;
	private int available_ticket;
	private int max_ticket;
	
	
	public EventBean() {
		this.id_event = -1;
		this.date = null;
		this.name = "";
		this.description = "";
		this.price = 0.0;
		this.available_ticket = 0;
		this.max_ticket = 0;
	}


	public int getId_event() {
		return id_event;
	}


	public void setId_event(int id_event) {
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


	public int getAvailable_ticket() {
		return available_ticket;
	}


	public void setAvailable_ticket(int available_ticket) {
		this.available_ticket = available_ticket;
	}


	public int getMax_ticket() {
		return max_ticket;
	}


	public void setMax_ticket(int max_ticket) {
		this.max_ticket = max_ticket;
	}
	
	
}