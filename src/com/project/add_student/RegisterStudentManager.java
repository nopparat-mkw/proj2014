package com.project.add_student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.bean.AddressBean;
import com.project.bean.EducationLevelBean;
import com.project.bean.MajorBean;
import com.project.bean.ParentBean;
import com.project.bean.StudentBean;
import com.project.bean.TeacherBean;
import com.project.utility.ConnectDB;

public class RegisterStudentManager {
	public List<MajorBean> findAllMajor() throws SQLException {
		List<MajorBean> listMajor = new ArrayList<MajorBean>();
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "select majorName from major where major_ID != '15'";
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
	
	public int findIdEducationLevel(MajorBean major) throws SQLException {
		int EducationLevel_ID = 0;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "select educationLevel_ID from educationlevel"
				+ " join major on major.major_ID = educationlevel.major_ID"
				+ " where educationlevel.educationalBackground = ?"
				+ " and educationlevel.educationLevel = ?"
				+ " and major.majorName = ?";
		try {
			dbConnection = ConnectDB.getInstance().DBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setString(1, major.getEducationLevel().getEducationalBackground());
			preparedStatement.setInt(2, major.getEducationLevel().getEducationLevel());
			preparedStatement.setString(3, major.getMajorName());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				EducationLevel_ID = Integer.parseInt(rs.getString("educationLevel_ID"));
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
		return EducationLevel_ID;
	}
	
	public boolean addStudentAndParent(StudentBean student,int idEducationLevel) throws SQLException {
		boolean chk = true;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String addStudent = "INSERT INTO student values (?,?,?,?,?)";
		try {
			dbConnection = ConnectDB.getInstance().DBConnection();
			preparedStatement = dbConnection.prepareStatement(addStudent);
			preparedStatement.setString(1, student.getStudentID());
			preparedStatement.setString(2, student.getAntecedent());
			preparedStatement.setString(3, student.getFirstName());
			preparedStatement.setString(4, student.getLastName());
			preparedStatement.setInt(5, idEducationLevel);
			preparedStatement.executeUpdate();
				String addParent = "insert into parent (antecedent_parent,firstName_parent,lastName_parent,studentID) VALUES (?,?,?,?)";
				preparedStatement = dbConnection.prepareStatement(addParent);
				preparedStatement.setString(1, student.getParent().getAntecedent());
				preparedStatement.setString(2, student.getParent().getFirstName());
				preparedStatement.setString(3, student.getParent().getLastName());
				preparedStatement.setString(4, student.getStudentID());
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
	
	public int findParentID(StudentBean student) throws SQLException {
		int Parent_ID = 0;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "select parent_ID from parent"
				+ " join student on student.studentID = parent.studentID"
				+ " where student.studentID = ?";
		try {
			dbConnection = ConnectDB.getInstance().DBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setString(1, student.getStudentID());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Parent_ID = Integer.parseInt(rs.getString("parent_ID"));
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
		return Parent_ID;
	}
	
	public boolean addAddressByParentID(AddressBean address,int idParent) throws SQLException {
		boolean chk = true;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String addStudent = "INSERT INTO address (addNo,moo,street,subDistrict,district,province,zipCode,parent_ID) values (?,?,?,?,?,?,?,?)";
		try {
			dbConnection = ConnectDB.getInstance().DBConnection();
			preparedStatement = dbConnection.prepareStatement(addStudent);
			preparedStatement.setString(1, address.getAddNo());
			preparedStatement.setString(2, address.getMoo());
			preparedStatement.setString(3, address.getStreet());
			preparedStatement.setString(4, address.getSubDistrict());
			preparedStatement.setString(5, address.getDistrict());
			preparedStatement.setString(6, address.getProvince());
			preparedStatement.setString(7, address.getZipCode());
			preparedStatement.setInt(8, idParent);
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
