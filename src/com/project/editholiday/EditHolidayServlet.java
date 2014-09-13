package com.project.editholiday;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.project.bean.HolidayBean;

/**
 * Servlet implementation class EditHolidayServlet
 */
@WebServlet("/EditHolidayServlet")
public class EditHolidayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditHolidayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("detail")== null){
			String date = request.getParameter("date");
			String month = request.getParameter("month");
			String year = request.getParameter("year");
			HolidayBean holiday;
			try {
				holiday = new EditHolidayManager().findHolidayByDateMonthYear(date, month, year);
				String json = new Gson().toJson(holiday);
			    response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
			    response.getWriter().write(json);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			String date = request.getParameter("dates");
			String month = request.getParameter("months");
			String year = request.getParameter("years");
			String detail = request.getParameter("detail");
			detail = new String(detail.getBytes("ISO8859_1"),"UTF-8");
			HolidayBean holiday = new HolidayBean();
			holiday.setDate(Integer.parseInt(date));
			holiday.setMonth(Integer.parseInt(month));
			holiday.setYear(Integer.parseInt(year));
			holiday.setDetail(detail);
			EditHolidayManager editManager = new EditHolidayManager();
			try {
				if(editManager.editHoliday(holiday)){
					request.setAttribute("ErrorColor", "green");
					request.setAttribute("ErrorMassage", "แก้ไขข้อมูลวันหยุดเรียบร้อยแล้ว");
					request.getRequestDispatcher("ListHolidayServlet").forward(request, response);
				}else{
					request.setAttribute("ErrorColor", "red");
					request.setAttribute("ErrorMassage", "ไม่สามารถแก้ไขข้อมูลวันหยุดได้");
					request.getRequestDispatcher("ListHolidayServlet").forward(request, response);
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
		doGet(request,response);
	}

}
