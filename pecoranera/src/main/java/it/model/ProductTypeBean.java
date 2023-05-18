package it.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;

public class ProductTypeBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id_type;
	private String name;
	private Collection<ProductBean> products;
	
	public ProductTypeBean() {
		this.id_type = -1;
		this.name = "";
		this.products = new LinkedList<ProductBean>();
	}

	public Collection<ProductBean> getProducts() {
		return products;
	}

	public void setProducts(Collection<ProductBean> products) {
		this.products = products;
	}

	public int getTypeId() {
		return id_type;
	}

	public void setTypeId(int id_type) {
		this.id_type = id_type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
