<%@page import="in.co.sunrays.ocha.model.AppRoles"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="in.co.sunrays.ocha.controller.PoliceStationListCtl"%>
<%@page import="in.co.sunrays.ocha.controller.PoliceStationCtl"%>
<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.ocha.model.PoliceStationModel"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<jsp:useBean id="model"
	class="in.co.sunrays.ocha.model.PoliceStationModel" scope="request"></jsp:useBean>


<h1>Police Station List</h1>

<form action="<%=ORSView.POLICESTATION_LIST_CTL%>">

	<table width="100%">
		<tr>
			<td align="center"><label>Name Of Police Station :</label> <input
				type="text" name="nameOfPoliceStation"
				value="<%=ServletUtility.getParameter("nameOfPoliceStation",
					request)%>">&emsp;
				<label>Code Of Police Station :</label> <input type="text"
				name="codeOfPoliceStation"
				value="<%=ServletUtility.getParameter("codeOfPoliceStation",
					request)%>">&emsp;
				<input type="submit" name="operation"
				value="<%=PoliceStationListCtl.OP_SEARCH%>"></td>
		</tr>
	</table>
	<br>
	<table border="1" width="100%">
		<tr>
			<th>Select</th>
			<th>Name Of Police Station</th>
			<th>Code Of Police Station</th>
			<th>Area Covered</th>
			<th>Contact No</th>
			<th></th>
		</tr>
		<tr>
			<td colspan="11"><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></td>
		</tr>
		<%
			int pageNo = ServletUtility.getPageNo(request);
			int pageSize = ServletUtility.getPageSize(request);
			int index = ((pageNo - 1) * pageSize) + 1;

			List list = ServletUtility.getList(request);
			Iterator<PoliceStationModel> it = list.iterator();

			while (it.hasNext()) {

				PoliceStationModel bean = it.next();
		%>
		<tr>
			<td><input type="checkbox" name="ids" value="<%=bean.getId()%>"></td>
			<td><%=bean.getNameOfPoliceStation()%></td>
			<td><%=bean.getCodeOfPoliceStation()%></td>
			<td><%=bean.getAreaCovered()%></td>
			<td><%=bean.getContactNo()%></td>

			<td>
				<%
					if (ServletUtility.getRole(request) > AppRoles.USER) {
				%> <a href="PoliceStationCtl?id=<%=bean.getId()%>">Edit</a> <%
 	}
 %>
			</td>
		</tr>

		<%
			}
		%>
	</table>
	<table width="100%">
		<tr>
			<td><input type="submit" name="operation"
				value="<%=PoliceStationListCtl.OP_PREVIOUS%>"></td>
			<td><input type="submit" name="operation"
				value="<%=PoliceStationListCtl.OP_NEW%>"></td>
			<td><input type="submit" name="operation"
				value="<%=PoliceStationListCtl.OP_DELETE%>"></td>
			<td align="right"><input type="submit" name="operation"
				value="<%=PoliceStationCtl.OP_NEXT%>"></td>
		</tr>
	</table>
	<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
		type="hidden" name="pageSize" value="<%=pageSize%>">
</form>
