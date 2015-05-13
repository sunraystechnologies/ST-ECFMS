package in.co.sunrays.common.controller;
import in.co.sunrays.ocha.controller.BaseCtl;

import in.co.sunrays.ocha.controller.ORSView;
import in.co.sunrays.util.ServletUtility;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class CollegeCtl
 */

public class ErrorCtl extends BaseCtl
{
	/**
	 * Logger to log the messages.
	 */
	public static final String OP_SAVE_UP = "Save";
	
	private static Logger log = Logger.getLogger(ErrorCtl.class);

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ServletUtility.forwardView(getView(), request, response);
	}

	@Override
	protected String getView() {
		return ORSView.ERROR_VIEW;
	}

}