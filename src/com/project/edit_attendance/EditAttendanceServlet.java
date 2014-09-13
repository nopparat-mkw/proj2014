package com.project.edit_attendance;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.project.bean.MajorBean;
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
		
		EditAttendanceManager edtMng = new EditAttendanceManager();
		if (request.getParameter("studentID") == null) {
			String majorName = request.getParameter("majorName");
			String eduBackground = request.getParameter("eduBackground");
			String eduLevel = request.getParameter("eduLevel");
			String term = request.getParameter("term");
			String dateAttendance = request.getParameter("dateAttendance");
			
		
				/*
				 * Show Date
				 */
				
				try {
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					System.out.println("- : "+dateAttendance);	
					Date result = df.parse(dateAttendance);
					System.out.println(result);
					Date createNow = new Date();
					System.out.println("+ : "+createNow);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				/*
				 * End Show Date
				 */

		    
			
			//List<ScheduleBean> listSchedule = edtMng.listStudentAndSchedule(majorName, eduBackground, eduLevel, term, dateAttendance);				
			
//			String json1 = new Gson().toJson(listSchedule);
//		    response.setContentType("application/json");
//		    response.setCharacterEncoding("UTF-8");
//		    response.getWriter().write(json1);
		}
	}

}
