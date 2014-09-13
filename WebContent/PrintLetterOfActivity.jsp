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
		<div class="container-fluid">
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
												<option>1</option>
												<option>2</option>
											</select>
										</div>
										<div class="form-group">
											<button type="submit" class="btn btn-default">ค้นหา</button>
										</div>
									</form>
								</div>
								
								<div class="table-responsive">
								<form>
									<table id="example2" class="table table-bordered table-hover">
										<thead>
											<tr>
												<th>ลำดับ</th>
												<th>รหัสประจำตัวนักศึกษา</th>
												<th>คำนำหน้า</th>
												<th>ชื่อ</th>
												<th>นามสกุล</th>
												<th>หมายเหตุ</th>
											</tr>
										</thead>
											<tbody>
												<tr>
													<td>1</td>
													<td>5021060180</td>
													<td>นาย</td>
													<td>นพรัตน์</td>
													<td>เมืองแก้ว</td>
													<td align="center">
														<button type="button" class="btn btn-danger">
															<i class="fa fa-print"></i>
														</button>
													</td>
												</tr>
												<tr>
													<td>2</td>
													<td>5021060181</td>
													<td>นาย</td>
													<td>ขจรเกียรติ</td>
													<td>ถิ่นแฝง</td>
													<td align="center">
														<button type="button" class="btn btn-danger">
															<i class="fa fa-print"></i>
														</button>
													</td>
												</tr>
												<tr>
													<td>3</td>
													<td>5021060182</td>
													<td>นางสาว</td>
													<td>สิริกานต์</td>
													<td>พจนารถ</td>
													<td align="center">
														<button type="button" class="btn btn-danger">
															<i class="fa fa-print"></i>
														</button>
													</td>
												</tr>
												<tr>
													<td>4</td>
													<td>5021060183</td>
													<td>นาย</td>
													<td>อธิปกรณ์</td>
													<td>อินจินดา</td>
													<td align="center">
														<button type="button" class="btn btn-danger">
															<i class="fa fa-print"></i>
														</button>
													</td>
												</tr>
												<tr>
													<td>5</td>
													<td>5021060184</td>
													<td>นาย</td>
													<td>สุรกิจ</td>
													<td>กาวะละ</td>
													<td align="center">
														<button type="button" class="btn btn-danger">
															<i class="fa fa-print"></i>
														</button>
													</td>
												</tr>
											</tbody>
										</table>
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