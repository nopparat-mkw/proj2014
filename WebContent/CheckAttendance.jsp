<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,600,800'
	rel='stylesheet' type='text/css'>

<link rel="stylesheet" href="css/leaflet.css" />
<link rel="stylesheet" href="css/main.css">

<script src="js/modernizr-2.6.2-respond-1.1.0.min.js"></script>
<title>Check Attendance</title>
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


							<div class="panel-heading">เช็คชื่อนักศึกษา</div>
							<div class="panel-body">

								<!-- /.Search Education -->
								<div class="row form-group" align="center">
									<form class="form-inline" role="form"
										action="CheckAttendanceServlet" method="post" name="test">
									<c:set var="now" value="<%=new java.util.Date()%>" />
										<div class="form-group">
											<input class="form-control" id="disabledInput" type="text"
												placeholder="<fmt:formatDate type="date" pattern="dd/MM/yyyy" value='${now}'/>" disabled>
												 
										</div>
										<c:set var="major" value="${showMajor}" />
												<div class="form-group">
													<input class="form-control" id="majorStudent" type="text" placeholder="${major.majorName}" disabled> 
													<input type="hidden" onchange="document.test.submit();" name="majorStudent" id="majorStudent" value="${major.majorName}">
												</div>
										<div class="form-group">
													<select id="level" name="selectLevelEdu"
														onchange="document.test.submit();" class="form-control" >
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
												
												
												
										<div class="form-group">
											<fmt:formatDate var="year" value="${now}" pattern="yyyy" />
											<select class="form-control" name="selectTerm" onchange="document.test.submit();">
											    <c:forEach var="i" begin="1" end="2">
											    <c:set var="term" value="${i}/${year}" />
											    	<c:choose>
											    		<c:when test="${term == selectTerm }">
											        		<option value="${i}/${year}"  selected="selected"><c:out value="${i}/${year}"/></option>
											        	</c:when>
											        	<c:otherwise>
											        		<option value="${i}/${year}"><c:out value="${i}/${year}"/></option>
											        	</c:otherwise>
											        </c:choose>
											    </c:forEach>
											</select>
										</div>
									</form>
								</div>
								
								<form action="CheckAttendanceServlet" method="post">
								<div class="table-responsive">									
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
											<c:set var="counter" value="${1}"/>
											<c:set var="theCounter" value="${0}"/>
												<c:forEach items="${listStudent}" var="item" varStatus="theCount">
												<c:set var="theCounter" value="${theCounter + 1}"/>
												<input type="hidden" name="majorStudent" id="majorStudent" value="${major.majorName}">
												<input type="hidden" name="stuID_${theCount.count}" value="${item.studentID}" >
												<input type="hidden" name="addSelectTerm" value="${selectTerm}" >
													<tr>
														<td class="TextCenter">${theCount.count}</td>
														<td class="TextCenter">${item.studentID}</td>														
														<td class="TextCenter">${item.antecedent}&nbsp;${item.firstName}&nbsp;&nbsp;${item.lastName}</td>
														<td>
															<div class="radiocheckbox">
																<div class="col-md-12">
																	<input id="radio${counter}" type="radio" name="status_${theCount.count}" value="มา" checked="checked"><label for="radio${counter}">มา</label>
																<c:set var="counter" value="${counter + 1}"/>	
																	<input id="radio${counter}" type="radio" name="status_${theCount.count}" value="สาย"><label for="radio${counter}">สาย</label> 
																<c:set var="counter" value="${counter + 1}"/>	
																	<input id="radio${counter}" type="radio" name="status_${theCount.count}" value="ขาด"><label for="radio${counter}">ขาด</label> 
																<c:set var="counter" value="${counter + 1}"/>	
																	<input id="radio${counter}" type="radio" name="status_${theCount.count}" value="ลาป่วย"><label for="radio${counter}">ลาป่วย</label> 
																<c:set var="counter" value="${counter + 1}"/>	
																	<input id="radio${counter}" type="radio" name="status_${theCount.count}" value="ลากิจ"><label for="radio${counter}">ลากิจ</label>
																<c:set var="counter" value="${counter + 1}"/>
																</div>
															</div>
														</td>
													</tr>													
												</c:forEach>
											</tbody>
										</table>
										<input type="hidden" name="theCount" value="${theCounter}" >
										<div align="center">
<!-- 											<button type="submit" class="btn btn-default">บันทึก</button> -->
											<input type="submit" class="btn btn-default" value="บันทึก"> 
											<button type="reset" class="btn btn-default">ยกเลิก</button>
										</div>									
								</div>
								</form>
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