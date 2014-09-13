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

<link rel='stylesheet' type='text/css' href='css/menu_source/styles.css' />
<script
	src='http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js'></script>
<script type='text/javascript' src='js/menu_source/menu_jquery.js'></script>

<title>Student Attendance For Phrae Technical College System</title>

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
					<div class="col-md-10 col-md-offset-2">
						<div class="row service-wrapper-row">
							<a href=#><img src="images/technic.png" alt="..."
								class="img-thumbnail"></a>
						</div>
						<div class="row service-wrapper-row">
							<a href=#> <img src="images/vcoptc.png" alt="..."
								class="img-thumbnail"></a>
						</div>
						<div class="row service-wrapper-row">
							<a href=#> <img src="images/new.png" alt="..."
								class="img-thumbnail"></a>
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