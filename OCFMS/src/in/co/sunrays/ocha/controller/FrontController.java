package in.co.sunrays.ocha.controller;

import in.co.sunrays.common.model.UserModel;
import in.co.sunrays.ocha.controller.ORSView;
import in.co.sunrays.util.ServletUtility;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * Main Controller performs session checking and logging operations before
 * calling any application controller. It prevents any user to access
 * application without login.
 * 
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */
public class FrontController implements Filter {

	private static Logger log = Logger.getLogger(FrontController.class);

	public void destroy() {
		log.debug("FrontCtl destroy Method");
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		log.debug("FrontCtl Method doFilter Started");

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		UserModel userModel = ServletUtility.getUserModel(request);
		log.debug("----->>>>User Model" + userModel);

		if (userModel != null) {
			chain.doFilter(req, resp);
			log.debug("----->>>>11");
		} else {
			ServletUtility.setErrorMessage(
					"OOPS ! Your session has been expired, Please relogin.",
					request);
			log.debug("----->>>>22");
			ServletUtility
					.redirect(
							ORSView.LOGIN_CTL
									+ "?error=OOPS ! Your session has been expired, Please relogin.",
							request, response);
		}
		log.debug("FrontCtl Method doFilter End");

	}

	public void init(FilterConfig conf) throws ServletException {
		log.debug("FrontCtl init Method");
	}

}