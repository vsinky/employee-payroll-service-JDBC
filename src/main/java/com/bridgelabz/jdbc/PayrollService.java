package com.bridgelabz.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Scanner;

public class PayrollService {
 
	public static Connection con = null;

	public static void main(String[] args) throws SQLException {
		String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
		String userName = "root";
		String password = "Sinky@1996";

		PayrollService pr = new PayrollService();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.print("Driver loaded");

		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("cannot find the driver in the path!", e);
		}
		pr.listDriver();

		try {
			System.out.println("Connecting to database:" + jdbcURL);
			PayrollService.con = DriverManager.getConnection(jdbcURL, userName, password);
			System.out.println("Connection successfull! " + PayrollService.con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pr.Operate();

	}

	private void Operate() throws SQLException {
		Scanner sc = new Scanner(System.in);
		int exit = 1;
		PayrollOperation payroll = new PayrollOperation();
		do {
			System.out.println("Database operation \n1.display\n2.insert\n3.update\n4.search\n5.salary range\n6.total salary\n7.group by gender");
			int input = sc.nextInt();
			switch (input) {
			case 1:
				payroll.display();
				break;
			case 2:
				payroll.input();
				break;
			case 3:
				payroll.update();
				break;
			case 4:
				payroll.search();
				break;
			case 5:
				payroll.searchRange();
				break;
			case 6:
				payroll.totalSalary();
				break;
			case 7:
				payroll.groupBy();
				break;
			default:
				System.out.println("Wrong input");
			}
			System.out.println("Enter 1 to continue");
			exit = sc.nextInt();

		} while (exit == 1);
		sc.close();

	}

	private void listDriver() {
		Enumeration<Driver> driverList = DriverManager.getDrivers();
		while (driverList.hasMoreElements()) {
			Driver driverClass = driverList.nextElement();
			System.out.println(" " + driverClass.getClass().getName());
		}
	}
}