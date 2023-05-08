package it.model;

import java.io.Serializable;

public class Artist implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id_artist;
	private String name;
	private String description;
	
	public Artist() {
		this.id_artist = -1;
		this.name = "";
		this.description = "";
	}

	public int getId_artist() {
		return id_artist;
	}

	public void setId_artist(int id_artist) {
		this.id_artist = id_artist;
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
	
	
}
