package in.co.sunrays.ocha.controller;

import in.co.sunrays.common.model.UserModel;
import in.co.sunrays.ocha.bean.UserBean;
import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.model.BaseModel;
import in.co.sunrays.ocha.model.FeedbackModel;
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

public class FeedbackCtl extends BaseCtl {

	public static final String OP_SAVE_UP = "Save";

	private static Logger log = Logger.getLogger(FeedbackCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {
		FeedbackModel model = new FeedbackModel();
		try {
			List l = model.search(null);
			request.setAttribute("roleList", l);
		} catch (ApplicationException e) {
			log.error(e);
		}

	}

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("FeedbackCtl Method validate Started");
		System.out.println("Feedback" + request.getParameter("feedback"));

		boolean pass = true;
		if (DataValidator.isNull(request.getParameter("feedback"))) {
			request.setAttribute("feedback",
					PropertyReader.getValue("error.require", "Feedback"));
			pass = false;
		}

		log.debug("FeedbackCtl Method validate Ended");
		System.out.println("flag " + pass);
		return pass;
	}

	@Override
	protected BaseModel populateModel(HttpServletRequest request) {

		log.debug("FeedbackCtl Method populatebean Started");

		FeedbackModel model = new FeedbackModel();
		
		model.setId(DataUtility.getLong(request.getParameter("id")));

		// bean.setRoleId(RoleBean.STUDENT);
		model.setName(DataUtility.getString(request.getParameter("name")));
		model.setEmailId(DataUtility.getString(request.getParameter("emailId")));
		model.setFeedback(DataUtility.getString(request
				.getParameter("feedback")));

		populateModel(model, request);

		log.debug("FeedbackCtl Method populatebean Ended");

		return model;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.debug("FeedbackCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		FeedbackModel model = (FeedbackModel) populateModel(request);

		long id = model.getId();

		if (OP_SAVE_UP.equalsIgnoreCase(op)) {
			try {
				if (id > 0) {
					model.update();
				} else {
					long pk = model.add();
					model.setId(pk);
				}
				ServletUtility.setModel(model, request);
				ServletUtility.setSuccessMessage("Data is successfully saved",
						request);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_DELETE.equalsIgnoreCase(op)) {
			try {
				model.delete();
				ServletUtility.redirect(ORSView.FEEDBACK_LIST_CTL, request,
						response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		} else {
			if (id > 0 || op != null) {
				FeedbackModel model1;
				try {
					model1 = model.findByPK(id);
					ServletUtility.setModel(model1, request);
				} catch (ApplicationException e) {

					ServletUtility.handleException(e, request, response);
					return;
				}
			}
		}
		ServletUtility.forwardView(ORSView.FEEDBACK_VIEW, request, response);
		log.debug("FeedbackCtl Method doGet Ended");
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Long id = DataUtility.getLong(request.getParameter("id"));
		FeedbackModel model = new FeedbackModel();
		if (id > 0) {
			try {
				model = model.findByPK(id);
				ServletUtility.setModel(model, request);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}
		} else {
			UserModel userModel = (UserModel) request.getSession().getAttribute(
					"user");
			model.setName(userModel.getFirstName() + " "
					+ userModel.getLastName());
			model.setUserId(userModel.getId());
			model.setUserLogin(userModel.getLogin());
			model.setEmailId(userModel.getLogin());
		}
		ServletUtility.forwardView(ORSView.FEEDBACK_VIEW, request, response);
	}

	@Override
	protected String getView() {
		return ORSView.FEEDBACK_VIEW;
	}

}