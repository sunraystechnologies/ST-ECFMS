
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>File Upload</title>
    </head>
 
    <body> 
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
		<form action="<%=ORSView.TIMETABLE_CTL%>" method="post" enctype="multipart/form-data" class="form-horizontal">
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
