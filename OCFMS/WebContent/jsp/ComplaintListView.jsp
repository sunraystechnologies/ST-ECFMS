
<%@page import="in.co.sunrays.ocha.model.AppRoles"%>
<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="in.co.sunrays.ocha.model.ComplaintModel"%>
<%@page import="in.co.sunrays.ocha.controller.ComplaintListCtl"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<h3>
	| <a href="<%=ORSView.COMPLAINT_CTL%>">Complaint</b></a> | <a
		href="<%=ORSView.COMPLAINT_LIST_CTL%>">Complaint List</a> |
</h3>
<hr>
<p class="st-title">Complaint List</p>

<form action="<%=ORSView.COMPLAINT_LIST_CTL%>">
	<table width="100%">
		<tr>
			<td align="center"><label> ComplaintId :</label> <input
				type="text" name="complaintId"
				value="<%=ServletUtility.getParameter("complaintId", request)%>">
				<label>Type :</label> <input type="text" name="type"
				value="<%=ServletUtility.getParameter("type", request)%>"> <input
				type="submit" name="operation"
				value="<%=ComplaintListCtl.OP_SEARCH%>"></td>
		</tr>
	</table>
	<br>
	<table border="1" width="100%">
		<tr>
			<th>Select</th>
			<th>ID.</th>
			<th>Complaint Id.</th>
			<th>Type.</th>
			<th>Doc.</th>
			<th>Police Station Id.</th>
			<th>Police Station Name.</th>
			<th>Status</th>
			<th>Doc1.</th>
			<th>Doc2</th>
			<th>Doc3.</th>
			<th>Edit</th>
		</tr>
		<tr>
			<td colspan="8"><%=HTMLUtility.getErrorMessage(request)%></td>
		</tr>
		<%
			int pageNo = ServletUtility.getPageNo(request);
			int pageSize = ServletUtility.getPageSize(request);
			int index = ((pageNo - 1) * pageSize) + 1;

			List list = ServletUtility.getList(request);
			Iterator<ComplaintModel> it = list.iterator();

			while (it.hasNext()) {

				ComplaintModel model = it.next();
		%>
		<tr>
			<td><input type="checkbox" name="ids" value="<%=model.getId()%>"></td>
			<td><%=model.getId()%></td>
			<td><%=model.getcomplaintId()%></td>
			<td><%=model.getType()%></td>
			<td><%=model.getDoc()%></td>
			<td><%=model.getPoliceStationId()%></td>
			<td><%=model.getpoliceStationName()%></td>
			<td><%=model.getDoc1()%></td>
			<td><%=model.getDoc2()%></td>
			<td><%=model.getDoc3()%></td>
			<td><%=model.getDoc4()%></td>
			<td>
				<%
					if (ServletUtility.getRole(request) > AppRoles.USER) {
				%> <a
				href="ComplaintCtl?id=<%=model.getId()%>">Edit</a> <%
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
				value="<%=ComplaintListCtl.OP_PREVIOUS%>"></td>
			<td align="center"><input type="submit" name="operation"
				value="<%=ComplaintListCtl.OP_DELETE%>"></td>
			<td align="right"><input type="submit" name="operation"
				value="<%=ComplaintListCtl.OP_NEXT%>"></td>
		</tr>
	</table>
	<input type="hidden" name="pageNo" value="<%=pageNo%>"><input
		type="hidden" name="pageSize" value="<%=pageSize%>">


</form>
</center>
</body>
</html>