
<%@page import="in.co.sunrays.ocha.model.AppRoles"%>
<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="java.util.List"%>
<%@page import="in.co.sunrays.common.controller.UserRegistrationCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>

<jsp:useBean id="bean" class="in.co.sunrays.ocha.bean.UserBean"
	scope="request"></jsp:useBean>

<p class="st-title">Visitor Registration</p>

<%=HTMLUtility.getSuccessMessage(request)%>
<%=HTMLUtility.getErrorMessage(request)%>

<Form action="<%=ORSView.USER_REGISTRATION_CTL%>" method="POST"
	name="stForm">


	<table>
		<tr>
			<th>Register As*</th>
			<td><input type="radio" name="roleId" value="<%=AppRoles.USER%>"
				checked="checked"> Civilian <input type="radio"
				name="roleId" value="<%=AppRoles.INSPECTOR%>"> Inspector <input
				type="radio" name="roleId" value="<%=AppRoles.COMMISSIONER%>">
				Commissioner</td>
		</tr>
		<tr>
			<th>First Name*</th>
			<td><input type="text" name="firstName"
				value="<%=DataUtility.getStringData(bean.getFirstName())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font></td>
		</tr>
		<tr>
			<th>Last Name*</th>
			<td><input type="text" name="lastName"
				value="<%=DataUtility.getStringData(bean.getLastName())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font></td>
		</tr>
		<tr>
			<th>LoginId*</th>
			<td><input type="text" name="login"
				placeholder="Must be Email ID"
				value="<%=DataUtility.getStringData(bean.getLogin())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
		</tr>
		<tr>
			<th>Password*</th>
			<td><input type="password" name="password"
				value="<%=DataUtility.getStringData(bean.getPassword())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font></td>
		</tr>
		<tr>
			<th>Confirm Password*</th>
			<td><input type="password" name="confirmPassword"
				value="<%=DataUtility.getStringData(bean.getConfirmPassword())%>"><font
				color="red"> <%=ServletUtility
					.getErrorMessage("confirmPassword", request)%></font></td>
		</tr>
		<tr>
			<th>Gender *</th>
			<td>
				<%
					HashMap map = new HashMap();
					map.put("M", "Male");
					map.put("F", "Female");

					String htmlList = HTMLUtility.getList("gender", bean.getGender(),
							map);
				%> <%=htmlList%>

			</td>
		</tr>
		<tr>
			<th>Mobile No *</th>
			<td><input type="text" name="mobileNo"
				value="<%=DataUtility.getStringData(bean.getMobileNo())%>">
				</a><font color="red"> <%=ServletUtility.getErrorMessage("mobileNo", request)%></font></td>
		</tr>

		<tr>
			<th>Date Of Birth (mm/dd/yyyy) *</th>
			<td><input type="text" name="dob" readonly="readonly"
				value="<%=DataUtility.getDateString(bean.getDob())%>"> <a
				href="javascript:getCalendar(document.forms['stForm'].dob);"> <img
					src="./img/cal.jpg" width="16" height="15" border="0"
					alt="Calender">
			</a><font color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
		</tr>

		<tr>
			<th></th>
			<td colspan="2" align="center"><input type="submit"
				name="operation" value="<%=UserRegistrationCtl.OP_SIGN_UP%>">
			</td>
		</tr>
	</table>
</form>
