<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>รายชื่อนักศึกษา</title>
<meta name="description" content="Staff Main Page">
<meta name="author" content="www.zerotheme.com">

<!-- Mobile Specific Metas
  ================================================== -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
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

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="${contextPath}/js/customScript.js"></script>

<script type="text/javascript">
function listAllMajor(){
	$.post('RegisterStudentServlet',{},function(data) {
		$('#selectMajorName').empty();
		$('#selectMajorName').append($("<option></option>").attr("value", "กรุณาเลือกแผนกวิชา").text("กรุณาเลือกแผนกวิชา"));
		$.each(data, function(i, item) {
				$('#selectMajorName').append($("<option></option>").attr("value", item.majorName).text(item.majorName));
		});
	});
	
}

function ListEducation(){
		var selectMajorNames = $('#selectMajorName').find(":selected").val();
		if(selectMajorNames == "กรุณาเลือกแผนกวิชา"){
			$('#selectEducation').append($("<option></option>").attr("value", "").text(""));
		}else{
			$('#addCourseName').val(selectMajorNames);
			
			$.post('RegisterStudentServlet',{
				'selectMajorName' : selectMajorNames,
				},function(data) {
				$('#selectEducation').empty();
				$('#selectEducation').append($("<option></option>").attr("value", "กรุณาเลือกชั้นปี").text("กรุณาเลือกชั้นปี"));
				$.each(data, function(i, item) {
					var tmp = item.educationLevel.educationalBackground+" "+item.educationLevel.educationLevel;
					$('#selectEducation').append($("<option></option>").attr("value", tmp).text(tmp));
				});
			});
		}	
}
</script>
</head>
<body class="skin-blue">
	<!--------------Header--------------->
	<jsp:include page="Admin/Header.jsp" />
	<div class="wrapper row-offcanvas row-offcanvas-left">

		<!---------------Menu---------------->
		<%@include file="Admin/Menu.jsp" %>
<%-- 		<jsp:include page="Admin/Menu.jsp" /> --%>

		<aside class="right-side"> <section class="content-header">
		<h1>
			<i class="fa fa-laptop"></i>&nbsp;&nbsp;List Student <small>Control
				panel</small>
		</h1>
		</section> <section class="content"> <!-- Search -->
		<div class="row form-group" align="center">
			<form class="form-inline" role="form" action="ListStudentServlet"
				method="post">
				<div class="form-group">
					<div class="ui-select">
						<select id="majorName" name="selectMajorName"
							onchange="this.form.submit()" class="form-control">
							<c:forEach items="${listMajor}" var="item">
								<c:choose>
									<c:when test="${item.majorName == selectMajorName}">
										<option value="${item.majorName}" selected="selected">${item.majorName}</option>
									</c:when>
									<c:otherwise>
										<option value="${item.majorName}">${item.majorName}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<select id="level" name="selectLevelEdu"
						onchange="this.form.submit()" class="form-control">
						<c:forEach items="${listEducationLevel}" var="item">
							<c:choose>
								<c:when
									test="${item.educationLevel.educationalBackground == selectEducation && item.educationLevel.educationLevel == selectEducation1}">
									<option
										value="${item.educationLevel.educationalBackground} ${item.educationLevel.educationLevel}"
										selected="selected">${item.educationLevel.educationalBackground}&nbsp;&nbsp;${item.educationLevel.educationLevel}</option>
								</c:when>
								<c:otherwise>
									<option
										value="${item.educationLevel.educationalBackground} ${item.educationLevel.educationLevel}">${item.educationLevel.educationalBackground}&nbsp;&nbsp;${item.educationLevel.educationLevel}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
			</form>
		</div>

		<table class="table table-hover" id="dataTables-example">
			<thead>
				<tr>
					<th>ลำดับ</th>
					<th>รหัสประจำตัวนักศึกษา</th>
					<th colspan="3">ชื่อ - นามสกุล</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listStudent}" var="item" varStatus="theCount">
					<tr>
						<td class="TextCenter">${theCount.count}</td>
						<td class="TextCenter">${item.studentID}</td>
						<td class="TextCenter" width="5%">${item.antecedent}</td>
						<td class="TextCenter">${item.firstName}</td>
						<td class="TextCenter">${item.lastName}</td>
						<td class="TextCenter">
							<button type="button" class="btn btn-primary btn-circle"
								data-toggle="modal" data-target="#editStudent"
								onclick="editStudentByID(${item.studentID})">
								<i class="glyphicon glyphicon-pencil" Title="แก้ไข"></i>
							</button>
							
								<a onclick="return remove_student(${item.studentID});">
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
							<button class="btn btn-primary btn-lg" data-toggle="modal" onclick="listAllMajor('0')"
								data-target="#addStudent">
								<i class="glyphicon glyphicon-plus"></i>&nbsp;&nbsp;เพิ่มนักศึกษา
							</button>
							<button type="submit" class="btn btn-primary btn-lg" onclick="window.location.href='ImportStudentServlet'"><i class="ion ion-upload"></i>&nbsp;&nbsp;อัพโหลดไฟล์</button>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
		</section> </aside>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="uploadStudent">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">เพิ่มนักศึกษา อัพโหลดไฟล์</h4>
				</div>
				<div class="modal-body">
					<jsp:include page="Admin/RegisterStudentUploadfilePage.jsp"
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

	<div class="modal fade" id="addStudent">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">เพิ่มนักศึกษา</h4>
				</div>
				<div class="modal-body">
					<jsp:include page="Admin/RegisterStudentPage.jsp" flush="false" />
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">ยกเลิก</button>
					<a class='btn btn-primary' onclick="document.getElementById('regis-student').submit();">บันทึก</a>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
<!-- 	<form id="modal-form" accept-charset="UTF-8" data-remote="true" -->
<!-- 		action="EditStudentServlet" method="post" class="form-horizontal" -->
<!-- 		role="form"> -->
		<div class="modal fade" id="editStudent">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title">แก้ไขข้อมูลนักศึกษา</h4>
					</div>
					<div class="modal-body">
						<jsp:include page="Admin/EditStudentPage.jsp" flush="false" />
					</div>
					<div class="modal-footer">
<!-- 						<button type="button" class="btn btn-default" data-dismiss="modal">ยกเลิก</button> -->
<!-- 						<input type="submit" value="แก้ไข" class='btn btn-primary'> -->
<button type="button" class="btn btn-default" data-dismiss="modal">ยกเลิก</button>
					<a class='btn btn-primary' onclick="document.getElementById('edit-studentForm').submit();">แก้ไข</a>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
<!-- 	</form> -->
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

	<script src="Admin/js/plugins/iCheck/icheck.min.js"
		type="text/javascript"></script>
	<script src="Admin/js/AdminLTE/app.js" type="text/javascript"></script>
	<script type="text/javascript" src="js/validateScript.js"></script>

</body>
</html>
