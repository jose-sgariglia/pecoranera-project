package it.model;

import java.io.Serializable;

public class Cart implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id_cart;
	private User user;
	
	public Cart() {
		this.id_cart = -1;
		this.user = null;
	}

	public int getId_cart() {
		return id_cart;
	}

	public void setId_cart(int id_cart) {
		this.id_cart = id_cart;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
