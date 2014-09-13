<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List Teacher</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">

<link href="Admin/css/bootstrap.min.css" rel="stylesheet" />
<link href="Admin/css/font-awesome.min.css" rel="stylesheet" />
<link href="Admin/css/ionicons.min.css" rel="stylesheet" />
<link href="Admin/css/morris/morris.css" rel="stylesheet" />
<link href="Admin/css/jvectormap/jquery-jvectormap-1.2.2.css" />
<link href="Admin/css/fullcalendar/fullcalendar.css" rel="stylesheet" />
<link href="Admin/css/daterangepicker/daterangepicker-bs3.css" />
<link href="Admin/css/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css" />
<link href="Admin/css/AdminLTE.css" rel="stylesheet" />
<link rel="stylesheet" href="css/jasny-bootstrap.css">

<script type="text/javascript" src="js/customScript.js"></script>
</head>
<body class="skin-blue">
	<!--------------Header--------------->
	<jsp:include page="Admin/Header.jsp" />

	<div class="wrapper row-offcanvas row-offcanvas-left">
		<!---------------Menu---------------->
		<%@include file="Admin/Menu.jsp" %>
<%-- 		<jsp:include page="Admin/Menu.jsp" /> --%>
		<aside class="right-side"> <!-- Content Header (Page header) -->
		<section class="content-header">
		<h1>
			<i class="glyphicon glyphicon-user"></i>&nbsp;&nbsp;List Teacher <small>Control
				panel</small>
		</h1>
		</section> <section class="content">

		<div class="form-group">
			<table class="span9 table table-hover">
				<thead>
					<tr>
						<th>ลำดับที่</th>
						<th colspan="2">ชื่อ - นามสกุล</th>
						<th>แผนกวิชาที่รับผิดชอบ</th>
						<th></th>
					</tr>
				</thead>
				<tbody>


					<c:forEach items="${listTeacher}" var="item" varStatus="theCount">
						<tr>
							<td class="TextCenter">${theCount.count}</td>
							<td class="TextCenter">${item.antecedent}&nbsp;&nbsp;${item.firstName}</td>
							<td class="TextCenter">${item.lastName}</td>
							<td class="TextCenter">${item.major.majorName}</td>
							<td class="TextCenter">
								<button type="button" class="btn btn-primary btn-circle"
									data-toggle="modal" data-target="#editTeacher"
									onclick="editTeacherByIdCard(${item.idCard})">
									<i class="glyphicon glyphicon-pencil" Title="แก้ไข"></i>
								</button>
								
								<a onclick="return remove_teacher(${item.idCard});">
									<button type="button" class="btn btn-danger btn-circle">
										<i class="glyphicon glyphicon-trash" Title="ลบ"></i>
									</button>
								</a>

							</td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="5" align="right">
							<div class="form-group">
								<button class="btn btn-primary btn-lg" data-toggle="modal"
									data-target="#addTeacher">
									<i class="glyphicon glyphicon-plus"></i>&nbsp;&nbsp;เพิ่มอาจารย์
								</button>
								<button type="submit" class="btn btn-primary btn-lg" onclick="window.location.href='ImportTeacherServlet'"><i class="ion ion-upload"></i>&nbsp;&nbsp;อัพโหลดไฟล์</button>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		</section> </aside>
	</div>
	<!-- Modal -->
	<div class="modal fade" id="uploadTeacher">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">เพิ่มอาจารย์ อัพโหลดไฟล์</h4>
				</div>
				<div class="modal-body">
					<jsp:include page="Admin/RegisterTeacherUploadfilePage.jsp"
						flush="false" />
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">ยกเลิก</button>
					<button type="button" class="btn btn-info">
						<i class="ion ion-upload"></i>&nbsp;&nbsp;Upload
					</button>

				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
<form id="modal-form-regis" accept-charset="UTF-8" data-remote="true"
	method="post" action="RegisterTeacherServlet"
	enctype="multipart/form-data" class="form-horizontal">
	<div class="modal fade" id="addTeacher">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">เพิ่มอาจารย์</h4>
				</div>
				<div class="modal-body">
					<jsp:include page="Admin/RegisterTeacherPage.jsp" flush="false" />
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">ยกเลิก</button>
					<input type="submit" value="เพิ่ม" class='btn btn-primary'>
				</div>
				
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	</form>
	<!-- /.modal -->

	<div class="modal fade" id="editTeacher">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">แก้ไขข้อมูลอาจารย์</h4>
				</div>
				<div class="modal-body">
					<jsp:include page="Admin/EditTeacherPage.jsp" flush="false" />
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">ยกเลิก</button>
					<a class='btn btn-primary' onclick="document.getElementById('formEditTeacher').submit();">บันทึก</a>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<!-- script -->
	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
	<script src="Admin/js/jquery-ui-1.10.3.min.js" type="text/javascript"></script>
	<script src="Admin/js/bootstrap.min.js" type="text/javascript"></script>
	<script
		src="//cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
	<script src="Admin/js/plugins/morris/morris.min.js"
		type="text/javascript"></script>
	<script src="Admin/js/plugins/sparkline/jquery.sparkline.min.js"
		type="text/javascript"></script>
	<script
		src="Admin/js/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"
		type="text/javascript"></script>
	<script
		src="Admin/js/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"
		type="text/javascript"></script>

	<!-- fullCalendar -->
	<script src="Admin/js/plugins/iCheck/icheck.min.js"
		type="text/javascript"></script>
	<script src="Admin/js/AdminLTE/app.js" type="text/javascript"></script>


	<script src="jasny-bootstrap.js" type="text/javascript"></script>
	<script src="js/jasny-bootstrap.min.js" type="text/javascript"></script>



</body>
</html>