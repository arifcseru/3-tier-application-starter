package com.infy.persistencetier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.infy.businesstier.AccountTO;

public class AccountServiceSQLite {
	public static String DATABASE_NAME = "jdbc:sqlite:myBlog.db";
	private List<String> userList = new ArrayList<String>();
	private LinkedHashMap<String, String> passwordList = new LinkedHashMap<String, String>();
	private LinkedHashMap<String, Double> balanceList = new LinkedHashMap<String, Double>();
	private LinkedHashMap<String, String> usersFullName = new LinkedHashMap<String, String>();

	public AccountServiceSQLite() {
		userList.add("Rahim");
		passwordList.put("Rahim", "1122");
		balanceList.put("Rahim", 433.00);
		usersFullName.put("Rahim", "Rahim Biswas");

		userList.add("Karim");
		passwordList.put("Karim", "1133");
		balanceList.put("Karim", 563.00);
		usersFullName.put("Karim", "Karim Mazumdar");

		userList.add("Delwar");
		passwordList.put("Delwar", "1212");
		balanceList.put("Delwar", 493.00);
		usersFullName.put("Delwar", "Delwar Hossain");

		userList.add("Arif");
		passwordList.put("Arif", "12345");
		balanceList.put("Arif", 133.00);
		usersFullName.put("Arif", "Arif Rahman");

	}

	public String getUser(String userName) {
		if (this.getUserList().contains(userName)) {
			return "Found";
		}
		return "Not Found";
	}

	public AccountTO loginUser(AccountTO accountTO) throws Exception {
		try {

			accountTO.setFullName(this.getUsersFullName().get(accountTO.getUserName()));
			accountTO.setAmount(this.getBalanceList().get(accountTO.getUserName()));
			return accountTO;

		} catch (Exception e) {
			throw new Exception("Service.TECHNICAL_ERROR");
		}

	}
	public static void create(AccountTO accountTO) {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(DATABASE_NAME);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "INSERT INTO account_user (fullName,username,password,createdDate) "
					+ "VALUES ('"+accountTO.getFullName()+"',"+accountTO.getUserName()+"', '"+accountTO.getPassword()+"', datetime());";
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
	public LinkedHashMap<String, String> addUser(AccountTO accountTO) throws Exception {
		try {
			this.getUserList().add(accountTO.getUserName());
			this.getUsersFullName().put(accountTO.getUserName(),accountTO.getFullName());
			this.getBalanceList().put(accountTO.getUserName(),accountTO.getAmount());
			this.getPasswordList().put(accountTO.getUserName(),accountTO.getPassword());
			return this.getUsersFullName();
		} catch (Exception e) {
			throw new Exception("Service.TECHNICAL_ERROR");
		}

	}

	public LinkedHashMap<String, String> deleteUser(AccountTO accountTO)
			throws Exception {
		try {
			this.getUserList().remove(accountTO.getUserName());
			this.getUsersFullName().remove(accountTO.getUserName());
			return this.getUsersFullName();
		} catch (Exception e) {
			throw new Exception("Service.TECHNICAL_ERROR");
		}
	}

	public LinkedHashMap<String, String> updateUser(AccountTO accountTO)
			throws Exception {
		try {

			this.getUsersFullName().put(accountTO.getUserName(),
					accountTO.getFullName());
			this.getBalanceList().put(accountTO.getUserName(),
					accountTO.getAmount());
			this.getPasswordList().put(accountTO.getUserName(),
					accountTO.getPassword());

			return this.getUsersFullName();
		} catch (Exception e) {
			throw new Exception("Service.TECHNICAL_ERROR");
		}
	}

	public LinkedHashMap<String, Double> debit(AccountTO accountTO)
			throws Exception {
		try {
			Double balance = this.getBalanceList().get(accountTO.getUserName());
			balance = balance - accountTO.getAmount();
			this.getBalanceList().put(accountTO.getUserName(), balance);
			return this.getBalanceList();
		} catch (Exception e) {
			throw new Exception("Service.TECHNICAL_ERROR");
		}
	}

	public LinkedHashMap<String, Double> credit(AccountTO accountTO)
			throws Exception {

		try {
			Double balance = this.getBalanceList().get(accountTO.getUserName());
			balance = balance + accountTO.getAmount();
			this.getBalanceList().put(accountTO.getUserName(), balance);
			return this.getBalanceList();
		} catch (Exception e) {
			throw new Exception("Service.TECHNICAL_ERROR");
		}
	}

	public List<AccountTO> getAllUsersData() {
		// ListIterator<String> iterator = this.getUserList().listIterator();
		List<AccountTO> allUsers = new ArrayList<AccountTO>();
		AccountTO accountTO = null;
		for (String userName : this.getUserList()) {
			accountTO = new AccountTO();
			accountTO.setUserName(userName);
			accountTO.setPassword(this.getPasswordList().get(userName));
			accountTO.setFullName(this.getUsersFullName().get(userName));
			accountTO.setAmount(this.getBalanceList().get(userName));

			allUsers.add(accountTO);

		}
		return allUsers;
	}

	// ************ database operation*************************

	public void setUsersFullName(LinkedHashMap<String, String> usersFullName) {
		this.usersFullName = usersFullName;
	}

	public LinkedHashMap<String, String> getPasswordList() {
		return passwordList;
	}

	public void setPasswordList(LinkedHashMap<String, String> passwordList) {
		this.passwordList = passwordList;
	}

	public LinkedHashMap<String, Double> getBalanceList() {
		return balanceList;
	}

	public void setBalanceList(LinkedHashMap<String, Double> balanceList) {
		this.balanceList = balanceList;
	}

	public LinkedHashMap<String, String> getUsersFullName() {
		return usersFullName;
	}

	public void setUserList(List<String> userList) {
		this.userList = userList;
	}

	public List<String> getUserList() {
		return userList;
	}

}
