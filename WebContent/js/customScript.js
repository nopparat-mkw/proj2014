$('#modal-form-submit').on('click', function(e) {
	// We don't want this to act as a link so cancel the link action
	e.preventDefault();

	// Find form and submit it
	$('#modal-form').submit();

	// alert("Hello");
});

$('#modal-form-submitEditTeacher').on('click', function(e) {
	// We don't want this to act as a link so cancel the link action
	e.preventDefault();

	// Find form and submit it
	$('#modal-formEditTeacher').submit();
});

function confirm_delete() {
	if (confirm(urldecode('Confirm Delete!!!'))) {
		return true;
	} else {
		return false;
	}
}

function remove_student(id) {
	if (confirm(urldecode('Confirm Delete!!!'))) {
		$.get('ListStudentServlet', {
			'id' : id
		}, function(data) {
			history.go(0);
		});
		return true;
	} else {
		return false;
	}
}

function remove_teacher(id) {
	if (confirm(urldecode('Confirm Delete!!!'))) {
		$.get('ListTeacherServlet', {
			'id' : id
		}, function(data) {
			history.go(0);
		});
		return true;
	} else {
		return false;
	}
}

function editHoliday(date, month, year) {
	$.get('EditHolidayServlet', {
		'date' : date,
		'month' : month,
		'year' : year
	}, function(data) {
		var tmp = data.date + "/" + data.month + "/" + data.year;
		$('#EditHolidaydate').val(tmp);
		$('#Editdetail').val(data.detail);

		$('#Editdates').val(data.date);
		$('#Editmonths').val(data.month);
		$('#Edityears').val(data.year);
	});
}

function editAttendance(majorName, eduBackground, eduLevel, term,
		dateAttendance) {
	$.post('EditAttendanceServlet', {
		'majorName' : majorName,
		'eduBackground' : eduBackground,
		'eduLevel' : eduLevel,
		'term' : term,
		'dateAttendance' : dateAttendance
	}, function(data) {
		var dShow = "วันที่ - "+dateAttendance;
		var eShow = eduBackground + " "+eduLevel;
		var tShow = "เทอม "+term;
		$('#DateShow').val(dShow);
		$('#EducationShow').val(eShow);
		$('#TermShow').val(tShow);
		
		$.each(data, function(i, item) {
	    $('#tbody-show').append('<tr><td>'+ i
	    			+'<td>'+ item.attendance.student.studentID
	 				+' </td><td>'+ item.attendance.student.antecedent +''+ item.attendance.student.firstName
	 				+' </td><td>'+ item.attendance.student.lastName
	 				+' </td><td>'+ item.attendance.statusActivity
	 				+'</td></tr>');
		});
	});
}

function editStudentByID(id) {
	$.get('EditStudentServlet',{
		'id' : id
	},function(data) {
		var data1 = data[0], data2 = data[1];
		$('#EditStudentID').val(data1.studentID);
		$('#EditStudentIDs').val(data1.studentID);
		$('#EditStudentAntecedent').val(data1.antecedent);
		$('#EditStudentFirstName').val(data1.firstName);
		$('#EditStudentLastName').val(data1.lastName);
		$('#EditStudentAntecedentParent').val(data1.parent.antecedent);
		$('#EditStudentFirstNameParent').val(data1.parent.firstName);
		$('#EditStudentLastNameParent').val(data1.parent.lastName);
		$('#EditStudentAddNoParent').val(data1.parent.address.addNo);
		$('#EditStudentMooParent').val(data1.parent.address.moo);
		$('#EditStudentStreetParent').val(data1.parent.address.street);
		$('#EditStudentSubDistrictParent').val(data1.parent.address.subDistrict);
		$('#EditStudentDistrictParent').val(data1.parent.address.district);
		$('#EditStudentProvinceParent').val(data1.parent.address.province);
		$('#EditStudentZipCodeParent').val(data1.parent.address.zipCode);
						
		$('#selectMajorNameEdit').empty();
		$('#selectMajorNameEdit').append($("<option></option>").attr("value", "กรุณาเลือกแผนกวิชา").text("กรุณาเลือกแผนกวิชา"));
		$.each(data2, function(i, item) {
			$('#selectMajorNameEdit').append($("<option></option>").attr("value", item.majorName).text(item.majorName));
		});
	});
}

function editTeacherByIdCard(id) {
	$.get('EditTeacherServlet', {
		'id' : id
	}, function(data) {		
		$('#EditTeacherIdCard').val(data.idCard);
		$('#EditTeacherIdCards').val(data.idCard);
		$('#EditTeacherAntecedent').val(data.antecedent);
		$('#EditTeacherFirstName').val(data.firstName);
		$('#EditTeacherLastName').val(data.lastName);
		$('#EditTeacherEducationalBackground').val(data.education.educationalBackground);
		$('#EditTeacherEducationalInstitution').val(data.education.educationalInstitution);
		$('#EditTeacherEducationalMajor').val(data.education.educationalMajor);
		$('#EditTeacherVacancy').val(data.vacancy);
		
		var textToSelect = data.major.majorName;
	      $("#user_department option").each(function (a, b) {
	            if ($(this).html() == textToSelect ) $(this).attr("selected", "selected");
        });
	});
}

function ListEducationEdit(){
	var selectMajorNames = $('#selectMajorNameEdit').find(":selected").val();
	if(selectMajorNames == "กรุณาเลือกแผนกวิชา"){
		$('#selectEducationEdit').append($("<option></option>").attr("value", "").text(""));
	}else{		
		$.post('EditStudentServlet',{
			'selectMajorNameEdit' : selectMajorNames,
			},function(data) {
			$('#selectEducationEdit').empty();
			$('#selectEducationEdit').append($("<option></option>").attr("value", "กรุณาเลือกชั้นปี").text("กรุณาเลือกชั้นปี"));
			$.each(data, function(i, item) {
				var tmp = item.educationLevel.educationalBackground+" "+item.educationLevel.educationLevel;
				$('#selectEducationEdit').append($("<option></option>").attr("value", tmp).text(tmp));
			});
		});
	}	
}

function editProfileTeacherModal(id) {
	$.get('EditProfileTeacherServlet', {
		'id' : id
	}, function(data) {
		$('#EditProfileAntecedent').val(data.antecedent);
		$('#EditProfileFirstName').val(data.firstName);
		$('#EditProfileLastName').val(data.lastName);
		$('#EditProfileEmail').val(data.email);
		$('#EditProfilePhone').val(data.phone);
		// $('#EditProfileUsername').val(data.login.username);
		$('#EditProfileIdCard').val(data.idCard);
		$('#EditProfileIdCards').val(data.idCard);
	});
}

function editRoom(id) {
	$.get('EditRoomServlet', {
		'id' : id
	}, function(data) {
		$('#e_roomId').val(data.roomId);
		$('#e_RoomIds').val(data.roomId);
		$('#e_RoomType').val(data.roomType);
		$('#e_RoomCapacity').val(data.roomCapacity);
		$('#e_Build').val(data.build);
	});
}

function addCourseSelectCourseId() {
	var selectCourseName = $('#addcourseId').find(":selected").val();
	var selectCourseId = $('#addcourseId').find(":selected").text();
	$('#addCourseName').val(selectCourseName);
	$('#addcourseIds').val(selectCourseId);
}

function addCourse() {
	$.get('AddCourseServlet', {}, function(data) {
		var data1 = data[0], data2 = data[1];
		$('#teacher').empty();
		$('#lectureRoom').empty();
		$('#labRoom').empty();
		$.each(data2,
				function(i, item) {
					$('#teacher').append(
							$("<option></option>").attr("value", item.idCard)
									.text(
											"อาจารย์ " + item.firstName + " "
													+ item.lastName));
				});
		$.each(data1, function(i, item) {

			$('#lectureRoom').append(
					$("<option></option>").attr("value", item.roomId).text(
							item.roomId));
			$('#labRoom').append(
					$("<option></option>").attr("value", item.roomId).text(
							item.roomId));
		});
	});
}

function editCourse(courseId, term, year, section) {
	$.get('EditCourseServlet', {
		'courseId' : courseId,
		'term' : term,
		'year' : year,
		'section' : section
	}, function(data) {
		var data1 = data[0], data2 = data[1], data3 = data[2];
		$('#editTeacher').empty();
		$('#editLectureRoom').empty();
		$('#editLabRoom').empty();
		$.each(data2, function(i, item) {
			if (item.idCard == data3.teacher.idCard) {
				$('#editTeacher').append(
						$("<option selected></option>").attr("value",
								item.idCard).text(item.firstName));
			} else {
				$('#editTeacher').append(
						$("<option></option>").attr("value", item.idCard).text(
								"อาจารย์ " + item.firstName + " "
										+ item.lastName));
			}

		});
		$.each(data1, function(i, item) {
			if (item.roomId == data3.listCourseDetailBean[0].room.roomId) {
				$('#editLectureRoom').append(
						$("<option selected></option>").attr("value",
								item.roomId).text(item.roomId));
			} else {
				$('#editLectureRoom').append(
						$("<option></option>").attr("value", item.roomId).text(
								item.roomId));
			}
			if (item.roomId == data3.listCourseDetailBean[1].room.roomId) {
				$('#editLabRoom').append(
						$("<option selected></option>").attr("value",
								item.roomId).text(item.roomId));
			} else {
				$('#editLabRoom').append(
						$("<option></option>").attr("value", item.roomId).text(
								item.roomId));
			}

		});
		$('#courseIds').val(data3.courseId);
		$('#courseName').val(data3.courseName);
		$('#sections').val(data3.section);
		$('#terms').val(data3.term);
		$('#years').val(data3.year);
		$('#courseId').val(data3.courseId);
		$('#section').val(data3.section);
		$('#term').val(data3.term);
		$('#year').val(data3.year);
		$('#startDate').val(data3.startDate);
		$('#endDate').val(data3.endDate);
		var tmp1 = data3.listCourseDetailBean[0].time.split("-");
		var tmp2 = data3.listCourseDetailBean[1].time.split("-");
		$('#editletureTimeStart').val(tmp1[0]);
		$('#editletureTimeEnd').val(tmp1[1]);
		$('#lectureDate').val(data3.listCourseDetailBean[0].date);
		$('#editlabTimeStart').val(tmp2[0]);
		$('#editlabTimeEnd').val(tmp2[1]);
		$('#labDate').val(data3.listCourseDetailBean[1].date);

	});
}

function cancelClass(id) {
	$.get('CancelClassServlet', {
		'id' : id,
	}, function(data) {
		$('#c_day').empty();
		$('#c_date').empty();
		$('#c_time').empty();
		$('#c_room').empty();
		$('#c_lab').empty();
		$('#c_lecture').empty();

		$('#c_day').append(data.scheduleDay);
		$('#c_date').append(
				data.scheduleDate + "/" + data.scheduleMonth + "/"
						+ data.scheduleYear);
		$('#c_time').append(data.scheduleTime);
		$('#c_room').append(data.scheduleRoom);
		if (data.studyType == "Lab") {
			$('#c_lab').append(data.hourOfTeach);
			$('#c_lecture').append("-");
		} else {
			$('#c_lecture').append(data.hourOfTeach);
			$('#c_lab').append("-");
		}
		$('#c_because').val(data.because);
		$('#c_id').val(data.id);
	});
}

function addMakeUp(courseId, term, year, section) {
	$.get('AddMakeUpServlet', {
		'courseId' : courseId,
		'term' : term,
		'year' : year,
		'section' : section
	}, function(data) {
		var course = data[0], room = data[1];
		$('#showCourse').empty();
		$('#a_room').empty();
		$('#showCourse').append(course.courseId + " " + course.courseName);
		$('#a_courseId').val(course.courseId);
		$('#a_term').val(course.term);
		$('#a_years').val(course.year);
		$('#a_section').val(course.section);
		$.each(room, function(i, item) {
			$('#a_room').append(
					$("<option></option>").attr("value", item.roomId).text(
							item.roomId));
		});
	});
}
function urldecode(str) {
	return decodeURIComponent((str + '').replace(/\+/g, '%20'));
}