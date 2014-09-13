package com.project.listholiday;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import com.project.bean.HolidayBean;
import com.project.utility.ConnectDB;

public class ListHolidayManager {
	
	public List<HolidayBean> findAllHolidayInYear(String year) throws SQLException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * from holiday where year = ? order by month ASC,date ASC";
		List<HolidayBean> listHoliday = new ArrayList<HolidayBean>();
		try {
			dbConnection = ConnectDB.getInstance().DBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setString(1, year);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				HolidayBean holiday = new HolidayBean();
				holiday.setDate(Integer.parseInt(rs.getString("date")));
				holiday.setMonth(Integer.parseInt(rs.getString("month")));
				holiday.setYear(Integer.parseInt(rs.getString("year")));
				holiday.setDetail(rs.getString("detail"));
				listHoliday.add(holiday);
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
		return listHoliday;
	}
	
	public List<HolidayBean> findAllHolidayAllYear(List<HolidayBean> listHoliday,int year) throws SQLException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * from holiday where year = ? order by month ASC,date ASC";
		try {
			dbConnection = ConnectDB.getInstance().DBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, year);;
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				HolidayBean holiday = new HolidayBean();
				holiday.setDate(Integer.parseInt(rs.getString("date")));
				holiday.setMonth(Integer.parseInt(rs.getString("month")));
				holiday.setYear(year);
				holiday.setDetail(rs.getString("detail"));
				listHoliday.add(holiday);
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
		return sortHoliday(listHoliday);
	}
	
	public List<HolidayBean> sortHoliday(List<HolidayBean> listHoliday){
		Calendar startDay = Calendar.getInstance(Locale.ENGLISH);
		Calendar endDay = Calendar.getInstance(Locale.ENGLISH);
		for(int i = 0;i<listHoliday.size()-1;i++){
			for(int j = i+1;j<listHoliday.size();j++){
				HolidayBean holiday1 = listHoliday.get(i);
				HolidayBean holiday2 = listHoliday.get(j);
				startDay.set(holiday1.getYear(), holiday1.getMonth()-1, holiday1.getDate());
				endDay.set(holiday2.getYear(), holiday2.getMonth()-1, holiday2.getDate());
				if(!startDay.getTime().before(endDay.getTime())){
					HolidayBean tmp = listHoliday.get(i);
					listHoliday.set(i, holiday2);
					listHoliday.set(j, tmp);
				}
			}
		}
		return listHoliday;
	}
	
	public List<HolidayBean> findAllYear() throws SQLException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT DISTINCT year from holiday ORDER BY year";
		List<HolidayBean> listHoliday = new ArrayList<HolidayBean>();
		try {
			dbConnection = ConnectDB.getInstance().DBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				HolidayBean holiday = new HolidayBean();
				holiday.setYear(Integer.parseInt(rs.getString("year")));
				listHoliday.add(holiday);
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
		return listHoliday;
	}
}
