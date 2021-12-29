package com.bridgelabz.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;

public class PayrollService {

	public static void main(String[] args) {
		String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
		String userName = "root";
		String password = "Sinky@1996";
		Connection con = null;
		Statement stmt = null;
		ResultSet rs   = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.print("Driver loaded");

		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("cannot find the driver in the path!", e);
		}
		listDriver();

		try {
			System.out.println("Connecting to database:" + jdbcURL);
			con = DriverManager.getConnection(jdbcURL, userName, password);
			System.out.println("Connection successfull! " + con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			stmt = con.createStatement();
		}  catch (SQLException e) {
			e.printStackTrace();
		}
		String query = "Select * from employee_payroll;";
		try {
			rs = stmt.executeQuery (query);
			while (rs.next()){
				System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " "
						+ rs.getString(4) + " " + rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
			}

	private static void listDriver() {
		Enumeration<Driver> driverList = DriverManager.getDrivers();
		while (driverList.hasMoreElements()) {
			Driver driverClass = driverList.nextElement();
			System.out.println(" " + driverClass.getClass().getName());
		}

	}
}





