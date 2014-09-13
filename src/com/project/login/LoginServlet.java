package com.project.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.bean.LoginBean;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		try {
			request.getSession().setMaxInactiveInterval(60*60);
			LoginManager loginManager = new LoginManager();
			LoginBean login = new LoginBean(request.getParameter("username"),request.getParameter("password"));
			login = loginManager.vertifyLogin(login);
			if (login != null) {
				if(login.getStatus().equals("Admin")){
					request.getSession().setAttribute("login", login);
					response.sendRedirect("ListTeacherServlet");
				}else{
					request.getSession().setAttribute("login", login);
					response.sendRedirect("ViewProfileTeacherServlet");
				}
			}else{
				System.out.println("Login False");
				request.setAttribute("loginError", "ชื่อผู้ใช้หรือรหัสผ่านผิด");
				request.getRequestDispatcher("LoginPage.jsp").forward(request, response);
			}
	
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
