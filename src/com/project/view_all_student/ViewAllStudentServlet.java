package com.project.view_all_student;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.bean.LoginBean;
import com.project.bean.MajorBean;
import com.project.bean.StudentBean;

/**
 * Servlet implementation class ViewAllStudentServlet
 */
@WebServlet("/ViewAllStudentServlet")
public class ViewAllStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAllStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ViewAllStudentManager viewAllStudentMng = new ViewAllStudentManager();
		try {
			String majorStudent = request.getParameter("majorStudent");	
			
			if(majorStudent == null){
				LoginBean login = (LoginBean) request.getSession().getAttribute("login");
				MajorBean major = viewAllStudentMng.findAllMajor(login.getUsername());
				List<MajorBean> listEducationLevel = viewAllStudentMng.findAllEducationLevelByMajorName(major.getMajorName());
				
				/*
				 * 
				 */
				String selectEducation = "ปวช";
				int selectEducation1 = 1;
				request.setAttribute("selectEducation", selectEducation);
				request.setAttribute("selectEducation1", selectEducation1);
				List<StudentBean> listStudent = viewAllStudentMng.findAllStudent(major.getMajorName(), selectEducation,selectEducation1);
				request.setAttribute("listStudent", listStudent);
				
				
				
				request.getSession().setAttribute("listEducationLevel", listEducationLevel);
				request.getSession().setAttribute("showMajor", major);
				
				request.getRequestDispatcher("ViewAllStudent.jsp").forward(request, response);
			}else{
				
				LoginBean login = (LoginBean) request.getSession().getAttribute("login");
				MajorBean major = viewAllStudentMng.findAllMajor(login.getUsername());
				List<MajorBean> listEducationLevel = viewAllStudentMng.findAllEducationLevelByMajorName(majorStudent);
				
				request.getSession().setAttribute("listEducationLevel",listEducationLevel);
				request.getSession().setAttribute("showMajor", major);
				
				String selectMajorName11= request.getParameter("selectLevelEdu");
				String[] result = selectMajorName11.split(" ");
				String selectEducation = result[0];
				String selectEducation1 = result[1];
				request.setAttribute("selectEducation", selectEducation);
				request.setAttribute("selectEducation1", selectEducation1);
				
				List<StudentBean> listStudent = viewAllStudentMng.findAllStudent(majorStudent, selectEducation,Integer.parseInt(selectEducation1));
				request.setAttribute("listStudent", listStudent);
				
				request.getRequestDispatcher("ViewAllStudent.jsp").forward(request, response);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
