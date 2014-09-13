package com.project.view_profile_teacher;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.bean.LoginBean;
import com.project.bean.MajorBean;
import com.project.bean.TeacherBean;

/**
 * Servlet implementation class ViewProfileTeacherServlet
 */
@WebServlet("/ViewProfileTeacherServlet")
public class ViewProfileTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewProfileTeacherServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ViewProfileTeacherManager viewMsg = new ViewProfileTeacherManager();
		try {
				LoginBean login = (LoginBean) request.getSession().getAttribute("login");
				List<TeacherBean> teacherBean = viewMsg.findProfileTeacherByUsername(login.getUsername());				
				request.getSession().setAttribute("ViewProfile", teacherBean);
				request.getRequestDispatcher("ViewProfileTeacher.jsp").forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
