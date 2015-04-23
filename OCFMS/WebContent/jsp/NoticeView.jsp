<%@page import="in.co.sunrays.ocha.controller.NoticeCtl"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<!-- Latest compiled and minified JavaScript -->
<script src="../js/bootstrap.js"></script>
 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

	<script>
$(function() {
    $('.date-picker').datepicker( );
});
</script>
</head>
<body>
<%-- 	<jsp:useBean id="model" class="in.co.sunrays.ocha.model.NoticeModel" 
			scope="request"></jsp:useBean>
<div class="container">
    <div class="row">
        <div  class="col-md-2">
        <%@include file="Header.jsp"%>
        </div>
        <div class="col-md-10">
        	<h3 align="Center"  style="margin-top: 140px">
					<hr>
					Add Notice
					</h3>
					<h3 class="col-md-offset-4">
				<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
				</font>
					<font color="red" >  <%=ServletUtility.getErrorMessage(request)%>
					</font></h3>
			<input type="hidden" name="id" value="<%=model.getId()%>">
			<input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(model.getCreatedOn())%>">
			<form action="<%=ORSView.NOTICE_CTL%>" class="form-horizontal">
			<div class="form-group">
					<label for="inputsubject" class="control-label col-md-offset-2 col-md-2">
					Subject</label>
					<div class="col-md-3">
						<input type="text" class="form-control" name="subject" id="subject"
							placeholder="Subject" value="<%=DataUtility.getStringData(model.getSubject())%>">  
						<font
						color="red"> <%=ServletUtility.getErrorMessage("subject", request)%></font>
					</div>
				</div>	
			
				<div class="form-group">
					<label for="inputDetail" class="control-label col-md-offset-2 col-md-2">
					Detail</label>
					<div class="col-md-3">
						<input type="text" class="form-control" name="detail" id="detail"
							placeholder="Detail"value="<%=DataUtility.getStringData(model.getDetails())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("detail", request)%></font>
					</div>
				</div>	
					
					<div class="form-group">
					<label for="inputexpireDate" class="control-label col-md-offset-2 col-md-2" >Expire Date</label>
					<div class="col-md-3">
						<input type="text"  name="expireDate" id="expireDate"
							 class="form-control date-picker"
							placeholder="Expire Date"  
						value="<%=DataUtility.getDateString(model.getExpireDate())%>">
					<font color="red"> <%=ServletUtility.getErrorMessage("expireDate", request)%></font>
					</div>
				</div>
						
				<div class="form-group">
				<div class="col-md-offset-4 ">
						<button name="operation" style="margin-left: 20px" class="btn icon-btn-save btn-success" value="<%=NoticeCtl.OP_SAVE%>" type="submit">
						<span class="btn-save-label">
						<i class="glyphicon glyphicon-floppy-disk"></i>
						</span>
						save</button>
							<%
				 	if (model.getId() > 0) {
				 %>
				 	<button name="operation" class="btn btn-success"  value="<%=NoticeCtl.OP_DELETE%>" type="submit">
						
						Delete
							</button>
				  <%
				 	}
				 %><button name="operation" class="btn btn-success"  value="<%=NoticeCtl.OP_CANCEL%>" type="submit">
						
						Cancel
							</button>
						</div>
						</div>
				</form>		
        </div>
        </div>
        </div>
    
</body>
</html> --%>
 <div class="container">
    <div class="row">
        <div  class="col-md-2">
        <%@include file="Header.jsp"%>
        </div>
        <div class="col-md-10">
        	<h3 align="Center"  style="margin-top: 140px">
					<hr>
			Choose File to Upload in Server
					</h3>
					<div id="result">
            <h3 class="col-md-offset-4 ">${requestScope["message"]}</h3>
        </div>
		<form action="<%=ORSView.NOTICE_CTL%>" method="post" enctype="multipart/form-data" class="form-horizontal">
				<div class="form-group">
			
					<div class="col-md-offset-4 col-md-3">
						<input type="file" class="form-control" name="file" id="file"/>
						
					</div>
				</div>	
				<div class="col-md-offset-4 ">
				<button  value="upload" type="submit" class="col-md-offset-1 btn btn-info">
				upload
				</button>
				</div>

		</form>
	</div>
			</div>
			</div>

 
    </body>
</html>