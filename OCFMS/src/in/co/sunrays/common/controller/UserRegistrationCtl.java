package in.co.sunrays.common.controller;

import in.co.sunrays.common.model.RoleModel;
import in.co.sunrays.common.model.UserModel;
import in.co.sunrays.ocha.bean.BaseBean;
import in.co.sunrays.ocha.bean.UserBean;
import in.co.sunrays.ocha.controller.BaseCtl;
import in.co.sunrays.ocha.controller.ORSView;
import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.exception.DuplicateRecordException;
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

import org.apache.log4j.Logger;

/**
 * User registration functionality Controller. Performs operation for User
 * Registration
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */

public class UserRegistrationCtl extends BaseCtl {

	public static final String OP_SIGN_UP = "SignUp";

	/**
	 * Logger to log the messages.
	 */
	private static Logger log = Logger.getLogger(UserRegistrationCtl.class);

	/**
	 * Loads pre-loaded data like Dropdown List.
	 */
	@Override
	protected void preload(HttpServletRequest request) {
		RoleModel model = new RoleModel();
		try {
			List l = model.search();
			request.setAttribute("roleList", l);
		} catch (ApplicationException e) {
			log.error(e);
		}

	}

	/**
	 * Validates Input data
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("UserRegistrationCtl Method validate Started");

		boolean pass = true;

		String login = request.getParameter("login");
		String dob = request.getParameter("dob");

		if (DataValidator.isNull(request.getParameter("firstName"))) {
			request.setAttribute("firstName",
					PropertyReader.getValue("error.require", "First Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName",
					PropertyReader.getValue("error.require", "Last Name"));
			pass = false;
		}
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
		if (DataValidator.isNull(request.getParameter("confirmPassword"))) {
			request.setAttribute("confirmPassword", PropertyReader.getValue(
					"error.require", "Confirm Password"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("gender"))) {
			request.setAttribute("gender",
					PropertyReader.getValue("error.require", "Gender"));
			pass = false;
		}
		if (DataValidator.isNull(dob)) {
			request.setAttribute("dob",
					PropertyReader.getValue("error.require", "Date Of Birth"));
			pass = false;
		} else if (!DataValidator.isDate(dob)) {
			request.setAttribute("dob",
					PropertyReader.getValue("error.date", "Date Of Birth"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo",
					PropertyReader.getValue("error.require", "Mobile No"));
			pass = false;
		}
		if (!request.getParameter("password").equals(
				request.getParameter("confirmPassword"))
				&& !"".equals(request.getParameter("confirmPassword"))) {
			ServletUtility
					.setErrorMessage(
							"Confirm  Password  does not match with password!",
							request);

			pass = false;
		}
		log.debug("UserRegistrationCtl Method validate Ended");

		return pass;
	}

	@Override
	protected BaseModel populateModel(HttpServletRequest request) {

		log.debug("UserRegistrationCtl Method populatebean Started");

		UserModel bean = new UserModel();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		// bean.setRoleId(RoleBean.STUDENT);
		bean.setRoleId(DataUtility.getLong(request.getParameter("roleId")));

		bean.setFirstName(DataUtility.getString(request
				.getParameter("firstName")));

		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));

		
		bean.setLogin(DataUtility.getString(request.getParameter("login")));
		bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));
		bean.setPassword(DataUtility.getString(request.getParameter("password")));

		bean.setConfirmPassword(DataUtility.getString(request
				.getParameter("confirmPassword")));

		bean.setGender(DataUtility.getString(request.getParameter("gender")));

		bean.setDob(DataUtility.getDate(request.getParameter("dob")));
		System.out.println("sdsddsddsd"+bean.getDob());
		populateModel(bean, request);

		log.debug("UserRegistrationCtl Method populatebean Ended");

		return bean;
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// ServletUtility.forward(ORSView.USER_REGISTRATION_VIEW,
		// request,response);

		ServletUtility.forwardView(ORSView.USER_REGISTRATION_VIEW, request,
				response);

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in get method");
		log.debug("UserRegistrationCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		UserModel model = new UserModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SIGN_UP.equalsIgnoreCase(op)) {

			UserModel bean = (UserModel) populateModel(request);

			try {
				long pk = bean.registerUser();
				bean.setId(pk);
				request.getSession().setAttribute("UserBean", bean);
				ServletUtility.redirect(ORSView.LOGIN_CTL, request, response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				log.error(e);
				ServletUtility.setModel(bean, request);
				ServletUtility.setErrorMessage(
						"Sorry! Login is already taken!", request);
				ServletUtility.forwardView(ORSView.USER_REGISTRATION_VIEW, request,
						response);
			}

		} else {
			/*
			 * ServletUtility.forward(ORSView.USER_REGISTRATION_VIEW, request,
			 * response);
			 */
			ServletUtility.forwardView(ORSView.USER_REGISTRATION_VIEW, request,
					response);
		}

		log.debug("UserRegistrationCtl Method doGet Ended");
	}

	/**
	 * Returns View page of Controller.
	 */

	@Override
	protected String getView() {
		return ORSView.USER_REGISTRATION_VIEW;
	}

}