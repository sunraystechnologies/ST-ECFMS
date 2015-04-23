<%@page import="in.co.sunrays.ocha.bean.UserBean"%>
<%@page import="in.co.sunrays.ocha.model.FeedbackModel"%>
<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="java.util.List"%>
<%@page import="in.co.sunrays.ocha.controller.FeedbackCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>

<jsp:useBean id="model" class="in.co.sunrays.ocha.model.FeedbackModel"
	scope="request" />
<h3>
	| <a href="<%=ORSView.FEEDBACK_CTL%>">Feedback</b></a> | <a
		href="<%=ORSView.FEEDBACK_LIST_CTL%>">Feedback List</a> |
</h3>
<hr>
<h1>Feedback</h1>

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
<form action="<%=ORSView.FEEDBACK_CTL%>" method="POST">
	<input type="hidden" name="emailId" value="<%=userBean.getLogin()%>">
	<input type="hidden" name="name" value="<%=userBean.getFirstName()%>">

	<table>
		<tr>
			<th>Name</th>
			<td><%=userBean.getFirstName()%>
		</tr>
		<tr>
			<th>Email Id</th>
			<td><%=userBean.getLogin()%>
		</tr>

		<tr>
			<th>FeedBack*</th>
			<td><textarea rows="3" cols="80" name="feedback"><%=DataUtility.getStringData(model.getFeedback())%></textarea>
				<font color="red"> <%=ServletUtility.getErrorMessage("feedback", request)%></font></td>
		</tr>

		<tr>
			<th></th>
			<td colspan="2">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
				<input type="submit" name="operation"
				value="<%=FeedbackCtl.OP_SAVE_UP%>">
			</td>
		</tr>
	</table>
</form>
