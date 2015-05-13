<%@page import="in.co.sunrays.ocha.model.AppRoles"%>
<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="in.co.sunrays.ocha.controller.HotNewsListCtl"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.ocha.model.HotNewsModel"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="in.co.sunrays.util.AccessUtility"%>

<jsp:useBean id="model" class="in.co.sunrays.ocha.model.HotNewsModel"
	scope="request"></jsp:useBean>


<p class="st-title">HotNews List</p>

<form action="<%=ORSView.HOT_NEWS_LIST_CTL%>">


	<table width="100%">
		<tr>
			<td align="center"><label>News :</label> <input type="text"
				name="news"
				value="<%=ServletUtility.getParameter("news", request)%>">
				&emsp; <input type="submit" name="operation"
				value="<%=HotNewsListCtl.OP_SEARCH%>"></td>
		</tr>
	</table>
	<br>

	<table border="1" width="100%">
		<tr>
			<th>Select</th>
			<th>News</th>
			<th>Declared Date</th>
			<th>Time</th>
			<th>AuthorizedPerson</th>
			<th>Edit</th>
			<th></th>
		</tr>

		<tr>
			<td colspan="8"><%=HTMLUtility.getErrorMessage(request)%></td>
		</tr>

		<%
			int pageNo = ServletUtility.getPageNo(request);
			int pageSize = ServletUtility.getPageSize(request);
			int index = ((pageNo - 1) * pageSize) + 1;

			List list = ServletUtility.getList(request);
			Iterator<HotNewsModel> it = list.iterator();
			while (it.hasNext()) {
				HotNewsModel bean = it.next();
		%>
		<tr>
			<td><input type="checkbox" name="ids" value="<%=bean.getId()%>"></td>
			<td><%=bean.getNews()%></td>
			<td><%=bean.getDeclaredDate()%></td>
			<td><%=bean.getTime()%></td>
			<td><%=bean.getAuthorizedPerson()%></td>
			<td>
				<%
					String label = (AccessUtility.canWrite(request)) ? "Edit"
								: "View";
			
			%> <a href="<%=ORSView.HOT_NEWS_CTL%>?id=<%=model.getId()%>"><%=label%></a>


			</td>
		</tr>
		<%
			}
		%>
	</table>
	<table width="100%">
		<tr>
			<td><input type="submit" name="operation"
				value="<%=HotNewsListCtl.OP_PREVIOUS%>"></td>
			<td><input type="submit" name="operation"
				value="<%=HotNewsListCtl.OP_DELETE%>"></td>
			<td align="right"><input type="submit" name="operation"
				value="<%=HotNewsListCtl.OP_NEXT%>"></td>
		</tr>
	</table>
	<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
		type="hidden" name="pageSize" value="<%=pageSize%>">
</form>
