<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="java.util.List"%>
<%@page import="in.co.sunrays.ocha.controller.HotNewsCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>

<jsp:useBean id="model" class="in.co.sunrays.ocha.model.HotNewsModel"
	scope="request"></jsp:useBean>

<p class="st-title">Hot News</p>

<%=HTMLUtility.getSuccessMessage(request)%>
<%=HTMLUtility.getErrorMessage(request)%>

<form  name="stForm"  action="<%=ORSView.HOT_NEWS_CTL%>" method="POST">

	<input type="hidden" name="id" value="<%=model.getId()%>">

	<table>

		<tr>
			<th>News*</th>
			<td><input type="text" name="news"
				value="<%=DataUtility.getStringData(model.getNews())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("news", request)%></font></td>
		</tr>
		<tr>
					<th>Declared Date(mm/dd/yyyy)*</th>
					<td><input type="text" name="declaredDate" readonly="readonly"
						value="<%=DataUtility.getDateString(model.getDeclaredDate())%>">
						<a href="javascript:getCalendar(document.forms['stForm'].declaredDate);">
							<img src="../img/cal.jpg" width="16" height="15" border="0"
							alt="Calender">
					</a><font color="red"> <%=ServletUtility.getErrorMessage("declaredDate", request)%></font></td>
				</tr>
		<tr>
			<th>Time(HH:MM) *</th>
			<td><input type="text" name="time"
				value="<%=DataUtility.getStringData(model.getTime())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("time", request)%></font></td>
		</tr>
		<tr>
			<th>Authorized Person*</th>
			<td><input type="text" name="authorizedPerson"
				value="<%=DataUtility.getStringData(model.getAuthorizedPerson())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("authorizedPerson",
					request)%></font></td>
		</tr>

		<tr>
			<th></th>
			<td colspan="2">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
				<input type="submit" name="operation"
				value="<%=HotNewsCtl.OP_SAVE_UP%>">
			</td>
		</tr>
	</table>
</form>
