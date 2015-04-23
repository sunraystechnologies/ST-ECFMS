
<%@page import="in.co.sunrays.ocha.controller.CommentListCtl"%>
<%@page import="in.co.sunrays.ocha.model.CommentModel"%>
<%@page import="in.co.sunrays.ocha.model.EResourceModel"%>
<%@page import="in.co.sunrays.ocha.controller.EResourceListCtl"%>
<%@page import="in.co.sunrays.ocha.model.NoticeModel"%>
<%@page import="in.co.sunrays.ocha.controller.NoticeListCtl"%>
<%@page import="in.co.sunrays.ocha.controller.UserListCtl"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

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
					Comment List
					</h2>
						<form action="<%=ORSView.COMMENT_LIST_CTL%>" class="form-inline">
<div class="table-responsive">
						<table class="table table-bordered table-hover" >
				<tr>
				<th>ID</th>
					<th>EReSource Name</th>
					<th>Text</th>
					<th>Edit</th>
				</tr>

				<tr>
					<td colspan="8"><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></td>
				</tr>

				<%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;

					List list = ServletUtility.getList(request);
					Iterator<CommentModel> it = list.iterator();
					while (it.hasNext()) {
						CommentModel model = it.next();
				%>
				<tr>
					<td><%=model.getId()%></td>
					<td><%=model.getName()%></td>
					<td><%=model.getText()%></td>
					<td><a href="CommentCtl?id=<%=model.getId()%>">Edit</a></td>
				</tr>
				<%
					}
				%>
			</table>
						
			<table width="100%">
				<tr>
					<td ><input type="submit" name="operation" class="btn btn-info"
						value="<%=CommentListCtl.OP_PREVIOUS%>"></td>
					 <td ><input type="submit"
						name="operation" class="btn btn-info" value="<%=CommentListCtl.OP_DELETE%>"></td>
					 <td align="right"><input type="submit" name="operation" class="btn btn-info"
						value="<%=CommentListCtl.OP_NEXT%>"></td>
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