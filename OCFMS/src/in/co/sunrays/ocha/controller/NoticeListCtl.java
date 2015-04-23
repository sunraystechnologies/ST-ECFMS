package in.co.sunrays.ocha.controller;

import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.model.NoticeModel;
import in.co.sunrays.util.DataUtility;
import in.co.sunrays.util.PropertyReader;
import in.co.sunrays.util.ServletUtility;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class NoticeListCtl extends HttpServlet{

	private static Logger log = Logger.getLogger(NoticeListCtl.class);

/*
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("NoticeListCtl doGet Start");

		List list = null;

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;

		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader
				.getValue("page.size")) : pageSize;


		String op = DataUtility.getString(request.getParameter("operation"));

		NoticeModel model = new NoticeModel();

		model.setSubject(DataUtility.getString(request.getParameter("subject")));
		model.setDetails(DataUtility.getString(request.getParameter("detail")));

		try {

			if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op)
					|| "Previous".equalsIgnoreCase(op)) {

				if (OP_SEARCH.equalsIgnoreCase(op)) {
					pageNo = 1;
				} else if (OP_NEXT.equalsIgnoreCase(op)) {
					pageNo++;
				} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
					pageNo--;
				}

			}
			list = model.search(pageNo, pageSize);
			ServletUtility.setList(list, request);
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No record found ", request);
			}
			ServletUtility.setList(list, request);

			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			request.setAttribute("notice", model);
			ServletUtility.forward(ORSView.NOTICE_LIST_VIEW, request, response);
		} catch (ApplicationException e) {
			log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
		}
		log.debug("NoticeListCtl doGet End");
	}

	@Override
	protected String getView() {
		return ORSView.NOTICE_LIST_VIEW;
	}*/
	
	private static final int BYTES_DOWNLOAD = 1024;
	private final ResourceBundle resourceBundle = ResourceBundle
			.getBundle("in.co.sunrays.bundle.system");
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String fileName = request.getParameter("fileName");
		String op = request.getParameter("operation");
		String UPLOAD_DIRECTORY = resourceBundle.getString("log.noticepath");
		 File filepath = new File(UPLOAD_DIRECTORY+fileName);
		/* System.out.println(fileName);
		String pathname = "/media/ncs02/Workspace/My_Workspace/ORSProject4/WebContent/TimeTable/";

		File f = new File(pathname, fileName);
		Scanner in = new Scanner(f);
		PrintWriter out = response.getWriter();
		String line = null;
		while (in.hasNext()) {
			line = in.nextLine();
			out.println(line);
		}
		in.close();
		out.close();
*/	if(op.equalsIgnoreCase("Delete")){
	if(filepath.delete()){
		System.out.println("Deleted");
		 ServletUtility.forward("/jsp/NoticeListView.jsp", request, response);
	}
}else if(op.equalsIgnoreCase("Download")){
		System.out.println("ggg"+fileName);
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition",
	                     "attachment;filename="+fileName);
		ServletContext ctx = getServletContext();
		InputStream is = ctx.getResourceAsStream("Notice/"+fileName);
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

