package in.co.sunrays.common.controller;

import in.co.sunrays.common.model.RoleModel;
import in.co.sunrays.common.model.UserModel;
import in.co.sunrays.ocha.bean.BaseBean;
import in.co.sunrays.ocha.bean.UserBean;
import in.co.sunrays.ocha.controller.BaseCtl;
import in.co.sunrays.ocha.controller.ORSView;
import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.model.BaseModel;
import in.co.sunrays.util.DataUtility;
import in.co.sunrays.util.DataValidator;
import in.co.sunrays.util.PropertyReader;
import in.co.sunrays.util.ServletUtility;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * Login functionality Controller. Performs operation for Login
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */

public class LoginCtl extends BaseCtl {

	private static final long serialVersionUID = 1L;
	public static final String OP_REGISTER = "Register";
	public static final String OP_SIGN_IN = "SignIn";
	public static final String OP_SIGN_UP = "SignUp";
	public static final String OP_LOG_OUT = "logout";

	/**
	 * Logger to log the messages.
	 */

	private static Logger log = Logger.getLogger(LoginCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("LoginCtl Method validate Started");

		boolean pass = true;

		String op = request.getParameter("operation");
		if (OP_SIGN_UP.equals(op) || OP_LOG_OUT.equals(op)) {
			return pass;
		}

		String login = request.getParameter("login");

		if (DataValidator.isNull(login)) {
			request.setAttribute("login",
					PropertyReader.getValue("error.require", "Login Id"));
			pass = false;
		} else if (!DataValidator.isEmail(login)) {
			request.setAttribute("login",
					PropertyReader.getValue("error.email", "Login "));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password",
					PropertyReader.getValue("error.require", "Password"));
			pass = false;
		}

		log.debug("LoginCtl Method validate Ended");

		return pass;
	}

	@Override
	protected BaseModel populateModel(HttpServletRequest request) {
	log.debug("LoginCtl Method populatebean Started");
		UserModel bean = new UserModel();
		bean.setLogin(DataUtility.getString(request.getParameter("login")));
		bean.setPassword(DataUtility.getString(request.getParameter("password")));

        	log.debug("LoginCtl Method populatebean Ended");

		return bean;
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String operation = request.getParameter("op");
		if ("Home".equals(operation) && ServletUtility.isLoggedIn(request)) {
			ServletUtility.forwardView(ORSView.LOGIN_VIEW, request, response);
		} else {
		request.getSession().invalidate();
		//ServletUtility.forward(ORSView.LOGIN_VIEW, request, response);
		ServletUtility.forwardView(ORSView.LOGIN_VIEW, request, response);
		
	}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);

		log.debug(" Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		log.debug("Operation : " + op);

		// get model
		UserModel model = new UserModel();
		RoleModel role = new RoleModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SIGN_IN.equalsIgnoreCase(op)) {

			UserModel bean = (UserModel) populateModel(request);

			try {

				bean = model.authenticate(bean.getLogin(), 
                                            bean.getPassword());

				if (bean != null) {
					
					session.setAttribute("user", bean);
					session.setAttribute("userId", bean.getId());
					
					long rollId = bean.getRoleId();

					RoleModel rolemodel = role.findByPK(rollId);

					if (rolemodel != null) {
						session.setAttribute("role", rolemodel.getName());
					}

					System.out.println("I am here ");
					ServletUtility.forwardView(ORSView.WELCOME_VIEW, request,
							response);
					return;
				} else {
					bean = (UserModel) populateModel(request);
					ServletUtility.setModel(bean, request);
					ServletUtility.setErrorMessage(
							"Invalid LoginId And Password", request);
				}

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_LOG_OUT.equals(op)) {

			session = request.getSession();

			session.invalidate();

			ServletUtility.redirect(ORSView.LOGIN_CTL, request, response);

			return;

		} else if (OP_SIGN_UP.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.USER_REGISTRATION_CTL, request,
					response);
			return;

		}

		else { // View page

			if (id > 0 || op != null) {
				UserModel userbean;
				try {
					userbean = model.findByPK(id);
					ServletUtility.setModel(userbean, request);
				} catch (ApplicationException e) {
					log.error(e);
					ServletUtility.handleException(e, request, response);
					return;
				}
			}
		}

		//ServletUtility.forward(ORSView.LOGIN_VIEW, request, response);
		ServletUtility.forwardView(ORSView.LOGIN_VIEW, request, response);

		log.debug("LoginCtl Method doGet Ended");
	}

	@Override
	protected String getView() {
		return ORSView.LOGIN_VIEW;
	}

}