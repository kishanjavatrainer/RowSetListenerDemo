package com.infotech.listener;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.sql.RowSetEvent;
import javax.sql.RowSetListener;

import com.sun.rowset.JdbcRowSetImpl;

public class EmployeeServiceRowSetListener implements RowSetListener{

	@Override
	public void rowSetChanged(RowSetEvent event) {
		System.out.println("RowSetChanged event..");
		Object source = event.getSource();
		System.out.println(source);
	}

	@Override
	public void rowChanged(RowSetEvent event) {
		System.out.println("RowChanged event..");
		Object source = event.getSource();
		System.out.println(source);
	}

	@Override
	public void cursorMoved(RowSetEvent event) {
		Object source = event.getSource();
		JdbcRowSetImpl jdbcRowSet=(JdbcRowSetImpl)source;
		try {
			if(jdbcRowSet.isAfterLast()||jdbcRowSet.isBeforeFirst()){
				//do nothing
			}else{
				System.out.println("Cursor Moved event occured with following employee details:::");
				
				int empId = jdbcRowSet.getInt("employee_id");
				String eName = jdbcRowSet.getString("employee_name");
				String email = jdbcRowSet.getString("email");
				Double salary = jdbcRowSet.getDouble("salary");
				BigDecimal bonus = jdbcRowSet.getBigDecimal("bonus");

				System.out.println(empId + "\t" + eName + "\t" + salary + "\t" + email + "\t" + bonus);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(jdbcRowSet.isAfterLast()){
					jdbcRowSet.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("---------------------------------------------------");
	}
}
