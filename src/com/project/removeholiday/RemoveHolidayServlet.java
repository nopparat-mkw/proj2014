package com.project.removeholiday;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RemoveHolidayServlet
 */
@WebServlet("/RemoveHolidayServlet")
public class RemoveHolidayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveHolidayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String date = request.getParameter("date");
		String month = request.getParameter("month");
		String year = request.getParameter("year");
		RemoveHolidayManager removeHoliday = new RemoveHolidayManager();
		try {
			if(removeHoliday.removeHoliday(date, month, year)){
				request.setAttribute("ErrorColor", "green");
				request.setAttribute("ErrorMassage", "ลบข้อมูลวันหยุดเรียบร้อยแล้ว");
				request.getRequestDispatcher("ListHolidayServlet").forward(request, response);
			}else{
				request.setAttribute("ErrorColor", "red");
				request.setAttribute("ErrorMassage", "ไม่สามารถลบข้อมูลวันหยุดได้");
				request.getRequestDispatcher("ListHolidayServlet").forward(request, response);
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
		doGet(request,response);
	}

}
