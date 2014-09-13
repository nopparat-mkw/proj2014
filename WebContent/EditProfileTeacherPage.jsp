<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="row">
<form id="modal-formEditTeacher" accept-charset="UTF-8" data-remote="true"
		action="EditProfileTeacherServlet" method="post" class="form-horizontal" role="form" >
			<!-- edit form column -->
				<h3>ข้อมูลส่วนตัว</h3>
				<div class="form-group">
						<label class="col-lg-3 control-label">รหัสประจำตัวประชาชน :</label>
						<div class="col-lg-8">
							<input class="form-control" type="text" id="EditProfileIdCard" disabled="disabled">
						</div>
					</div>
					<input type="hidden" name="profileIdCard" id="EditProfileIdCards">
					<div class="form-group">
						<label class="col-lg-3 control-label">คำนำหน้าชื่อ :</label>
						<div class="col-lg-8">
							<input class="form-control" type="text" id="EditProfileAntecedent" name="profileAntecedent">
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">ชื่อ :</label>
						<div class="col-lg-8">
							<input class="form-control" type="text" id="EditProfileFirstName" name="profileFirstName">
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">นามสกุล :</label>
						<div class="col-lg-8">
							<input class="form-control" type="text" id="EditProfileLastName"  name="profileLastName">
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">อีเมล :</label>
						<div class="col-lg-8">
							<input class="form-control" type="text" id="EditProfileEmail"  name="profileEmail">
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">เบอร์โทรศัพท์ :</label>
						<div class="col-lg-8">
							<input class="form-control" type="text" id="EditProfilePhone"  name="profilePhone">
						</div>
					</div>
<%-- 					<input type="hidden" name="username" value="${login.username }"> --%>
<%-- 					<input type="hidden" name="password" value="${login.password }"> --%>
					
<!-- 					<div class="form-group"> -->
<!-- 						<label class="col-md-3 control-label">Username :</label> -->
<!-- 						<div class="col-md-8"> -->
<!-- 							<input class="form-control" type="text" disabled="disabled" id="EditProfileUsername"  name="profileUsername"> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<div class="form-group"> -->
<!-- 						<label class="col-md-3 control-label">Old Password :</label> -->
<!-- 						<div class="col-md-8"> -->
<!-- 							<input class="form-control" type="password"  name="profileOldPassword"> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<div class="form-group"> -->
<!-- 						<label class="col-md-3 control-label">New Password :</label> -->
<!-- 						<div class="col-md-8"> -->
<!-- 							<input class="form-control" type="password"  name="profileNewPassword"> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<div class="form-group"> -->
<!-- 						<label class="col-md-3 control-label">Confirm password :</label> -->
<!-- 						<div class="col-md-8"> -->
<!-- 							<input class="form-control" type="password"  name="profileConfirmPassword"> -->
<!-- 						</div> -->
<!-- 					</div>		 -->
						
			</form>
		</div>