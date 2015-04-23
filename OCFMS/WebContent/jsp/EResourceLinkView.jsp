
<%@page import="in.co.sunrays.ocha.model.EResourceModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.sunrays.ocha.controller.BaseCtl"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>

<%@page import="java.util.List"%>

<html>
<body>
<div class="container">
    <div class="row">
        <div  class="col-md-2">
	<%@include file="Header.jsp"%>
        </div>
        <div class="col-md-10">
	
					<h2 align="Center"  style="margin-top: 140px">
					<hr>
			ERsource Link List
					</h2>
		<form action="<%=ORSView.ERESOURCE_LINK_CTL%>">
						<div class="table-responsive">
						<table class="table table-bordered table-hover" >
				<tr>
					<th>Table Contains</th>
				</tr>

				<tr>
					<td colspan="8"><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></td>
				</tr>

				<%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;

					List list = ServletUtility.getList(request);
					Iterator<EResourceModel> it = list.iterator();
					while (it.hasNext()) {
						EResourceModel model = it.next();
				%>
				<tr>
					<td><a href="CommentCtl?resourceId=<%=model.getId()%>&name=<%=model.getDetail()%>"><%=model.getTablesContains()%></a></td>
				</tr>
				<%
					}
				%>
			</table>
			<table width="100%">
				<tr>
					<td ><input type="submit" name="operation"  class="btn btn-info"
						value="<%=BaseCtl.OP_PREVIOUS%>"></td>
					 <td align="right"><input type="submit" name="operation"  class="btn btn-info"
						value="<%=BaseCtl.OP_NEXT%>"></td>
				</tr>
			</table>
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
						</div>
		</form>
					</div>
					</div>
					</div>

</body>
</html>