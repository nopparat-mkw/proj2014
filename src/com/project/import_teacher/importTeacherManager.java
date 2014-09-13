package com.project.import_teacher;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.project.bean.EducationBean;
import com.project.bean.LoginBean;
import com.project.bean.MajorBean;
import com.project.bean.TeacherBean;
import com.project.utility.ConnectDB;

public class importTeacherManager {
	
	public Vector<TeacherBean> readCSV(String filename) {
		TeacherBean teacher = null;
		EducationBean education = null;
		LoginBean login = null;
		MajorBean major = null;
				
		Vector<TeacherBean> allTeacher = new Vector<TeacherBean>();
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
				teacher = new TeacherBean();
				education = new EducationBean();
				login = new LoginBean();
				major = new MajorBean();
				
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					count += 1;
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
					
						if (count == 1) {
							teacher.setIdCard(cell.getStringCellValue());
							break;
						} else if (count == 2) {
							teacher.setAntecedent(cell.getStringCellValue());
							break;
						} else if (count == 3) {							
							teacher.setFirstName(cell.getStringCellValue());						
							break;
						}else if (count == 4){
							teacher.setLastName(cell.getStringCellValue());						
							break;
						}else if (count == 5){
							education.setEducationalBackground(cell.getStringCellValue());						
							break;
						}else if(count == 6){
							education.setEducationalInstitution(cell.getStringCellValue());
							break;
						}else if(count == 7){
							education.setEducationalMajor(cell.getStringCellValue());
							break;
						}else if(count == 8){
							teacher.setEmail(cell.getStringCellValue());
							break;
						}else if(count == 9){
							teacher.setPhone(cell.getStringCellValue());
							break;
						}else if(count == 10){
							teacher.setVacancy(cell.getStringCellValue());
							break;
						}else if(count == 11){
							major.setMajorName(cell.getStringCellValue());
							break;
						}else if(count == 12){
							login.setUsername(cell.getStringCellValue());
							break;
						}else if(count == 13){
							login.setPassword(cell.getStringCellValue());
							break;
						}else if(count == 14){
							login.setStatus(cell.getStringCellValue());
							break;
						}
						break;
					}
				}
				teacher.setMajor(major);
				teacher.setEducation(education);
				teacher.setLogin(login);
				
				allTeacher.add(teacher);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allTeacher;
	}
	
	public boolean findIdCardTeacher(String idCard) throws SQLException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "select * from teacher where idCard = ?";
		try {
			dbConnection = ConnectDB.getInstance().DBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setString(1, idCard);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				return true;
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
		return false;
	}

	public boolean finMajorName(String majorName) throws SQLException {
		Connection con = null;
		PreparedStatement prep = null;
		String select_major_ID = "select * from major where majorName = ?";
		try {
			con = ConnectDB.getInstance().DBConnection();
			prep = con.prepareStatement(select_major_ID);
			prep.setString(1, majorName);
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

	public boolean updateDataTeacher(TeacherBean teacher) throws SQLException {
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
			prep.setString(6, teacher.getMajor().getMajorName());
			
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

	public boolean importDataTeacher(TeacherBean teacher) throws SQLException{
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String addSQL = "insert into Teacher (idCard,antecedent,firstName,lastName,vacancy,email,phone,major_ID)"
				+ " VALUES (?,?,?,?,?,?,?,(SELECT major_ID FROM major WHERE major.majorName = ?))";
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
			preparedStatement.setString(8, teacher.getMajor().getMajorName());
			preparedStatement.executeUpdate();
				String addLogin = "insert into login (username,password,status,teacherID) VALUES (?,?,?,?)";
				preparedStatement = dbConnection.prepareStatement(addLogin);
				preparedStatement.setString(1, teacher.getLogin().getUsername());
				preparedStatement.setString(2, teacher.getLogin().getPassword());
				preparedStatement.setString(3, teacher.getLogin().getStatus());
				preparedStatement.setString(4, teacher.getIdCard());
				preparedStatement.executeUpdate();
					String addEducation = "insert into education (educationalInstitution,educationalBackground,educationalMajor,teacherID)"
							+ " VALUES (?,?,?,?)";
					preparedStatement = dbConnection.prepareStatement(addEducation);
					preparedStatement.setString(1, teacher.getEducation().getEducationalInstitution());
					preparedStatement.setString(2, teacher.getEducation().getEducationalBackground());
					preparedStatement.setString(3, teacher.getEducation().getEducationalMajor());
					preparedStatement.setString(4, teacher.getIdCard());
					preparedStatement.executeUpdate();
					return true;
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
		return false;
	}
	
}
