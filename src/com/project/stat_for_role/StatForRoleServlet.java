package com.project.stat_for_role;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class StatForRoleServlet
 */
@WebServlet("/StatForRoleServlet")
public class StatForRoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StatForRoleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	    
	 // แสดงจำนวนมาเข้าแถว
		request.getSession().setAttribute("presents", request.getSession().getAttribute("present"));
		// แสดงจำนวนขาดเข้าแถว
		request.getSession().setAttribute("lacks", request.getSession().getAttribute("lackLate"));
		// แสดงจำนวนลาป่วยเข้าแถว
		request.getSession().setAttribute("sickLeaves", request.getSession().getAttribute("sickLeave"));
		// แสดงจำนวนลากิจเข้าแถว
		request.getSession().setAttribute("personalLeaves", request.getSession().getAttribute("personalLeave"));

		request.getRequestDispatcher("StatForRole.jsp").forward(request, response);
	}

}
