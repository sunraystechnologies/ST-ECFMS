package in.co.sunrays.ocha.controller;

import in.co.sunrays.util.ServletUtility;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ResourceBundle;
import java.util.Scanner;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Contains navigation logic for Comment Views.
 * 
 * @version 1.0
 * @since 01 Feb 2015
 * @author SUNRAYS Developer
 * @Copyright (c) sunRays Technologies. All rights reserved.
 * @URL www.sunrays.co.in
 */

public class DownloadCtl extends HttpServlet {

	private static final int BYTES_DOWNLOAD = 1024;

	private final ResourceBundle resourceBundle = ResourceBundle
			.getBundle("in.co.sunrays.bundle.system");

	/**
	 * Handles GET request.
	 * 
	 */

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String fileName = request.getParameter("fileName");
		String op = request.getParameter("operation");
		String UPLOAD_DIRECTORY = resourceBundle.getString("log.path");
		 File filepath = new File(UPLOAD_DIRECTORY+fileName);
		/*
		 * System.out.println(fileName); String pathname =
		 * "/media/ncs02/Workspace/My_Workspace/ORSProject4/WebContent/TimeTable/"
		 * ;
		 * 
		 * File f = new File(pathname, fileName); Scanner in = new Scanner(f);
		 * PrintWriter out = response.getWriter(); String line = null; while
		 * (in.hasNext()) { line = in.nextLine(); out.println(line); }
		 * in.close(); out.close();
*/	if(op.equalsIgnoreCase("Delete")){
	if(filepath.delete()){
		System.out.println("Deleted");
				ServletUtility.forward("/jsp/TimeTableListView.jsp", request,
						response);
	}
		} else {
		System.out.println("ggg"+fileName);
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition",
	                     "attachment;filename="+fileName);
		ServletContext ctx = getServletContext();
		InputStream is = ctx.getResourceAsStream("TimeTable/"+fileName);
	 System.out.println("sss"+is);
		int read=0;
		byte[] bytes = new byte[BYTES_DOWNLOAD];
		OutputStream os = response.getOutputStream();
	 
		while((read = is.read(bytes))!= -1){
			os.write(bytes, 0, read);
		}
		os.flush();
		os.close();	
	
	   }
	}
	
	}
		
	

