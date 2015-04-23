
<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="in.co.sunrays.ocha.controller.BaseCtl"%>
<%@page import="java.io.File"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="in.co.sunrays.ocha.model.NoticeModel"%>
<%@page import="in.co.sunrays.ocha.controller.NoticeListCtl"%>
<%@page import="in.co.sunrays.ocha.controller.UserListCtl"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>


<h1>Notice List</h1>

<form action="<%=ORSView.NOTICE_LIST_CTL%>">

	<table width="100%">
		<tr>
			<td align="center"><label> Subject :</label> <input type="text"
				name="subject"
				value="<%=ServletUtility.getParameter("subject", request)%>">
				&emsp; <label>Detail :</label> <input type="text" name="detail"
				value="<%=ServletUtility.getParameter("detail", request)%>">&emsp;
				<input type="submit" name="operation" value="<%=BaseCtl.OP_SEARCH%>"></td>
		</tr>
	</table>
	<br>

	<table border="1" width="100%">
		<tr>
			<th>Sq. No</th>
			<th>Subject</th>
			<th>Details</th>
			<th>Create Date</th>
			<th>Expire Date</th>
		</tr>

		<%
			int i = 1;
			List list = ServletUtility.getList(request);
			Iterator<NoticeModel> it = list.iterator();
			while (it.hasNext()) {
				NoticeModel model = it.next();
		%>
		<tr>
			<td><%=i++%></td>
			<td><%=model.getSubject()%></td>
			<td><%=model.getDetails()%></td>
			<td><%=model.getCreatedOn()%></td>
			<td><%=model.getExpireDate()%></td>
		</tr>
		<%
			}
		%>
	</table>

	<table width="100%">
		<tr>
			<td><input type="submit" name="operation"
				value="<%=BaseCtl.OP_PREVIOUS%>"></td>
			<td><input type="submit" name="operation"
				value="<%=BaseCtl.OP_NEW%>"></td>
			<td><input type="submit" name="operation"
				value="<%=BaseCtl.OP_DELETE%>"></td>
			<td align="right"><input type="submit" name="operation"
				value="<%=BaseCtl.OP_NEXT%>"></td>
		</tr>
	</table>

	<%
		int pageNo = ServletUtility.getPageNo(request);
		int pageSize = ServletUtility.getPageSize(request);
		int index = ((pageNo - 1) * pageSize) + 1;
	%>
	<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
		type="hidden" name="pageSize" value="<%=pageSize%>">
</form>


