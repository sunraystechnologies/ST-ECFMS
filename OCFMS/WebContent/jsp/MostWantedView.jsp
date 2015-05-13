<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="java.util.List"%>
<%@page import="in.co.sunrays.ocha.controller.MostWantedCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>

<jsp:useBean id="model" class="in.co.sunrays.ocha.model.MostWantedModel"
	scope="request"></jsp:useBean>

<jsp:useBean id="toc" class="java.util.HashMap" scope="request"></jsp:useBean>

<jsp:useBean id="policeStList" class="java.util.ArrayList"
	scope="request" />

<h3>
	| <a href="<%=ORSView.MOSTWANTED_CTL%>">Most Wanted</b></a> | <a
		href="<%=ORSView.MOSTWANTED_LIST_CTL%>">Most wanted List</a> |
</h3>
<hr>
<p class="st-title">Most Wanted</p>


<%=HTMLUtility.getSuccessMessage(request)%>
<%=HTMLUtility.getErrorMessage(request)%>
<form action="<%=ORSView.MOSTWANTED_CTL%>" method="POST">

	<input type="hidden" name="id" value="<%=model.getId()%>">
	<table>

		<tr>
			<th>Name Of Criminal*</th>
			<td><input type="text" name="nameOfCriminal"
				value="<%=DataUtility.getStringData(model.getNameOfCriminal())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("nameOfCriminal", request)%></font></td>
		</tr>
		<tr>
			<th>Type Of Crime*</th>
			<td><%=HTMLUtility.getList("typeOfCrime",
					model.getTypeOfCrime(), toc)%></td>
		</tr>
		<tr>
			<th>Complexion *</th>
			<td><input type="text" name="complexion"
				value="<%=DataUtility.getStringData(model.getComplexion())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("complexion", request)%></font></td>
		</tr>
		<tr>
			<th>Mark Of Identification*</th>
			<td><input type="text" name="markOfIdentification"
				value="<%=DataUtility.getStringData(model.getMarkOfIdentification())%>"></input><font
				color="red"> <%=ServletUtility.getErrorMessage("markOfIdentification",
					request)%></font></td>
		</tr>
		<tr>
			<th>Age *</th>
			<td><input type="text" name="age"
				value="<%=DataUtility.getStringData(model.getAge())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("age", request)%></font></td>
		</tr>
		<tr>
			<th>Gender</th>
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
			<th>Status *</th>
			<td><input type="text" name="status"
				value="<%=DataUtility.getStringData(model.getStatus())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("status", request)%></font></td>
		</tr>
		<tr>
			<th>Photo *</th>
			<td><input type="file" name="photo"
				value="<%=DataUtility.getStringData(model.getPhoto())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("photo", request)%></font></td>
		</tr>

		<tr>
			<th>Police St Id*</th>
			<td><%=HTMLUtility.getList("policsStId",
					String.valueOf(model.getPolicsStId()), policeStList)%></td>
		</tr>
		<tr>
			<th></th>
			<td colspan="2"><input type="submit" name="operation"
				value="<%=MostWantedCtl.OP_SAVE_UP%>"></td>
		</tr>
	</table>
</form>

