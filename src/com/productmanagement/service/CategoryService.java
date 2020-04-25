package com.productmanagement.service;

import java.util.ArrayList;

import com.productmanagement.dao.CategoryDao;
import com.productmanagement.model.Category;

public class CategoryService {
	private CategoryDao categoryDao = new CategoryDao();
	
	public boolean insert(Category category) {
		return categoryDao.insert(category);
	}
	
	public boolean update(Category category) {
		return categoryDao.update(category);
	}
	
	public boolean delete(Category category) {
		return categoryDao.delete(category);
	}
	
	public ArrayList<Category> getAllCategory(){
		return categoryDao.getAllCategory();
	}
}
