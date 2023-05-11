package it.model;

import java.io.Serializable;

public class ProductBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id_product;
	private String name;
	private String description;
	private double price;
	private ProductTypeBean type;
	
	public ProductBean() {
		this.id_product = -1;
		this.name = "";
		this.description = "";
		this.price = 0.0;
		this.type = null;
	}

	public int getId_product() {
		return id_product;
	}

	public void setId_product(int id_product) {
		this.id_product = id_product;
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

	public ProductTypeBean getType() {
		return type;
	}

	public void setType(ProductTypeBean type) {
		this.type = type;
	}
	
	

}
