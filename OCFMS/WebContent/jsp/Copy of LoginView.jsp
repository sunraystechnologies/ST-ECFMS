
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

<!-- link href="font-awesome/css/font-awesome.min.css" rel="stylesheet"  -->

<html>

<body>

	<jsp:useBean id="bean" class="in.co.sunrays.ocha.bean.UserBean"
		scope="request"></jsp:useBean>

	<div class="container">

		<div class="row" style="margin-top: 30px">

			<img alt="" src="<%=ORSView.APP_CONTEXT%>/img/customLogo.jpg"
				width="200" height="90"> <span class=" col-md-offset-3 "
				style="font-size: 25pt;">Login</span>
		</div>

		<h1>Login</h1>

		<h3 >
			<font color="red"> <%=ServletUtility.getErrorMessage(request)%></font>
		</h3>

		<form action="<%=ORSView.LOGIN_CTL%>" class="form-horizontal">
			'

			<div class="control-group">
				<label class="control-label" for="inputEmail">Login ID</label>

				<div class="controls">
					<input type="text" id="inputEmail" name="login">
				</div>

			</div>

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
					<button name="operation" class="btn btn-success"
						value="<%=LoginCtl.OP_SIGN_IN%>" type="submit">SignIn</button>
					<button name="operation" class="btn btn-success"
						value="<%=LoginCtl.OP_SIGN_UP%>" type="submit">SignUp</button>
				</div>

			</div>
			<div class="form-group">
				<div class="col-md-offset-6 col-md-7">
					<a href="<%=ORSView.FORGET_PASSWORD_CTL%>"> <i
						class="glyphicon glyphicon-edit"></i> Forget Password
					</a>
				</div>
			</div>
	</div>
</body>
</html>