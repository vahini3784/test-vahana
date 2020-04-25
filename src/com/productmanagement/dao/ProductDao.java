package com.productmanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.productmanagement.db.DBUtil;
import com.productmanagement.model.Category;
import com.productmanagement.model.Product;

public class ProductDao {
	private Connection con;
	private PreparedStatement ps;
	private String sql;
	private ResultSet rs;
	
	public boolean insert(Product product) {
		boolean isInserted = false;
		
		sql = "INSERT INTO product (name, category_id) VALUES ( ?, ?)";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, product.getName());
			ps.setInt(2, product.getCategory().getId());
			
			int rowInserted = ps.executeUpdate();
			
			if (rowInserted > 0) {
				isInserted = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return isInserted;
	}
	
	public boolean update(Product product) {
		boolean isUpdated = false;
		
		sql = "UPDATE product SET name = ?, category_id = ? WHERE id = ?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, product.getName());
			ps.setInt(2, product.getCategory().getId());
			ps.setInt(3, product.getId());
			
			int rowUpdated = ps.executeUpdate();
			if(rowUpdated > 0) {
				isUpdated = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return isUpdated;
	}
	
	public boolean delete(Product product) {
		boolean isDeleted = false;
		
		sql = "DELETE FROM product WHERE id = ?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, product.getId());
			
			int rowDeleted = ps.executeUpdate();
			
			if(rowDeleted > 0) {
				isDeleted = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return isDeleted;
	}
	
	public ArrayList<Product> getAllProduct(){
		ArrayList<Product> productList = new ArrayList<>();
		
		sql = "SELECT * \r\n" + 
			  "FROM product \r\n" + 
			  "INNER JOIN category ON product.category_id = category.id";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				int categoryId = rs.getInt(3);
				String categoryName = rs.getString(5);
				Category category = new Category(categoryId, categoryName);
				Product product = new Product(id, name, category);
				productList.add(product);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return productList;
	}
}
