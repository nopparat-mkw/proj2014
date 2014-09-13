package com.project.view_all_attendance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.utility.ConnectDB;

public class ViewAllAttendanceManager {

	public List<String> findAllTerm(String majorName, String educationalBackground, int educationLevel)	throws SQLException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "select DISTINCT term from schedule"
				+ " join attendance on schedule.Schedule_ID = attendance.schedule_ID"
				+ " join student on student.studentID = attendance.studentID"
				+ " join educationLevel on student.educationLevel_ID = educationLevel.educationLevel_ID"
				+ " join major on major.major_ID = educationLevel.major_ID"
				+ " where educationLevel.educationalBackground = ? "
				+ " and educationLevel.educationLevel = ?"
				+ " and major.majorName = ?";
		List<String> term = new ArrayList<String>();
		try {
			dbConnection = ConnectDB.getInstance().DBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setString(1, educationalBackground);
			preparedStatement.setInt(2, educationLevel);
			preparedStatement.setString(3, majorName);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				term.add(rs.getString("term"));
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
		return term;
	}
	
	public List<String> ListDateSchedule(String majorName, String educationalBackground, int educationLevel,String term)	throws SQLException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "select DISTINCT schedule.dateAttendance from schedule"
				+ " join attendance on schedule.Schedule_ID = attendance.schedule_ID"
				+ " join student on student.studentID = attendance.studentID"
				+ " join educationLevel on student.educationLevel_ID = educationLevel.educationLevel_ID"
				+ " join major on major.major_ID = educationLevel.major_ID"
				+ " where educationLevel.educationalBackground = ? "
				+ " and educationLevel.educationLevel = ?"
				+ " and major.majorName = ?"
				+ " and schedule.term = ?";
		List<String> dateSchedule = new ArrayList<String>();
		try {
			dbConnection = ConnectDB.getInstance().DBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setString(1, educationalBackground);
			preparedStatement.setInt(2, educationLevel);
			preparedStatement.setString(3, majorName);
			preparedStatement.setString(4, term);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				dateSchedule.add(rs.getString("dateAttendance"));
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
		return dateSchedule;
	}
	
	// แสดงจำนวนมาเข้าแถว
	public List<Integer> ListPresent(String majorName, String educationalBackground, int educationLevel,List listDateSchedule)	throws SQLException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "select COUNT(attendance.statusActivity) as present from schedule"
				+ " join attendance on schedule.schedule_ID = attendance.schedule_ID"
				+ " join student on student.studentID = attendance.studentID"
				+ " join educationLevel on student.educationLevel_ID = educationLevel.educationLevel_ID"
				+ " join major on major.major_ID = educationLevel.major_ID"
				+ " where educationLevel.educationalBackground = ? "
				+ " and educationLevel.educationLevel = ?"
				+ " and major.majorName = ?"
				+ " and schedule.dateAttendance = ?"
				+ " and attendance.statusActivity = 'มา'";
		List<Integer> present = new ArrayList<Integer>();
		try {
			for (int i = 0; i < listDateSchedule.size(); i++) {
				dbConnection = ConnectDB.getInstance().DBConnection();
				preparedStatement = dbConnection.prepareStatement(selectSQL);
				preparedStatement.setString(1, educationalBackground);
				preparedStatement.setInt(2, educationLevel);
				preparedStatement.setString(3, majorName);
				preparedStatement.setString(4, listDateSchedule.get(i).toString());
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					present.add(Integer.parseInt(rs.getString("present")));
				}
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
		return present;
	}


	//แสดงจำนวนขาดเข้าแถว
	public List<Integer> ListLack(String majorName, String educationalBackground, int educationLevel,List listDateSchedule)	throws SQLException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "select COUNT(attendance.statusActivity) as present from schedule"
				+ " join attendance on schedule.Schedule_ID = attendance.schedule_ID"
				+ " join student on student.studentID = attendance.studentID"
				+ " join educationLevel on student.educationLevel_ID = educationLevel.educationLevel_ID"
				+ " join major on major.major_ID = educationLevel.major_ID"
				+ " where educationLevel.educationalBackground = ? "
				+ " and educationLevel.educationLevel = ?"
				+ " and major.majorName = ?"
				+ " and schedule.dateAttendance = ?"
				+ " and attendance.statusActivity = 'ขาด'";
		List<Integer> present = new ArrayList<Integer>();
		try {
			for (int i = 0; i < listDateSchedule.size(); i++) {
				dbConnection = ConnectDB.getInstance().DBConnection();
				preparedStatement = dbConnection.prepareStatement(selectSQL);
				preparedStatement.setString(1, educationalBackground);
				preparedStatement.setInt(2, educationLevel);
				preparedStatement.setString(3, majorName);
				preparedStatement.setString(4, listDateSchedule.get(i).toString());
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					present.add(Integer.parseInt(rs.getString("present")));
				}
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
		return present;
	}

	
	//แสดงจำนวนลาป่วยเข้าแถว
		public List<Integer> ListSickLeave(String majorName, String educationalBackground, int educationLevel,List listDateSchedule)	throws SQLException {
			Connection dbConnection = null;
			PreparedStatement preparedStatement = null;
			String selectSQL = "select COUNT(attendance.statusActivity) as present from schedule"
					+ " join attendance on schedule.Schedule_ID = attendance.schedule_ID"
					+ " join student on student.studentID = attendance.studentID"
					+ " join educationLevel on student.educationLevel_ID = educationLevel.educationLevel_ID"
					+ " join major on major.major_ID = educationLevel.major_ID"
					+ " where educationLevel.educationalBackground = ? "
					+ " and educationLevel.educationLevel = ?"
					+ " and major.majorName = ?"
					+ " and schedule.dateAttendance = ?"
					+ " and attendance.statusActivity = 'ลาป่วย'";
			List<Integer> present = new ArrayList<Integer>();
			try {
				for (int i = 0; i < listDateSchedule.size(); i++) {
					dbConnection = ConnectDB.getInstance().DBConnection();
					preparedStatement = dbConnection.prepareStatement(selectSQL);
					preparedStatement.setString(1, educationalBackground);
					preparedStatement.setInt(2, educationLevel);
					preparedStatement.setString(3, majorName);
					preparedStatement.setString(4, listDateSchedule.get(i).toString());
					ResultSet rs = preparedStatement.executeQuery();
					while (rs.next()) {
						present.add(Integer.parseInt(rs.getString("present")));
					}
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
			return present;
		}
		
		//แสดงจำนวนลากิจเข้าแถว
				public List<Integer> ListPersonalLeave(String majorName, String educationalBackground, int educationLevel,List listDateSchedule)	throws SQLException {
					Connection dbConnection = null;
					PreparedStatement preparedStatement = null;
					String selectSQL = "select COUNT(attendance.statusActivity) as present from schedule"
							+ " join attendance on schedule.Schedule_ID = attendance.schedule_ID"
							+ " join student on student.studentID = attendance.studentID"
							+ " join educationLevel on student.educationLevel_ID = educationLevel.educationLevel_ID"
							+ " join major on major.major_ID = educationLevel.major_ID"
							+ " where educationLevel.educationalBackground = ? "
							+ " and educationLevel.educationLevel = ?"
							+ " and major.majorName = ?"
							+ " and schedule.dateAttendance = ?"
							+ " and attendance.statusActivity = 'ลากิจ'";
					List<Integer> present = new ArrayList<Integer>();
					try {
						for (int i = 0; i < listDateSchedule.size(); i++) {
							dbConnection = ConnectDB.getInstance().DBConnection();
							preparedStatement = dbConnection.prepareStatement(selectSQL);
							preparedStatement.setString(1, educationalBackground);
							preparedStatement.setInt(2, educationLevel);
							preparedStatement.setString(3, majorName);
							preparedStatement.setString(4, listDateSchedule.get(i).toString());
							ResultSet rs = preparedStatement.executeQuery();
							while (rs.next()) {
								present.add(Integer.parseInt(rs.getString("present")));
							}
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
					return present;
				}
				
				//แสดงจำนวนมาสายเข้าแถว
				public List<Integer> ListLate(String majorName, String educationalBackground, int educationLevel,List listDateSchedule)	throws SQLException {
					Connection dbConnection = null;
					PreparedStatement preparedStatement = null;
					String selectSQL = "select COUNT(attendance.statusActivity) as present from schedule"
							+ " join attendance on schedule.Schedule_ID = attendance.schedule_ID"
							+ " join student on student.studentID = attendance.studentID"
							+ " join educationLevel on student.educationLevel_ID = educationLevel.educationLevel_ID"
							+ " join major on major.major_ID = educationLevel.major_ID"
							+ " where educationLevel.educationalBackground = ? "
							+ " and educationLevel.educationLevel = ?"
							+ " and major.majorName = ?"
							+ " and schedule.dateAttendance = ?"
							+ " and attendance.statusActivity = 'สาย'";
					List<Integer> present = new ArrayList<Integer>();
					try {
						for (int i = 0; i < listDateSchedule.size(); i++) {
							dbConnection = ConnectDB.getInstance().DBConnection();
							preparedStatement = dbConnection.prepareStatement(selectSQL);
							preparedStatement.setString(1, educationalBackground);
							preparedStatement.setInt(2, educationLevel);
							preparedStatement.setString(3, majorName);
							preparedStatement.setString(4, listDateSchedule.get(i).toString());
							ResultSet rs = preparedStatement.executeQuery();
							while (rs.next()) {
								present.add(Integer.parseInt(rs.getString("present")));
							}
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
					return present;
				}
}
