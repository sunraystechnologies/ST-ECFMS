
<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="in.co.sunrays.common.controller.ForgetPasswordCtl"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<jsp:useBean id="bean" class="in.co.sunrays.ocha.bean.UserBean"
	scope="request"></jsp:useBean>

<p class="st-title">Forgot your password?</p>

<H2>Submit your email address and we'll send you password.</H2>

<form action="<%=ORSView.FORGET_PASSWORD_CTL%>">

	<b>Email Id :</b> <input type="text" name="login"
		placeholder="Enter ID Here"
		value="<%=ServletUtility.getParameter("login", request)%>">&emsp;

	<input type="submit" name="operation"
		value="<%=ForgetPasswordCtl.OP_GO%>"> <br> <font
		color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font>
	<%=HTMLUtility.getSuccessMessage(request)%>
<%=HTMLUtility.getErrorMessage(request)%>
</form>

