<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.List"%>
<%@page import="in.co.sunrays.ocha.controller.CrimeReportCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="in.co.sunrays.ocha.controller.ORSView"%>

<jsp:useBean id="model"
	class="in.co.sunrays.ocha.model.CrimeReportModel" scope="request"></jsp:useBean>

<jsp:useBean id="toc" class="java.util.HashMap" scope="request"></jsp:useBean>

<jsp:useBean id="stationList" class="java.util.ArrayList"
	scope="request"></jsp:useBean>

<h3>
	| <a href="<%=ORSView.CRIMEREPORT_CTL%>">Crime Report</b></a> | <a
		href="<%=ORSView.CRIMEREPORT_LIST_CTL%>">Crime Report List</a> |
</h3>
<hr>

<h1>Crime Report</h1>

<H2>
	<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
	</font>
</H2>
<H2>
	<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
	</font>
</H2>

<form name="stForm" action="<%=ORSView.CRIMEREPORT_CTL%>" method="POST">

	<input type="hidden" name="id" value="<%=model.getId()%>">

	<table>
		<tr>
			<th>Cr. Id*</th>
			<td><input type="text" name="crId"
				value="<%=DataUtility.getStringData(model.getCrId())%>"></input><font
				color="red"> <%=ServletUtility.getErrorMessage("crId", request)%></font></td>
		</tr>
		<tr>
			<th>Type Of Crime*</th>
			<td><%=HTMLUtility.getList("typeOfCrime",
					model.getTypeOfCrime(), toc)%></td>
		</tr>
		<tr>
			<th>Date Of Crime(mm/dd/yyyy)*</th>
			<td><input type="text" name="dateOfCrime" readonly="readonly"
				value="<%=DataUtility.getDateString(model.getDateOfCrime())%>">
				<a
				href="javascript:getCalendar(document.forms['stForm'].dateOfCrime);">
					<img src="../img/cal.jpg" width="16" height="15" border="0"
					alt="Calender">
			</a><font color="red"> <%=ServletUtility.getErrorMessage("dateOfCrime", request)%></font></td>
		</tr>
		<tr>
			<th>Time (HH:MM)*</th>
			<td><input type="text" name="time"
				value="<%=DataUtility.getStringData(model.getTime())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("time", request)%></font></td>
		</tr>
		<tr>
			<th>Report Date(mm/dd/yyyy)*</th>
			<td><input type="text" name="reportDate" readonly="readonly"
				value="<%=DataUtility.getDateString(model.getReportDate())%>">
				<a
				href="javascript:getCalendar(document.forms['stForm'].reportDate);">
					<img src="../img/cal.jpg" width="16" height="15" border="0"
					alt="Calender">
			</a><font color="red"> <%=ServletUtility.getErrorMessage("reportDate", request)%></font></td>
		</tr>
		<tr>
			<th>Place *</th>
			<td><input type="text" name="place"
				value="<%=DataUtility.getStringData(model.getPlace())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("place", request)%></font></td>
		</tr>

		<tr>
			<th>Police Station *</th>
			<td><%=HTMLUtility.getList("policeStId", "" +model.getPoliceStId(),
					stationList)%></td>
		</tr>
		<tr>
			<th>Detail *</th>
			<td><textarea rows="4" cols="40" name="detail"><%=DataUtility.getStringData(model.getDetail())%></textarea>
				<font color="red"> <%=ServletUtility.getErrorMessage("detail", request)%></font>
			</td>
		</tr>
		<tr>
			<th>Picture *</th>
			<td><input type="file" name="picture"
				value="<%=DataUtility.getStringData(model.getPicture())%>"></input><font
				color="red"> <%=ServletUtility.getErrorMessage("picture", request)%></font></td>
		</tr>
		<tr>
			<th>Document *</th>
			<td><input type="file" name="document"
				value="<%=DataUtility.getStringData(model.getDocument())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("document", request)%></font></td>
		</tr>
		<tr>

			<td align="center" colspan="3"><input type="submit"
				name="operation" value="<%=CrimeReportCtl.OP_SAVE_UP%>"></td>
		</tr>
	</table>
</form>

