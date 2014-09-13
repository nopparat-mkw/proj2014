package com.project.edit_student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.project.bean.AddressBean;
import com.project.bean.EducationLevelBean;
import com.project.bean.HolidayBean;
import com.project.bean.MajorBean;
import com.project.bean.ParentBean;
import com.project.bean.StudentBean;
import com.project.editholiday.EditHolidayManager;

/**
 * Servlet implementation class EditStudentServlet
 */
@WebServlet("/EditStudentServlet")
public class EditStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		EditStudentManager editMng = new EditStudentManager();
		String majorName = request.getParameter("selectMajorNameEdit");
		if(majorName == null){
			String studentID = request.getParameter("id");
			StudentBean studentBean;
			try {
				List<MajorBean> listMajor = editMng.findAllMajor();				
				studentBean = editMng.findStudentByStudentID(studentID);
				String json1 = new Gson().toJson(studentBean);
				String json2 = new Gson().toJson(listMajor);
			    response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
			    String bothJson = "["+json1+","+json2+"]"; 
			    response.getWriter().write(bothJson);
			    
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if (request.getParameter("selectEducationEdit") == null){
				try {
					List<MajorBean> listEducation = editMng.findAllEducationLevelByMajorName(majorName);
					System.out.println(listEducation.get(0).getEducationLevel().getEducationalBackground());
					String json = new Gson().toJson(listEducation);
					response.setContentType("application/json");
				    response.setCharacterEncoding("UTF-8");
				    response.getWriter().write(json);
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}else{
			String studentID_stu = request.getParameter("studentID");
			String antecedent_stu = request.getParameter("studentAntecedent");
			String firstName_stu = request.getParameter("studentFirstName");
			String lastName_stu = request.getParameter("studentLastName");
			
			String selectMajorName = request.getParameter("selectMajorNameEdit");
			String selectEducation = request.getParameter("selectEducationEdit");
			
			String antecedent_par = request.getParameter("parentAntecedent");
			String firstName_par = request.getParameter("parentFirstName");
			String lastName_par = request.getParameter("parentLastName");
			
			String addNo_regis = request.getParameter("parentAddresss");
			String moo_regis = request.getParameter("parentMoo");				
			String street_regis = request.getParameter("parentStreet");
			String subDistrict_regis = request.getParameter("parentSubDistrict");
			String district_regis = request.getParameter("parentDistrict");
			String province_regis = request.getParameter("parentProvince");
			String zipCode_regis = request.getParameter("parentZipCode");
			
			AddressBean address = new AddressBean(addNo_regis, moo_regis, street_regis, subDistrict_regis, district_regis, province_regis, zipCode_regis);
			ParentBean parent = new ParentBean(antecedent_par, firstName_par, lastName_par);
			parent.setAddress(address);
			StudentBean student = new StudentBean(studentID_stu, antecedent_stu, firstName_stu, lastName_stu);
			student.setParent(parent);
			
			String[] result = selectEducation.split(" ");
			String eduBackground = result[0];
			String eduLevel = result[1];
			
			EducationLevelBean education = new EducationLevelBean(Integer.parseInt(eduLevel),eduBackground, student);
			MajorBean major = new MajorBean(selectMajorName);
			major.setEducationLevel(education);
			
			try {
				boolean check_update = editMng.updateDataStudent(major);
				if (check_update) {
					System.out.println("Update Student Success");
					request.setAttribute("ErrorColor", "green");
					request.setAttribute("CompleteMessage",
							"Update Data Success");
				} else {
					System.out.println("Update Student Error");
					request.setAttribute("ErrorColor", "red");
					request.setAttribute("CompleteMessage",
							"Update Data Fail");
				}
				response.sendRedirect("ListStudentServlet");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		doGet(request, response);
	}

}
