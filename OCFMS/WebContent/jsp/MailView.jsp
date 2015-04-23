<%@page import="in.co.sunrays.ocha.bean.UserBean"%>
<%@page import="in.co.sunrays.ocha.controller.MailCtl"%>
<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>

<jsp:useBean id="model" class="in.co.sunrays.ocha.model.MailModel"
	scope="request" />

<jsp:useBean id="userEmails" class="java.util.HashMap" scope="request" />

<h3>
	| <a href="<%=ORSView.MAIL_CTL%>">Compose</b></a> | <a
		href="<%=ORSView.INBOX_LIST_CTL%>">Inbox</a> | <a
		href="<%=ORSView.SENDBOX_LIST_CTL%>">Sent Mail</a> | <a
		href="<%=ORSView.MAIL_LIST_CTL%>">All Mail</b></a> |

</h3>
<hr>

<h1>Mail</h1>

<H2>
	<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
	</font>
</H2>
<H2>
	<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
	</font>
</H2>
<%
	UserBean userBean = (UserBean) session.getAttribute("user");
%>
<form action="<%=ORSView.MAIL_CTL%>" method="POST">

	<input type="hidden" name="id" value="<%=model.getId()%>">



	<table>
		<tr>
			<th>To</th>
			<td><%=HTMLUtility.getList("to", model.getReceiver(), userEmails)%></td>
		</tr>
		<tr>
			<th>Sender</th>
			<td><input readonly="readonly" name="sender"
				value="<%=userBean.getLogin()%>"></td>
		</tr>
		<tr>
			<th>Detail*</th>
			<td><textarea rows="3" cols="80" name="detail"><%=DataUtility.getStringData(model.getDetail())%></textarea>
				<font color="red"> <%=ServletUtility.getErrorMessage("detail", request)%></font></td>
		</tr>

		<tr>
			<th></th>
			<td colspan="2"><input type="submit" name="operation"
				value="<%=MailCtl.OP_SAVE_UP%>"></td>
		</tr>
	</table>
</form>
