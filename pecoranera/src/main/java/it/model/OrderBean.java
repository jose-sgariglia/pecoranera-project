package it.model;

import java.io.Serializable;
import java.util.Date;

public class OrderBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id_order;
	private int tickets;
	private double price;
	private Date date;
	private UserBean user;
	private EventBean event;
	
	public OrderBean() {
		this.id_order = -1;
		this.tickets = 0;
		this.price = 0.0;
		this.date = null;
		this.user = null;
		this.event = null;
	}

	public int getOrderId() {
		return id_order;
	}

	public void setOrderId(int id_order) {
		this.id_order = id_order;
	}

	public int getTickets() {
		return tickets;
	}

	public void setTickets(int tickets) {
		this.tickets = tickets;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}

	public EventBean getEvent() {
		return event;
	}

	public void setEvent(EventBean event) {
		this.event = event;
	}
	
	

}
