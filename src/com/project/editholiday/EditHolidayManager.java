package com.project.editholiday;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.project.bean.HolidayBean;
import com.project.utility.ConnectDB;

public class EditHolidayManager {
	
	public HolidayBean findHolidayByDateMonthYear(String date,String month,String year) throws SQLException {
		HolidayBean holiday = new HolidayBean();
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * from holiday where date = ? and month = ? and year = ?";
		try {
			dbConnection = ConnectDB.getInstance().DBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setString(1, date);
			preparedStatement.setString(2, month);
			preparedStatement.setString(3, year);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				holiday.setDate(Integer.parseInt(rs.getString("date")));
				holiday.setMonth(Integer.parseInt(rs.getString("month")));
				holiday.setYear(Integer.parseInt(rs.getString("year")));
				holiday.setDetail(rs.getString("detail"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return holiday;
	}
	
	public boolean editHoliday(HolidayBean holiday) throws SQLException {
		boolean chk = true;
		Connection conn = null;
		PreparedStatement prep = null;
		String editRoom = "Update holiday set detail = ? where date = ? and month = ? and year = ?";
		try {
			conn = ConnectDB.getInstance().DBConnection();;
			prep = conn.prepareStatement(editRoom);

			prep.setString(1, holiday.getDetail());
			prep.setInt(2, holiday.getDate());
			prep.setInt(3, holiday.getMonth());
			prep.setInt(4, holiday.getYear());
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
