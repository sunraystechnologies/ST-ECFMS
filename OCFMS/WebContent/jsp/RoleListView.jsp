
<%@page import="in.co.sunrays.ocha.controller.RoleListCtl"%>
<%@page import="in.co.sunrays.ocha.controller.BaseCtl"%>
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
				Role List
					</h2>
						<form action="<%=ORSView.ROLE_LIST_CTL%>" class="form-inline">
						 <div class="form-group">
					<label for="inputName" class="control-label  col-md-3">Name</label>
		<div class="col-md-2">
						<input type="text" class="form-control" name="name" id="name"
							placeholder="Name" value="<%=ServletUtility.getParameter("name", request)%>">
							</div>
								</div>
								<div class="form-group">
						<button name="operation" value="<%=RoleListCtl.OP_SEARCH %>" type="submit" class=" btn btn-info">
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
					<th>S.No.</th>
					<th>Id</th>
					<th>Name</th>
					<th>Description</th>
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
					Iterator<RoleModel> it = list.iterator();
					while (it.hasNext()) {
						RoleModel model = it.next();
				%>
				<tr>
					<td><%=index++%></td>
					<td><%=model.getId()%></td>
					<td><%=model.getName()%></td>
					<td><%=model.getDescription()%></td>
					<td><a href="RoleCtl?id=<%=model.getId()%>">Edit</a></td>
				</tr>
				<%
					}
				%>
			</table>
						
			<table width="100%">
				<tr>
					<td><input type="submit" class="btn btn-info" name="operation"
						value="<%=RoleListCtl.OP_PREVIOUS%>"></td>
					<td></td>
					<td align="right"><input type="submit" class="btn btn-info" name="operation"
						value="<%=RoleListCtl.OP_NEXT%>"></td>
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