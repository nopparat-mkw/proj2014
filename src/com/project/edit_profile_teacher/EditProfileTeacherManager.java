package com.project.edit_profile_teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.project.bean.AddressBean;
import com.project.bean.LoginBean;
import com.project.bean.ParentBean;
import com.project.bean.StudentBean;
import com.project.bean.TeacherBean;
import com.project.utility.ConnectDB;


public class EditProfileTeacherManager {
	
	public TeacherBean findTeacherByIdCard(String idCard)
			throws SQLException {
		
		TeacherBean teacher = new TeacherBean();	
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "select * from teacher where teacher.idCard = ?";
		try {
			dbConnection = ConnectDB.getInstance().DBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setString(1, idCard);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {								
				teacher.setIdCard(rs.getString("idCard"));
				teacher.setAntecedent(rs.getString("antecedent"));
				teacher.setFirstName(rs.getString("firstName"));
				teacher.setLastName(rs.getString("lastName"));
				teacher.setEmail(rs.getString("email"));
				teacher.setPhone(rs.getString("phone"));			
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
		return teacher;
	}
	
	
	public boolean updateProfileTeacher(TeacherBean teacherBean) throws SQLException {
		boolean chk = true;
		String sql = "update teacher set antecedent=?,firstName=?,lastName=?,email=?,phone=? where idCard=?";
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		try {
			dbConnection = ConnectDB.getInstance().DBConnection();
			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setString(1, teacherBean.getAntecedent());
			preparedStatement.setString(2, teacherBean.getFirstName());
			preparedStatement.setString(3, teacherBean.getLastName());
			preparedStatement.setString(4, teacherBean.getEmail());
			preparedStatement.setString(5, teacherBean.getPhone());   
			preparedStatement.setString(6, teacherBean.getIdCard()); 
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
