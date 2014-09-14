<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
				<!-- /.Search Education -->
				<div class="row form-group" align="center">
					<form class="form-inline" role="form">
						<div class="form-group">
							<input class="form-control" id="DateShow" type="text" disabled>
						</div>
						<div class="form-group">
							<input class="form-control" id="EducationShow" type="text" disabled>
						</div>
						<div class="form-group">
							<input class="form-control" id="TermShow" type="text"  disabled>
						</div>
					</form>
				</div>

				<div class="table-responsive">
					<form>
						<table id="modal-table" class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>ลำดับ</th>
									<th>รหัสประจำตัวนักศึกษา</th>
									<th>ชื่อ</th>
									<th>นามสกุล</th>
									<th>ผลการเช็คชื่อ</th>
								</tr>
							</thead>
							<tbody id="tbody-show">
								<c:forEach items="${listSchedule}" var="itemList" varStatus="theCount">
								<tr>
									<td >${theCount.count}</td>
									<td >${itemList.attendance.student.studentID}</td>
									<td >${itemList.attendance.student.antecedent}  ${itemList.attendance.student.firstName }</td>
									<td >${itemList.attendance.student.lastName}</td>
									<td >${itemList.attendance.statusActivity}</td>
								</tr>
								</c:forEach>
									
							</tbody>
						</table>

					</form>
				</div>
				<!-- /.box-body -->