package com.project.import_student;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.project.bean.AddressBean;
import com.project.bean.EducationLevelBean;
import com.project.bean.MajorBean;
import com.project.bean.ParentBean;
import com.project.bean.StudentBean;
import com.project.utility.ConnectDB;

public class ImportStudentManager {
	public Vector<MajorBean> readCSV(String filename) {
		MajorBean major = null;
		EducationLevelBean eduLevel = null;
		StudentBean student = null;
		ParentBean parent = null;
		AddressBean address = null;		
				
		Vector<MajorBean> allStudent = new Vector<MajorBean>();
		try {
			FileInputStream file = new FileInputStream(new File(filename));

			XSSFWorkbook workbook = new XSSFWorkbook(file);

			XSSFSheet sheet = workbook.getSheetAt(0);		

			Iterator<Row> rowIterator = sheet.iterator();
			rowIterator.next();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				int count = 0;
				System.out.println("");
				Iterator<Cell> cellIterator = row.cellIterator();
				major = new MajorBean();
				eduLevel = new EducationLevelBean();
				student = new StudentBean();
				parent = new ParentBean();
				address = new AddressBean();
				
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					count += 1;
					switch (cell.getCellType()) {

					case Cell.CELL_TYPE_NUMERIC:
						if (count == 3) {
							eduLevel.setEducationLevel((int)cell.getNumericCellValue());
						break;
					}
					case Cell.CELL_TYPE_STRING:
					
						if (count == 1) {
							major.setMajorName(cell.getStringCellValue());
							break;
						} else if (count == 2) {
							eduLevel.setEducationalBackground(cell.getStringCellValue());
							break;
						} else if (count == 4) {							
							student.setStudentID(cell.getStringCellValue());						
							break;
						}else if (count == 5){
							student.setAntecedent(cell.getStringCellValue());						
							break;
						}else if(count == 6){
							student.setFirstName(cell.getStringCellValue());
							break;
						}else if(count == 7){
							student.setLastName(cell.getStringCellValue());
							break;
						}else if(count == 8){
							parent.setAntecedent(cell.getStringCellValue());
							break;
						}else if(count == 9){
							parent.setFirstName(cell.getStringCellValue());
							break;
						}else if(count == 10){
							parent.setLastName(cell.getStringCellValue());
							break;
						}else if(count == 11){
							address.setAddNo(cell.getStringCellValue());
							break;
						}else if(count == 12){
							address.setMoo(cell.getStringCellValue());
							break;
						}else if(count == 13){
							address.setStreet(cell.getStringCellValue());
							break;
						}else if(count == 14){
							address.setSubDistrict(cell.getStringCellValue());
							break;
						}else if(count == 15){
							address.setDistrict(cell.getStringCellValue());
							break;
						}else if(count == 16){
							address.setProvince(cell.getStringCellValue());
							break;
						}else if(count == 17){
							address.setZipCode(cell.getStringCellValue());
							break;
						}
						break;
					}
				}
				parent.setAddress(address);
				student.setParent(parent);
				eduLevel.setStudent(student);
				major.setEducationLevel(eduLevel);				
				
				allStudent.add(major);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allStudent;
	}
	
	public int findMajorIdByMajorName(String majorName) throws SQLException {
		int majorID = 0;
		Connection con = null;
		PreparedStatement prep = null;
		String select_major_ID = "select major_ID from major where majorName = ?";
		try {
			con = ConnectDB.getInstance().DBConnection();
			prep = con.prepareStatement(select_major_ID);
			prep.setString(1, majorName);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				majorID = rs.getInt("major_ID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (prep != null) {
				prep.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return majorID;
	}
	
	public int findEducationLevel(String educationalBackground,int educationLevel,int majorID) throws SQLException {
		int educationLevel_ID = 0;
		Connection con = null;
		PreparedStatement prep = null;
		String select_educationLevel_ID = "SELECT educationLevel_ID FROM major "
				+"JOIN educationlevel ON major.Major_ID = educationlevel.Major_ID "
				+"WHERE educationlevel.educationalBackground = ? "
				+"AND educationlevel.educationLevel = ? "
				+"AND major.Major_ID = ?";
		try {
			con = ConnectDB.getInstance().DBConnection();
			prep = con.prepareStatement(select_educationLevel_ID);
			prep.setString(1, educationalBackground);
			prep.setInt(2, educationLevel);
			prep.setInt(3, majorID);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				educationLevel_ID = rs.getInt("educationLevel_ID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (prep != null) {
				prep.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return educationLevel_ID;
	}
	
	public boolean findstudentID(String studentID) throws SQLException {
		Connection con = null;
		PreparedStatement prep = null;
		String select_studentID = "SELECT studentID FROM student WHERE studentID = ?";
		try {
			con = ConnectDB.getInstance().DBConnection();
			prep = con.prepareStatement(select_studentID);
			prep.setString(1, studentID);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
	
	public boolean updateDataStudent(StudentBean student) throws SQLException {
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
			+ " WHERE student.studentID = ? ";
		try {
			con = ConnectDB.getInstance().DBConnection();
			prep = con.prepareStatement(update_data);
			//student
			prep.setString(1, student.getAntecedent());
			prep.setString(2, student.getFirstName());
			prep.setString(3, student.getLastName());
			//parent
			prep.setString(4, student.getParent().getAntecedent());
			prep.setString(5, student.getParent().getFirstName());
			prep.setString(6, student.getParent().getLastName());
			//address
			prep.setString(7, student.getParent().getAddress().getAddNo());
			prep.setString(8, student.getParent().getAddress().getMoo());
			prep.setString(9, student.getParent().getAddress().getStreet());
			prep.setString(10, student.getParent().getAddress().getSubDistrict());
			prep.setString(11, student.getParent().getAddress().getDistrict());
			prep.setString(12, student.getParent().getAddress().getProvince());
			prep.setString(13, student.getParent().getAddress().getZipCode());
			
			prep.setString(14, student.getStudentID());
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
	
	public int findMaxPersonIdForInsertToAddress() throws SQLException {
		Connection dbConnection = null;
		PreparedStatement prepSelect = null;
		int parent_ID = 0;
		try {
			dbConnection = ConnectDB.getInstance().DBConnection();
			String sql_select = "select MAX(parent_ID) AS Parent_ID from parent";
			prepSelect = dbConnection.prepareStatement(sql_select);
			ResultSet rs = prepSelect.executeQuery();
			while (rs.next()) {
				parent_ID = rs.getInt("parent_ID");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (prepSelect != null) {
				prepSelect.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}

		return parent_ID;
	}
	
	public boolean importDataStudentAndParent(StudentBean student,int idEducationLevel) throws SQLException {
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
	
	public boolean importDataAddress(AddressBean address,int idParent) throws SQLException {
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
