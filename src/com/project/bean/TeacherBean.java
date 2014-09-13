package com.project.bean;
import java.util.Vector;

public class TeacherBean extends PersonBean {
	private String idCard;
	private String vacancy;
	private String email;
	private String phone;
	Vector<EducationBean> educationVector = new Vector<EducationBean>();
	MajorBean major;
	EducationBean education;
	String path_image;

	public TeacherBean() {
	}

	public TeacherBean(String idCard, String antecedent, String firstName,
			String lastName, String vacancy, String email, String phone) {
		super(antecedent, firstName, lastName);
		this.idCard = idCard;
		this.email = email;
		this.vacancy = vacancy;
		this.phone = phone;
	}

	public String getIdCard() {
		return idCard;
	}

	public String getEmail() {
		return email;
	}

	public String getVacancy() {
		return vacancy;
	}

	public String getPhone() {
		return phone;
	}

	public Vector<EducationBean> getEducationVector() {
		return educationVector;
	}

	public MajorBean getMajor() {
		return major;
	}
	
	public void addMajorVacancy(EducationBean education){
		this.educationVector.add(education);
	}

	public void setMajor(MajorBean major) {
		this.major = major;
	}

	public EducationBean getEducation() {
		return education;
	}

	public void setEducation(EducationBean education) {
		this.education = education;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public void setVacancy(String vacancy) {
		this.vacancy = vacancy;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setEducationVector(Vector<EducationBean> educationVector) {
		this.educationVector = educationVector;
	}

	public String getPath_image() {
		return path_image;
	}

	public void setPath_image(String path_image) {
		this.path_image = path_image;
	}

}
