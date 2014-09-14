package com.project.edit_attendance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.project.bean.AttendanceBean;
import com.project.bean.ScheduleBean;
import com.project.bean.StudentBean;
import com.project.utility.ConnectDB;

public class EditAttendanceManager {
	public List<ScheduleBean> listStudentAndSchedule(String majorName, String eduBackground, int eduLevel, String term, Date dateAttendance)
			throws SQLException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT student.studentID, student.antecedent, student.firstName ,student.lastName, attendance.statusActivity"
				+ " FROM student" + " JOIN educationlevel ON educationlevel.educationLevel_ID = student.educationLevel_ID"
				+ " JOIN major ON major.major_ID = educationlevel.major_ID" + " JOIN attendance ON attendance.studentID = student.studentID"
				+ " JOIN schedule ON schedule.schedule_ID = attendance.schedule_ID" + " WHERE major.majorName= ?"
				+ " AND educationLevel.educationalBackground = ?" + " AND educationLevel.educationLevel = ?" + " AND schedule.term = ?"
				+ " AND schedule.dateAttendance = ?";
		List<ScheduleBean> listSchedule = new ArrayList<ScheduleBean>();
		try {
			dbConnection = ConnectDB.getInstance().DBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setString(1, majorName);
			preparedStatement.setString(2, eduBackground);
			preparedStatement.setInt(3, eduLevel);
			preparedStatement.setString(4, term);

			java.sql.Date sqlCreateDate = new java.sql.Date(dateAttendance.getTime());
			preparedStatement.setDate(5, sqlCreateDate);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				StudentBean studentBean = new StudentBean();
				studentBean.setStudentID(rs.getString("studentID"));
				studentBean.setAntecedent(rs.getString("antecedent"));
				studentBean.setFirstName(rs.getString("firstName"));
				studentBean.setLastName(rs.getString("lastName"));

				AttendanceBean attendanceBean = new AttendanceBean();
				attendanceBean.setStatusActivity(rs.getString("statusActivity"));
				attendanceBean.setStudent(studentBean);

				ScheduleBean scheduleBean = new ScheduleBean();
				scheduleBean.setAttendance(attendanceBean);
				listSchedule.add(scheduleBean);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return listSchedule;
	}
}
