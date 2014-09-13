package com.project.edit_teacher;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.project.bean.EducationBean;
import com.project.bean.TeacherBean;

/**
 * Servlet implementation class EditTeacherServlet
 */
@WebServlet("/EditTeacherServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
// 50MB
public class EditTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * Name of the directory where uploaded files will be saved, relative to the
	 * web application directory.
	 */
	private static final String SAVE_DIR = "images\\profile";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditTeacherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		EditTeacherManager edtMng = new EditTeacherManager();
		
		if (request.getParameter("editTeacherIdCard") == null) {
			String idCard = request.getParameter("id");			
			try {
				TeacherBean teacherBean = edtMng.findTeacherByIDCard(idCard);
				System.out.println("- : " + idCard);				
				String json = new Gson().toJson(teacherBean);
			    response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
			    response.getWriter().write(json);
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}else{
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
			
			String idCardTeacher = request.getParameter("editTeacherIdCard");
			String antecedentTeacher = request.getParameter("editTeacherAntecedent");
			String firstNameTeacher = request.getParameter("editTeacherFirstName");
			String lastNameTeacher = request.getParameter("editTeacherLastName");
			String educationalBackgroundTeacher = request.getParameter("editTeacherEducationalBackground");
			String educationalInstitutionTeacher = request.getParameter("editTeacherEducationalInstitution");
			String educationalMajorTeacher = request.getParameter("editTeacherEducationalMajor");
			String vacancyTeacher = request.getParameter("editTeacherVacancy");
			String departmentTeacher = request.getParameter("editTeacherDepartment");
			
			EducationBean education = new EducationBean(educationalInstitutionTeacher, educationalBackgroundTeacher, educationalMajorTeacher);
			TeacherBean teacherBean = new TeacherBean();
			teacherBean.setIdCard(idCardTeacher);
			teacherBean.setAntecedent(antecedentTeacher);
			teacherBean.setFirstName(firstNameTeacher);
			teacherBean.setLastName(lastNameTeacher);
			teacherBean.setVacancy(vacancyTeacher);
			teacherBean.setPath_image(fileName);
			teacherBean.setEducation(education);
			
			try {
				boolean chkUpdate = edtMng.updateDataTeacher(teacherBean, departmentTeacher);
				if (chkUpdate) {
					System.out.println("Update Teacher Success");
					response.sendRedirect("ListTeacherServlet");
				}
			} catch (SQLException e) {
				System.out.println("Update Teacher Fail");
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		doGet(request, response);
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

}
