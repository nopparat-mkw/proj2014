package com.project.list_student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project.bean.EducationLevelBean;
import com.project.bean.MajorBean;
import com.project.bean.StudentBean;
import com.project.utility.ConnectDB;

public class ListStudentManager {
	
	public List<MajorBean> findAllMajor() throws SQLException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * from major where majorName != 'null'";
		List<MajorBean> listMajor = new ArrayList<MajorBean>();
		try {
			dbConnection = ConnectDB.getInstance().DBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				
				MajorBean majorBean = new MajorBean();
				majorBean.setMajorName(rs.getString("majorName"));
				listMajor.add(majorBean);
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
		return listMajor;
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
		String selectSQL = "SELECT * from educationlevel join major on educationlevel.major_ID = major.major_ID "
						+ "join student on student.EducationLevel_ID = educationlevel.EducationLevel_ID "
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
	
	public boolean removeStudent(String id) throws SQLException {
		boolean chk = true;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String removeStudent = "DELETE FROM student WHERE studentID = ?";
		try {			
			dbConnection = ConnectDB.getInstance().DBConnection();
			preparedStatement = dbConnection.prepareStatement(removeStudent);
			preparedStatement.setString(1, id);			
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			chk = false;
		} finally {
			if (dbConnection != null) {
				dbConnection.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		}
		return chk;
	}
	
}
