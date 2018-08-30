package com.infy.presentationtier;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

import com.infy.businesstier.AccountManager;
import com.infy.businesstier.AccountTO;
import utility.AppConfig;

public class AccountTester {
	public static AccountManager manager = new AccountManager();
	public static Scanner input = new Scanner(System.in);
	public static boolean someExceptions = false;

	public static void main(String[] args) throws Exception {

		int choice = 0;
		int login = 1;
		while (login != 0) {
			System.out
					.println("==============\nUnique Bank Limited\n============");
			System.out.println("1.Login\n0.Cancel");
			login = input.nextInt();
			if (login == 1) {
				loginUser();
				if (!someExceptions) {
					choice = 99;
				}
			}
			while (choice != 0) {
				login = 2;
				System.out
						.println("==============\nUnique Bank Limited\n============");
				System.out.println("1.Add user\n2.Delete user\n3.Update user"
						+ "\n4.Debit\n5.Credit\n6.All user Details\n"
						+ "Choose 0 to exit..\n\n");
				try {
					System.out.print("Please Enter choice:");
					choice = input.nextInt();
					switch (choice) {
					case 1:
						addUser();

						break;
					case 2:
						deleteUser();
						break;
					case 3:
						updateUser();
						break;
					case 4:
						debit();
						break;
					case 5:
						credit();
						break;
					case 6:
						getAllUsersData();
						break;
					case 0:
						break;
					default:
						System.out.println("Wrong Input..");
						break;
					}
				} catch (Exception e) {
					System.out.println("Something Mistaken!");
					throw e;
				}

			}
		}

		System.out.println("\nYou are exit now!");

	}

	private static void updateUser() {
		try {
			System.out.println("PROCESSING TO UPDATE OLD USER...");
			AccountTO accountTO = new AccountTO();
			System.out.print("Username:");
			accountTO.setUserName(input.next());
			System.out.print("Password:");
			accountTO.setPassword(input.next());
			System.out
					.print("Full Name:(use '_' underscore instead of spaces)");
			accountTO.setFullName(input.next());
			System.out.print("Balance: ");
			accountTO.setAmount(input.nextDouble());

			// AccountManager manager = new AccountManager();
			LinkedHashMap<String, String> usersFullNames = manager
					.updateUser(accountTO);
			System.out.println(usersFullNames.values());
			// getAllUsersData();

		} catch (Exception e) {
			String message = AppConfig.PROPERTIES.getProperty(e.getMessage());
			System.out.println(message);
		}
	}

	private static void deleteUser() {
		try {
			System.out.println("PROCESSING TO DELETE OLD USER...");
			AccountTO accountTO = new AccountTO();
			System.out.print("Username:");
			accountTO.setUserName(input.next());
			// accountTO.setFullName("Rahim Biswas");

			LinkedHashMap<String, String> usersFullNames = manager
					.deleteUser(accountTO);
			System.out.println(usersFullNames.values());
			// getAllUsersData();

		} catch (Exception e) {
			String message = AppConfig.PROPERTIES.getProperty(e.getMessage());
			System.out.println(message);
		}
	}

	private static void addUser() {
		try {
			System.out.println("PROCESSING TO ADD NEW USER...");
			AccountTO accountTO = new AccountTO();
			System.out
					.print("Full Name:(use '_' underscore instead of spaces)");
			accountTO.setFullName(input.next());
			System.out.print("Balance: ");
			System.out.print("Username:");
			accountTO.setUserName(input.next());
			System.out.print("Password:");
			accountTO.setPassword(input.next());
			accountTO.setAmount(input.nextDouble());
			// AccountManager manager = new AccountManager();
			LinkedHashMap<String, String> usersFullNames = manager
					.addUser(accountTO);
			System.out.println(usersFullNames.values());
			// getAllUsersData();

		} catch (Exception e) {
			String message = AppConfig.PROPERTIES.getProperty(e.getMessage());
			System.out.println(message);
		}
	}

	public static void loginUser() {
		try {
			System.out.println("PLEASE ENTER LOGIN DETAILS...");
			AccountTO accountTO = new AccountTO();
			System.out.print("Username:");
			accountTO.setUserName(input.next());
			System.out.print("Password:");
			accountTO.setPassword(input.next());

			// manager = new AccountManager();
			accountTO = manager.loginUser(accountTO);
			System.out.println("Welcome to Mr. " + accountTO.getFullName());
			System.out
					.println("Your Account Details:\n========================");
			System.out.println("Username: " + accountTO.getUserName());
			System.out.println("Password: " + accountTO.getPassword());
			System.out.println("Total Balance: " + accountTO.getAmount());
			System.out.println("=======================================");
			someExceptions = false;
		} catch (Exception e) {
			someExceptions = true;
			String message = AppConfig.PROPERTIES.getProperty(e.getMessage());
			System.out.println(message);
		}
	}

	public static void debit() {
		try {
			System.out.println("PROCESSING TO DEBIT...");
			AccountTO accountTO = new AccountTO();
			System.out.print("Username:");
			accountTO.setUserName(input.next());
			System.out.print("Password:");
			accountTO.setPassword(input.next());
			System.out.print("Amount: ");
			accountTO.setAmount(input.nextDouble());
			// AccountManager manager = new AccountManager();
			LinkedHashMap<String, Double> balanceList = manager
					.debit(accountTO);
			balanceList = manager.debit(accountTO);
			// balanceList = manager.debit(accountTO);//causes Insufficient
			// Amount!!

			System.out.println(balanceList);
			// getAllUsersData();
		} catch (Exception e) {
			String message = AppConfig.PROPERTIES.getProperty(e.getMessage());
			System.out.println(message);
		}
	}

	public static void credit() {
		try {
			System.out.println("PROCESSING TO CREDIT...");
			AccountTO accountTO = new AccountTO();
			System.out.print("Username:");
			accountTO.setUserName(input.next());
			System.out.print("Password:");
			accountTO.setPassword(input.next());
			System.out.print("Amount: ");
			accountTO.setAmount(input.nextDouble());
			// AccountManager manager = new AccountManager();
			LinkedHashMap<String, Double> balanceList = manager
					.credit(accountTO);
			System.out.println(balanceList);

		} catch (Exception e) {
			String message = AppConfig.PROPERTIES.getProperty(e.getMessage());
			System.out.println(message);
		}
	}

	public static void getAllUsersData() {
		try {
			System.out.println("PROCESSING TO SHOW ALL CUSTOMERS DETAILS...");
			// AccountManager manager = new AccountManager();
			List<AccountTO> accountTOList = manager.getAllUsersData();
			System.out.println("Full Name\tBalance\tUsername\tPassword");
			System.out
					.println("================================================");
			for (AccountTO accountTO : accountTOList) {
				System.out.println(accountTO.getFullName() + "\t"
						+ accountTO.getAmount() + "\t"
						+ accountTO.getUserName() + "\t\t"
						+ accountTO.getPassword());
			}
			System.out
					.println("================================================");
		} catch (Exception e) {
			String message = AppConfig.PROPERTIES.getProperty(e.getMessage());
			System.out.println(message);
		}
	}
}
