package com.project.edit_profile_teacher;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.project.bean.TeacherBean;


/**
 * Servlet implementation class EditProfileTeacherServlet
 */
@WebServlet("/EditProfileTeacherServlet")
public class EditProfileTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {

	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfileTeacherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		if(request.getParameter("profileFirstName")==null){
			String idCard = request.getParameter("id");
			TeacherBean teacherBean;
			try {
				teacherBean = new EditProfileTeacherManager().findTeacherByIdCard(idCard);
				String json = new Gson().toJson(teacherBean);
			    response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
			    response.getWriter().write(json);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else{
			String profileAntecedent = request.getParameter("profileAntecedent");
			String profileFirstName = request.getParameter("profileFirstName");
			String profileLastName = request.getParameter("profileLastName");
			String profileEmail = request.getParameter("profileEmail");
			String profilePhone = request.getParameter("profilePhone");	
			String profileIdCard = request.getParameter("profileIdCard");
			
			TeacherBean teacher = new TeacherBean();
			teacher.setIdCard(profileIdCard);
			teacher.setAntecedent(profileAntecedent);
			teacher.setFirstName(profileFirstName);
			teacher.setLastName(profileLastName);
			teacher.setEmail(profileEmail);
			teacher.setPhone(profilePhone);
			
			
			EditProfileTeacherManager editProfileMng = new EditProfileTeacherManager();
			try {
				if(editProfileMng.updateProfileTeacher(teacher)){
					request.setAttribute("ErrorColor", "green");
					request.setAttribute("ErrorMassage", "แก้ไขข้อมูลส่วนตัวเรียบร้อยแล้ว");
					request.getRequestDispatcher("ViewProfileTeacherServlet").forward(request, response);
				}else{
					request.setAttribute("ErrorColor", "red");
					request.setAttribute("ErrorMassage", "ไม่สามารถแก้ไขข้อมูลส่วนตัวได้");
					request.getRequestDispatcher("ViewProfileTeacherServlet").forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
