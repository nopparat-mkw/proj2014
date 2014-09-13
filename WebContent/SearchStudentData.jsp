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

<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/icomoon-social.css">
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,600,800'
	rel='stylesheet' type='text/css'>

<link rel="stylesheet" href="css/leaflet.css" />

<link rel="stylesheet" href="css/main.css">
<link rel='stylesheet' type='text/css' href='css/menu_source/styles.css' />
<script
	src='http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js'></script>
<script type='text/javascript' src='js/menu_source/menu_jquery.js'></script>
<script src="js/modernizr-2.6.2-respond-1.1.0.min.js"></script>
<script type="text/javascript">
</script>
<title>Search Student Data</title>
</head>
<body>
	<!--------------Header--------------->
	<jsp:include page="Header.jsp" />
	<!---------------Menu---------------->
	<jsp:include page="MenuUser.jsp" />
	<div class="section">
		<div class="container-fluid">
			<div id="page-wrapper">
				<div class="row">
					<div class="col-md-10 col-md-offset-1">
						<div class="panel panel-default">
							<div class="col-md-12">
								<div class="panel-heading">ค้นหาข้อมูลนักศึกษา</div>
							</div>
							<!-- /.col-lg-12 -->

							<!-- Search -->
							
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-3"></div>
									<form role="form" action="SearchStudentDataServlet" method="post">
										<div class="col-md-4">
											<div class="form-group">
												<input class="form-control" name="searchStudentID">
											</div>
										</div>
										<div class="col-md-3">
											<input type="submit" value="ค้นหา" class="btn btn-default">
										</div>
									</form>
								</div>
								<div class="col-md-2"></div>
								<!-- /.col-md-12 -->
							</div>
						</div>
						<!-- /.row -->
						<c:set var="major" value="${major}" />
						<!-- ================================================================================================ -->
						<c:choose>
						  <c:when test="${empty major}"></c:when>
						  <c:otherwise>
								<c:choose>
									<c:when test="${empty major.educationLevel.student.studentID}">
								    	<div align="center">
											<p style="color: ${ErrorColor}">${ErrorMassage}</p>
										</div>
								  	</c:when>
								 	<c:otherwise>
								 	<div class="row">
							<div class="panel panel-default">
								    	<div class="panel-heading">ข้อมูลนักศึกษา</div>
									<div class="panel-body">
										<div class="row">
											<div class="col-xs-6 col-sm-2">
												<p class="help-block">รหัสประจำตัวนักศึกษา</p>
											</div>
											<div class="col-xs-6 col-sm-2">
												<p class="help-block">${major.educationLevel.student.studentID}</p>
											</div>
										</div>
										<div class="row">
											<div class="col-xs-6 col-sm-2">
												<p class="help-block">ชื่อ - นามสกุล</p>
											</div>
											<div class="col-xs-6 col-sm-2">
												<p class="help-block">${major.educationLevel.student.antecedent} ${major.educationLevel.student.firstName} ${major.educationLevel.student.lastName}</p>
											</div>
										</div>
										<div class="row">
											<div class="col-xs-6 col-sm-2">
												<p class="help-block">สาขาวิชา</p>
											</div>
											<div class="col-xs-6 col-sm-2">
												<p class="help-block">${major.majorName}</p>
											</div>
										</div>
										<div class="row">
											<div class="col-xs-6 col-sm-2">
												<p class="help-block">ระดับการศึกษา</p>
											</div>
											<div class="col-xs-6 col-sm-2">
												<p class="help-block">${major.educationLevel.educationalBackground} ${major.educationLevel.educationLevel}</p>
											</div>
										</div>
										<div class="row">
											<div class="col-xs-12">
												<br> <label>ข้อมูลการเข้าร่วมกิจกรรมหน้าเสาธง</label>
											</div>
										</div>
	
										<div class="col-md-6 col-md-offset-3">
											<div class="table-responsive">
												<table class="table table-striped table-bordered table-hover">
													<thead>
														<tr>
															<th></th>
															<th><p align="center">วัน</p></th>
														</tr>
													</thead>
													<tbody>
														<tr>
															<td>เข้าร่วมกิจกรรม</td>
															<td><p align="center">${present }</p></td>
														</tr>
														<tr>
															<td>ขาด</td>
															<td><p align="center">${lackLate }</p></td>
														</tr>
														<tr>
															<td>ลาป่วย</td>
															<td><p align="center">${sickLeave }</p></td>
														</tr>
														<tr>
															<td>ลากิจ</td>
															<td><p align="center">${personalLeave }</p></td>
														</tr>
													</tbody>
												</table>
											</div>
											<!-- /.Table -->
										</div>
										<!-- /.Offset -->
	
										<div class="row">
											<div class="col-md-12"></div>
											<div class="col-xs-6 col-sm-2">
												<p class="help-block">ผลการตรวจสอบ</p>
											</div>
											<div class="col-xs-6 col-sm-2">
												<p class="help-block">Fail</p>
											</div>
										</div>
										<div class="col-md-12">
											<button type="submit" class="btn btn-default" onclick="window.location.href='StatForRoleServlet'">ดูสถิติการเข้าแถว</button>
										</div>
									</div>
									<!-- /.panel-body -->
								</div>
								<!-- /.panel default-->
							</div>
						<!-- /.row -->
								 	</c:otherwise>
								</c:choose>
							
								
						  </c:otherwise>
						</c:choose>
						
						
						<!-- ================================================================================================ -->

					</div>
					<!-- col-md-10 col-md-offset-1 -->
				</div>
				<!-- row -->
			</div>
			<!-- page-wrapper -->
		</div>
		<!-- container-fluid -->
	</div>

	<!--------------Footer--------------->
	<jsp:include page="Footer.jsp" />

	<!-- Javascripts -->
	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="js/jquery-1.9.1.min.js"><\/script>');
	</script>
	<script src="js/bootstrap.min.js"></script>
	<script src="http://cdn.leafletjs.com/leaflet-0.5.1/leaflet.js"></script>
	<script src="js/jquery.fitvids.js"></script>
	<script src="js/jquery.sequence-min.js"></script>
	<script src="js/jquery.bxslider.js"></script>
	<script src="js/main-menu.js"></script>
	<script src="js/template.js"></script>
</body>
</html>