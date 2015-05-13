<%@page import="in.co.sunrays.ocha.model.AppRoles"%>
<%@page import="in.co.sunrays.common.model.RoleModel"%>
<%@page import="in.co.sunrays.common.controller.LoginCtl"%>
<%@page import="in.co.sunrays.ocha.bean.UserBean"%>
<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="in.co.sunrays.common.model.UserModel"%>
<%
	UserModel userModel = (UserModel) session.getAttribute("user");

	boolean userLoggedIn = userModel != null;

	String welcomeMsg = "Hi, ";

	if (userLoggedIn) {
		String role = (String) session.getAttribute("role");
		welcomeMsg += userModel.getFirstName() + " (" + role + ")";
	} else {
		welcomeMsg += "Visitor";
	}
%>

<HR>

<%
	if (userLoggedIn) {
		if (AppRoles.ADMIN == userModel.getRoleId() || AppRoles.INSPECTOR == userModel.getRoleId() || AppRoles.COMMISSIONER == userModel.getRoleId()  ) {
%>
<table width="100%">
	<tr>
		<td width="80%" align="left"><a href="<%=ORSView.LOGIN_CTL%>">Home</a>
			| <a href="<%=ORSView.HOT_NEWS_CTL%>">Hot News</a> | <a
			href="<%=ORSView.HOT_NEWS_LIST_CTL%>">Hot News List</a> | <a
			href="<%=ORSView.CRIMEREPORT_CTL%>">Crime Report</a> | <a
			href="<%=ORSView.CRIMEREPORT_LIST_CTL%>">Crime Report List</a> | <a
			href="<%=ORSView.COMPLAINT_CTL%>">Complaint</b></a> | <a
			href="<%=ORSView.COMPLAINT_LIST_CTL%>">Complaint List</b></a> | <a
			href="<%=ORSView.FEEDBACK_CTL%>">Feedback</b></a> | <a
			href="<%=ORSView.FEEDBACK_LIST_CTL%>">Feedback List</b></a> | <a
			href="<%=ORSView.MAIL_CTL%>">Mail</b></a> | <a
			href="<%=ORSView.MAIL_LIST_CTL%>">Mail List</b></a> | <a
			href="<%=ORSView.MOSTWANTED_CTL%>">Most Wanted</a> | <a
			href="<%=ORSView.MOSTWANTED_LIST_CTL%>">Most Wanted List</a> | <a
			href="<%=ORSView.MISSINGPERSON_CTL%>">Missing Person</a> | <a
			href="<%=ORSView.MISSINGPERSON_LIST_CTL%>">Missing Person List</a> |
			<a href="<%=ORSView.POLICESTATION_CTL%>">Police Station</a> | <a
			href="<%=ORSView.POLICESTATION_LIST_CTL%>">Police Station List</a> |
			<a href="<%=ORSView.INBOX_LIST_CTL%>">Inbox</a> | <a
			href="<%=ORSView.SENDBOX_LIST_CTL%>">Send Items</a> | <a
			href="<%=ORSView.MY_PROFILE_CTL%>">My Profile</a> |</td>

		<td align="right"><%=welcomeMsg%></td>
	</tr>

</table>
<%
	} else {
%>

<table width="100%">
	<tr>
		<td width="80%" align="left"><a href="<%=ORSView.LOGIN_CTL%>">Home</a>
			| <a href="<%=ORSView.COMPLAINT_CTL%>">Complaint</b></a> | <a
			href="<%=ORSView.CRIMEREPORT_CTL%>">Crime Report</a> | <a
			href="<%=ORSView.MISSINGPERSON_CTL%>">Missing Person</a> | <a
			href="<%=ORSView.MOSTWANTED_CTL%>">Most Wanted</a> | <a
			href="<%=ORSView.FEEDBACK_CTL%>">Feedback</b></a> | <a
			href="<%=ORSView.MAIL_CTL%>">Mail</b></a> | <a
			href="<%=ORSView.MY_PROFILE_CTL%>">My Profile</a> |</td>

		<td align="right"><%=welcomeMsg%></td>
	</tr>

</table>
<%
	}
	} else {
%>

<table width="100%">
	<tr>
		<td width="80%" align="left"><a href="<%=ORSView.LOGIN_CTL%>">Home</a>
			| <a href="<%=ORSView.HOT_NEWS_LIST_CTL%>">Hot News List</a> | <a
			href="<%=ORSView.MISSINGPERSON_LIST_CTL%>">Missing Person List</a> |
		</td>
		<td align="right"><%=welcomeMsg%></td>
	</tr>

</table>

<%
	}
%>
<HR>