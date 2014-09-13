package com.project.addholiday;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import com.project.bean.HolidayBean;
import com.project.utility.ConnectDB;

public class AddHolidayManager {
	
	public boolean addHoliday(HolidayBean holiday) throws SQLException {
		boolean chk = true;
		Connection conn = null;
		Statement stmt = null; // statement object
		String addSQL = "insert into holiday (date,month,year,detail) VALUES ('"+holiday.getDate()+"'"
				+",'"+holiday.getMonth()+"'"
				+",'"+holiday.getYear()+"'"
				+",'"+holiday.getDetail()+"')";
		try {
			conn = ConnectDB.getInstance().DBConnection();
		    stmt = conn.createStatement();
		    conn.setAutoCommit(false);
		    stmt.executeUpdate(addSQL);
		    conn.commit();
		    conn.setAutoCommit(true); 
		} catch (Exception e) {
			System.out.println(e.getMessage());
			chk = false;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return chk;
	}
}
