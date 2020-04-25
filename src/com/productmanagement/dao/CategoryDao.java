package com.productmanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.productmanagement.db.DBUtil;
import com.productmanagement.model.Category;

//this class contains code to perform CRUD operations on category table
public class CategoryDao {
	private Connection con;
	private PreparedStatement ps;
	private String sql;
	private ResultSet rs;
	
	public boolean insert(Category category) {
		boolean isInserted = false;
		
		sql = "INSERT INTO category (name) VALUES (?)";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, category.getName());
			
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
	
	public boolean update(Category category) {
		boolean isUpdated = false;
		
		sql = "UPDATE category SET name = ? WHERE id = ?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, category.getName());
			ps.setInt(2, category.getId());
			
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
	
	public boolean delete(Category category) {
		boolean isDeleted = false;
		
		sql = "DELETE FROM category WHERE id = ?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, category.getId());
			
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
	
	public ArrayList<Category> getAllCategory(){
		ArrayList<Category> categoryList = new ArrayList<>();
		
		sql = "SELECT * FROM category";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				Category category = new Category(id, name);
				categoryList.add(category);
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
		
		return categoryList;
	}
	
//	public String getCategoryName(int id) {
//		String name = null;
//		
//		sql = "SELECT name FROM category WHERE id = ?";
//		
//		try {
//			con = DBUtil.getConnection();
//			ps = con.prepareStatement(sql);
//			
//			ps.setInt(1, id);
//			
//			rs = ps.executeQuery();
//			
//			while(rs.next()) {
//				name = rs.getString(1);
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				con.close();
//				ps.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		return name;
//	}
}
