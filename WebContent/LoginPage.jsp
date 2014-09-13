<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">

<link rel="stylesheet" href="css/bootstrap.min.css">

<link rel="stylesheet" href="css/icomoon-social.css">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,600,800'
	rel='stylesheet' type='text/css'>

<link rel="stylesheet" href="css/leaflet.css" />
<link rel="stylesheet" href="css/main.css">
<script src="js/modernizr-2.6.2-respond-1.1.0.min.js"></script>

<title>Login Page</title>
</head>
<body>

	<div class="section">
		<div class="container">

			<div id="loginbox" style="margin-top: 50px;"
				class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
				<div class="panel panel-info">
					<div class="panel-heading">
						<div class="panel-title">Sign In</div>
					</div>
					<div style="padding-top: 30px" class="panel-body">

						<div style="display: none" id="login-alert"
							class="alert alert-danger col-sm-12"></div>

						<form id="loginform" class="form-horizontal" action="LoginServlet"
							role="form" method="post" name="login"
							onSubmit="JavaScript:return LoginSubmit();">

							<div style="margin-bottom: 25px" class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-user"></i></span> <input
									id="login-username" type="text" class="form-control"
									name="username" value="" placeholder="username or email">
							</div>

							<div style="margin-bottom: 25px" class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-lock"></i></span> <input
									id="login-password" type="password" class="form-control"
									name="password" placeholder="password">
							</div>



							<div style="margin-top: 10px" class="form-group">
								<!-- Button -->

								<div class="col-sm-12 controls">
									<button type="submit" class="btn btn-lg btn-success btn-block">Login</button>
								</div>
							</div>


							<div class="form-group">
								<div class="col-md-12 control">
									<div
										style="border-top: 1px solid #888; padding-top: 15px; font-size: 85%">
										<i class="glyphicon glyphicon-share-alt"></i> <a
											href="index.jsp">Back to home <i
											class="glyphicon glyphicon-home"></i></a>
									</div>
								</div>
							</div>
						</form>



					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Javascripts -->
	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="js/jquery-1.9.1.min.js"><\/script>')
	</script>

	<script src="http://cdn.leafletjs.com/leaflet-0.5.1/leaflet.js"></script>
	<script src="js/jquery.fitvids.js"></script>
	<script src="js/jquery.sequence-min.js"></script>
	<script src="js/jquery.bxslider.js"></script>
	<script src="js/main-menu.js"></script>
	<script src="js/template.js"></script>

	<script src="js/bootstrap.min.js"></script>
</body>
</html>