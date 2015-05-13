<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="in.co.sunrays.ocha.model.MailModel"%>
<%@page import="in.co.sunrays.ocha.controller.InboxListCtl"%>


<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>


<jsp:useBean id="model" class="in.co.sunrays.ocha.model.MailModel"
	scope="request"></jsp:useBean>

<h3>
	| <a href="<%=ORSView.MAIL_CTL%>">Compose</b></a> | <a
		href="<%=ORSView.INBOX_LIST_CTL%>">Inbox</a> | <a
		href="<%=ORSView.SENDBOX_LIST_CTL%>">Sent Mail</a> | <a
		href="<%=ORSView.MAIL_LIST_CTL%>">All Mail</b></a> |

</h3>
<hr>
<p class="st-title">Inbox</p>

<form action="<%=ORSView.INBOX_LIST_CTL%>">


	<table width="100%">
		<tr>
			<td align="center"><label>Sender :</label> <input type="text"
				name="sender"
				value="<%=ServletUtility.getParameter("sender", request)%>">

				<input type="submit" name="operation"
				value="<%=InboxListCtl.OP_SEARCH%>"></td>

		</tr>
	</table>
	<br>

	<table border="1" cellspacing="0" width="100%">
		<tr>
			<th>Select</th>
			<th>Id</th>
			<th>Sender</th>
			<th>Detail</th>
			<th>Attachment</th>
		</tr>

		<tr>
			<td colspan="8"><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></td>
		</tr>

		<%
			int pageNo = ServletUtility.getPageNo(request);
			int pageSize = ServletUtility.getPageSize(request);
			int index = ((pageNo - 1) * pageSize) + 1;

			List list = ServletUtility.getList(request);
			Iterator<MailModel> it = list.iterator();
			int i = 0;
			while (it.hasNext()) {
				i++;
				MailModel bean = it.next();
		%>
		<tr>
			<td><input type="checkbox" name="ids" value="<%=bean.getId()%>"></td>
			<td><%=i%></td>
			<td><%=bean.getSender()%></td>
			<td><%=bean.getDetail()%></td>
			<td><%=bean.getAttachment()%></td>
			<%-- 			<td><a href="<%=ORSView.MAIL_CTL%>?id=<%=bean.getId()%>">Edit</a></td>
		 --%>
		</tr>
		<%
			}
		%>
	</table>
	<table width="100%">
		<tr>
			<td><input type="submit" name="operation"
				value="<%=InboxListCtl.OP_PREVIOUS%>"></td>
			<td><input type="submit" name="operation"
				value="<%=InboxListCtl.OP_DELETE%>"></td>
			<td align="right"><input type="submit" name="operation"
				value="<%=InboxListCtl.OP_NEXT%>"></td>
		</tr>
	</table>
	<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
		type="hidden" name="pageSize" value="<%=pageSize%>">
</form>
