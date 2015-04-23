package in.co.sunrays.ocha.controller;

import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.model.BaseModel;
import in.co.sunrays.ocha.model.HotNewsModel;
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

public class HotNewsCtl extends BaseCtl {

	public static final String OP_SAVE_UP = "Save";

	private static Logger log = Logger.getLogger(HotNewsCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {
		HotNewsModel model = new HotNewsModel();
		try {
			List l = model.search(null);
			request.setAttribute("roleList", l);
		} catch (ApplicationException e) {
			log.error(e);
		}

	}

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("HotNewsCtl Method validate Started");
		System.out.println("news" + request.getParameter("news"));
		System.out.println("date" + request.getParameter("declaredDate"));
		System.out.println("time" + request.getParameter("time"));
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("news"))) {
			request.setAttribute("news",
					PropertyReader.getValue("error.require", "News"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("declaredDate"))) {
			request.setAttribute("declaredDate",
					PropertyReader.getValue("error.require", "Declared Date"));
			pass = false;

		}
		if (DataValidator.isNull(request.getParameter("time"))) {
			request.setAttribute("time",
					PropertyReader.getValue("error.require", "Time"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("authorizedPerson"))) {
			request.setAttribute("authorizedPerson", PropertyReader.getValue(
					"error.require", "Authorized Person"));
			pass = false;
		}

		log.debug("HotNewsCtl Method validate Ended");
		System.out.println("flag " + pass);
		return pass;
	}

	@Override
	protected BaseModel populateModel(HttpServletRequest request) {

		log.debug("HotNewsCtl Method populatebean Started");

		HotNewsModel model = new HotNewsModel();

		model.setId(DataUtility.getLong(request.getParameter("id")));

		// bean.setRoleId(RoleBean.STUDENT);
		model.setNews(DataUtility.getString(request.getParameter("news")));
		model.setDeclaredDate(DataUtility.getDate(request
				.getParameter("declaredDate")));
		model.setTime(DataUtility.getDate(request.getParameter("time")));
		model.setAuthorizedPerson(DataUtility.getString(request
				.getParameter("authorizedPerson")));

		populateDTO(model, request);

		log.debug("HotNewsCtl Method populatebean Ended");

		return model;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.debug("HotNewsCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		HotNewsModel model = (HotNewsModel) populateModel(request);

		long id = model.getId();

		if (OP_SAVE_UP.equalsIgnoreCase(op)) {
			try {
				if (id > 0) {
					model.update(model);
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
				ServletUtility.redirect(ORSView.HOT_NEWS_LIST_CTL, request,
						response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		} else {
			if (id > 0 || op != null) {
				HotNewsModel model1;
				try {
					model1 = model.findByPK(id);
					ServletUtility.setModel(model1, request);
				} catch (ApplicationException e) {

					ServletUtility.handleException(e, request, response);
					return;
				}
			}
		}
		ServletUtility.forwardView(ORSView.HOTNEWS_VIEW, request, response);
		log.debug("HotNewsCtl Method doGet Ended");
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Long id = DataUtility.getLong(request.getParameter("id"));
		HotNewsModel model = new HotNewsModel();
		if (id > 0) {
			try {
				model = model.findByPK(id);
				ServletUtility.setModel(model, request);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forwardView(ORSView.HOTNEWS_VIEW, request, response);
	}

	@Override
	protected String getView() {
		return ORSView.HOTNEWS_VIEW;
	}

}