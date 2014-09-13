package com.project.SearchStudentData;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.bean.MajorBean;

/**
 * Servlet implementation class SearchStudentDataServlet
 */
@WebServlet("/SearchStudentDataServlet")
public class SearchStudentDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchStudentDataServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		SearchStudentDataManager searchMng = new SearchStudentDataManager();
		String student_id = request.getParameter("searchStudentID");
		if (student_id != null) {
			try {
				MajorBean major = searchMng.findStudentByStudentID(student_id);
				request.getSession().setAttribute("major", major);
				
				// แสดงจำนวนมาเข้าแถว
				int present = searchMng.ListPresent(student_id);
				request.getSession().setAttribute("present", present);
				// แสดงจำนวนขาดเข้าแถว
				int lack = searchMng.ListLack(student_id);
				request.getSession().setAttribute("lack", lack);
				// แสดงจำนวนลาป่วยเข้าแถว
				int sickLeave = searchMng.ListSickLeave(student_id);
				request.getSession().setAttribute("sickLeave", sickLeave);
				// แสดงจำนวนลากิจเข้าแถว
				int personalLeave = searchMng.ListPersonalLeave(student_id);
				request.getSession().setAttribute("personalLeave", personalLeave);
				// แสดงจำนวนมาสายเข้าแถว
				int late = searchMng.ListLate(student_id);
				int calLate = late/2;
				request.getSession().setAttribute("calLate", calLate);
				
				int lackLate = lack+calLate;
				request.getSession().setAttribute("lackLate", lackLate);

				request.setAttribute("ErrorColor", "red");
				request.setAttribute("ErrorMassage", "ไม่มีข้อมูลนักศึกษา");
				
				request.getRequestDispatcher("SearchStudentData.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else{
			request.getRequestDispatcher("SearchStudentData.jsp").forward(request, response);
		}
	}

}
