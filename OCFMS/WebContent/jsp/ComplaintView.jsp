
<%@page import="in.co.sunrays.ocha.model.AppRoles"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="in.co.sunrays.ocha.controller.ComplaintCtl"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="java.util.List"%>

<jsp:useBean id="model" class="in.co.sunrays.ocha.model.ComplaintModel"
	scope="request" />

<jsp:useBean id="policeStList" class="java.util.ArrayList"
	scope="request" />
<h3>
	| <a href="<%=ORSView.COMPLAINT_CTL%>">Complaint</b></a> | <a
		href="<%=ORSView.COMPLAINT_LIST_CTL%>">Complaint List</a> |
</h3>
<hr>

<h1>Add Complain</h1>

<H2>
	<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
	</font>
</H2>

<H2>
	<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
	</font>
</H2>

<form name="stForm" action="ComplaintCtl" method="POST">

	<input type="hidden" name="id" value="<%=model.getId()%>">

	<table>

		<tr>
			<th>Complaint Id*</th>
			<td><input type="text" name="complaintId"
				value="<%=DataUtility.getStringData(model.getcomplaintId())%>"
				<%=(model.getId() > 0) ? "readonly" : ""%>><font color="red">
					<%=ServletUtility.getErrorMessage("complaintId", request)%></font></td>
		</tr>
		<tr>
			<th>Type*</th>
			<td><input type="text" name="type"
				value="<%=DataUtility.getStringData(model.getType())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("type", request)%></font></td>
		</tr>
		<tr>
			<th>Date Of crime (mm/dd/yyyy)</th>
			<td><input type="text" name="doc"
				value="<%=DataUtility.getDateString(model.getDoc())%>"> <a
				href="javascript:getCalendar(document.forms['stForm'].doc);"> <img
					src="../img/cal.jpg" width="16" height="15" border="0"
					alt="Calender">
			</a><font color="red"> <%=ServletUtility.getErrorMessage("doc", request)%></font></td>
		</tr>
		<tr>
			<th>Police Station*</th>
			<td><%=HTMLUtility.getList("policeStationid",
					String.valueOf(model.getPoliceStationId()), policeStList)%></td>
		</tr>
		<tr>
			<th>Status</th>
			<td>
				<%
					HashMap map = new HashMap();
					map.put("Not Started", "Not Started");
					map.put("In Progress", "In Progress");
					map.put("Completed", "Completed");
					String htmlList = HTMLUtility.getList("doc1", model.getDoc1(), map);

					if (ServletUtility.getRole(request) > AppRoles.USER) {
						out.println(htmlList);
					} else {
						out.println("Not Started" );
						out.println("<input type='hidden' name='doc1' value='Not Started'>");
					}
				%>

			</td>
		</tr>
		<tr>
			<th>Doc2</th>
			<td><input type="file" name="doc2"
				value="<%=DataUtility.getStringData(model.getDoc2())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("doc2", request)%></font></td>
		</tr>
		<tr>
			<th>Doc3</th>
			<td><input type="file" name="doc3"
				value="<%=DataUtility.getStringData(model.getDoc3())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("doc3", request)%></font></td>
		</tr>
		<tr>
			<th>Doc4</th>
			<td><input type="file" name="doc4"
				value="<%=DataUtility.getStringData(model.getDoc4())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("doc4", request)%></font></td>
		</tr>
		<tr>
			<td align="center" colspan="3"><input type="submit"
				name="operation" value="<%=ComplaintCtl.OP_SAVE%>"></td>
		</tr>
	</table>
</form>
