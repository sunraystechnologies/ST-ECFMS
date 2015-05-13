<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="in.co.sunrays.ocha.model.MailModel"%>
<%@page import="in.co.sunrays.ocha.controller.MailListCtl"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<jsp:useBean id="model" class="in.co.sunrays.ocha.model.MailModel"
	scope="request"></jsp:useBean>

<h3>
	| <a href="<%=ORSView.MAIL_CTL%>">Compose</b></a> | <a
		href="<%=ORSView.INBOX_LIST_CTL%>">Inbox</a> | <a
		href="<%=ORSView.SENDBOX_LIST_CTL%>">Sent Mail</a> | <a
		href="<%=ORSView.MAIL_LIST_CTL%>">All Mail</b></a> |

</h3>
<hr>

<p class="st-title">Mail List</p>

<form action="<%=ORSView.MAIL_LIST_CTL%>">


	<table width="100%">
		<tr>
			<td align="center"><label>Sender :</label> <input type="text"
				name="sender"
				value="<%=ServletUtility.getParameter("sender", request)%>">

				<input type="submit" name="operation"
				value="<%=MailListCtl.OP_SEARCH%>"></td>

		</tr>
	</table>
	<br>

	<table border="1" width="100%">
		<tr>
			<th>Select</th>
			<th>Id</th>
			<th>Receiver</th>
			<th>Sender</th>
			<th>Detail</th>
		</tr>

		<tr>
			<td colspan="8"><%=HTMLUtility.getErrorMessage(request)%></td>
		</tr>

		<%
			int pageNo = ServletUtility.getPageNo(request);
			int pageSize = ServletUtility.getPageSize(request);
			int index = ((pageNo - 1) * pageSize) + 1;

			List list = ServletUtility.getList(request);
			Iterator<MailModel> it = list.iterator();
			while (it.hasNext()) {
				MailModel bean = it.next();
		%>
		<tr>
			<td><input type="checkbox" name="ids" value="<%=bean.getId()%>"></td>
			<td><%=bean.getId()%></td>
			<td><%=bean.getReceiver()%></td>
			<td><%=bean.getSender()%></td>
			<td><%=bean.getDetail()%></td>
		</tr>
		<%
			}
		%>
	</table>
	<table width="100%">
		<tr>
			<td align="left"><input type="submit" name="operation"
				value="<%=MailListCtl.OP_PREVIOUS%>"></td>
			<td align="center"><input type="submit" name="operation"
				value="<%=MailListCtl.OP_DELETE%>"></td>
			<td align="right"><input type="submit" name="operation"
				value="<%=MailListCtl.OP_NEXT%>"></td>
		</tr>
	</table>
	<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
		type="hidden" name="pageSize" value="<%=pageSize%>">

</form>
