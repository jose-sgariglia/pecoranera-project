package it.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;

public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public static enum Role {
		ADMIN, USER
	}
	
	private int id_user;
	private String email;
	private String password;
	private String username;
	private Role role;
	private Collection<TagBean> tags;
	private Collection<OrderBean> orders;

	public UserBean() {
		this.id_user = -1;
		this.email = "";
		this.password = "";
		this.username = "";
		this.role = null;
		this.tags = new LinkedList<TagBean>();
		this.orders = new LinkedList<OrderBean>();
	}

	public int getUserId() {
		return id_user;
	}

	public void setUserId(int id_user) {
		this.id_user = id_user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Collection<TagBean> getTags() {
		return tags;
	}

	public void setTags(Collection<TagBean> tags) {
		this.tags = tags;
	}

	public Collection<OrderBean> getOrders() {
		return orders;
	}

	public void setOrders(Collection<OrderBean> orders) {
		this.orders = orders;
	}
}
