package com.project.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.project.bean.LoginBean;
import com.project.utility.ConnectDB;

public class LoginManager {
	
	public LoginBean vertifyLogin(LoginBean login) {
		LoginBean loginBean = null ;
		try {
			loginBean = findByUsername(login.getUsername(),login.getPassword());
			return loginBean;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return loginBean;
	}

	public LoginBean findByUsername(String username,String password) throws SQLException {
		LoginBean loginBean = null;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String selectSQL = "SELECT * from login inner join teacher on login.teacherID = teacher.idCard  where username = '"
				+ username + "' and password = '"+password+"'";
		try {
			dbConnection = ConnectDB.getInstance().DBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				LoginBean login = new LoginBean();
				login.setUsername(rs.getString("username"));
				login.setPassword(rs.getString("password"));
				login.setStatus(rs.getString("status"));	
								
				loginBean = login;			
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if(rs != null){
				rs.close();
			}
			if (preparedStatement != null) {				
				preparedStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return loginBean;
	}
}
