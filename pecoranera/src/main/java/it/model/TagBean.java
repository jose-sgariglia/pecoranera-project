package it.model;

import java.io.Serializable;

public class TagBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id_tag;
	private String name;
	
	public TagBean() {
		this.id_tag = -1;
		this.name = "";
	}

	public int getId_tag() {
		return id_tag;
	}

	public void setId_tag(int id_tag) {
		this.id_tag = id_tag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
