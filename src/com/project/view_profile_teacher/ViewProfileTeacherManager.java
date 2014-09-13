package com.project.view_profile_teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.bean.EducationBean;
import com.project.bean.MajorBean;
import com.project.bean.TeacherBean;
import com.project.utility.ConnectDB;

public class ViewProfileTeacherManager {
	
	public List<TeacherBean> findProfileTeacherByUsername(String username) throws SQLException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "select * from login join teacher on login.teacherID = teacher.idCard join major on major.major_ID = teacher.major_ID where login.username ='"+username+"'";
		List<TeacherBean> listTeacher = new ArrayList<TeacherBean>();
		try {
			dbConnection = ConnectDB.getInstance().DBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				MajorBean majorBean = new MajorBean();
				majorBean.setMajorName(rs.getString("majorName"));
				
				TeacherBean teacherBean = new TeacherBean();
				teacherBean.setIdCard(rs.getString("idCard"));
				teacherBean.setAntecedent(rs.getString("antecedent"));
				teacherBean.setFirstName(rs.getString("firstName"));
				teacherBean.setLastName(rs.getString("lastName"));
				teacherBean.setEmail(rs.getString("email"));
				teacherBean.setPhone(rs.getString("phone"));
				teacherBean.setPath_image(rs.getString("path_image"));
				teacherBean.setMajor(majorBean);
				
				String selectEducation = "SELECT * FROM teacher join education on teacher.idCard = education.teacherID where teacher.idCard ='"+teacherBean.getIdCard()+"'";
				preparedStatement = dbConnection.prepareStatement(selectEducation);
				ResultSet res = preparedStatement.executeQuery();
				while (res.next()) {
					EducationBean educationBean = new EducationBean();
					educationBean.setEducationalInstitution(res.getString("educationalInstitution"));
					educationBean.setEducationalBackground(res.getString("educationalBackground"));
					educationBean.setEducationalMajor(res.getString("educationalMajor"));
					teacherBean.addMajorVacancy(educationBean);
				}
				
				listTeacher.add(teacherBean);
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
	
}
