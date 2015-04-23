<%@page import="in.co.sunrays.ocha.model.AppRoles"%>
<%@page import="in.co.sunrays.ocha.controller.MostWantedListCtl"%>
<%@page import="in.co.sunrays.ocha.controller.MostWantedCtl"%>
<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.ocha.model.MostWantedModel"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<jsp:useBean id="model" class="in.co.sunrays.ocha.model.MostWantedModel"
	scope="request"></jsp:useBean>
<h3>
	| <a href="<%=ORSView.MOSTWANTED_CTL%>">Most Wanted</b></a> | <a
		href="<%=ORSView.MOSTWANTED_LIST_CTL%>">Most wanted List</a> |
</h3>
<hr>
<h1>Most Wanted List</h1>

<form action="<%=ORSView.MOSTWANTED_LIST_CTL%>">

	<table width="100%">
		<tr>
			<td align="center"><label>Name Of Criminal :</label> <input
				type="text" name="nameOfCriminal"
				value="<%=ServletUtility.getParameter("nameOfCriminal", request)%>">&emsp;
				<label>Type Of Crime :</label> <input type="text" name="typeOfCrime"
				value="<%=ServletUtility.getParameter("typeOfCrime", request)%>">&emsp;
				<input type="submit" name="operation"
				value="<%=MostWantedListCtl.OP_SEARCH%>"></td>
		</tr>
	</table>
	<br>
	<table border="1" width="100%">
		<tr>
			<th>Select</th>
			<th>Name Of Criminal</th>
			<th>Type Of Crime</th>
			<th>Complexion</th>
			<th>Mark Of Identification</th>
			<th>Age</th>
			<th>Gender</th>
			<th>Height</th>
			<th>Status</th>
			<th>Photo</th>
			<th>Polics St</th>
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
			Iterator<MostWantedModel> it = list.iterator();

			while (it.hasNext()) {

				MostWantedModel bean = it.next();
		%>
		<tr>
			<td><input type="checkbox" name="ids" value="<%=bean.getId()%>"></td>
			<td><%=bean.getNameOfCriminal()%></td>
			<td><%=bean.getTypeOfCrime()%></td>
			<td><%=bean.getComplexion()%></td>
			<td><%=bean.getMarkOfIdentification()%></td>
			<td><%=bean.getAge()%></td>
			<td><%=bean.getGender()%></td>
			<td><%=bean.getHeight()%></td>
			<td><%=bean.getStatus()%></td>
			<td><%=bean.getPhoto()%></td>
			<td><%=bean.getPolicsStId()%></td>

			<td>
				<%
					if (ServletUtility.getRole(request) > AppRoles.USER) {
				%> <a href="MostWantedCtl?id=<%=bean.getId()%>">Edit</a> <%
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
				value="<%=MostWantedListCtl.OP_PREVIOUS%>"></td>
			<td align="center"><input type="submit" name="operation"
				value="<%=MostWantedListCtl.OP_DELETE%>"></td>
			<td align="right"><input type="submit" name="operation"
				value="<%=MostWantedCtl.OP_NEXT%>"></td>
		</tr>
	</table>
	<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
		type="hidden" name="pageSize" value="<%=pageSize%>">
</form>