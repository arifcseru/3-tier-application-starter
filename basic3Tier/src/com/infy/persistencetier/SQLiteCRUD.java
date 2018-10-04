package com.infy.persistencetier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLiteCRUD {
	public static String DATABASE_NAME = "jdbc:sqlite:myBlog.db";
	public static void main(String args[]) {
		connectDB();
		createDB();
		insertDB();
		selectDB();
		// updateDB();
		// deleteDB();
	}

	public static void connectDB() {
		Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(DATABASE_NAME);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
	}

	public static void createDB() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(DATABASE_NAME);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS web_blog "
					+ "(ID INTEGER PRIMARY KEY autoincrement,"
					+ " NAME           CHAR(50)    NOT NULL, "
					+ " message        TEXT     NOT NULL, "
					+ " date_added     datetime)";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS account_user "
					+ "(ID INTEGER PRIMARY KEY autoincrement,"
					+ " fullName           CHAR(50)    NOT NULL, "
					+ " username        TEXT     NOT NULL, "
					+ " password        TEXT     NOT NULL, "
					+ " balance        NUMBER     NOT NULL, "
					+ " createdDate     datetime)";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS web_blog "
					+ "(ID INTEGER PRIMARY KEY autoincrement,"
					+ " NAME           CHAR(50)    NOT NULL, "
					+ " message        TEXT     NOT NULL, "
					+ " date_added     datetime)";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS web_blog "
					+ "(ID INTEGER PRIMARY KEY autoincrement,"
					+ " NAME           CHAR(50)    NOT NULL, "
					+ " message        TEXT     NOT NULL, "
					+ " date_added     datetime)";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS web_blog "
					+ "(ID INTEGER PRIMARY KEY autoincrement,"
					+ " NAME           CHAR(50)    NOT NULL, "
					+ " message        TEXT     NOT NULL, "
					+ " date_added     datetime)";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Table created successfully");
	}

	public static void insertDB() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(DATABASE_NAME);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "INSERT INTO web_blog (NAME,message,date_added) "
					+ "VALUES ('Ken', 'Hello every one!!!', datetime());";
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Records created successfully");
	}

	public static void selectDB() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(DATABASE_NAME);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM web_blog;");
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String message = rs.getString("message");
				String date_added = rs.getString("date_added");
				System.out.println("ID : " + id);
				System.out.println("Name : " + name);
				System.out.println("Message : " + message);
				System.out.println("Date Added : " + date_added);
				System.out.println();
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
	}

	public static void updateDB() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(DATABASE_NAME);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "UPDATE web_blog set message = 'This is updated by updateDB()' where ID=1;";
			stmt.executeUpdate(sql);
			c.commit();

			ResultSet rs = stmt.executeQuery("SELECT * FROM web_blog;");
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String message = rs.getString("message");
				String date_added = rs.getString("date_added");
				System.out.println("ID : " + id);
				System.out.println("Name : " + name);
				System.out.println("Message : " + message);
				System.out.println("Date Added : " + date_added);
				System.out.println();
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
	}

	public static void deleteDB() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(DATABASE_NAME);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "DELETE from web_blog where ID=1;";
			stmt.executeUpdate(sql);
			c.commit();

			ResultSet rs = stmt.executeQuery("SELECT * FROM web_blog;");
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String message = rs.getString("message");
				String date_added = rs.getString("date_added");
				System.out.println("ID : " + id);
				System.out.println("Name : " + name);
				System.out.println("Message : " + message);
				System.out.println("Date Added : " + date_added);
				System.out.println();
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
	}
}
