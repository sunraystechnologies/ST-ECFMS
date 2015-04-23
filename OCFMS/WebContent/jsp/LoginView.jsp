
<%@page import="java.io.File"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="in.co.sunrays.ocha.model.NoticeModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.sunrays.ocha.controller.LoginCtl"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>


<jsp:useBean id="bean" class="in.co.sunrays.ocha.bean.UserBean"
	scope="request"></jsp:useBean>

<h2>Login</h2>

<H2>
	<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
	</font>
</H2>

<form action="<%=ORSView.LOGIN_CTL%>" method="POST">

	<table>
		<tr>
			<th>LoginId*</th>
			<td><input type="text" name="login" size=30
				value="<%=DataUtility.getStringData(bean.getLogin())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
		</tr>
		<tr>
			<th>Password*</th>
			<td><input type="password" name="password" size=30
				value="<%=DataUtility.getStringData(bean.getPassword())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font></td>
		</tr>
		<tr>
			<th></th>
			<td colspan="2"><input type="submit" name="operation"
				value="<%=LoginCtl.OP_SIGN_IN%>"> &nbsp; <input
				type="submit" name="operation" value="<%=LoginCtl.OP_SIGN_UP%>">
				&nbsp;</td>
		</tr>
		<tr>
			<th></th>
			<td><a href="<%=ORSView.FORGET_PASSWORD_CTL%>"><b>Forget
						my password</b></a>&nbsp;</td>
		</tr>
	</table>

</form>

