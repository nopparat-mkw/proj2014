<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

	<form id="modal-formEditTeacher" accept-charset="UTF-8"
		data-remote="true" action="EditHolidayServlet" method="get"
		name="editHolidayfrm" onSubmit="JavaScript:return editHolidaychk();"
		class="form-horizontal">
		
		<div class="form-group">
			<label class="col-lg-3 control-label">วัน/เดือน/ปี :</label>
			<div class="col-lg-8">
				<input class="form-control" type="text" id="EditHolidaydate"
					disabled="disabled">
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">รายละเอียด :</label>
			<div class="col-lg-8">
				<input class="form-control" type="text" name="detail"
					id="Editdetail">
			</div>
		</div>
		<input type="hidden" name="dates" id="Editdates"> 
		<input type="hidden" name="months" id="Editmonths"> 
		<input type="hidden" name="years" id="Edityears">
	</form>
