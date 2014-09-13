
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<!-- Navigation & Logo-->

<c:set var="login" value="${login }" scope="session" />
<c:if test="${empty sessionScope['login']}">
    <c:redirect url="LoginServlet" />
</c:if>
<%
	int timeout = session.getMaxInactiveInterval();
	response.setHeader("Refresh", timeout + "; URL = LoginServlet");
%>
	        <div class="container">
		        <nav id="mainmenu" class="mainmenu">
					<ul>						
						<li>
							<a href="ViewProfileTeacherServlet">Profile</a>
						</li>
						
						<li  class="has-submenu">
							<a href="#">Attendance</a>
							<div class="mainmenu-submenu">
							<div class="mainmenu-submenu-inner"> 
									<div>
										<h4>View</h4>
										<ul>
											<li><a href="ViewAllAttendanceServlet">View All Attendance</a></li>
										</ul>
									</div>
									<div>
										<h4>Check</h4>
										<ul>
											<li><a href="CheckAttendanceServlet">Check Attendance</a></li>
										</ul>
									</div>									
								</div><!-- /mainmenu-submenu-inner -->
							</div><!-- /mainmenu-submenu -->
						</li>
						<li>
							<a href="ViewAllStudentServlet">View All Student</a>
						</li>
						<li class="has-submenu">
							<a href="#">Print</a>
							<div class="mainmenu-submenu">
								<div class="mainmenu-submenu-inner"> 
									<div>
										<h4>Print Letter of Parent</h4>
										<ul>
											<li><a href="PrintLetterOfActivity.jsp">Print Letter</a></li>
										</ul>
									</div>
									<div>
										<h4>Print List Student for Activity</h4>
										<ul>
											<li><a href="PrintListStudent.jsp">Print List Student</a></li>
										</ul>
									</div>
									
								</div><!-- /mainmenu-submenu-inner -->
							</div><!-- /mainmenu-submenu -->
						</li>
						<li>
							<a href="LogoutServlet">Logout</a>
						</li>
					</ul>
				</nav>
			</div>
		</div>