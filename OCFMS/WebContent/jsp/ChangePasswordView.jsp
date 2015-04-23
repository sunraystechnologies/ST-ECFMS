
<%@page import="in.co.sunrays.ocha.controller.MyProfileCtl"%>
<%@page import="in.co.sunrays.ocha.controller.BaseCtl"%>
<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="in.co.sunrays.ocha.controller.ChangePasswordCtl"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>

<jsp:useBean id="bean" class="in.co.sunrays.ocha.bean.UserBean"
	scope="request"></jsp:useBean>
<H1>Change Password</H1>
<H2>
	<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
	</font>
</H2>
<H2>
	<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
	</font>
</H2>
<form action="<%=ORSView.CHANGE_PASSWORD_CTL%>" class="form-horizontal">
	<input type="hidden" name="id" value="<%=bean.getId()%>"> 
	<input type="hidden" name="roleId" value="<%=bean.getRoleId()%>"> 
	<input type="hidden" name="email" value="<%=bean.getLogin()%>">
	<input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
	<input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
	<input type="hidden" name="createdDatetime"	value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
	<input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

	<table>
		<tr>
			<th>Old Password</th>
			<td><input type="text" name="oldPassword" size=30
				value="<%=DataUtility
					.getString(request.getParameter("oldPassword") == null ? ""
							: DataUtility.getString(request
									.getParameter("oldPassword")))%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("oldPassword", request)%></font></td>
		<tr>
			<th>New Password</th>
			<td><input type="text" name="newPassword" size=30
				value="<%=DataUtility
					.getString(request.getParameter("newPassword") == null ? ""
							: DataUtility.getString(request
									.getParameter("newPassword")))%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("newPassword", request)%></font></td>
		</tr>

		<tr>
			<th>Confirm Password</th>
			<td><input type="text" name="confirmPassword" size=30
				value="<%=DataUtility.getString(request
					.getParameter("confirmPassword") == null ? "" : DataUtility
					.getString(request.getParameter("confirmPassword")))%>"><font
				color="red"> <%=ServletUtility
					.getErrorMessage("confirmPassword", request)%></font></td>
		</tr>

		<tr>
			<th></th>
			<td colspan="2"><input type="submit" name="operation"
				value="<%=BaseCtl.OP_SAVE%>"> <input type="submit"
				name="operation" value="<%=ChangePasswordCtl.OP_CHANGE_MY_PROFILE%>">
			</td>
		</tr>
	</table>
</form>
