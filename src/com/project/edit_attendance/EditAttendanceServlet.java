package com.project.edit_attendance;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.project.bean.ScheduleBean;

/**
 * Servlet implementation class EditAttendanceServlet
 */
@WebServlet("/EditAttendanceServlet")
public class EditAttendanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditAttendanceServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		EditAttendanceManager edtMng = new EditAttendanceManager();
		String majorName = request.getParameter("majorName");
		String eduBackground = request.getParameter("eduBackground");
		String eduLevel = request.getParameter("eduLevel");
		String term = request.getParameter("term");
		String dateAttendance = request.getParameter("dateAttendance");

		if (request.getParameter("studentID") == null) {
			try {
				int intEduLevel = Integer.parseInt(eduLevel);
				Date result = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(dateAttendance);
				System.out.println(result);
				List<ScheduleBean> listSchedule = edtMng.listStudentAndSchedule(majorName, eduBackground, intEduLevel, term, result);

				for (int i = 0; i < listSchedule.size(); i++) {
					System.out.println("- : " + listSchedule.get(i).getAttendance().getStudent().getStudentID());
				}

				String json1 = new Gson().toJson(listSchedule);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json1);

			} catch (SQLException | ParseException e) {
				e.printStackTrace();
			}

		} else if (request.getParameter("studentID").equalsIgnoreCase("1")) {
			try {
				int intEduLevel = Integer.parseInt(eduLevel);
				Date result = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(dateAttendance);
				System.out.println(result);
				List<ScheduleBean> listSchedule = edtMng.listStudentAndSchedule(majorName, eduBackground, intEduLevel, term, result);

				for (int i = 0; i < listSchedule.size(); i++) {
					System.out.println(listSchedule.get(i).getAttendance().getStudent().getStudentID());
				}
				request.getSession().setAttribute("listSchedule", listSchedule);
				// request.getRequestDispatcher("EditAttendance.jsp").forward(request,
				// response);
				response.sendRedirect("EditAttendance.jsp");
			} catch (SQLException | ParseException e) {
				e.printStackTrace();
			}
		}
		// else {
		//
		// request.getRequestDispatcher("EditAttendance.jsp").forward(request,
		// response);
		// }
	}

}
