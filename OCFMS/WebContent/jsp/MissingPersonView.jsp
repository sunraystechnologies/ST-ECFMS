<%@page import="java.util.List"%>
<%@page import="in.co.sunrays.ocha.controller.MissingPersonCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="in.co.sunrays.ocha.controller.ORSView"%>

<jsp:useBean id="model"
	class="in.co.sunrays.ocha.model.MissingPersonModel" scope="request"></jsp:useBean>

<jsp:useBean id="policeStList" class="java.util.ArrayList"
	scope="request" />


<h3>
	| <a href="<%=ORSView.MISSINGPERSON_CTL%>">Missing Person</b></a> | <a
		href="<%=ORSView.MISSINGPERSON_LIST_CTL%>">Missing Person List</a> |
</h3>
<hr>

<h1>Missing Person</h1>

<H2>
	<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
	</font>
</H2>
<H2>
	<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
	</font>
</H2>

<form name="stForm" action="<%=ORSView.MISSINGPERSON_CTL%>" method="POST">
	<input type="hidden" name="id" value="<%=model.getId()%>">

	<table>

		<tr>
			<th>Police Station*</th>
			<td><%=HTMLUtility.getList("policeStId",
					String.valueOf(model.getPoliceStId()), policeStList)%></td>
		</tr>
		<tr>
			<th>Name*</th>
			<td><input type="text" name="name"
				value="<%=DataUtility.getStringData(model.getName())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("name", request)%></font></td>
		</tr>
		<tr>
			<th>Age *</th>
			<td><input type="text" name="age"
				value="<%=DataUtility.getStringData(model.getAge())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("age", request)%></font></td>
		</tr>
		<tr>
			<th>Gender *</th>
			<td>
				<%
					HashMap map = new HashMap();
					map.put("M", "Male");
					map.put("F", "Female");

					String htmlList = HTMLUtility.getList("gender", model.getGender(),
							map);
				%> <%=htmlList%>
		</tr>
		<tr>
			<th>Height *</th>
			<td><input type="text" name="height"
				value="<%=DataUtility.getStringData(model.getHeight())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("height", request)%></font></td>
		</tr>
		<tr>
			<th>Date Of Missing (mm/dd/yyyy)*</th>
			<td><input type="text" name="dateOfMissing" readonly="readonly"
				value="<%=DataUtility.getDateString(model.getDateOfMissing())%>">
				<a href="javascript:getCalendar(document.forms['stForm'].dateOfMissing);">
					<img src="../img/cal.jpg" width="16" height="15" border="0"
					alt="Calender">
			</a><font color="red"> <%=ServletUtility.getErrorMessage("dateOfMissing", request)%></font></td>
		</tr>
		<tr>
			<th>Date Of Reporting (mm/dd/yyyy)*</th>
			<td><input type="text" name="dateOfReporting"
				readonly="readonly"
				value="<%=DataUtility.getDateString(model.getDateOfReporting())%>">
				<a href="javascript:getCalendar(document.forms['stForm'].dateOfReporting);">
					<img src="../img/cal.jpg" width="16" height="15" border="0"
					alt="Calender">
			</a><font color="red"> <%=ServletUtility
					.getErrorMessage("dateOfReporting", request)%></font></td>
		</tr>

		<tr>
			<th>Complexion *</th>
			<td><input type="text" name="complexion"
				value="<%=DataUtility.getStringData(model.getComplexion())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("complexion", request)%></font></td>
		</tr>
		<tr>
			<th>Hair *</th>
			<td><input type="text" name="hair"
				value="<%=DataUtility.getStringData(model.getComplexion())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("hair", request)%></font></td>
		</tr>
		<tr>
			<th>Mark Of Identification*</th>
			<td><input type="text" name="markOfIdentification"
				value="<%=DataUtility.getStringData(model.getMarkOfIdentification())%>"></input><font
				color="red"> <%=ServletUtility.getErrorMessage("markOfIdentification",
					request)%></font></td>
		</tr>
		<tr>
			<th>Area Of Missing *</th>
			<td><input type="text" name="areaOfMissing"
				value="<%=DataUtility.getStringData(model.getAreaOfMissing())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("areaOfMissing", request)%></font></td>
		</tr>
		<th>Report Id*</th>
		<td><input type="text" name="reportId"
			value="<%=DataUtility.getStringData(model.getPoliceStId())%>"></input><font
			color="red"> <%=ServletUtility.getErrorMessage("reportId", request)%></font></td>
		</tr>
		<tr>
			<th>Photo *</th>
			<td><input type="file" name="photo"
				value="<%=DataUtility.getStringData(model.getPhoto())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("photo", request)%></font></td>
		</tr>


		<tr>
			<th></th>
			<td colspan="2"><input type="submit" name="operation"
				value="<%=MissingPersonCtl.OP_SAVE_UP%>"></td>
		</tr>
	</table>
</form>

