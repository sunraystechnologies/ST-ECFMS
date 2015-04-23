package in.co.sunrays.ocha.controller;

import in.co.sunrays.util.ServletUtility;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Contains navigation logic for Comment Views.
 * 
 * @version 1.0
 * @since 01 Feb 2015
 * @author SUNRAYS Developer
 * @Copyright (c) sunRays Technologies. All rights reserved.
 * @URL www.sunrays.co.in
 */

public class TimeTableCtl extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	// private final String UPLOAD_DIRECTORY =
	// "/media/ncs02/Workspace/My_Workspace/OCHA/WebContent/TimeTable/";
	
	private final ResourceBundle resourceBundle = ResourceBundle
			.getBundle("in.co.sunrays.bundle.system");
	  
	    @Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	      
	        //process only if its multipart content
	        if(ServletFileUpload.isMultipartContent(request)){
	        	String UPLOAD_DIRECTORY = resourceBundle.getString("log.path");
	            try {
	                List<FileItem> multiparts = new ServletFileUpload(
	                                         new DiskFileItemFactory()).parseRequest(request);
	              
	                for(FileItem item : multiparts){
	                    if(!item.isFormField()){
	                        String name = new File(item.getName()).getName();
						item.write(new File(UPLOAD_DIRECTORY + File.separator
								+ name));
	                    }
	                }
	           
	               //File uploaded successfully
	               request.setAttribute("message", "File Uploaded Successfully");
	            } catch (Exception ex) {
				request.setAttribute("message", "File Upload Failed due to "
						+ ex);
	            }          
	         
	        }else{
	            request.setAttribute("message",
	                                 "Sorry this Servlet only handles file upload request");
	        }
	    
		// request.getRequestDispatcher("/result.jsp").forward(request,
		// response);
	        ServletUtility.forward(ORSView.TIMETABLE1_VIEW, request, response);
	     
	    }

}
