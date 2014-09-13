<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="description" content="">
<meta name="viewport" content="width=device-width">
<link rel="stylesheet" href="css/sb-admin.css">

<link href="css/plugins/stylesheet-image-based.css" rel="stylesheet">

<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/icomoon-social.css">

<link rel="stylesheet" href="css/leaflet.css" />
<link rel="stylesheet" href="css/main.css">

<script src="js/modernizr-2.6.2-respond-1.1.0.min.js"></script>
<title>Edit Attendance</title>
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
							<div class="panel-heading">แก้ไขการเช็คชื่อนักศึกษา</div>
							<div class="panel-body">

								<!-- /.Search Education -->
								<div class="row form-group" align="center">
									<form class="form-inline" role="form">
										<div class="form-group">
											<input class="form-control" id="disabledInput" type="text"
												placeholder="วันที่ 18/06/2557" disabled>
										</div>
										<div class="form-group">
											<input class="form-control" id="disabledInput" type="text"
												placeholder="ปวส 2" disabled>
										</div>
										<div class="form-group">
											<input class="form-control" id="disabledInput" type="text"
												placeholder="เทอม 1/2557" disabled>
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
													<th>ชื่อ - นามสกุล</th>
													<th>ผลการเช็คชื่อ</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>1</td>
													<td>5021060180</td>
													<td>นาย นพรัตน์ เมืองแก้ว</td>
													<td><div class="radiocheckbox">
															<div>
																<input id="radio1" type="radio" name="radio1" value="1"
																	checked="checked"><label for="radio1">มา</label>
																	<input id="radio2" type="radio" name="radio1" value="2"><label for="radio2">สาย</label>
																	<input id="radio3" type="radio" name="radio1" value="3"><label for="radio3">ขาด</label>
																	<input id="radio4" type="radio" name="radio1" value="4"><label for="radio4">ลาป่วย</label>
																	<input id="radio5" type="radio" name="radio1" value="5"><label for="radio5">ลากิจ</label>
															</div>
														</div></td>
												</tr>
												<tr>
													<td>2</td>
													<td>5021060181</td>
													<td>นาย ขจรเกียรติ ถิ่นแฝง</td>
													<td><div class="radiocheckbox">
															<div>
																<input id="radio1" type="radio" name="radio2" value="1"
																	checked="checked"><label for="radio1">มา</label>
																	<input id="radio2" type="radio" name="radio2" value="2"><label for="radio2">สาย</label>
																	<input id="radio3" type="radio" name="radio2" value="3"><label for="radio3">ขาด</label>
																	<input id="radio4" type="radio" name="radio2" value="4"><label for="radio4">ลาป่วย</label>
																	<input id="radio5" type="radio" name="radio2" value="5"><label for="radio5">ลากิจ</label>
															</div>
														</div></td>
												</tr>
												<tr>
													<td>3</td>
													<td>5021060182</td>
													<td>นางสาว สิริกานต์ พจนารถ</td>
													<td><div class="radiocheckbox">
															<div>
																<input id="radio1" type="radio" name="radio3" value="1"
																	checked="checked"><label for="radio1">มา</label>
																	<input id="radio2" type="radio" name="radio3" value="2"><label for="radio2">สาย</label>
																	<input id="radio3" type="radio" name="radio3" value="3"><label for="radio3">ขาด</label>
																	<input id="radio4" type="radio" name="radio3" value="4"><label for="radio4">ลาป่วย</label>
																	<input id="radio5" type="radio" name="radio3" value="5"><label for="radio5">ลากิจ</label>
															</div>
														</div></td>
												</tr>
												<tr>
													<td>4</td>
													<td>5021060183</td>
													<td>นาย อธิปกรณ์ อินจินดา</td>
													<td><div class="radiocheckbox">
															<div>
																<input id="radio1" type="radio" name="radio4" value="1"
																	checked="checked"><label for="radio1">มา</label>
																	<input id="radio2" type="radio" name="radio4" value="2"><label for="radio2">สาย</label>
																	<input id="radio3" type="radio" name="radio4" value="3"><label for="radio3">ขาด</label>
																	<input id="radio4" type="radio" name="radio4" value="4"><label for="radio4">ลาป่วย</label>
																	<input id="radio5" type="radio" name="radio4" value="5"><label for="radio5">ลากิจ</label>
															</div>
														</div></td>
												</tr>
												<tr>
													<td>5</td>
													<td>5021060184</td>
													<td>นาย สุรกิจ กาวะละ</td>
													<td><div class="radiocheckbox">
															<div>
																<input id="radio1" type="radio" name="radio5" value="1"
																	checked="checked"><label for="radio1">มา</label>
																	<input id="radio2" type="radio" name="radio5" value="2"><label for="radio2">สาย</label>
																	<input id="radio3" type="radio" name="radio5" value="3"><label for="radio3">ขาด</label>
																	<input id="radio4" type="radio" name="radio5" value="4"><label for="radio4">ลาป่วย</label>
																	<input id="radio5" type="radio" name="radio5" value="5"><label for="radio5">ลากิจ</label>
															</div>
														</div></td>
												</tr>
											</tbody>
										</table>
										<div align="center">
											<button type="submit" class="btn btn-default">บันทึก</button>
											<button type="reset" class="btn btn-default">ยกเลิก</button>
										</div>

									</form>
								</div>
								<!-- /.box-body -->
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
						.write('<script src="js/jquery-1.9.1.min.js"><\/script>')
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