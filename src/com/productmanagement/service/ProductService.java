package com.productmanagement.service;

import java.util.ArrayList;

import com.productmanagement.dao.ProductDao;
import com.productmanagement.model.Product;

public class ProductService {
	private ProductDao productDao = new ProductDao();
	
	public boolean insert(Product product) {
		return productDao.insert(product);
	}
	
	public boolean update(Product product) {
		return productDao.update(product);
	}
	
	public boolean delete(Product product) {
		return productDao.delete(product);
	}
	
	public ArrayList<Product> getAllProduct(){
		return productDao.getAllProduct();
	}
}
