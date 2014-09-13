package com.project.addholiday;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.bean.HolidayBean;

/**
 * Servlet implementation class AddHolidayServlet
 */
@WebServlet("/AddHolidayServlet")
public class AddHolidayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddHolidayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HolidayBean holiday = new HolidayBean();
		AddHolidayManager addHoliday = new AddHolidayManager();
		String detail = request.getParameter("detail");
		String date1 = request.getParameter("StartDate");
		String date2 = request.getParameter("EndDate");
		detail = new String(detail.getBytes("ISO8859_1"),"UTF-8");
		SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/YYYY",Locale.ENGLISH);
		Calendar startDay = Calendar.getInstance(Locale.ENGLISH);
		Calendar endDay = Calendar.getInstance(Locale.ENGLISH);
		String[] startDate = date1.split("/");
		String[] endDate = date2.split("/");
		startDay.set(Integer.parseInt(startDate[2]),Integer.parseInt(startDate[1])-1,Integer.parseInt(startDate[0]));
		endDay.set(Integer.parseInt(endDate[2]),Integer.parseInt(endDate[1])-1,Integer.parseInt(endDate[0]));
		while(startDay.getTime().before(endDay.getTime())){
			String[] date = formatDate.format(startDay.getTime()).split("/");
			holiday.setDate(Integer.parseInt(date[0]));
			holiday.setMonth(Integer.parseInt(date[1]));
			holiday.setYear(Integer.parseInt(date[2]));
			holiday.setDetail(detail);
			
			try {
				if(addHoliday.addHoliday(holiday)){
					
				}else{
					request.setAttribute("ErrorColor", "red");
					request.setAttribute("ErrorMassage", "ไม่สามารถเพิ่มข้อมูลวันหยุดได้");
					request.getRequestDispatcher("ListHolidayServlet").forward(request, response);
					break;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
			startDay.add(Calendar.DAY_OF_YEAR, 1);
		}
		String[] dates = date2.split("/");
		holiday.setDate(Integer.parseInt(dates[0]));
		holiday.setMonth(Integer.parseInt(dates[1]));
		holiday.setYear(Integer.parseInt(dates[2]));
		holiday.setDetail(detail);
		try {
			addHoliday.addHoliday(holiday);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("ErrorColor", "green");
		request.setAttribute("ErrorMassage", "เพิ่มข้อมูลวันหยุดเรียบร้อยแล้ว");
		request.getRequestDispatcher("ListHolidayServlet").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
