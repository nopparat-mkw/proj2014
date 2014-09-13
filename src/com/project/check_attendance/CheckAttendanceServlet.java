package com.project.check_attendance;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.bean.AttendanceBean;
import com.project.bean.LoginBean;
import com.project.bean.MajorBean;
import com.project.bean.ScheduleBean;
import com.project.bean.StudentBean;
import com.project.view_all_student.ViewAllStudentManager;

/**
 * Servlet implementation class CheckAttendanceServlet
 */
@WebServlet("/CheckAttendanceServlet")
public class CheckAttendanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckAttendanceServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param servletContext 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		CheckAttendanceManager chk = new CheckAttendanceManager();
		ViewAllStudentManager viewAllStudentMng = new ViewAllStudentManager();
		try {
		String majorStudent = request.getParameter("majorStudent");
		LoginBean login = (LoginBean) request.getSession().getAttribute("login");
		MajorBean major = viewAllStudentMng.findAllMajor(login.getUsername());
		
		if (majorStudent == null) {
			List<MajorBean> listEducationLevel = viewAllStudentMng.findAllEducationLevelByMajorName(major.getMajorName());
			
			// ListStudent
			System.out.println("Search "+listEducationLevel.get(0).getEducationLevel().getEducationalBackground()+" - "+listEducationLevel.get(0).getEducationLevel().getEducationLevel());
			String selectEducation = listEducationLevel.get(0).getEducationLevel().getEducationalBackground();
			int selectEducation1 = listEducationLevel.get(0).getEducationLevel().getEducationLevel();
			request.setAttribute("selectEducation", selectEducation);
			request.setAttribute("selectEducation1", selectEducation1);
			List<StudentBean> listStudent = viewAllStudentMng.findAllStudent(major.getMajorName(), selectEducation,selectEducation1);
			request.setAttribute("listStudent", listStudent);
			
			
			
			request.getSession().setAttribute("listEducationLevel", listEducationLevel);
			request.getSession().setAttribute("showMajor", major);
			
			String selectTerm = "1/2014";
			request.setAttribute("selectTerm", selectTerm);
			
			request.getRequestDispatcher("CheckAttendance.jsp").forward(request, response);
		} else{
			String stu = request.getParameter("stuID_1");
			
			if (stu != null) {
				int theCount = Integer.parseInt(request.getParameter("theCount"));
				int count=1;
				
				Date createNow = new Date();
				
				String term = request.getParameter("addSelectTerm");
				
				for (int i = 0; i < theCount; i++) {
					String status = request.getParameter("status_"+count);
					String studentID = request.getParameter("stuID_"+count);
					
					AttendanceBean attendanceBean = new AttendanceBean();
					attendanceBean.setStatusActivity(status);
					
					StudentBean studentBean = new StudentBean();
					studentBean.setStudentID(studentID);
					attendanceBean.setStudent(studentBean);
					
					ScheduleBean scheduleBean = new ScheduleBean(createNow, term, attendanceBean);
					try {
						int Schedule_ID = chk.findSchedule(createNow, term);
						if (Schedule_ID != 0) {
							boolean insertAttendance = chk.addAttendance(scheduleBean, Schedule_ID);
							if (insertAttendance) {
								System.out.println("Insert Attendance Success");								
							}else{
								System.out.println("Insert Attendance Fail !!!");
							}
						}else{
							boolean insertSchedule = chk.addSchedule(scheduleBean);
							if (insertSchedule) {
								System.out.println("Insert Schedule Success");
								int schedule_ID = chk.findSchedule(createNow, term);
								boolean insertAttendance = chk.addAttendance(scheduleBean, schedule_ID);
								if (insertAttendance) {
									System.out.println("Insert Attendance By Schedule Success");
								}else{
									System.out.println("Insert Attendance By Schedule Fail");
								}
							}
						}
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
					count++;
				}
				
				request.getSession().setAttribute("majorStudent", major);
				response.sendRedirect("ViewAllAttendanceServlet");
			}else{
				
				List<MajorBean> listEducationLevel = viewAllStudentMng.findAllEducationLevelByMajorName(majorStudent);
				
				request.getSession().setAttribute("listEducationLevel",listEducationLevel);
				request.getSession().setAttribute("showMajor", major);
				
				String selectMajorName11= request.getParameter("selectLevelEdu");
				String[] result = selectMajorName11.split(" ");
				String selectEducation = result[0];
				String selectEducation1 = result[1];
				request.setAttribute("selectEducation", selectEducation);
				request.setAttribute("selectEducation1", selectEducation1);
				
				List<StudentBean> listStudent = viewAllStudentMng.findAllStudent(majorStudent, selectEducation,Integer.parseInt(selectEducation1));
				request.setAttribute("listStudent", listStudent);
				
				String selectTerm = request.getParameter("selectTerm");
				request.setAttribute("selectTerm", selectTerm);
				
				request.getRequestDispatcher("CheckAttendance.jsp").forward(request, response);
			}
			
			
		}
		}catch (Exception e) {
			// TODO: handle exception
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
