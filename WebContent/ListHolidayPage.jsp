<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<!-- Basic Page Needs
  ================================================== -->
<title>รายชื่อวันหยุด</title>
<meta name="description" content="Staff Main Page">
<meta name="author" content="www.zerotheme.com">

<!-- Mobile Specific Metas
  ================================================== -->
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<!-- CSS
  ================================================== -->
<link href="Admin/css/bootstrap.min.css" rel="stylesheet">
<link href="Admin/css/font-awesome.min.css" rel="stylesheet">
<link href="Admin/css/ionicons.min.css" rel="stylesheet">
<link href="Admin/css/morris/morris.css" rel="stylesheet">
<link href="Admin/css/jvectormap/jquery-jvectormap-1.2.2.css">
<link href="Admin/css/fullcalendar/fullcalendar.css" rel="stylesheet">
<link href="Admin/css/daterangepicker/daterangepicker-bs3.css">
<link href="Admin/css/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
<link href="Admin/css/AdminLTE.css" rel="stylesheet">

</head>
<body class="skin-blue">
	<!--------------Header--------------->
	<jsp:include page="Admin/Header.jsp" />

	<div class="wrapper row-offcanvas row-offcanvas-left">
		<!---------------Menu---------------->
<%-- 		<jsp:include page="Admin/Menu.jsp" /> --%>
		<%@include file="Admin/Menu.jsp" %>
		<aside class="right-side"> <!-- Content Header (Page header) -->
		<section class="content-header">
		<h1>
			<i class="fa fa-calendar"></i>&nbsp;&nbsp;List Holiday <small>Control
				panel</small>
		</h1>
		</section> <section class="content"> <!----- Select Year ----->

		<div class="col-md-2" style="float: right;">
			<form role="form" action="ListHolidayServlet" method="post">
				<div class="form-group">
					<div class="ui-select">
						<select id="year" name="selectYear" onchange="this.form.submit()"
							class="form-control">
							<c:forEach items="${listYear}" var="item">
								<c:choose>
									<c:when test="${item.year == selectYear}">
										<option value="${item.year}" selected="selected">${item.year}</option>
									</c:when>
									<c:otherwise>
										<option value="${item.year}">${item.year}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</div>
				</div>
			</form>
		</div>
		<!----- End Select Year ----->
		<div class="form-group">
			<table class="span9 table table-hover">
				<thead>
					<tr>
						<th>วัน</th>
						<th>เดือน</th>
						<th>ปี</th>
						<th>รายละเอียด</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ListHoliday}" var="item">
						<tr>
							<td class="TextCenter">${item.date}</td>
							<td class="TextCenter">${item.month}</td>
							<td class="TextCenter">${item.year}</td>
							<td class="TextCenter">${item.detail}</td>
							<td class="TextCenter">
								<button type="button" class="btn btn-primary btn-circle"
									data-toggle="modal" data-target="#editHoliday"
									onclick="editHoliday(${item.date},${item.month},${item.year})">
									<i class="glyphicon glyphicon-pencil" Title="แก้ไข"></i>
								</button>
								<a
										href="RemoveHolidayServlet?date=${item.date}&month=${item.month}&year=${item.year}"
										OnClick="return confirm_delete();">
								<button type="button" class="btn btn-danger btn-circle">
									<i class="glyphicon glyphicon-trash" Title="ลบ"></i>
								</button>
								</a>
							</td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="6" align="right">
							<div class="form-group">
								<button class="btn btn-primary btn-lg" data-toggle="modal"
									data-target="#addHoliday">เพิ่มวันหยุด</button>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<!-- /.row -->
	</div>
	<!-- top row -->
	</section>
	</aside>

	<!-- Modal -->
	<div class="modal fade" id="addHoliday">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">เพิ่มวันหยุด</h4>
				</div>
				<div class="modal-body">
					<jsp:include page="Admin/AddHolidayPage.jsp" flush="false" />
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">ยกเลิก</button>
					<a id="modal-form-submit" class='btn btn-primary' href="#">เพิ่ม</a>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<div class="modal fade" id="editHoliday">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">แก้ไขวันหยุด</h4>
				</div>
				<div class="modal-body">
					<jsp:include page="Admin/EditHolidayPage.jsp" flush="false" />
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">ยกเลิก</button>
					<a id="modal-form-submitEditTeacher" class='btn btn-primary'
						href="#">แก้ไข</a>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<!-- script -->
	
	<script	src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
	<script src="Admin/js/jquery-ui-1.10.3.min.js" type="text/javascript"></script>
	<script src="Admin/js/bootstrap.min.js" type="text/javascript"></script>
	<script	src="//cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
	<script src="Admin/js/plugins/morris/morris.min.js"	type="text/javascript"></script>
	<script src="Admin/js/plugins/sparkline/jquery.sparkline.min.js" type="text/javascript"></script>
	<script	src="Admin/js/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js" type="text/javascript"></script>
	<script	src="Admin/js/plugins/jvectormap/jquery-jvectormap-world-mill-en.js" type="text/javascript"></script>
	<script src="Admin/js/plugins/iCheck/icheck.min.js"	type="text/javascript"></script>
	<script src="Admin/js/AdminLTE/app.js" type="text/javascript"></script>
	
	<script src="js/customScript.js"></script>
	<script type="text/javascript" src="js/validateScript.js"></script>
</body>
</html>