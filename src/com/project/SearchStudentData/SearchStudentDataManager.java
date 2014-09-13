package com.project.SearchStudentData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.project.bean.EducationLevelBean;
import com.project.bean.MajorBean;
import com.project.bean.StudentBean;
import com.project.utility.ConnectDB;

public class SearchStudentDataManager {

	public MajorBean findStudentByStudentID(String studentID)
			throws SQLException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "select * from major"
				+ " join educationlevel on major.major_ID = educationlevel.major_ID"
				+ " join student on student.educationLevel_ID = educationlevel.educationLevel_ID"
				+ " where student.studentID = ?";

		MajorBean major = new MajorBean();
		try {
			dbConnection = ConnectDB.getInstance().DBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setString(1, studentID);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				StudentBean student = new StudentBean();
				student.setStudentID(rs.getString("studentID"));
				student.setAntecedent(rs.getString("antecedent"));
				student.setFirstName(rs.getString("firstName"));
				student.setLastName(rs.getString("lastName"));

				EducationLevelBean educationLevel = new EducationLevelBean();
				educationLevel.setEducationalBackground(rs
						.getString("educationalBackground"));
				educationLevel.setEducationLevel(Integer.parseInt(rs
						.getString("educationLevel")));
				educationLevel.setStudent(student);

				major.setMajorName(rs.getString("majorName"));
				major.setEducationLevel(educationLevel);
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
		return major;
	}

	// แสดงจำนวนมาเข้าแถว
	public Integer ListPresent(String studentID) throws SQLException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT  COUNT(attendance.statusActivity) as present from student"
				+ " join attendance on student.studentID = attendance.studentID"
				+ " join schedule on schedule.schedule_ID = attendance.schedule_ID"
				+ " where student.studentID = ?"
				+ " and attendance.statusActivity = 'มา'"
				+ " and term = (SELECT term FROM    schedule WHERE   schedule_ID = (SELECT MAX(schedule_ID)  FROM schedule))";
		Integer present = null;
		try {
			dbConnection = ConnectDB.getInstance().DBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setString(1, studentID);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				present = Integer.parseInt(rs.getString("present"));
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

	// แสดงจำนวนขาดเข้าแถว
	public Integer ListLack(String studentID) throws SQLException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT  COUNT(attendance.statusActivity) as present from student"
				+ " join attendance on student.studentID = attendance.studentID"
				+ " join schedule on schedule.schedule_ID = attendance.schedule_ID"
				+ " where student.studentID = ?"
				+ " and attendance.statusActivity = 'ขาด'"
				+ " and term = (SELECT term FROM    schedule WHERE   schedule_ID = (SELECT MAX(schedule_ID)  FROM schedule))";
		Integer present = null;
		try {
			dbConnection = ConnectDB.getInstance().DBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setString(1, studentID);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				present = Integer.parseInt(rs.getString("present"));
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

	// แสดงจำนวนลาป่วยเข้าแถว
	public Integer ListSickLeave(String studentID) throws SQLException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT  COUNT(attendance.statusActivity) as present from student"
				+ " join attendance on student.studentID = attendance.studentID"
				+ " join schedule on schedule.schedule_ID = attendance.schedule_ID"
				+ " where student.studentID = ?"
				+ " and attendance.statusActivity = 'ลาป่วย'"
				+ " and term = (SELECT term FROM    schedule WHERE   schedule_ID = (SELECT MAX(schedule_ID)  FROM schedule))";
		Integer present = null;
		try {
			dbConnection = ConnectDB.getInstance().DBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setString(1, studentID);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				present = Integer.parseInt(rs.getString("present"));
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

	// แสดงจำนวนลากิจเข้าแถว
	public Integer ListPersonalLeave(String studentID) throws SQLException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT  COUNT(attendance.statusActivity) as present from student"
				+ " join attendance on student.studentID = attendance.studentID"
				+ " join schedule on schedule.schedule_ID = attendance.schedule_ID"
				+ " where student.studentID = ?"
				+ " and attendance.statusActivity = 'ลากิจ'"
				+ " and term = (SELECT term FROM    schedule WHERE   schedule_ID = (SELECT MAX(schedule_ID)  FROM schedule))";
		Integer present = null;
		try {
			dbConnection = ConnectDB.getInstance().DBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setString(1, studentID);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				present = Integer.parseInt(rs.getString("present"));
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

	// แสดงจำนวนมาสายเข้าแถว
	public Integer ListLate(String studentID) throws SQLException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT  COUNT(attendance.statusActivity) as present from student"
				+ " join attendance on student.studentID = attendance.studentID"
				+ " join schedule on schedule.schedule_ID = attendance.schedule_ID"
				+ " where student.studentID = ?"
				+ " and attendance.statusActivity = 'สาย'"
				+ " and term = (SELECT term FROM  schedule WHERE schedule_ID = (SELECT MAX(schedule_ID)  FROM schedule))";
		Integer present = null;
		try {
			dbConnection = ConnectDB.getInstance().DBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setString(1, studentID);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				present = Integer.parseInt(rs.getString("present"));
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
