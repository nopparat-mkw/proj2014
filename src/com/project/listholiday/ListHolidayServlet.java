package com.project.listholiday;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.bean.HolidayBean;

/**
 * Servlet implementation class ListHolidayServlet
 */
@WebServlet("/ListHolidayServlet")
public class ListHolidayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ListHolidayServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ListHolidayManager listHolidayManager = new ListHolidayManager();
		try {
			String seleteYear = request.getParameter("selectYear");
			if(seleteYear == null){
				List<HolidayBean> listYear = listHolidayManager.findAllYear();
				List<HolidayBean> listHoliday = listHolidayManager.findAllHolidayInYear(Integer.toString(listYear.get(listYear.size()-1).getYear()));
				request.setAttribute("ListHoliday", listHoliday);
				request.setAttribute("listYear", listYear);
				request.setAttribute("selectYear", listYear.get(listYear.size()-1).getYear());
				request.getRequestDispatcher("ListHolidayPage.jsp").forward(request, response);
			}else{
				List<HolidayBean> listYear = listHolidayManager.findAllYear();
				List<HolidayBean> listHoliday = listHolidayManager.findAllHolidayInYear(seleteYear);
				request.setAttribute("ListHoliday", listHoliday);
				request.setAttribute("listYear", listYear);
				request.setAttribute("selectYear", seleteYear);
				request.getRequestDispatcher("ListHolidayPage.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
