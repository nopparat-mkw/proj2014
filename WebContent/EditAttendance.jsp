<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="description" content="">
<meta name="viewport" content="width=device-width">

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="${contextPath}/js/customScript.js"></script>

<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/icomoon-social.css">
<link rel="stylesheet" href="css/leaflet.css" />
<link rel="stylesheet" href="css/main.css">
<link href="css/plugins/stylesheet-image-based.css" rel="stylesheet">
<script src="js/modernizr-2.6.2-respond-1.1.0.min.js"></script>

<link href="font-awesome/css/font-awesome.css" rel="stylesheet">
<link rel="stylesheet" href="css/icomoon-social.css">
<link href="css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
 <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script type="text/javascript">
	   $(document).ready(function() {
	       $('#example2').dataTable();
	   });
   </script>
<title>Edit Attendance</title>
</head>
<body>
	<!--------------Header--------------->
	<jsp:include page="Header.jsp" />
	<!---------------Menu---------------->
<%-- 	<jsp:include page="Menu.jsp" /> --%>
	<%@include file="Menu.jsp" %>
	<div class="section">
		<div class="container-fluid">
			<div id="page-wrapper">
				<div class="row">
					<div class="col-md-10 col-md-offset-1">
						<div class="panel panel-default">
							<div class="panel-heading">แก้ไขการเช็คชื่อนักศึกษา</div>
							<div class="panel-body">

								<!-- /.Search Education -->
								<div class="row form-group" align="center">
					<form class="form-inline" role="form">
						<div class="form-group">
							<input class="form-control" id="DateShowss" type="text" disabled>
						</div>
						<div class="form-group">
							<input class="form-control" id="EducationShowss" type="text" disabled>
						</div>
						<div class="form-group">
							<input class="form-control" id="TermShowss" type="text"  disabled>
						</div>
					</form>
				</div>

								<div class="table-responsive">
									<form>
										<table id="example2" class="table table-bordered table-hover">
											<thead>
												<tr>
													<th>ลำดับ</th>
													<th>รหัสประจำตัวนักศึกษา</th>
													<th>คำนำหน้าชื่อ</th>
													<th>ชื่อ</th>
													<th>นามสกุล</th>
													<th>ผลการเช็คชื่อ</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${listSchedule}" var="itemList" varStatus="theCount">
								<tr>
									<td >${theCount.count}</td>
									<td >${itemList.attendance.student.studentID}</td>
									<td >${itemList.attendance.student.antecedent}</td>
									<td >${itemList.attendance.student.firstName }</td>
									<td >${itemList.attendance.student.lastName}</td>
									<td >${itemList.attendance.statusActivity}</td>
								</tr>
								</c:forEach>
											</tbody>
										</table>
										<div align="center">
											<button type="submit" class="btn btn-default">บันทึก</button>
											<button type="reset" class="btn btn-default">ยกเลิก</button>
										</div>

									</form>
								</div>
								<!-- /.box-body -->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--------------Footer--------------->
	<jsp:include page="Footer.jsp" />

	<!-- Javascripts -->
	<script	src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

	<script src="js/bootstrap.min.js"></script>
	<script src="http://cdn.leafletjs.com/leaflet-0.5.1/leaflet.js"></script>
	<script src="js/jquery.fitvids.js"></script>
	<script src="js/jquery.sequence-min.js"></script>
	<script src="js/jquery.bxslider.js"></script>
	<script src="js/main-menu.js"></script>
	<script src="js/template.js"></script>
	<script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>
	<script src="js/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="js/plugins/dataTables/dataTables.bootstrap.js"></script>
    <script src="js/sb-admin.js"></script>
    <script type="text/javascript" src="js/customScript.js"></script>

</body>
</html>