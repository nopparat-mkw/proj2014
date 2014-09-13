<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form id="edit-studentForm" accept-charset="UTF-8" data-remote="true"
		action="EditStudentServlet" method="post" class="form-horizontal" role="form">
			<!-- edit form column -->

				<hr>
				<div class="form-group">
						<label class="col-lg-3 control-label">รหัสประจำตัวนักศึกษา :</label>
						<div class="col-lg-8">
							<input class="form-control" type="text" id="EditStudentID" disabled="disabled">
							<input class="form-control" type="hidden" id="EditStudentIDs" name="studentID">
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">คำนำหน้าชื่อ :</label>
						<div class="col-lg-8">
							<input class="form-control" type="text" id="EditStudentAntecedent" name="studentAntecedent">
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">ชื่อ :</label>
						<div class="col-lg-8">
							<input class="form-control" type="text" id="EditStudentFirstName" name="studentFirstName">
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">นามสกุล :</label>
						<div class="col-lg-8">
							<input class="form-control" type="text" id="EditStudentLastName" name="studentLastName">
						</div>
					</div>					
					<div class="form-group">
						<label class="col-lg-3 control-label">แผนกวิชา :</label>
						<div class="col-lg-8">
							<div class="ui-select">
								<select name="selectMajorNameEdit" id="selectMajorNameEdit" class="form-control" onchange="ListEducationEdit()"></select>								
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-3 control-label">ชั้นปีการศึกษา :</label>
						<div class="col-lg-8">
							<div class="ui-select">
								<select name="selectEducationEdit" id="selectEducationEdit" class="form-control" ></select>
							</div>
						</div>
					</div>
					<h3>Parent info</h3>
					<hr>
					<div class="form-group">
						<label class="col-lg-3 control-label">คำนำหน้าชื่อผู้ปกครอง :</label>
						<div class="col-lg-8">
							<input class="form-control" type="text" id="EditStudentAntecedentParent" name="parentAntecedent">
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">ชื่อผู้ปกครอง :</label>
						<div class="col-lg-8">
							<input class="form-control" type="text" id="EditStudentFirstNameParent" name="parentFirstName">
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">นามสกุลผู้ปกครอง :</label>
						<div class="col-lg-8">
							<input class="form-control" type="text" id="EditStudentLastNameParent" name="parentLastName">
						</div>
					</div>				
					
					<div class="form-group">
						<label class="col-md-3 control-label">บ้านเลขที่ :</label>
						<div class="col-md-8">
							<input class="form-control" type="text" id="EditStudentAddNoParent" name="parentAddresss">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">หมู่ :</label>
						<div class="col-md-8">
							<input class="form-control" type="text" id="EditStudentMooParent" name="parentMoo">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">ถนน :</label>
						<div class="col-md-8">
							<input class="form-control" type="text" id="EditStudentStreetParent" name="parentStreet">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">ตำบล/แขวง :</label>
						<div class="col-md-8">
							<input class="form-control" type="text" id="EditStudentSubDistrictParent" name="parentSubDistrict">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">อำเภอ/เขต :</label>
						<div class="col-md-8">
							<input class="form-control" type="text" id="EditStudentDistrictParent" name="parentDistrict">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">จังหวัด :</label>
						<div class="col-md-8">
							<input class="form-control" type="text" id="EditStudentProvinceParent" name="parentProvince">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">รหัสไปรษณีย์ :</label>
						<div class="col-md-8">
							<input class="form-control" type="text" id="EditStudentZipCodeParent" name="parentZipCode">
						</div>
					</div>		
			</form>