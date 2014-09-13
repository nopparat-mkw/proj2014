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
<link rel='stylesheet' type='text/css' href='css/menu_source/styles.css' />
<script
	src='http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js'></script>
<script type='text/javascript' src='js/menu_source/menu_jquery.js'></script>
<link rel="stylesheet" href="css/leaflet.css" />
<link rel="stylesheet" href="css/main.css">
<link href="font-awesome/css/font-awesome.css" rel="stylesheet">
<script src="js/modernizr-2.6.2-respond-1.1.0.min.js"></script>
<title>Stat For Role</title>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
	google.load("visualization", "1", {
		packages : [ "corechart" ]
	});
	google.setOnLoadCallback(drawChart);
	function drawChart() {
		var data1 = ${presents};
		var data2 = ${lacks};
		var data3 = ${sickLeaves};
		var data4 = ${personalLeaves};

	
		var data = google.visualization.arrayToDataTable([
				[ 'Task', 'Hours per Day' ],
				[ 'เข้าร่วมกิจกรรม', parseInt(data1) ],
				[ 'ขาด', parseInt(data2) ], 
				[ 'ลาป่วย', parseInt(data3) ],
				[ 'ลากิจ', parseInt(data4) ] ]);

		var options = {
			title : 'สถิติการเข้าแถว',
			is3D : true,
		};

		// 		alert(data);
		var chart = new google.visualization.PieChart(document
				.getElementById('piechart_3d'));
		chart.draw(data, options);
	}
</script>


</head>
<body>
	<!--------------Header--------------->
	<jsp:include page="Header.jsp" />
	<!---------------Menu---------------->
	<jsp:include page="MenuUser.jsp" />
	<div class="section">
		<div class="container">
			<div id="page-wrapper">
				<div class="row">
					<div class="col-md-10 col-md-offset-1">
						<div class="panel panel-default">
							<div class="panel-heading">
								<i class="fa fa-bar-chart-o fa-fw"></i>สถิติการเข้าร่วมกิจกรรม
							</div>
							<div class="panel-body">
								<div id="piechart_3d" style="width: 900px; height: 500px;"></div>								
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
	<script src="js/bootstrap.min.js"></script>
	<script src="http://cdn.leafletjs.com/leaflet-0.5.1/leaflet.js"></script>
	<script src="js/jquery.fitvids.js"></script>
	<script src="js/jquery.sequence-min.js"></script>
	<script src="js/jquery.bxslider.js"></script>
	<script src="js/main-menu.js"></script>
	<script src="js/template.js"></script>
	<script src="js/demo/dashboard-demo.js"></script>
	<script src="js/plugins/morris/raphael-2.1.0.min.js"></script>
	<script src="js/plugins/morris/morris.js"></script>
</body>
</html>