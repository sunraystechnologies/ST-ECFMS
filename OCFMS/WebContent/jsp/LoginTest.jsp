<%@page import="java.io.File"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="in.co.sunrays.ocha.model.NoticeModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.sunrays.ocha.controller.LoginCtl"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="js/jquery-1.10.2.js"></script>
<script src="js/bootstrap.js"></script>
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet">
<html>
<body>
	<div class="container">
		<%-- 		<%@ include file="Header.jsp"%> --%>
		<jsp:useBean id="bean" class="in.co.sunrays.ocha.bean.UserBean"
			scope="request"></jsp:useBean>
		<br>
		<div class="row">
			<div class="jumbotron col-md-offset-2 col-sm-8">
				<div>
					<h1 class="col-md-offset-3">ECA Ajmer</h1>
				</div>
			</div>
		</div>
		<div class="row col-md-offset-2">
			<div class="col-md-4">
				<img alt="SGSITS"
					src="<%=ORSView.APP_CONTEXT%>/img/SGSITS_Indore.png" width="200"
					height="120">
			</div>
			<div class="col-md-4">
				<form action="">
					<div class="row">
						<label class="col-md-2">Login*</label> <input class="form-control" type="email"
							id="login" name="login" placeholder="Email ID"
							value="<%=DataUtility.getStringData(bean.getLogin())%>">
						<font color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font>
					</div>
					<br> <input type="password" class="form-control"
						name="password" id="password" placeholder="Password"
						value="<%=DataUtility.getStringData(bean.getLogin())%>"> <font
						color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font>
				</form>
			</div>
		</div>
	</div>



	<div class="row" style="margin-top: 30px">
		<img alt="" src="<%=ORSView.APP_CONTEXT%>/img/SGSITS_Indore.png"
			width="200" height="90"> <span class=" col-md-offset-3 "
			style="font-size: 25pt;">Login</span>

		<div class=" col-md-offset-3 " style="margin-top: -40px">
			<hr>
		</div>
	</div>

	<h3 class=" col-md-offset-4 ">
		<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
		</font>
	</h3>

	<input type="hidden" name="id" value="<%=bean.getId()%>">
	<input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
	<input type="hidden" name="modifiedBy"
		value="<%=bean.getModifiedBy()%>">
	<input type="hidden" name="createdDatetime"
		value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
	<input type="hidden" name="modifiedDatetime"
		value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
	<form action="<%=ORSView.LOGIN_CTL%>" class="form-horizontal">
		<div class="form-group">
			<div class="col-md-1"></div>
			<label for="inputEmail" class="control-label col-md-3">Email</label>
			<div class="col-md-3">
				<input type="email" class="form-control" id="login" name="login"
					placeholder="Email ID"
					value="<%=DataUtility.getStringData(bean.getLogin())%>"> <font
					color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font>
			</div>
		</div>
		<div class="form-group ">
			<div class="col-md-1"></div>
			<label for="inputPassword" class="control-label col-md-3">Password</label>
			<div class="col-md-3">
				<input type="password" class="form-control" name="password"
					id="password" placeholder="Password"
					value="<%=DataUtility.getStringData(bean.getLogin())%>"> <font
					color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font>
			</div>
		</div>

		<div class="form-group">
			<div class="col-md-offset-4 col-md-7">
				<button name="operation" style="margin-left: 15px"
					class="btn btn-success" value="<%=LoginCtl.OP_SIGN_IN%>"
					type="submit">
					<span class="btn-save-label"> <i
						class="glyphicon glyphicon-ok-circle"></i>
					</span> Sign In
				</button>
				<button name="operation" class="btn btn-success"
					value="<%=LoginCtl.OP_SIGN_UP%>" type="submit">Sign Up</button>
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-offset-6 col-md-7">
				<a href="<%=ORSView.FORGET_PASSWORD_CTL%>"> <i
					class="glyphicon glyphicon-edit"></i> Forget Password
				</a>
			</div>
		</div>
		<span class=" col-md-offset-5 " style="font-size: 25pt;">Notice</span>
		<marquee style="height: 20px" direction="left" behavior=scroll
			scrollamount="3">
			<%
				final ResourceBundle resourceBundle = ResourceBundle
						.getBundle("in.co.sunrays.bundle.system");
				String UPLOAD_DIRECTORY = resourceBundle
						.getString("log.noticepath");
				File jsp = new File(UPLOAD_DIRECTORY);
				String f = "";
				File[] list = jsp.listFiles();
				int index = 1;
				for (int i = 0; i < list.length; i++) {
					f = list[i].getName();
			%>
			<a
				href="<%=ORSView.NOTICE_LIST_CTL%>?fileName=<%=f%>&operation=Download"><%=f%></a>
			<%
				}
			%>
		</marquee>
		<%-- 			<div style="padding-left: 800px;margin-top: -100px">
			<h2 style="margin-left:150px">Notice</h2>
			<table border="1" width="80%">
			<tr>
			<th>Subject</th>
			<th>Details</th>
			<th>Create Date</th>
			<th>Expire Date</th>
			</tr>
				<%
			List list = (List) request.getAttribute("noticeList");
				Iterator it = list.iterator();
				while (it.hasNext()) {
				NoticeModel	model = (NoticeModel) it.next(); %>
				<tr>
				<td><%=model.getSubject() %></td>
				<td><%=model.getDetails() %></td>
				<td><%=model.getCreatedOn() %></td>
				<td><%=model.getExpireDate() %></td>
					</tr>
			<%
					}
				%>
		</table>
		</div> --%>
	</form>
	</div>
</body>
</html>