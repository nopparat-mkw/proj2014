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
<link rel="stylesheet" href="css/sb-admin.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/icomoon-social.css">
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,600,800'
	rel='stylesheet' type='text/css'>

<link rel="stylesheet" href="css/leaflet.css" />
<!--[if lte IE 8]>
		    <link rel="stylesheet" href="css/leaflet.ie.css" />
		<![endif]-->
<link rel="stylesheet" href="css/main.css">
<script src="js/modernizr-2.6.2-respond-1.1.0.min.js"></script>
<link rel='stylesheet' type='text/css' href='css/menu_source/styles.css' />
<script
	src='http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js'></script>
<script type='text/javascript' src='js/menu_source/menu_jquery.js'></script>

<link rel="stylesheet" href="css/jasny-bootstrap.css">
<link rel="stylesheet" href="css/jasny-bootstrap.css.map">
<title>View Profile Teacher</title>
</head>
<body>
	<!--------------Header--------------->
	<jsp:include page="Header.jsp" />
	<!---------------Menu---------------->
<%-- 	<jsp:include page="Menu.jsp" /> --%>
	<%@include file="Menu.jsp" %>
	<div id="page-wrapper">
		<div class="row">
			<br>
			<div class="col-md-6 col-md-offset-3">
				<div class="thumbnail">
					<c:forEach  items="${ViewProfile}" var="item">
						<img data-src="holder.js/300x200" alt="..." src="images/profile/${item.path_image }">
						<div align="center">
							<p style="color: ${ErrorColor}">${ErrorMassage}</p>
						</div>
						<div class="caption">
							<blockquote>
								
									<h3>${item.antecedent}${item.firstName}&nbsp;&nbsp;&nbsp;&nbsp;${item.lastName}</h3>
									<footer>ประวัติการศึกษา
										<ul>	
										<c:forEach  items="${item.educationVector}" var="view">
												<li>${view.educationalInstitution} ${view.educationalMajor } ${view.educationalBackground }</li>
										</c:forEach>
										</ul>
									</footer>
									<footer>อาจารย์ประจำสาขาวิชา
										<ul>	
											<li>${item.major.majorName }</li>
										</ul>
									</footer>
									<footer>ติดต่อ
										<ul>
											<li>${item.email }</li>
											<li>${item.phone }</li>
										</ul>
									</footer>
								
							</blockquote>
						</div>
					</c:forEach>
					<c:forEach  items="${ViewProfile}" var="items">
					<div class="form-group" align="right">
						<button class="btn btn-primary" data-toggle="modal"
							data-target="#editProfileTeacher" onclick="editProfileTeacherModal(${items.idCard})">
							<i class="glyphicon glyphicon-wrench"></i>&nbsp;&nbsp;แก้ไขข้อมูล
						</button>
					</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="editProfileTeacher">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">แก้ไขข้อมูลส่วนตัว</h4>
				</div>
				<div class="modal-body">
					<jsp:include page="EditProfileTeacherPage.jsp" flush="false" />
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

	<script src="js/jasny-bootstrap.js"></script>
	<script src="js/jasny-bootstrap.min.js"></script>
	
	<script src="js/customScript.js"></script>
	<script type="text/javascript" src="js/validateScript.js"></script>
</body>
</html>