<%@page import="in.co.sunrays.ocha.model.AppRoles"%>
<%@page import="in.co.sunrays.ocha.controller.MissingPersonListCtl"%>
<%@page import="in.co.sunrays.ocha.controller.MissingPersonCtl"%>
<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.ocha.model.MissingPersonModel"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<h3>
	| <a href="<%=ORSView.MISSINGPERSON_CTL%>">Missing Person</b></a> | <a
		href="<%=ORSView.MISSINGPERSON_LIST_CTL%>">Missing Person List</a> |
</h3>
<hr>

<h1>Missing Person List</h1>

<form action="<%=ORSView.MISSINGPERSON_LIST_CTL%>">

	<table width="100%">
		<tr>
			<td align="center"><label>Name :</label> <input type="text"
				name="name"
				value="<%=ServletUtility.getParameter("name", request)%>">&emsp;
				<input type="submit" name="operation"
				value="<%=MissingPersonListCtl.OP_SEARCH%>"></td>
		</tr>
	</table>
	<br>
	<table border="1" width="100%">
		<tr>
			<th>Select</th>
			<th>Police St Id</th>
			<th>Name</th>
			<th>Age</th>
			<th>Gender</th>
			<th>Height</th>
			<th>Date Of Missing</th>
			<th>Date Of Reporting</th>
			<th>Complexion</th>
			<th>Hair</th>
			<th>Mark Of Identification</th>
			<th>Area Of Missing</th>
			<th>Report Id</th>
			<th>Photo</th>
			<th></th>
		</tr>
		<tr>
			<td colspan="15"><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></td>
		</tr>
		<%
			int pageNo = ServletUtility.getPageNo(request);
			int pageSize = ServletUtility.getPageSize(request);
			int index = ((pageNo - 1) * pageSize) + 1;

			List list = ServletUtility.getList(request);
			Iterator<MissingPersonModel> it = list.iterator();

			while (it.hasNext()) {

				MissingPersonModel bean = it.next();
		%>
		<tr>
			<td><input type="checkbox" name="ids" value="<%=bean.getId()%>"></td>
			<td><%=bean.getPoliceStId()%></td>
			<td><%=bean.getName()%></td>
			<td><%=bean.getAge()%></td>
			<td><%=bean.getGender()%></td>
			<td><%=bean.getHeight()%></td>
			<td><%=bean.getDateOfMissing()%></td>
			<td><%=bean.getDateOfReporting()%></td>
			<td><%=bean.getComplexion()%></td>
			<td><%=bean.getHair()%></td>
			<td><%=bean.getMarkOfIdentification()%></td>
			<td><%=bean.getAreaOfMissing()%></td>
			<td><%=bean.getReportId()%></td>
			<td><%=bean.getPhoto()%></td>


			<td>
				<%
					if (ServletUtility.getRole(request) > AppRoles.USER) {
				%> <a href="MissingPersonCtl?id=<%=bean.getId()%>">Edit</a> <%
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
			<td align="left"><input type="submit" name="operation"
				value="<%=MissingPersonListCtl.OP_PREVIOUS%>"></td>
			<td align="center"><input type="submit" name="operation"
				value="<%=MissingPersonListCtl.OP_DELETE%>"></td>
			<td align="right"><input type="submit" name="operation"
				value="<%=MissingPersonCtl.OP_NEXT%>"></td>
		</tr>
	</table>
	<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
		type="hidden" name="pageSize" value="<%=pageSize%>">
</form>
