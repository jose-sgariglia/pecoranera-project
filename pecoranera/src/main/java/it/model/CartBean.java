package it.model;

import java.io.Serializable;

public class CartBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id_cart;
	private UserBean user;
	
	public CartBean() {
		this.id_cart = -1;
		this.user = null;
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
	
	
}
