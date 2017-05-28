package com.infotech.client;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

import com.infotech.listener.EmployeeServiceRowSetListener;

public class ClientTest {

	private static final String DB_USERNAME="root";
	private static final String DB_PASSWORD="root";
	private static final String DB_URL ="jdbc:mysql://localhost:3306/jdbcdb";

	public static void main(String[] args) throws SQLException {
		
		RowSetFactory rowSetFactory = RowSetProvider.newFactory();
		JdbcRowSet jdbcRowSet = rowSetFactory.createJdbcRowSet();
		
		jdbcRowSet.setUrl(DB_URL);
		jdbcRowSet.setUsername(DB_USERNAME);
		jdbcRowSet.setPassword(DB_PASSWORD);
		
		jdbcRowSet.setCommand("SELECT *FROM employee_table");
		
		jdbcRowSet.execute();
		
		jdbcRowSet.addRowSetListener(new EmployeeServiceRowSetListener());
		
		//addEmployee(jdbcRowSet);
		//updateEmployeeEmailById(jdbcRowSet);
		//deleteEmployeeById(jdbcRowSet);
		showEmployeesInfo(jdbcRowSet);
	}

	private static void addEmployee(JdbcRowSet jdbcRowSet) throws SQLException {
		jdbcRowSet.moveToInsertRow();
		jdbcRowSet.updateString("employee_name", "Barry");
		jdbcRowSet.updateString("email", "barry.cs2001@siffy.com");
		jdbcRowSet.updateDouble("salary", 580000.00);
		jdbcRowSet.updateTimestamp("date_of_joining", new java.sql.Timestamp(new java.util.Date().getTime()));
		jdbcRowSet.updateBigDecimal("bonus", new BigDecimal(3000.00));
		
		jdbcRowSet.insertRow();
		System.out.println("A new EMployee is added..");
		jdbcRowSet.beforeFirst();
	}

	private static void updateEmployeeEmailById(JdbcRowSet jdbcRowSet) throws SQLException {
		
		int employeeId = 3;
		while (jdbcRowSet.next()) {
			int empId = jdbcRowSet.getInt("employee_id");
			if(employeeId ==empId ){
				jdbcRowSet.updateString("email", "sam.cs2001@siffy.com");
				jdbcRowSet.updateRow();
				System.out.println("Email is updated.");
				break;
			}
		}
		jdbcRowSet.beforeFirst();
	}

	private static void deleteEmployeeById(JdbcRowSet jdbcRowSet) throws SQLException {
		
		int employeeId = 4;
		while (jdbcRowSet.next()) {
			int empId = jdbcRowSet.getInt("employee_id");
			if(employeeId ==empId ){
				jdbcRowSet.deleteRow();
				System.out.println("Employee is deleted with ID:"+employeeId);
				break;
			}
		}
		jdbcRowSet.beforeFirst();
	}

	private static void showEmployeesInfo(JdbcRowSet jdbcRowSet) throws SQLException {
		while (jdbcRowSet.next()) {
/*			int empId = jdbcRowSet.getInt("employee_id");
			String eName = jdbcRowSet.getString("employee_name");
			String email = jdbcRowSet.getString("email");
			Double salary = jdbcRowSet.getDouble("salary");
			BigDecimal bonus = jdbcRowSet.getBigDecimal("bonus");

			System.out.println(empId + "\t" + eName + "\t" + salary + "\t" + email + "\t" + bonus);
*/		
		}
	}
}
