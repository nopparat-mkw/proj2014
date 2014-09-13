package com.project.list_teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.bean.MajorBean;
import com.project.bean.TeacherBean;
import com.project.utility.ConnectDB;

public class ListTeacherManager {
	public List<TeacherBean> findAllTeacher() throws SQLException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * from teacher inner join major on teacher.major_ID = major.major_ID where major.major_ID != 15 order by major.major_ID";
		List<TeacherBean> listTeacher = new ArrayList<TeacherBean>();
		try {
			dbConnection = ConnectDB.getInstance().DBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				
				TeacherBean teacher = new TeacherBean();
				teacher.setIdCard(rs.getString("idCard"));
				teacher.setAntecedent(rs.getString("antecedent"));
				teacher.setFirstName(rs.getString("firstName"));
				teacher.setLastName(rs.getString("lastName"));
				teacher.setVacancy(rs.getString("vacancy"));
				
				MajorBean major = new MajorBean();
				major.setMajorName(rs.getString("majorName"));
				
				teacher.setEmail(rs.getString("email"));
				teacher.setPhone(rs.getString("phone"));
				teacher.setMajor(major);
				listTeacher.add(teacher);
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
		return listTeacher;
	}
	
	public boolean removeTeacher(String id) throws SQLException {
		boolean chk = true;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String removeStudent = "DELETE FROM teacher WHERE idCard = ?";
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
