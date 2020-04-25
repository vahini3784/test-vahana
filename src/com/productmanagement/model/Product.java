package com.productmanagement.model;

public class Product {
	private int id;
	private String name;
	private Category category;
	
	
	public Product(String name, Category category) {
		super();
		this.name = name;
		this.category = category;
	}
	
	public Product(int id, String name, Category category) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", category=" + category + "]";
	}
	
}
