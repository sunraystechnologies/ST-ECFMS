package in.co.sunrays.ocha.controller;

import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.model.CommentModel;
import in.co.sunrays.ocha.model.EResourceModel;
import in.co.sunrays.util.DataUtility;
import in.co.sunrays.util.DataValidator;
import in.co.sunrays.util.PropertyReader;
import in.co.sunrays.util.ServletUtility;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * Contains Comment navigation logics .
 * 
 * @version 1.0
 * @since 01 Feb 2015
 * @author SUNRAYS Developer
 * @Copyright (c) sunRays Technologies. All rights reserved.
 * @URL www.sunrays.co.in
 */

public class CommentCtl extends BaseCtl {

	private static final long serialVersionUID = 1L;

	/**
	 * Logger to log the messages.
	 */
	private static Logger log = Logger.getLogger(CommentCtl.class);

	/**
	 * Loads pre-loaded data like Dropdown List.
	 */

	@Override
	protected void preload(HttpServletRequest request) {
		EResourceModel model = new EResourceModel();
		try {
			List l = model.search();
			request.setAttribute("resourceList", l);
		} catch (ApplicationException e) {
			log.error(e);
		}

	}

	/**
	 * Validates Input data
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("CommentCtl Method validate Started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("text"))) {
			request.setAttribute("text",
					PropertyReader.getValue("error.require", "text"));
			pass = false;
		}

		log.debug("EResourceCtl Method validate Ended");

		return pass;

	}

	/**
	 * Handles GET request.
	 * 
	 */

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		CommentModel model = new CommentModel();
		model.setId(DataUtility.getLong(request.getParameter("id")));
		model.setText(DataUtility.getString(request.getParameter("text")));
		model.setName(DataUtility.getString(request.getParameter("name")));
		model.setResourceId(DataUtility.getLong(request
				.getParameter("resourceId")));
		System.out.println("hkhkh" + request.getParameter("resourceId"));
		long userId = (Long) session.getAttribute("userId");
		model.setUserId(userId);
		long id = DataUtility.getLong(request.getParameter("id"));
		String op = DataUtility.getString(request.getParameter("operation"));
		if (OP_SAVE.equalsIgnoreCase(op)) {
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
				ServletUtility.redirect(ORSView.COMMENT_LIST_CTL, request,
						response);
				return;

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility
					.redirect(ORSView.COMMENT_LIST_CTL, request, response);
			return;

		} else { // View page
			if (model.getResourceId() > 0) {

				ServletUtility.setModel(model, request);
			} else if (id > 0 || op != null) {

				try {
					model = model.findByPK(id);
					ServletUtility.setModel(model, request);
				} catch (ApplicationException e) {
					log.error(e);
					ServletUtility.handleException(e, request, response);
					return;
				}
			}
		}

		ServletUtility.forward(ORSView.COMMENT_VIEW, request, response);
	}

	/**
	 * Returns View page of Controller.
	 */
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.COMMENT_VIEW;
	}
}
