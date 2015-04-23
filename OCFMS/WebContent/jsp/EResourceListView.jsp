
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
				ERsource List
					</h2>
						<form action="<%=ORSView.ERESOURCE_LIST_CTL%>" class="form-inline">
						    	 <div class="form-group">
					<label for="inputtableContains" class="control-label  col-md-5">Table Contains</label>
		<div class="col-md-2">
						<input type="text" class="form-control" name="tablesContains" id="tablesContains"
							placeholder="Table Contains" value="<%=ServletUtility.getParameter("tablesContains", request)%>">
					</div>
					</div>
					<div class="form-group">
				<label for="inputName" class="control-label col-md-4">Name</label>
					<div class="col-md-2">
						<input type="text" class="form-control" name="name" id="name"
					value="<%=ServletUtility.getParameter("name", request)%>"	placeholder="Name">
					</div>
		</div>
				<div class="form-group">
						<button name="operation" value="<%=EResourceListCtl.OP_SEARCH %>" type="submit" class="col-md-offset-1 btn btn-info">
							<span class="btn-save-label">
						<i class="glyphicon glyphicon-search"></i>
						</span>
						Search
						</button>
						</div>
			<br>
		<br>
		<div class="table-responsive">
						<table class="table table-bordered table-hover" >
				<tr>
				<th>ID</th>
					<th>Table Contains</th>
					<th>Name</th>
					<th>Details</th>
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
					Iterator<EResourceModel> it = list.iterator();
					while (it.hasNext()) {
						EResourceModel model = it.next();
				%>
				<tr>
					<td><%=model.getId()%></td>
					<td><%=model.getTablesContains()%></td>
					<td><%=model.getName()%></td>
					<td><%=model.getDetail()%></td>
					<td><a href="EResourceCtl?id=<%=model.getId()%>">Edit</a></td>
				</tr>
				<%
					}
				%>
			</table>
						

			<table width="100%">
				<tr>
					<td ><input type="submit" name="operation"
						value="<%=EResourceListCtl.OP_PREVIOUS%>" class="btn btn-info"></td>
					 <td ><input type="submit"
						name="operation" value="<%=EResourceListCtl.OP_DELETE%>" class="btn btn-info"></td>
					 <td align="right"><input type="submit" name="operation"
						value="<%=EResourceListCtl.OP_NEXT%>" class="btn btn-info"></td>
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