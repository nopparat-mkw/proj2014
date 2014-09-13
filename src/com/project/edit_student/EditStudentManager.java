package com.project.edit_student;

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
import com.project.utility.ConnectDB;

public class EditStudentManager {

	public StudentBean findStudentByStudentID(String studentID)
			throws SQLException {
		StudentBean student = new StudentBean();	
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "select * from student join parent on student.studentID = parent.studentID"
				+ " join address on parent.Parent_ID = address.Parent_ID"
				+ " where student.studentID = ?";
		try {
			dbConnection = ConnectDB.getInstance().DBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setString(1, studentID);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				AddressBean address = new AddressBean();
				address.setAddNo(rs.getString("addNo"));
				address.setMoo(rs.getString("moo"));
				address.setStreet(rs.getString("street"));
				address.setSubDistrict(rs.getString("subDistrict"));
				address.setDistrict(rs.getString("district"));
				address.setProvince(rs.getString("province"));
				address.setZipCode(rs.getString("zipCode"));
				
				ParentBean parent = new ParentBean();
				parent.setAntecedent(rs.getString("antecedent_parent"));
				parent.setFirstName(rs.getString("firstName_parent"));
				parent.setLastName(rs.getString("lastName_parent"));
				parent.setAddress(address);
				
				student.setStudentID(rs.getString("studentID"));
				student.setAntecedent(rs.getString("antecedent"));
				student.setFirstName(rs.getString("firstName"));
				student.setLastName(rs.getString("lastName"));

				
				student.setParent(parent);
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
		return student;
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
	
	public boolean updateDataStudent(MajorBean major) throws SQLException {
		Connection con = null;
		PreparedStatement prep = null;
		String update_data = "UPDATE student "
			+ " INNER JOIN parent"
			+ " ON student.studentID = parent.studentID"
			+ " INNER JOIN address"
			+ " ON parent.Parent_ID = address.Parent_ID"
			+ " SET student.antecedent = ? "
			+ " , student.firstName = ? "
			+ " , student.lastName = ? "
			+ " , parent.antecedent_parent = ? "
			+ " , parent.firstName_parent = ? "
			+ " , parent.lastName_parent = ? "
			+ " , address.addNo = ? "
			+ " , address.moo = ? "
			+ " , address.street = ? "
			+ " , address.subDistrict = ? "
			+ " , address.district = ? "
			+ " , address.province = ? "
			+ " , address.zipCode = ? "
			+ " , student.educationLevel_ID = (SELECT educationlevel.EducationLevel_ID FROM educationlevel "
				+"JOIN major ON major.Major_ID = educationlevel.Major_ID "
				+"WHERE educationLevel.educationalBackground = ? "
				+"AND educationLevel.educationLevel = ? "
				+"AND major.majorName = ?) "
			+ " WHERE student.studentID = ? ";
		try {
			con = ConnectDB.getInstance().DBConnection();
			prep = con.prepareStatement(update_data);
			//student
			prep.setString(1, major.getEducationLevel().getStudent().getAntecedent());
			prep.setString(2, major.getEducationLevel().getStudent().getFirstName());
			prep.setString(3, major.getEducationLevel().getStudent().getLastName());
			//parent
			prep.setString(4, major.getEducationLevel().getStudent().getParent().getAntecedent());
			prep.setString(5, major.getEducationLevel().getStudent().getParent().getFirstName());
			prep.setString(6, major.getEducationLevel().getStudent().getParent().getLastName());
			//address
			prep.setString(7, major.getEducationLevel().getStudent().getParent().getAddress().getAddNo());
			prep.setString(8, major.getEducationLevel().getStudent().getParent().getAddress().getMoo());
			prep.setString(9, major.getEducationLevel().getStudent().getParent().getAddress().getStreet());
			prep.setString(10, major.getEducationLevel().getStudent().getParent().getAddress().getSubDistrict());
			prep.setString(11, major.getEducationLevel().getStudent().getParent().getAddress().getDistrict());
			prep.setString(12, major.getEducationLevel().getStudent().getParent().getAddress().getProvince());
			prep.setString(13, major.getEducationLevel().getStudent().getParent().getAddress().getZipCode());
			
			//education
			prep.setString(14, major.getEducationLevel().getEducationalBackground());
			prep.setInt(15, major.getEducationLevel().getEducationLevel());
			prep.setString(16, major.getMajorName());
			
			prep.setString(17, major.getEducationLevel().getStudent().getStudentID());
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
