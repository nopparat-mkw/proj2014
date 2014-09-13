package com.project.view_all_attendance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.bean.LoginBean;
import com.project.bean.MajorBean;
import com.project.bean.StudentBean;
import com.project.view_all_student.ViewAllStudentManager;

/**
 * Servlet implementation class ViewAllAttendanceServlet
 */
@WebServlet("/ViewAllAttendanceServlet")
public class ViewAllAttendanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAllAttendanceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ViewAllStudentManager viewAllStudentMng = new ViewAllStudentManager();
		ViewAllAttendanceManager viewAllAttendanceMng = new ViewAllAttendanceManager();
		try {
			String majorStudent = request.getParameter("majorStudent");	
			LoginBean login = (LoginBean) request.getSession().getAttribute("login");
			MajorBean major = viewAllStudentMng.findAllMajor(login.getUsername());
			
			if(majorStudent == null){
				
				List<MajorBean> listEducationLevel = viewAllStudentMng.findAllEducationLevelByMajorName(major.getMajorName());
				
				request.getSession().setAttribute("majorStudent", major.getMajorName());
				
				// EducationLevel
				System.out.println("Search "+listEducationLevel.get(0).getEducationLevel().getEducationalBackground()+" - "+listEducationLevel.get(0).getEducationLevel().getEducationLevel());
				String selectEducation = listEducationLevel.get(0).getEducationLevel().getEducationalBackground();
				int selectEducation1 = listEducationLevel.get(0).getEducationLevel().getEducationLevel();
				request.setAttribute("selectEducation", selectEducation);
				request.setAttribute("selectEducation1", selectEducation1);
				
				// Term
				List<String> listTerm = viewAllAttendanceMng.findAllTerm(major.getMajorName(), selectEducation, selectEducation1);
				request.setAttribute("listTerm", listTerm);
				request.setAttribute("chkTerm", listTerm.get(listTerm.size()-1).toString());
				
				// Major
				request.getSession().setAttribute("listEducationLevel", listEducationLevel);
				request.getSession().setAttribute("showMajor", major);
				
				// DateSchedule
				List<String> listDateSchedule = viewAllAttendanceMng.ListDateSchedule(major.getMajorName(), selectEducation, selectEducation1, listTerm.get(listTerm.size()-1).toString());
				request.getSession().setAttribute("listDateSchedule", listDateSchedule);
				
				
				// Present มาเข้าแถว
				List<Integer> listPresent = viewAllAttendanceMng.ListPresent(major.getMajorName(), selectEducation, selectEducation1, listDateSchedule);
				request.getSession().setAttribute("listPresent", listPresent);
				
				// Lack ขาด
				List<Integer> listLack = viewAllAttendanceMng.ListLack(major.getMajorName(), selectEducation, selectEducation1, listDateSchedule);
				request.getSession().setAttribute("listLack", listLack);
				
				// SickLeave ลาป่วย
				List<Integer> listSickLeave = viewAllAttendanceMng.ListSickLeave(major.getMajorName(), selectEducation, selectEducation1, listDateSchedule);
				request.getSession().setAttribute("listSickLeave", listSickLeave);
				
				// PersonalLeave ลากิจ
				List<Integer> listPersonalLeave = viewAllAttendanceMng.ListPersonalLeave(major.getMajorName(), selectEducation, selectEducation1, listDateSchedule);
				request.getSession().setAttribute("listPersonalLeave", listPersonalLeave);
				
				// Late สาย
				List<Integer> listLate = viewAllAttendanceMng.ListLate(major.getMajorName(), selectEducation, selectEducation1, listDateSchedule);
				request.getSession().setAttribute("listLate", listLate);
				
				request.getRequestDispatcher("ViewAllAttendance.jsp").forward(request, response);
			}else{
				
				
				List<MajorBean> listEducationLevel = viewAllStudentMng.findAllEducationLevelByMajorName(majorStudent);
				
				request.getSession().setAttribute("listEducationLevel",listEducationLevel);
				request.getSession().setAttribute("showMajor", major);
				request.getSession().setAttribute("majorStudent", majorStudent);
				
				String selectMajorName11= request.getParameter("selectLevelEdu");
				String[] result = selectMajorName11.split(" ");
				String selectEducation = result[0];
				String selectEducation1 = result[1];
				request.setAttribute("selectEducation", selectEducation);
				request.setAttribute("selectEducation1", selectEducation1);

				
				List<String> listTerm = viewAllAttendanceMng.findAllTerm(majorStudent, selectEducation, Integer.parseInt(selectEducation1));
				request.setAttribute("listTerm", listTerm);
				
				String chkTerm = request.getParameter("selectTerm");
				request.setAttribute("chkTerm", chkTerm);
				
					List<String> listDateSchedule = viewAllAttendanceMng.ListDateSchedule(majorStudent, selectEducation, Integer.parseInt(selectEducation1), chkTerm);
					request.getSession().setAttribute("listDateSchedule", listDateSchedule);
						
					// Present มาเข้าแถว
					List<Integer> listPresent = viewAllAttendanceMng.ListPresent(majorStudent, selectEducation, Integer.parseInt(selectEducation1), listDateSchedule);
					request.getSession().setAttribute("listPresent", listPresent);
						
					// Lack ขาด
					List<Integer> listLack = viewAllAttendanceMng.ListLack(majorStudent, selectEducation, Integer.parseInt(selectEducation1), listDateSchedule);
					request.getSession().setAttribute("listLack", listLack);
						
					// SickLeave ลาป่วย
					List<Integer> listSickLeave = viewAllAttendanceMng.ListSickLeave(majorStudent, selectEducation, Integer.parseInt(selectEducation1), listDateSchedule);
					request.getSession().setAttribute("listSickLeave", listSickLeave);
						
					// PersonalLeave ลากิจ
					List<Integer> listPersonalLeave = viewAllAttendanceMng.ListPersonalLeave(majorStudent, selectEducation, Integer.parseInt(selectEducation1), listDateSchedule);
					request.getSession().setAttribute("listPersonalLeave", listPersonalLeave);
						
					// Late สาย
					List<Integer> listLate = viewAllAttendanceMng.ListLate(majorStudent, selectEducation, Integer.parseInt(selectEducation1), listDateSchedule);
					request.getSession().setAttribute("listLate", listLate);
				
				request.getRequestDispatcher("ViewAllAttendance.jsp").forward(request, response);
			}
			
		} catch (Exception e) {
			response.sendRedirect("index.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
