<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

<script src="js/modernizr-2.6.2-respond-1.1.0.min.js"></script>

<link href="font-awesome/css/font-awesome.css" rel="stylesheet">
<link rel="stylesheet" href="css/icomoon-social.css">
<title>Print</title>
</head>
<body>
	<!--------------Header--------------->
	<jsp:include page="Header.jsp" />
	<!---------------Menu---------------->
<%-- 	<jsp:include page="Menu.jsp" /> --%>
	<%@include file="Menu.jsp" %>
	<div class="section">
		<div class="container">
			<div id="page-wrapper">
				<div class="row">
					<div class="col-md-10 col-md-offset-1">
						<div class="panel panel-default">


							<div class="panel-heading">พิมพ์รายชื่อนักศึกษา</div>
							<div class="panel-body">

								<!-- /.Search Education -->
								<div class="row form-group" align="center">
									<form class="form-inline" role="form">
										<div class="form-group">
											<input class="form-control" id="disabledInput" type="text"
												placeholder="ช่างสำรวจ" disabled>
										</div>
										<div class="form-group">
											<select class="form-control">
												<option>เลือกชั้นปี</option>
												<option>ปวช 1</option>
												<option>ปวช 2</option>
												<option>ปวช 3</option>
												<option>ปวส 1</option>
												<option>ปวส 2</option>
											</select>
										</div>
										<div class="form-group">
											<select class="form-control">
												<option>เลือกเทอม</option>
												<option>1/2556</option>
												<option>2/2556</option>
												<option>1/2557</option>
												<option>2/2557</option>
											</select>
										</div>
										<div class="form-group">
												<button type="button" class="btn btn-success">
													<i class="fa fa-print"></i>&nbsp;&nbsp;พิมพ์รายชื่อนักศึกษา
												</button>
										</div>
									</form>
								</div>
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
	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="js/jquery-1.9.1.min.js"><\/script>');
	</script>
	<script src="http://cdn.leafletjs.com/leaflet-0.5.1/leaflet.js"></script>
	<script src="js/jquery.fitvids.js"></script>
	<script src="js/jquery.sequence-min.js"></script>
	<script src="js/jquery.bxslider.js"></script>
	<script src="js/main-menu.js"></script>
	<script src="js/template.js"></script>
</body>
</html>