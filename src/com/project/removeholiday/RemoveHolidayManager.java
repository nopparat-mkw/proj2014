package com.project.removeholiday;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.project.bean.HolidayBean;
import com.project.utility.ConnectDB;

public class RemoveHolidayManager {
	
	public boolean removeHoliday(String date,String month,String year) throws SQLException {
		boolean chk = true;
		Connection conn = null;
		PreparedStatement prep = null;
		String removeRoom = "delete from holiday where date = ? and month = ? and year = ?";
		try {
			conn = ConnectDB.getInstance().DBConnection();
			prep = conn.prepareStatement(removeRoom);

			prep.setString(1, date);
			prep.setString(2, month);
			prep.setString(3, year);
			prep.executeUpdate();
			chk = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			chk = false;
		} finally {
			if (prep != null) {
				prep.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return chk;
	}
}
