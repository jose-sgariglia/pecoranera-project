package it.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CartBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id_cart;
	private UserBean user;
	private Map<EventBean, Integer> events;
	
	public CartBean() {
		this.id_cart = -1;
		this.user = null;
		this.events = new HashMap<EventBean, Integer>();
	}

	public int getCartId() {
		return id_cart;
	}

	public void setId_cart(int id_cart) {
		this.id_cart = id_cart;
	}

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}

	public Map<EventBean, Integer> getEvents() {
		return events;
	}

	public void setEvents(Map<EventBean, Integer> events) {
		this.events = events;
	}
}
