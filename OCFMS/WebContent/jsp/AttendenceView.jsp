
<%@page import="in.co.sunrays.ocha.controller.AttendenceListCtl"%>
<%@page import="in.co.sunrays.ocha.model.AttendenceModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.sunrays.ocha.controller.BaseCtl"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>

<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Latest compiled and minified JavaScript -->
<script src="../js/bootstrap.js"></script>
 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

	<script>
$(function() {
    $('.date-picker').datepicker( );
});
</script>
<title>Attendance Management</title>
</head>
<body>
		<jsp:useBean id="model" class="in.co.sunrays.ocha.model.AttendenceModel" scope="request"/>
<div class="container">
    <div class="row">
        <div  class="col-md-2">
        <%@include file="Header.jsp"%>
        </div>
        <div class="col-md-10">
        	<h3 align="Center"  style="margin-top: 140px">
					<hr>
				Attendance
					</h3>
					<h3 class="col-md-offset-4">
					<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
				</font>
					<font color="red" >  <%=ServletUtility.getErrorMessage(request)%>
					</font></h3>
						<input type="hidden" name="id" value="<%=model.getId()%>">
			<form action="<%=ORSView.ATTENDENCE_CTL%>" class="form-horizontal">
       <%
			List userList = (List) request.getAttribute("userList");
       List branchList = (List) request.getAttribute("branchList");
       List subjectList = (List) request.getAttribute("subjectList");
		%>
			
					<div class="form-group">
					<label for="inputstudentId" class="control-label col-md-offset-2 col-md-2">
					Student Name</label>
					<div class="col-md-3">
						<%=HTMLUtility.getList("studentId", model.getStudentName(), userList)%>
					<font
						color="red"> <%=ServletUtility.getErrorMessage("studentId", request)%></font>
					</div>
				</div>	
				
				<div class="form-group">
					<label for="inputbranchId" class="control-label col-md-offset-2 col-md-2">
					Branch Name</label>
					<div class="col-md-3">
						<%=HTMLUtility.getList("branchId", model.getBranchName(), branchList)%>
					<font
						color="red"> <%=ServletUtility.getErrorMessage("branchId", request)%></font>
					</div>
				</div>	
					<div class="form-group">
					<label for="inputSubject" class="control-label col-md-offset-2 col-md-2">
					Subject</label>
					<div class="col-md-3">
					 <%=HTMLUtility.getList("subjectId", model.getSubject(), subjectList)%><font
						color="red"> <%=ServletUtility.getErrorMessage("subjectId", request)%></font>
					</div>
					</div>
						<div class="form-group">
					<label for="inputtotalAttendance" class="control-label col-md-offset-2 col-md-2">
					Total Attendance</label>
					<div class="col-md-3">
						<input type="text" class="form-control" name="totalAttendance" id="totalAttendance"
							placeholder="Total Attendance"value="<%=ServletUtility.getParameter("totalAttendance", request)%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("totalAttendance", request)%></font>
					</div>
				</div>	
						<div class="form-group">
					<label for="inputAttendance" class="control-label col-md-offset-2 col-md-2">
					Attendance</label>
					<div class="col-md-3">
						<input type="text" class="form-control" name="attendance" id="attendance"
							placeholder="Attendance"value="<%=DataUtility.getStringData(model.getAttendence())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("attendance", request)%></font>
					</div>
				</div>	
				
					<div class="form-group">
					<label for="inputexpireDate" class="control-label col-md-offset-2 col-md-2" >Attendance Date</label>
					<div class="col-md-3">
						<input type="text"  name="Attendancedate" id="Attendancedate"
							 class="form-control date-picker"
							placeholder="Attendance Date"  
					value="<%=DataUtility.getDateString(model.getCreatedOn())%>">
					<font color="red"> <%=ServletUtility.getErrorMessage("Attendancedate", request)%></font>
					</div>
				</div>
				
						<div class="form-group">
				<div class="col-md-offset-4 ">
						<button name="operation" style="margin-left: 20px" class="btn icon-btn-save btn-success" value="<%=BaseCtl.OP_SAVE%>" type="submit">
						<span class="btn-save-label">
						<i class="glyphicon glyphicon-floppy-disk"></i>
						</span>
						save</button>
							<%
				 	if (model.getId() > 0) {
						 %>
				 	<button name="operation" class="btn btn-success"  	value="<%=BaseCtl.OP_DELETE%>" type="submit">
						
						Delete
							</button>
				  <%
				 	}
				 %><button name="operation" class="btn btn-success" value="<%=BaseCtl.OP_CANCEL%>" type="submit">
						
						Cancel
							</button>
						</div>
						</div>
				
			</form>	
				
				</div>
				</div>
		
				</div>
				</body>