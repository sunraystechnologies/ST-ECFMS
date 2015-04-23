
<%@page import="in.co.sunrays.ocha.controller.RoleCtl"%>
<%@page import="in.co.sunrays.ocha.controller.BaseCtl"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<html>
<body>
		<jsp:useBean id="model" class="in.co.sunrays.ocha.model.RoleModel"
			scope="request"></jsp:useBean>
<div class="container">
    <div class="row">
        <div  class="col-md-2">
        <%@include file="Header.jsp"%>
        </div>
        <div class="col-md-10">
        	<h3 align="Center"  style="margin-top: 140px">
					<hr>
				Add Role
					</h3>
					<h3 class="col-md-offset-4 ">
				<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
				</font>
					<font color="red" >  <%=ServletUtility.getErrorMessage(request)%>
					</font></h3>
			<input type="hidden" name="id" value="<%=model.getId()%>">
			<input type="hidden" name="createdBy" value="<%=model.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy" value="<%=model.getModifiedBy()%>"> 
			<input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(model.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(model.getModifiedDatetime())%>">
			<form action="<%=ORSView.ROLE_CTL%>" class="form-horizontal">
								<div class="form-group">
					<label for="inputName" class="control-label col-md-offset-2 col-md-2">
					Name</label>
					<div class="col-md-3">
						<input type="text" class="form-control" name="name" id="name"
							placeholder="Name" value="<%=DataUtility.getStringData(model.getName())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("name", request)%></font>
					</div>
				</div>	
			
			
				<div class="form-group">
					<label for="inputDescription" class="control-label col-md-offset-2 col-md-2">
					Description</label>
					<div class="col-md-3">
						<input type="text" class="form-control" name="description" id="description"
							placeholder="Description" value="<%=DataUtility.getStringData(model.getDescription())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("description", request)%></font>
					</div>
				</div>	
		
				
					<div class="form-group">
				<div class="col-md-offset-4 ">
						<button name="operation" style="margin-left: 20px" class="btn icon-btn-save btn-success" value="<%=RoleCtl.OP_SAVE%>" type="submit">
						<span class="btn-save-label">
						<i class="glyphicon glyphicon-floppy-disk"></i>
						</span>
						save</button>
				<button name="operation" class="btn btn-success" value="<%=RoleCtl.OP_CANCEL%>" type="submit">
						
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