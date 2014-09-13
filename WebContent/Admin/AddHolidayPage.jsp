<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" media="all"
	href="css/jsDatePick_ltr.min.css" />
<!-- 
	OR if you want to use the calendar in a right-to-left website
	just use the other CSS file instead and don't forget to switch g_jsDatePickDirectionality variable to "rtl"!
	
	<link rel="stylesheet" type="text/css" media="all" href="jsDatePick_ltr.css" />
-->
<script type="text/javascript" src="js/jsDatePick.min.1.3.js"></script>
<!-- 
	After you copied those 2 lines of code , make sure you take also the files into the same folder :-)
    Next step will be to set the appropriate statement to "start-up" the calendar on the needed HTML element.
    
    The first example of Javascript snippet is for the most basic use , as a popup calendar
    for a text field input.
-->
<script type="text/javascript">
	window.onload = function() {
		new JsDatePick({
			useMode : 2,
			target : "inputField",
			dateFormat : "%d/%M/%Y"
		/*selectedDate:{				This is an example of what the full configuration offers.
			day:5,						For full documentation about these settings please see the full version of the code.
			month:9,
			year:2006
		},
		yearsRange:[1978,2020],
		limitToToday:false,
		cellColorScheme:"beige",
		dateFormat:"%m-%d-%Y",
		imgPath:"img/",
		weekStartDay:1*/
		});

		new JsDatePick({
			useMode : 2,
			target : "inputField2",
			dateFormat : "%d/%M/%Y"
		/*selectedDate:{				This is an example of what the full configuration offers.
			day:5,						For full documentation about these settings please see the full version of the code.
			month:9,
			year:2006
		},
		yearsRange:[1978,2020],
		limitToToday:false,
		cellColorScheme:"beige",
		dateFormat:"%m-%d-%Y",
		imgPath:"img/",
		weekStartDay:1*/
		});
	};
</script>
<form id="modal-form" accept-charset="UTF-8" data-remote="true"
	action="AddHolidayServlet" method="post" name="addHolidayfrm"
	onSubmit="JavaScript:return addHolidaychk();" class="form-horizontal">

	<div class="form-group">
		<label class="col-lg-3 control-label">ตั้งแต่ วัน/เดือน/ปี :</label>
		<div class="col-lg-8">
			<input type="text" class="form-control" id="inputField" name="StartDate" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-lg-3 control-label">ถึง วัน/เดือน/ปี :</label>
		<div class="col-lg-8">
			<input type="text" class="form-control" id="inputField2" name="EndDate" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-lg-3 control-label">รายละเอียด :</label>
		<div class="col-lg-8">
			<input type="text" name="detail" class="form-control">
		</div>
	</div>
</form>