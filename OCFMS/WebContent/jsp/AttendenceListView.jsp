
<%@page import="in.co.sunrays.ocha.controller.AttendenceListCtl"%>
<%@page import="in.co.sunrays.ocha.model.AttendenceModel"%>
<%@page import="in.co.sunrays.ocha.controller.CommentListCtl"%>
<%@page import="in.co.sunrays.ocha.model.CommentModel"%>
<%@page import="in.co.sunrays.ocha.model.EResourceModel"%>
<%@page import="in.co.sunrays.ocha.controller.EResourceListCtl"%>
<%@page import="in.co.sunrays.ocha.model.NoticeModel"%>
<%@page import="in.co.sunrays.ocha.controller.NoticeListCtl"%>
<%@page import="in.co.sunrays.ocha.controller.UserListCtl"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<html>
<body>
<div class="container">
    <div class="row">
        <div  class="col-md-2">
	<%@include file="Header.jsp"%>
        </div>
        <div class="col-md-10">
        	
					<h2 align="Center"  style="margin-top: 140px">
					<hr>
					Attendance List
					</h2>
					<form action="<%=ORSView.ATTENDENCE_LIST_CTL%>" class="form-inline">
					  	 <div class="form-group">
					<label for="inputstudentName" class="control-label  col-md-5">Student Name</label>
		<div class="col-md-2">
						<input type="text" class="form-control" name="studentName" id="studentName"
							placeholder="Student Name" value="<%=ServletUtility.getParameter("studentName", request)%>">
					</div>
					</div>
					<div class="form-group">
				<label for="inputLoginId" class="control-label col-md-4">Subject</label>
					<div class="col-md-2">
						<input type="text" class="form-control" name="Subject" id="Subject"
						value="<%=ServletUtility.getParameter("Subject", request)%>"	placeholder="Subject">
					</div>
		</div>
				<div class="form-group">
						<button name="operation" value="<%=AttendenceListCtl.OP_SEARCH %>" type="submit" class="col-md-offset-1 btn btn-info">
							<span class="btn-save-label">
						<i class="glyphicon glyphicon-search"></i>
						</span>
						Search
						</button>
						</div>
			<br>
		<br>
		<div class="table-responsive">
						<table class="table table-bordered table-hover" >
				<tr>
					<th>Subject</th>
					<th>Total Attendance</th>
					<th>Attendance</th>
					<th>Edit</th>
				</tr>

				<tr>
					<td colspan="8"><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></td>
				</tr>

				<%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;

					List list = ServletUtility.getList(request);
					Iterator<AttendenceModel> it = list.iterator();
					while (it.hasNext()) {
						AttendenceModel model = it.next();
				%>
				<tr>
					<td><%=model.getSubject()%></td>
						<td><%=model.getTotalAttendence()%></td>
					<td><%=model.getAttendence()%></td>
					<td><a href="AttendenceCtl?id=<%=model.getId()%>">Edit</a></td>
				</tr>
				<%
					}
				%>
			</table>
						
			<table width="100%">
				<tr>
					<td ><input type="submit" name="operation"
						value="<%=AttendenceListCtl.OP_PREVIOUS%>" class="btn btn-info"></td>
					 <td ><input type="submit"
						name="operation" value="<%=AttendenceListCtl.OP_DELETE%>" class="btn btn-info"></td>
					 <td align="right"><input type="submit" name="operation"
						value="<%=AttendenceListCtl.OP_NEXT%>" class="btn btn-info"></td>
				</tr>
			</table>
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
						</div>
		</form>
					</div>
					</div>
					</div>

</body>
</html>