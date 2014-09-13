package com.project.edit_teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.project.bean.EducationBean;
import com.project.bean.MajorBean;
import com.project.bean.StudentBean;
import com.project.bean.TeacherBean;
import com.project.utility.ConnectDB;

public class EditTeacherManager {
	
	public TeacherBean findTeacherByIDCard(String idCard) throws SQLException{
		TeacherBean teacher = new TeacherBean();
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "select * from teacher"
					+ " join education on teacher.idCard = education.teacherID"
					+ " join major on major.major_ID = teacher.major_ID"
					+ " where teacher.idCard = ?";
		try {
			dbConnection = ConnectDB.getInstance().DBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setString(1, idCard);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				EducationBean educationBean = new EducationBean();
				educationBean.setEducationalBackground(rs.getString("educationalBackground"));
				educationBean.setEducationalInstitution(rs.getString("educationalInstitution"));
				educationBean.setEducationalMajor(rs.getString("educationalMajor"));
				
				MajorBean majorBean = new MajorBean();
				majorBean.setMajorName(rs.getString("majorName"));				
				
				teacher.setIdCard(rs.getString("idCard"));
				teacher.setAntecedent(rs.getString("antecedent"));
				teacher.setFirstName(rs.getString("firstName"));
				teacher.setLastName(rs.getString("lastName"));
				teacher.setVacancy(rs.getString("vacancy"));
				teacher.setEducation(educationBean);
				teacher.setMajor(majorBean);
				
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
	
	public boolean updateDataTeacher(TeacherBean teacher,String departmentTeacher) throws SQLException {
		Connection con = null;
		PreparedStatement prep = null;
		String update_data = "update teacher"
			+ " join education on teacher.idCard = education.teacherID"
			+ " set teacher.antecedent = ? "
			+ " ,teacher.firstName = ? " 
			+ " ,teacher.lastName = ? " 
			+ " ,teacher.vacancy = ? "
			+ " ,teacher.path_image = ? "
			+ " ,teacher.major_ID = (select major_ID from major where major.majorName = ? ) "
			+ " ,education.educationalBackground = ? "
			+ " ,education.educationalInstitution = ? "
			+ " ,education.educationalMajor = ? "			
			+ " where teacher.idCard = ? ";
		try {
			con = ConnectDB.getInstance().DBConnection();
			prep = con.prepareStatement(update_data);
			//teacher
			prep.setString(1, teacher.getAntecedent());
			prep.setString(2, teacher.getFirstName());
			prep.setString(3, teacher.getLastName());
			prep.setString(4, teacher.getVacancy());
			prep.setString(5, teacher.getPath_image());
			prep.setString(6, departmentTeacher);
			
			//education
			prep.setString(7, teacher.getEducation().getEducationalInstitution());
			prep.setString(8, teacher.getEducation().getEducationalBackground());
			prep.setString(9, teacher.getEducation().getEducationalMajor());
			
			prep.setString(10, teacher.getIdCard());
			
			prep.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (prep != null) {
				prep.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return false;
	}

}
