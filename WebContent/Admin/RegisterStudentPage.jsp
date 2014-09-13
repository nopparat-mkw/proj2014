<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row">
<form id="regis-student" data-remote="true"
		action="RegisterStudentServlet" method="post" name="addStudent" class="form-horizontal" role="form">
			<!-- edit form column -->
				<h3>Student info</h3>
				<hr>
				<div class="form-group">
						<label class="col-lg-3 control-label">รหัสประจำตัวนักศึกษา :</label>
						<div class="col-lg-8">
							<input class="form-control" type="text" name="studentID_stu">
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">คำนำหน้าชื่อ :</label>
						<div class="col-lg-8">
							<input class="form-control" type="text" name="antecedent_stu">
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">ชื่อ :</label>
						<div class="col-lg-8">
							<input class="form-control" type="text" name="firstName_stu">
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">นามสกุล :</label>
						<div class="col-lg-8">
							<input class="form-control" type="text" name="lastName_stu">
						</div>
					</div>					
					<div class="form-group">
						<label class="col-lg-3 control-label">แผนกวิชา :</label>
						<div class="col-lg-8">
							<div class="ui-select">
								<select name="selectMajorName" id="selectMajorName" class="form-control" onchange="ListEducation()"></select>								
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-3 control-label">ชั้นปีการศึกษา :</label>
						<div class="col-lg-8">
							<div class="ui-select">
								<select name="selectEducation" id="selectEducation" class="form-control" ></select>
							</div>
						</div>
					</div>

					<h3>Parent info</h3>
					<hr>
					<div class="form-group">
						<label class="col-lg-3 control-label">คำนำหน้าชื่อผู้ปกครอง :</label>
						<div class="col-lg-8">
							<input class="form-control" type="text" name="antecedent_par">
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">ชื่อผู้ปกครอง :</label>
						<div class="col-lg-8">
							<input class="form-control" type="text" name="firstName_par">
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">นามสกุลผู้ปกครอง :</label>
						<div class="col-lg-8">
							<input class="form-control" type="text" name="lastName_par">
						</div>
					</div>				
					
					<div class="form-group">
						<label class="col-md-3 control-label">บ้านเลขที่ :</label>
						<div class="col-md-8">
							<input class="form-control" type="text" name="addNo_regis">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">หมู่ :</label>
						<div class="col-md-8">
							<input class="form-control" type="text" name="moo_regis">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">ถนน :</label>
						<div class="col-md-8">
							<input class="form-control" type="text" name="street_regis">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">ตำบล/แขวง :</label>
						<div class="col-md-8">
							<input class="form-control" type="text" name="subDistrict_regis">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">อำเภอ/เขต :</label>
						<div class="col-md-8">
							<input class="form-control" type="text" name="district_regis">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">จังหวัด :</label>
						<div class="col-md-8">
							<input class="form-control" type="text" name="province_regis">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">รหัสไปรษณีย์ :</label>
						<div class="col-md-8">
							<input class="form-control" type="text" name="zipCode_regis">
						</div>
					</div>		
			</form>
		</div>