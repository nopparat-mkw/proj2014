package com.project.add_student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.bean.AddressBean;
import com.project.bean.EducationLevelBean;
import com.project.bean.MajorBean;
import com.project.bean.ParentBean;
import com.project.bean.StudentBean;

/**
 * Servlet implementation class RegisterStudentServlet
 */
@WebServlet("/RegisterStudentServlet")
public class RegisterStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		RegisterStudentManager regisMng = new RegisterStudentManager();
		String majorName = request.getParameter("selectMajorName");
		if(majorName==null){			
			try {
				List<MajorBean> listMajor = regisMng.findAllMajor();
				
				String json = new Gson().toJson(listMajor);
				response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
			    response.getWriter().write(json);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else{					
			try {
				List<MajorBean> listEducation = regisMng.findAllEducationLevelByMajorName(majorName);
				String json = new Gson().toJson(listEducation);
				response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
			    response.getWriter().write(json);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if (request.getParameter("studentID_stu") != null ) {
				String studentID_stu = request.getParameter("studentID_stu");
				String antecedent_stu = request.getParameter("antecedent_stu");
				String firstName_stu = request.getParameter("firstName_stu");
				String lastName_stu = request.getParameter("lastName_stu");
				
				String selectMajorName = request.getParameter("selectMajorName");
				String selectEducation = request.getParameter("selectEducation");
				
				String antecedent_par = request.getParameter("antecedent_par");
				String firstName_par = request.getParameter("firstName_par");
				String lastName_par = request.getParameter("lastName_par");
				
				String addNo_regis = request.getParameter("addNo_regis");
				String moo_regis = request.getParameter("moo_regis");				
				String street_regis = request.getParameter("street_regis");
				String subDistrict_regis = request.getParameter("subDistrict_regis");
				String district_regis = request.getParameter("district_regis");
				String province_regis = request.getParameter("province_regis");
				String zipCode_regis = request.getParameter("zipCode_regis");
				
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
					int EducationLevel_ID = regisMng.findIdEducationLevel(major);
					if (EducationLevel_ID != 0) {
						System.out.println("ค้นหารหัส สาขาวิชาสำเร็จ ID : "+EducationLevel_ID);
						boolean addStudent = regisMng.addStudentAndParent(student, EducationLevel_ID);
						if (addStudent) {
							System.out.println("บันทึก นักศึกษา และผุ้ปกครอง สำเร็จ");
							int Parent_ID = regisMng.findParentID(student);
							if (Parent_ID != 0) {
								System.out.println("ค้นหารหัสผู้ปกครอง สำเร็จ");
								boolean addAddress = regisMng.addAddressByParentID(address, Parent_ID);
								if (addAddress) {
									System.out.println("บันทึกที่อยู่สำเร็จ");
								}else{
									System.out.println("บันทึกที่อยู่ไม่สำเร็จ");
								}
							}else{
								System.out.println("ไม่พบชื่อผู้ปกครอง");
							}
						}else{
							System.out.println("บันทึก นักศึกษา และผุ้ปกครอง ไม่สำเร็จ");
						}
					}else{
						System.out.println("ไม่พบแผนกวิชา และชั้นปีการศึกษา");
					}
					response.sendRedirect("ListStudentServlet");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
