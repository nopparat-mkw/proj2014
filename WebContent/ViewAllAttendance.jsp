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
	       $('#dataTables-list').dataTable();
	       $('#modal-table').dataTable();
	   });
   </script>
<title>View All Attendance Page</title>
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


							<div class="panel-heading">ดูข้อมูลการเช็คชื่อ</div>
							<div class="panel-body">
						
								<!-- /.Search Education -->
								<div class="row form-group" align="center">
										<form class="form-inline" role="form"
												action="ViewAllAttendanceServlet" method="post" name="allAttendance">
											<c:set var="major" value="${showMajor}" />
												<div class="form-group">
													<input class="form-control" id="majorStudent" type="text" placeholder="${major.majorName}" disabled> 
													<input type="hidden" onchange="document.allAttendance.submit();" name="majorStudent" id="majorStudent" value="${major.majorName}">
												</div>
											
											<div class="form-group">
													<select id="level" name="selectLevelEdu"
														onchange="document.allAttendance.submit();" class="form-control">
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
											
											<div class=" form-group">
												<select class="form-control" name="selectTerm" onchange="document.allAttendance.submit();">
													<c:forEach items="${listTerm}" var="item">
															<c:choose>
																<c:when	test="${item == chkTerm}">
																	<option	value="${item}"	selected="selected">${item}</option>
																</c:when>
																<c:otherwise>
																	<option	value="${item}">${item}</option>
																</c:otherwise>
															</c:choose>
														</c:forEach>
												</select>												
											</div>
										</form>
								</div>
								<!-- /.From -->
								<!-- ================================== -->
								
										<div class="table-responsive">
											<table id="dataTables-list" class="table table-bordered table-hover">
												<thead>
													<tr>
														<th>ลำดับ</th>
														<th>วันที่</th>
														<th>เข้าแถว</th>
														<th>สาย</th>
														<th>ขาด</th>
														<th>ลาป่วย</th>
														<th>ลากิจ</th>
														<th>รวมขาดลา</th>
														<th>ปรับปรุง</th>
													</tr>
												</thead>
												
												<c:forEach items="${listDateSchedule}" var="item" varStatus="theCount">
														<tr>
															<td class="TextCenter">${theCount.count}</td>
															<td class="TextCenter">${item}</td>
															<td class="TextCenter">${listPresent[theCount.index]}</td>
															<td class="TextCenter">${listLate[theCount.index]}</td>
															<td class="TextCenter">${listLack[theCount.index]}</td>
															<td class="TextCenter">${listSickLeave[theCount.index]}</td>
															<td class="TextCenter">${listPersonalLeave[theCount.index]}</td>
															<td class="TextCenter">${listLack[theCount.index]+listSickLeave[theCount.index]+listPersonalLeave[theCount.index]}</td>
															<td align="center">
<!-- 																<button type="button" class="btn btn-primary btn-circle" -->
<%-- 																	onclick="viewAttendance('${majorStudent}','${selectEducation}','${selectEducation1}','${chkTerm}','${item}')"> --%>
<!-- 																	<i class="fa fa-list"></i> -->
<!-- 																</button> -->
																<button type="button" class="btn btn-primary btn-circle"
																	data-toggle="modal" data-target="#editAttendance"
																	onclick="editAttendance('${majorStudent}','${selectEducation}','${selectEducation1}','${chkTerm}','${item}')">
																	<i class="fa fa-list"></i>
																</button>
																<button type="button" class="btn btn-primary btn-circle"
																	onclick="test('${majorStudent}','${selectEducation}','${selectEducation1}','${chkTerm}','${item}')">
																	<i class="fa fa-user"></i>
																</button>

															

												</td>
														</tr>
												</c:forEach>
											</table>
										</div>
								<!-- ================================== -->
								<!-- /.box-body -->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
		<!-- Modal -->
	<div class="modal fade" id="editAttendance">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">แก้ไขข้อมูการเข้าแถว</h4>
				</div>
				<div class="modal-body">
					<jsp:include page="EditAttendancePage.jsp" flush="false" />
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