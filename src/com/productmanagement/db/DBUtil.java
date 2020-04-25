package com.productmanagement.db;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	public static void createDB() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:product_management.sqlite");
			System.out.println("Opened database successfully");

			stmt = c.createStatement();

			String categoryTableCreateQuery = "CREATE TABLE IF NOT EXISTS category " + "(id INTEGER PRIMARY KEY autoincrement,"
					+ " name CHAR(50)  NOT NULL)";
			
			String productTableCreateQuery = "CREATE TABLE IF NOT EXISTS product " + "(id INTEGER PRIMARY KEY autoincrement,"
					+ " name CHAR(50)  NOT NULL, category_id INTEGER NOT NULL, FOREIGN KEY (category_id) REFERENCES category(id))";
			
			stmt.executeUpdate(categoryTableCreateQuery);
			stmt.executeUpdate(productTableCreateQuery);
			stmt.close();
			c.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Tables created successfully");
	}

	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:product_management.sqlite");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}
