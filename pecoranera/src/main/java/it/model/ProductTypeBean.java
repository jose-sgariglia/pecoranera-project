package it.model;

import java.io.Serializable;

public class ProductTypeBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id_type;
	private String name;
	
	public ProductTypeBean() {
		this.id_type = -1;
		this.name = "";
	}

	public int getId_type() {
		return id_type;
	}

	public void setId_type(int id_type) {
		this.id_type = id_type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
