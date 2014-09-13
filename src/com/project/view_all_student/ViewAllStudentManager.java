package com.project.view_all_student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.bean.EducationLevelBean;
import com.project.bean.MajorBean;
import com.project.bean.StudentBean;
import com.project.utility.ConnectDB;

public class ViewAllStudentManager {

	public MajorBean findAllMajor(String username) throws SQLException {
		MajorBean majorBean = new MajorBean();
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "select * from login join teacher on login.teacherID = teacher.idCard"
				+ " join major on major.major_ID = teacher.major_ID"
				+ " where login.username = ?";
		try {
			dbConnection = ConnectDB.getInstance().DBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				majorBean.setMajorName(rs.getString("majorName"));
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
		return majorBean;
	}
	
	public List<MajorBean> findAllEducationLevelByMajorName(String majorName) throws SQLException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * from educationlevel inner join major on educationlevel.major_ID = major.major_ID where major.majorName = ? ORDER BY educationalBackground,educationLevel";
		List<MajorBean> listEducationLevel = new ArrayList<MajorBean>();
		try {
			dbConnection = ConnectDB.getInstance().DBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setString(1, majorName);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				EducationLevelBean educationLevelBean = new EducationLevelBean();
				educationLevelBean.setEducationalBackground(rs.getString("educationalBackground"));
				educationLevelBean.setEducationLevel(Integer.parseInt(rs.getString("educationLevel")));
				
				MajorBean major = new MajorBean();
				major.setMajorName(rs.getString("majorName"));
				major.setEducationLevel(educationLevelBean);
				listEducationLevel.add(major);
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
		return listEducationLevel;
	}	
	
	public List<StudentBean> findAllStudent(String majorName,String educationalBackground,int educationLevel) throws SQLException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * from educationlevel join major on educationlevel.Major_ID = major.Major_ID "
						+ "join student on student.educationLevel_ID = educationlevel.educationLevel_ID "
						+ "where educationlevel.educationalBackground = ? "
						+ "and educationlevel.educationLevel= ? "
						+ "and major.majorName = ? ORDER BY educationalBackground,educationLevel";
		
		List<StudentBean> listStudent = new ArrayList<StudentBean>();
		try {
			dbConnection = ConnectDB.getInstance().DBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setString(1, educationalBackground);
			preparedStatement.setInt(2, educationLevel);
			preparedStatement.setString(3, majorName);			
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				StudentBean student = new StudentBean();
				student.setStudentID(rs.getString("studentID"));
				student.setAntecedent(rs.getString("antecedent"));
				student.setFirstName(rs.getString("firstName"));
				student.setLastName(rs.getString("lastName"));
				listStudent.add(student);
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
		return listStudent;
	}

}
