package com.project.import_student;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.project.bean.MajorBean;

/**
 * Servlet implementation class ImportStudentServlet
 */
@WebServlet("/ImportStudentServlet")
public class ImportStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ImportStudentServlet() {
		super();

	}
	
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		ImportStudentManager impMng = new ImportStudentManager();

		HttpSession session = request.getSession();

		final String UPLOAD_DIRECTORY = request.getServletContext().getRealPath("/fileExcel");
		List<MajorBean> authorList = null;
		String filename = "";

		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				for (FileItem item : multiparts) {
					if (!item.isFormField()) {
						String name = new File(item.getName()).getName();
						item.write(new File(UPLOAD_DIRECTORY + File.separator + name));
						filename = UPLOAD_DIRECTORY + File.separator + name;
						authorList = impMng.readCSV(filename);
					}
				}

				for (int i = 0; i < authorList.size(); i++) {
					int majorID = impMng.findMajorIdByMajorName(authorList.get(i).getMajorName());

					if (majorID != 0) {
						int educationLevel_ID = impMng.findEducationLevel(authorList.get(i).getEducationLevel().getEducationalBackground(), 
								authorList.get(i).getEducationLevel().getEducationLevel(), majorID);
						if(educationLevel_ID != 0){
							// findStudentID
							boolean student_ID = impMng.findstudentID(authorList.get(i).getEducationLevel().getStudent().getStudentID());
							if (student_ID) {
								//update
								boolean check_update = impMng.updateDataStudent(authorList.get(i).getEducationLevel().getStudent());
								if (check_update) {
									System.out.println("Update Success");
									request.setAttribute("ErrorColor", "green");
									request.setAttribute("CompleteMessage",
											"Update Data Success");
								} else {
									System.out.println("Update Error");
									request.setAttribute("ErrorColor", "red");
									request.setAttribute("CompleteMessage",
											"Update Data Fail");
								}
							}else{
								//insert
								boolean chk_insert_student = impMng.importDataStudentAndParent(authorList.get(i).getEducationLevel().getStudent(), educationLevel_ID);
								int parent_Id = impMng.findMaxPersonIdForInsertToAddress();
								boolean chk_insert_address = impMng.importDataAddress(authorList.get(i).getEducationLevel().getStudent().getParent().getAddress(), parent_Id);

						if (chk_insert_student && chk_insert_address) {
							System.out.println("Insert Success");
							request.setAttribute("ErrorColor", "green");
							request.setAttribute("CompleteMessage",
									"Insert Data Success");
						} else {
							System.out.println("Insert Error");
							request.setAttribute("ErrorColor", "red");
							request.setAttribute("CompleteMessage",
									"Insert Data Fail");
						}
							}
						}else{
							System.out.println("ไม่มีชั้นปีนี้อยู่ในสาขาวิชา");
						}
					} else if(majorID == 0) {
						System.out.println("ไม่มีสาขาวิชาวิชานี้อยู่ในระบบ");
					}
				}

				deletefile(filename);
				session.setAttribute("listAuthor", authorList);
				response.sendRedirect("ListStudentServlet");

			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			request.getRequestDispatcher("ImportStudentPage.jsp").forward(
					request, response);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		doGet(request, response);
	}

	private void deletefile(String file) {
		File f1 = new File(file);
		boolean success = f1.delete();
		if (!success) {
			System.out.println("Deletion failed.");
			System.exit(0);
		} else {
			System.out.println("File deleted.");
		}
	}
}
