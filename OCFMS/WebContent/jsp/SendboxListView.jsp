<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="in.co.sunrays.ocha.controller.BaseCtl"%>
<%@page import="in.co.sunrays.ocha.model.MailModel"%>
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
<h1>Send Box</h1>

<form action="<%=ORSView.SENDBOX_LIST_CTL%>">
	<table width="100%">
		<tr>
			<td align="center"><label>Receiver :</label> <input type="text"
				name="receiver"
				value="<%=ServletUtility.getParameter("receiver", request)%>">
				<input type="submit" name="operation" value="<%=BaseCtl.OP_SEARCH%>"></td>
		</tr>
	</table>
	<br>

	<table border="1" cellspacing="0" width="100%">
		<tr>
			<th>Select</th>
			<th>Id</th>
			<th>Receiver</th>
			<th>Detail</th>
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
			<td><%=bean.getReceiver()%></td>
			<td><%=bean.getDetail()%></td>
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
				value="<%=BaseCtl.OP_DELETE%>"></td>
			<td align="right"><input type="submit" name="operation"
				value="<%=BaseCtl.OP_NEXT%>"></td>
		</tr>
	</table>
	<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
		type="hidden" name="pageSize" value="<%=pageSize%>">
</form>
