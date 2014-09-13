<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Import Student</title>
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

<script type="text/javascript" src="js/customScript.js"></script>

</head>
<body class="skin-blue">
	<!--------------Header--------------->
	<jsp:include page="Admin/Header.jsp" />

	<div class="wrapper row-offcanvas row-offcanvas-left">
		<!---------------Menu---------------->
		<%@include file="Admin/Menu.jsp"%>
		<%-- 		<jsp:include page="Admin/Menu.jsp" /> --%>
		<aside class="right-side"> <!-- Content Header (Page header) -->
			<section class="content-header">
			<h1>
				<i class="glyphicon glyphicon-user"></i>&nbsp;&nbsp;Import Student Data
			</h1>
			</section> 
			<section class="content">
			<form class="form-horizontal" role="form"
						action="ImportStudentServlet"
						method="post" enctype="multipart/form-data">
					<div class="form-group">
						<label class="col-sm-3 control-label"></label>
						<div class="col-md-6">
							<input type="file" class="filestyle" data-icon="false"	name="fileexcel" id="fileexcel" data-buttonName="btn-primary" >
						</div>
						<div class="col-md-3">
						<input type="submit" value="import" class="btn btn-default"/>
						</div>
					</div>
				</form>
	
			</section> 
		</aside>
	</div>


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

	<script type="text/javascript"
		src="http://markusslima.github.io/bootstrap-filestyle/js/bootstrap-filestyle.min.js"></script>
	<script type="text/javascript"
		src="http://markusslima.github.io/bootstrap-filestyle/js/bootstrap-filestyle.js"></script>



	<script type="text/javascript">$(":file").filestyle({buttonName: "btn-primary"});</script>
</body>
</html>