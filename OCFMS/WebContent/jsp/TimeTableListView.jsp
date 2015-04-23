<%@page import="java.util.ResourceBundle"%>
<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>File Upload </title>
    </head>
 
    <body> 
    <div class="container">
    <div class="row">
        <div  class="col-md-2">
        <%@include file="Header.jsp"%>
        </div>
        <div class="col-md-10">
        	
					<h2 align="Center"  style="margin-top: 140px">
					<hr>
					Time Table  List
					</h2>
					<div class="table-responsive">
						<table class="table table-bordered table-hover" >
				<tr>
				<th>ID</th>
					<th>FileName</th>
             <th>Download</th>
         <%
			if (userBean.getRoleId() == RoleModel.ADMIN  || userBean.getRoleId() == RoleModel.STAFF) {
		%>      <th>Delete</th>
		<%} %>
				</tr>
    <% 
      final  ResourceBundle resourceBundle = ResourceBundle
	.getBundle("in.co.sunrays.bundle.system");
    String UPLOAD_DIRECTORY = resourceBundle.getString("log.path");
    File jsp = new File(UPLOAD_DIRECTORY);

 String f = "";
 File[] list = jsp.listFiles();
int index=1;
 for(int i=0;i<list.length;i++)
 {

    f = list[i].getName();


	%>
	<tr>
		<td><%=index+i%></td>
		<td><%=f%></td>
<td><a href="<%=ORSView.Download_CTL %>?fileName=<%=f%>&operation=Download">Download</a></td>
 <%
			if (userBean.getRoleId() == RoleModel.ADMIN) {
		%>
<td><a href="<%=ORSView.Download_CTL %>?fileName=<%=f%>&operation=Delete">Delete</a></td>
<%} %>
	</tr>
	<%
		}
	%>
						</table>
						</div>
					</div>
					</div>
					</div>
	
    </body>
</html>
