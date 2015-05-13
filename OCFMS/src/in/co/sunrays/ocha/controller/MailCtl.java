package in.co.sunrays.ocha.controller;

import in.co.sunrays.common.model.UserModel;
import in.co.sunrays.ocha.bean.UserBean;
import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.model.BaseModel;
import in.co.sunrays.ocha.model.FeedbackModel;
import in.co.sunrays.ocha.model.MailModel;
import in.co.sunrays.ocha.model.PoliceStationModel;
import in.co.sunrays.util.DataUtility;
import in.co.sunrays.util.DataValidator;
import in.co.sunrays.util.PropertyReader;
import in.co.sunrays.util.ServletUtility;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
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

public class MailCtl extends BaseCtl {

	public static final String OP_SAVE_UP = "Send";

	private static Logger log = Logger.getLogger(MailCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {

		HashMap userEmails = new HashMap();

		UserModel uModel = new UserModel();

		try {
			List l = uModel.search(null);
			Iterator<UserModel> it = l.iterator();

			while (it.hasNext()) {
				UserModel model = it.next();
				String login = model.getLogin();
				String value = model.getFirstName() + " " + model.getLastName()
						+ " ( " + login + " ) ";
				userEmails.put(login, value);
			}
		} catch (ApplicationException e) {
			log.error(e);
		}
		request.setAttribute("userEmails", userEmails);
	}

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("MailCtl Method validate Started");
		System.out.println("Sender" + request.getParameter("sender"));

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("detail"))) {
			request.setAttribute("detail",
					PropertyReader.getValue("error.require", "Detail"));
			pass = false;
		}
	
		log.debug("MailCtl Method validate Ended");
		System.out.println("flag " + pass);
		return pass;
	}

	@Override
	protected BaseModel populateModel(HttpServletRequest request) {

		log.debug("MailCtl Method populatebean Started");

		MailModel model = new MailModel();

		System.out.println("To " + request.getParameter("to"));
		System.out.println("Sender " + request.getParameter("sender"));
		System.out.println("Detail" + request.getParameter("detail"));
		System.out.println("Attachement" + request.getParameter("attachment"));

		model.setId(DataUtility.getLong(request.getParameter("id")));
		model.setReceiver(DataUtility.getString(request.getParameter("to")));
		
		UserModel userModel = (UserModel) request.getSession().getAttribute("user");
		model.setSender(userModel.getLogin());

		model.setDetail(DataUtility.getString(request.getParameter("detail")));
		model.setAttachment(DataUtility.getString(request
				.getParameter("attachment")));

		populateModel(model, request);

		log.debug("MailCtl Method populatebean Ended");

		return model;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.debug("MailCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		MailModel model = (MailModel) populateModel(request);

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
				ServletUtility.redirect(ORSView.MAIL_LIST_CTL, request,
						response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		} else {
			if (id > 0 || op != null) {
				MailModel model1;
				try {
					model1 = model.findByPK(id);
					ServletUtility.setModel(model1, request);
				} catch (ApplicationException e) {

					ServletUtility.handleException(e, request, response);
					return;
				}
			}
		}
		ServletUtility.forwardView(ORSView.MAIL_VIEW, request, response);
		log.debug("MailCtl Method doGet Ended");
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Long id = DataUtility.getLong(request.getParameter("id"));
		MailModel model = new MailModel();
		if (id > 0) {
			try {
				model = model.findByPK(id);
				ServletUtility.setModel(model, request);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forwardView(ORSView.MAIL_VIEW, request, response);
	}

	@Override
	protected String getView() {
		return ORSView.MAIL_VIEW;
	}

}