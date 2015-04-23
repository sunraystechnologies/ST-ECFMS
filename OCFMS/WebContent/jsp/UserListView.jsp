<%@page import="in.co.sunrays.ocha.bean.UserBean"%>
<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
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
					User List
					</h2>
          	<form action="<%=ORSView.USER_LIST_CTL%>" class="form-inline">
          	 <div class="form-group">
					<label for="inputfirstName" class="control-label  col-md-4">First Name</label>
		<div class="col-md-2">
						<input type="text" class="form-control" name="firstName" id="firstName"
							placeholder="First Name" value="<%=ServletUtility.getParameter("firstName", request)%>">
					</div>
					</div>
					<div class="form-group">
				<label for="inputLoginId" class="control-label col-md-4">Login Id</label>
					<div class="col-md-2">
						<input type="text" class="form-control" name="login" id="login"
						value="<%=ServletUtility.getParameter("login", request)%>"	placeholder="Login ID">
					</div>
		</div>
				<div class="form-group">
						<button name="operation" value="<%=UserListCtl.OP_SEARCH %>" type="submit" class="col-md-offset-1 btn btn-info">
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
					<th>Select</th>
					<th>FirstName</th>
					<th>LastName</th>
					<th>LoginId</th>
					<th>Gender</th>
					<th>DOB</th>
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
					Iterator<UserBean> it = list.iterator();
					while (it.hasNext()) {
						UserBean bean = it.next();
				%>
				<tr>
					<td><input type="checkbox" name="ids" value="<%=bean.getId()%>"></td>
					<td><%=bean.getFirstName()%></td>
					<td><%=bean.getLastName()%></td>
					<td><%=bean.getLogin()%></td>
					<td><%=bean.getGender()%></td>
					<td><%=bean.getDob()%></td>
					<td><a href="UserCtl?id=<%=bean.getId()%>">Edit</a></td>
				</tr>
				<%
					}
				%>
			</table>
			<table width="100%">
				<tr>
							<td ><input type="submit" name="operation" class="btn btn-info"
						value="<%=UserListCtl.OP_PREVIOUS%>"></td>
					 <td ><input type="submit"
						name="operation" class="btn btn-info" value="<%=UserListCtl.OP_DELETE%>"></td>
					 <td align="right"><input type="submit" class="btn btn-info" name="operation"
						value="<%=UserListCtl.OP_NEXT%>"></td>
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