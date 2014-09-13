package com.project.import_teacher;

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

import com.project.bean.TeacherBean;

/**
 * Servlet implementation class ImportTeacherServlet
 */
@WebServlet("/ImportTeacherServlet")
public class ImportTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImportTeacherServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		importTeacherManager impMng = new importTeacherManager();

		HttpSession session = request.getSession();

		final String UPLOAD_DIRECTORY = request.getServletContext().getRealPath("/fileExcel");
		List<TeacherBean> authorList = null;
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
					boolean chkIdCard = impMng.findIdCardTeacher(authorList.get(i).getIdCard());
					if (chkIdCard) {
						// ค้นหาชื่อสาขาวิชาในฐานข้อมูล
						boolean chkMajorName = impMng.finMajorName(authorList.get(i).getMajor().getMajorName());
							if (chkMajorName) {
								// มีข้อมูลให้ทำการ Update
								boolean chkUpdate = impMng.updateDataTeacher(authorList.get(i));
								if (chkUpdate) {
									System.out.println("Update Teacher By IdCard : "+authorList.get(i).getIdCard()+" : Success");
								}
							}else{
								// ไม่มีสาขาวิชา ไม่สามารถ update ได้
							}
					}else{
						// ค้นหาชื่อสาขาวิชาในฐานข้อมูล
						boolean chkMajorName = impMng.finMajorName(authorList.get(i).getMajor().getMajorName());
						if (chkMajorName) {
							// ไม่มีข้อมูลทำการ Insert
							boolean chkInsert = impMng.importDataTeacher(authorList.get(i));
							if (chkInsert) {
								System.out.println("insert Teacher By IdCard : "+authorList.get(i).getIdCard()+" : Success");
							}
						}
					}
				}

				deletefile(filename);
				session.setAttribute("listAuthor", authorList);
				response.sendRedirect("ListTeacherServlet");

			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			request.getRequestDispatcher("ImportTeacherPage.jsp").forward(
					request, response);
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
