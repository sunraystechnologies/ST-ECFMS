<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="in.co.sunrays.ocha.model.FeedbackModel"%>
<%@page import="in.co.sunrays.ocha.controller.FeedbackListCtl"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<jsp:useBean id="model" class="in.co.sunrays.ocha.model.FeedbackModel"
	scope="request"></jsp:useBean>
<h3>
	| <a href="<%=ORSView.FEEDBACK_CTL%>">Feedback</b></a> | <a
		href="<%=ORSView.FEEDBACK_LIST_CTL%>">Feedback List</a> |
</h3>
<hr>

<p class="st-title">Feedback List</p>

<form action="<%=ORSView.FEEDBACK_LIST_CTL%>">


	<table width="100%">
		<tr>
			<td align="center"><label>Name :</label> <input type="text"
				name="name"
				value="<%=ServletUtility.getParameter("name", request)%>">
				&emsp; <input type="submit" name="operation"
				value="<%=FeedbackListCtl.OP_SEARCH%>"></td>
		</tr>
	</table>
	<br>

	<table border="1" width="100%">
		<tr>
			<th>Select</th>
			<th>Id</th>
			<th>Name</th>
			<th>Email Id</th>
			<th>FeedBack</th>
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
			Iterator<FeedbackModel> it = list.iterator();
			while (it.hasNext()) {
				FeedbackModel bean = it.next();
		%>
		<tr>
			<td><input type="checkbox" name="ids" value="<%=bean.getId()%>"></td>
			<td><%=bean.getId()%></td>
			<td><%=bean.getName()%></td>
			<td><%=bean.getEmailId()%></td>
			<td><%=bean.getFeedback()%></td>
			<td><a href="<%=ORSView.FEEDBACK_CTL%>?id=<%=bean.getId()%>">Edit</a></td>
		</tr>
		<%
			}
		%>
	</table>
	<table width="100%">
		<tr>
			<td align="left"><input type="submit" name="operation"
				value="<%=FeedbackListCtl.OP_PREVIOUS%>"></td>
			<td align="center"><input type="submit" name="operation"
				value="<%=FeedbackListCtl.OP_DELETE%>"></td>
			<td align="right"><input type="submit" name="operation"
				value="<%=FeedbackListCtl.OP_NEXT%>"></td>
		</tr>
	</table>
	<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
		type="hidden" name="pageSize" value="<%=pageSize%>">
</form>
