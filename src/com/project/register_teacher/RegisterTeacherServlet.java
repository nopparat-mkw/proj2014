package com.project.register_teacher;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.project.bean.EducationBean;
import com.project.bean.LoginBean;
import com.project.bean.TeacherBean;

/**
 * Servlet implementation class RegisterTeacherServlet
 */
@WebServlet("/RegisterTeacherServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
// 50MB
public class RegisterTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * Name of the directory where uploaded files will be saved, relative to the
	 * web application directory.
	 */
	private static final String SAVE_DIR = "images\\profile";

	/**
	 * handles file upload
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String appPath = request.getServletContext().getRealPath("");
		// constructs path of the directory to save uploaded file
		String savePath = appPath + File.separator + SAVE_DIR;

		// creates the save directory if it does not exists
		File fileSaveDir = new File(savePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}

		Part part = request.getPart("file");
		String fileName = extractFileName(part);
		part.write(savePath + File.separator + fileName);
		
		RegisterTeacherManager regisMng = new RegisterTeacherManager();
		String idCardTeacher = request.getParameter("IdCardTeacher");
		String antecedentTeacher = request.getParameter("AntecedentTeacher");
		String firstNameTeacher = request.getParameter("FirstNameTeacher");
		String lastNameTeacher = request.getParameter("LastNameTeacher");
		String educationalBackgroundTeacher = request.getParameter("EducationalBackgroundTeacher");
		String educationalInstitutionTeacher = request.getParameter("EducationalInstitutionTeacher");
		String educationalMajorTeacher = request.getParameter("EducationalMajorTeacher");
		String emailTeacher = request.getParameter("EmailTeacher");
		String phoneTeacher = request.getParameter("PhoneTeacher");
		String vacancyTeacher = request.getParameter("VacancyTeacher");
		String departmentTeacher = request.getParameter("DepartmentTeacher");
		String usernameTeacher = request.getParameter("UsernameTeacher");
		String passwordTeacher = request.getParameter("PasswordTeacher");
		LoginBean login = new LoginBean(usernameTeacher, passwordTeacher);
		EducationBean education = new EducationBean(educationalInstitutionTeacher, educationalBackgroundTeacher, educationalMajorTeacher);
		TeacherBean teacherBean = new TeacherBean();
		teacherBean.setIdCard(idCardTeacher);
		teacherBean.setAntecedent(antecedentTeacher);
		teacherBean.setFirstName(firstNameTeacher);
		teacherBean.setLastName(lastNameTeacher);
		teacherBean.setPhone(phoneTeacher);
		teacherBean.setEmail(emailTeacher);
		teacherBean.setVacancy(vacancyTeacher);
		teacherBean.setPath_image(fileName);
		teacherBean.setEducation(education);
		teacherBean.setLogin(login);
		
		try {
			int major_ID = regisMng.findMajorID(departmentTeacher);
			if (major_ID != 0) {
				boolean addteacher = regisMng.addTeacher(teacherBean, major_ID);
				if (addteacher) {
					System.out.println("Insert Success");
				}else{
				System.out.println("Insert Fail");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("message", "Upload has been done successfully!");
				
//		request.getRequestDispatcher("ListTeacherServlet").forward(request, response);
		response.sendRedirect("ListTeacherServlet");
	}

	/**
	 * Extracts file name from HTTP header content-disposition
	 */
	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);	
	}
}
