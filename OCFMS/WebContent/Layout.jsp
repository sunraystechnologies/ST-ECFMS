<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Crime Station</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%=ORSView.CSS_FOLDER%>/style.css" rel="stylesheet" type="text/css" />

<style type="">

A {
	color: #FFFFFF;
}

H2 {
	color: #FFFFFF;
}

H1 {
	font-family: 'Helvetica Neue', Helvetica, Arial;
	color: #FFFFFF;
}

TD {
	font-family: 'Helvetica Neue', Helvetica, Arial;
	color: #FFFFFF;
}

TH  {
	font-family: 'Helvetica Neue', Helvetica, Arial;
	color: #FFFFFF;
}



</style>

<link rel="stylesheet" type="text/css" href="<%=ORSView.CSS_FOLDER%>/coin-slider.css" />
<script type="text/javascript" src="<%=ORSView.JS_FOLDER%>/cufon-yui.js"></script>
<script type="text/javascript" src="<%=ORSView.JS_FOLDER%>/droid_sans_400-droid_sans_700.font.js"></script>
<script type="text/javascript" src="<%=ORSView.JS_FOLDER%>/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=ORSView.JS_FOLDER%>/script.js"></script>
<script type="text/javascript" src="<%=ORSView.JS_FOLDER%>/coin-slider.min.js"></script>
<script type="text/javascript"
	src="<%=ORSView.JS_FOLDER%>/calendar.js"></script>
</head>
<body>
<div class="main">
  <div class="header">
    <div class="header_resize">
      <div class="searchform">
        <form id="formsearch" name="formsearch" method="post" action="#">
          <span>
          <input name="editbox_search" class="editbox_search" id="editbox_search" maxlength="80" value="Search our ste:" type="text" />
          </span>
          <input name="button_search" src="<%=ORSView.IMAGES_FOLDER%>/search.gif" class="button_search" type="image" />
        </form>
      </div>
      
      <div class="logo">
        <h1><a href="index.html"><span>Crime</span>Station</a></h1>
      </div>
      <div class="clr"></div>
      <div class="menu_nav">
        <ul>
          <li class="active"><a href="<%=ORSView.APP_CONTEXT%>/index.html"><span>Home Page</span></a></li>
          <li><a href="<%=ORSView.USER_REGISTRATION_CTL%>"><span>SignUp</span></a></li>
          <li><a href="about.html"><span>About Us</span></a></li>
          <li><a href="blog.html"><span>FAQ</span></a></li>
          <li><a href="<%=ORSView.LOGOUT_CTL%>"><span>Logout</span></a></li>
        </ul>
      </div>
      <div class="clr"></div>
	  <div> 
	  
	  <%
		 String bodyPage = null;

		 bodyPage = (String) request.getAttribute("bodyPage");
		 
		 String headerPage = "/jsp/Header.jsp";
	   %>
	   
		<jsp:include page="<%=headerPage%>"></jsp:include>
		<jsp:include page="<%=bodyPage%>"></jsp:include>
	
	  </div>
      <div class="clr"></div>
  </div>
  <br>
  <div class="fbg">
    <div class="fbg_resize">
      <div class="col c1">
        <h2><span>Image</span> Gallery</h2>
        <a href="#"><img src="<%=ORSView.IMAGES_FOLDER%>/gal1.jpg" width="75" height="75" alt="" class="gal" /></a> <a href="#"><img src="<%=ORSView.IMAGES_FOLDER%>/gal2.jpg" width="75" height="75" alt="" class="gal" /></a> <a href="#"><img src="<%=ORSView.IMAGES_FOLDER%>/gal3.jpg" width="75" height="75" alt="" class="gal" /></a> <a href="#"><img src="<%=ORSView.IMAGES_FOLDER%>/gal4.jpg" width="75" height="75" alt="" class="gal" /></a> <a href="#"><img src="<%=ORSView.IMAGES_FOLDER%>/gal5.jpg" width="75" height="75" alt="" class="gal" /></a> <a href="#"><img src="<%=ORSView.IMAGES_FOLDER%>/gal6.jpg" width="75" height="75" alt="" class="gal" /></a> </div>
      <div class="col c2">
        <h2><span>Services</span> Overview</h2>
        <p>Curabitur sed urna id nunc pulvinar semper. Nunc sit amet tortor sit amet lacus sagittis posuere cursus vitae nunc.Etiam venenatis, turpis at eleifend porta, nisl nulla bibendum justo.</p>
        <ul class="fbg_ul">
          <li><a href="#">Lorem ipsum dolor labore et dolore.</a></li>
          <li><a href="#">Excepteur officia deserunt.</a></li>
          <li><a href="#">Integer tellus ipsum tempor sed.</a></li>
        </ul>
      </div>
      <div class="col c3">
        <h2><span>Contact</span> Us</h2>
        
        <p class="contact_info"> <span>Address:</span> 323-Dhanani park-4,<br />
          <span>&nbsp;</span>Ajwa Road, Vadodara<br />
          <span>Mobile:</span> 9033-498-580<br />
          <span>FAX:</span> +00000000<br />
          <span>E-mail:</span> <a href="www.gmail.com">saifcross2@gmail.com</a> </p>
      </div>
      <div class="clr"></div>
    </div>
  </div>
  <div class="footer">
    <div class="footer_resize">
      
      <p class="rf"><a href="#"></a></p>
      <div style="clear:both;"></div>
    </div>
  </div>
</div>
</body>
</html>
