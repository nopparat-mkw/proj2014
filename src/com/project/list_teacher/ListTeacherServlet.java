package com.project.list_teacher;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.bean.TeacherBean;

/**
 * Servlet implementation class ListTeacherServlet
 */
@WebServlet("/ListTeacherServlet")
public class ListTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListTeacherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ListTeacherManager listAllTeacher = new ListTeacherManager();
		
		try {
			String id = request.getParameter("id");
			
			if (id != null) {
				if (listAllTeacher.removeTeacher(id)) {
					request.setAttribute("ErrorColor", "green");
					request.setAttribute("ErrorMassage", "ลบข้อมูลอาจารย์ร้อยแล้ว");
					request.getRequestDispatcher("ListTeacher.jsp").forward(request, response);
				}else{
					request.setAttribute("ErrorColor", "red");
					request.setAttribute("ErrorMassage", "ลบข้อมูลอาจารย์ไม่สำเร็จ");
					request.getRequestDispatcher("ListTeacher.jsp").forward(request, response);
				}
			}else{
			
				List<TeacherBean> listTeacher = listAllTeacher.findAllTeacher();
				request.setAttribute("listTeacher",listTeacher);
				request.getRequestDispatcher("ListTeacher.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
