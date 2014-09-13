package com.project.register_teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.project.bean.TeacherBean;
import com.project.utility.ConnectDB;

public class RegisterTeacherManager {
	
	public int findMajorID(String majorName) throws SQLException {
		int MajorID = 0;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "select Major_ID from major where majorName = ?";
		try {
			dbConnection = ConnectDB.getInstance().DBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setString(1, majorName);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				MajorID = Integer.parseInt(rs.getString("major_ID"));
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
		return MajorID;
	}
				
	public boolean addTeacher(TeacherBean teacher,int MajorID) throws SQLException {
		boolean chk = true;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String addSQL = "insert into Teacher (idCard,antecedent,firstName,lastName,vacancy,email,phone,major_ID,path_image) VALUES (?,?,?,?,?,?,?,?,?)";
		try {
			dbConnection = ConnectDB.getInstance().DBConnection();
			preparedStatement = dbConnection.prepareStatement(addSQL);
			preparedStatement.setString(1, teacher.getIdCard());
			preparedStatement.setString(2, teacher.getAntecedent());
			preparedStatement.setString(3, teacher.getFirstName());
			preparedStatement.setString(4, teacher.getLastName());
			preparedStatement.setString(5, teacher.getVacancy());
			preparedStatement.setString(6, teacher.getEmail());
			preparedStatement.setString(7, teacher.getPhone());
			preparedStatement.setInt(8, MajorID);
			preparedStatement.setString(9, teacher.getPath_image());
			preparedStatement.executeUpdate();
				String addLogin = "insert into login (username,password,status,teacherID) VALUES (?,?,?,?)";
				preparedStatement = dbConnection.prepareStatement(addLogin);
				preparedStatement.setString(1, teacher.getLogin().getUsername());
				preparedStatement.setString(2, teacher.getLogin().getPassword());
				preparedStatement.setString(3, "Teacher");
				preparedStatement.setString(4, teacher.getIdCard());
				preparedStatement.executeUpdate();
					String addEducation = "insert into education (educationalInstitution,educationalBackground,educationalMajor,teacherID) VALUES (?,?,?,?)";
					preparedStatement = dbConnection.prepareStatement(addEducation);
					preparedStatement.setString(1, teacher.getEducation().getEducationalInstitution());
					preparedStatement.setString(2, teacher.getEducation().getEducationalBackground());
					preparedStatement.setString(3, teacher.getEducation().getEducationalMajor());
					preparedStatement.setString(4, teacher.getIdCard());
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
	
}
