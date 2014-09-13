package com.project.list_student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.bean.HolidayBean;
import com.project.bean.MajorBean;
import com.project.bean.StudentBean;
import com.project.bean.TeacherBean;
import com.project.list_teacher.ListTeacherManager;

/**
 * Servlet implementation class ListStudentServlet
 */
@WebServlet("/ListStudentServlet")
public class ListStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ListStudentManager listAllMajor = new ListStudentManager();
		try {
			String selectMajorName = request.getParameter("selectMajorName");	
			String id = request.getParameter("id");
			if(selectMajorName == null && id == null){				
				List<MajorBean> listMajor = listAllMajor.findAllMajor();
				List<MajorBean> listEducationLevel = listAllMajor.findAllEducationLevelByMajorName(listMajor.get(listMajor.size()-1).getMajorName());	
				
				request.setAttribute("listEducationLevel",listEducationLevel);
				request.setAttribute("listMajor",listMajor);
				request.setAttribute("selectMajorName", listMajor.get(listMajor.size()-1).getMajorName());
				
				String selectEducation = "ปวช";
				String selectEducation1 = "1";
				List<StudentBean> listStudent = listAllMajor.findAllStudent(listMajor.get(listMajor.size()-1).getMajorName(), selectEducation,Integer.parseInt(selectEducation1));
				request.setAttribute("listStudent", listStudent);
				request.getRequestDispatcher("ListStudent.jsp").forward(request, response);
			}else if(selectMajorName != null){
				List<MajorBean> listMajor = listAllMajor.findAllMajor();
				List<MajorBean> listEducationLevel = listAllMajor.findAllEducationLevelByMajorName(selectMajorName);
				
				request.setAttribute("listEducationLevel",listEducationLevel);
				request.setAttribute("listMajor",listMajor);
				request.setAttribute("selectMajorName", selectMajorName);
				
				String selectMajorName11= request.getParameter("selectLevelEdu");
				String[] result = selectMajorName11.split(" ");
				String selectEducation = result[0];
				String selectEducation1 = result[1];
				request.setAttribute("selectEducation", selectEducation);
				request.setAttribute("selectEducation1", selectEducation1);
				
				List<StudentBean> listStudent = listAllMajor.findAllStudent(selectMajorName, selectEducation,Integer.parseInt(selectEducation1));
				request.setAttribute("listStudent", listStudent);
				
				request.getRequestDispatcher("ListStudent.jsp").forward(request, response);
			}else if (id != null) {
				if (listAllMajor.removeStudent(id)) {
					request.setAttribute("ErrorColor", "green");
					request.setAttribute("ErrorMassage", "ลบข้อมูลนักศึกษาร้อยแล้ว");
					request.getRequestDispatcher("ListStudent.jsp").forward(request, response);
				}else{
					request.setAttribute("ErrorColor", "red");
					request.setAttribute("ErrorMassage", "ลบข้อมูลนักศึกษาไม่สำเร็จ");
					request.getRequestDispatcher("ListStudent.jsp").forward(request, response);
				}
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
