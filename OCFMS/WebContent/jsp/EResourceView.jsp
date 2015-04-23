<%@page import="in.co.sunrays.ocha.controller.EResourceCtl"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>
		<jsp:useBean id="model" class="in.co.sunrays.ocha.model.EResourceModel" 
			scope="request"></jsp:useBean>
<div class="container">
    <div class="row">
        <div  class="col-md-2">
        <%@include file="Header.jsp"%>
        </div>
        <div class="col-md-10">
        	<h3 align="Center"  style="margin-top: 140px">
					<hr>
				E-Resource
					</h3>
					<h3 class="col-md-offset-4">
				<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
				</font>
					<font color="red" >  <%=ServletUtility.getErrorMessage(request)%>
					</font></h3>
			<input type="hidden" name="id" value="<%=model.getId()%>">
			<input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(model.getCreatedOn())%>">
					<form action="<%=ORSView.ERESOURCE_CTL%>" class="form-horizontal">
						<div class="form-group">
					<label for="inputtableContains" class="control-label col-md-offset-2 col-md-2">
					Table Contains</label>
					<div class="col-md-3">
						<input type="text" class="form-control" name="tableContains" id="tableContains"
							placeholder="Table Contains" value="<%=DataUtility.getStringData(model.getTablesContains())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("tableContains", request)%></font>
					</div>
				</div>	
			
				<div class="form-group">
					<label for="inputDetail" class="control-label col-md-offset-2 col-md-2">
					Detail</label>
					<div class="col-md-3">
						<input type="text" class="form-control" name="detail" id="detail"
							placeholder="Detail" value="<%=DataUtility.getStringData(model.getDetail())%>">
						
					<font color="red"> <%=ServletUtility.getErrorMessage("detail", request)%></font>
					</div>
				</div>	
					<div class="form-group">
					<label for="inputName" class="control-label col-md-offset-2 col-md-2">
					Name</label>
					<div class="col-md-3">
						<input type="text" class="form-control" name="name" id="name"
							placeholder="Name"value="<%=DataUtility.getStringData(model.getName())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("name", request)%></font>
					</div>
				</div>	
						
					<div class="form-group">
				<div class="col-md-offset-4 ">
						<button name="operation" style="margin-left: 20px" class="btn icon-btn-save btn-success" value="<%=EResourceCtl.OP_SAVE%>" type="submit">
						<span class="btn-save-label">
						<i class="glyphicon glyphicon-floppy-disk"></i>
						</span>
						save</button>
							<%
				 	if (model.getId() > 0) {
				 %>
				 	<button name="operation" class="btn btn-success"  value="<%=EResourceCtl.OP_DELETE%>" type="submit">
						
						Delete
							</button>
				  <%
				 	}
				 %><button name="operation" class="btn btn-success" value="<%=EResourceCtl.OP_CANCEL%>" type="submit">
						
						Cancel
							</button>
						</div>
						</div>
	</form>
					</div>
					</div>
					</div>
		

</body>
</html>