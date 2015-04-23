<%@page import="in.co.sunrays.ocha.controller.CrimeReportListCtl"%>
<%@page import="in.co.sunrays.ocha.controller.CrimeReportCtl"%>
<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.ocha.model.CrimeReportModel"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<jsp:useBean id="model"
	class="in.co.sunrays.ocha.model.CrimeReportModel" scope="request"></jsp:useBean>
<h3>
	| <a href="<%=ORSView.CRIMEREPORT_CTL%>">Crime Report</b></a> | <a
		href="<%=ORSView.CRIMEREPORT_LIST_CTL%>">Crime Report List</a> |
</h3>
<hr>
<h1>Crime Report List</h1>

<form action="<%=ORSView.CRIMEREPORT_LIST_CTL%>">

	<table width="100%">
		<tr>
			<td align="center"><label>Type Of Crime:</label> <input
				type="text" name="typeOfCrime"
				value="<%=ServletUtility.getParameter("typeOfCrime", request)%>">&emsp;
				<input type="submit" name="operation"
				value="<%=CrimeReportListCtl.OP_SEARCH%>"></td>
		</tr>
	</table>
	<br>
	<table border="1" width="100%">
		<tr>
			<th>Select</th>
			<th>Cr Id</th>
			<th>Type Of Crime</th>
			<th>Date Of Crime</th>
			<th>Time</th>
			<th>Report Date</th>
			<th>Place</th>
			<th>Police Station Name</th>
			<th>Detail</th>
			<th>Picture</th>
			<th>Document</th>
			<th>Polics St Id</th>
			<th>Edit</th>
		</tr>
		<tr>
			<td colspan="11"><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></td>
		</tr>
		<%
			int pageNo = ServletUtility.getPageNo(request);
			int pageSize = ServletUtility.getPageSize(request);
			int index = ((pageNo - 1) * pageSize) + 1;

			List list = ServletUtility.getList(request);
			Iterator<CrimeReportModel> it = list.iterator();

			while (it.hasNext()) {

				CrimeReportModel bean = it.next();
		%>
		<tr>
			<td><input type="checkbox" name="ids" value="<%=bean.getId()%>"></td>
			<td><%=bean.getCrId()%></td>
			<td><%=bean.getTypeOfCrime()%></td>
			<td><%=bean.getDateOfCrime()%></td>
			<td><%=bean.getTime()%></td>
			<td><%=bean.getReportDate()%></td>
			<td><%=bean.getPlace()%></td>
			<td><%=bean.getPoliceStationName()%></td>
			<td><%=bean.getDetail()%></td>
			<td><%=bean.getPicture()%></td>
			<td><%=bean.getDocument()%></td>
			<td><%=bean.getPoliceStId()%></td>

			<td><a href="CrimeReportCtl?id=<%=bean.getId()%>">Edit</a></td>
		</tr>

		<%
			}
		%>
	</table>
	<table width="100%">
		<tr>
			<td align="left"><input type="submit" name="operation"
				value="<%=CrimeReportListCtl.OP_PREVIOUS%>"></td>
			<td align="center"><input type="submit" name="operation"
				value="<%=CrimeReportListCtl.OP_DELETE%>"></td>
			<td align="right"><input type="submit" name="operation"
				value="<%=CrimeReportCtl.OP_NEXT%>"></td>
		</tr>
	</table>
	<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
		type="hidden" name="pageSize" value="<%=pageSize%>">
</form>