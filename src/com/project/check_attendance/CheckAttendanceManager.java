package com.project.check_attendance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import com.project.bean.ScheduleBean;
import com.project.utility.ConnectDB;

public class CheckAttendanceManager {
	
	public boolean addAttendance(ScheduleBean schedule,int Schedule_ID) throws SQLException {
		boolean chk = true;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String addSQL = "insert into attendance (statusActivity,schedule_ID,studentID) VALUES (?,?,?)";
		try {
			dbConnection = ConnectDB.getInstance().DBConnection();
			preparedStatement = dbConnection.prepareStatement(addSQL);
			preparedStatement.setString(1, schedule.getAttendance().getStatusActivity());
			preparedStatement.setInt(2, Schedule_ID);
			preparedStatement.setString(3, schedule.getAttendance().getStudent().getStudentID());
			preparedStatement.executeUpdate();
		   
		} catch (Exception e) {
			System.out.println(e.getMessage());
			chk = false;
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return chk;
	}
	
	public boolean addSchedule(ScheduleBean schedule) throws SQLException {
		boolean chk = true;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String addSQL = "insert into schedule (dateAttendance,term) VALUES (?,?)";
		try {		
			dbConnection = ConnectDB.getInstance().DBConnection();
			preparedStatement = dbConnection.prepareStatement(addSQL);							        
			java.sql.Date sqlCreateDate = new java.sql.Date(schedule.getDateAttendance().getTime());
			preparedStatement.setDate(1, sqlCreateDate);			
			preparedStatement.setString(2, schedule.getTerm());
			preparedStatement.executeUpdate();
		   
		} catch (Exception e) {
			System.out.println(e.getMessage());
			chk = false;
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return chk;
	}
	
	public int findSchedule(Date date, String term) throws SQLException {
		int Schedule_ID = 0;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "select schedule_ID from schedule where dateAttendance = ? and term = ?";
		try {
			dbConnection = ConnectDB.getInstance().DBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			java.sql.Date sqlCreateDate = new java.sql.Date(date.getTime());
			preparedStatement.setDate(1, sqlCreateDate);
			preparedStatement.setString(2, term);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Schedule_ID = Integer.parseInt(rs.getString("schedule_ID"));
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
		return Schedule_ID;
	}
	
}
