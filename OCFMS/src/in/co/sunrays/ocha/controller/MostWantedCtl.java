package in.co.sunrays.ocha.controller;
import in.co.sunrays.ocha.model.BaseModel;

import in.co.sunrays.ocha.model.CrimeReportModel;
import in.co.sunrays.ocha.model.MostWantedModel;
import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.exception.DuplicateRecordException;
import in.co.sunrays.ocha.model.UserModel;
import in.co.sunrays.util.DataUtility;
import in.co.sunrays.util.DataValidator;
import in.co.sunrays.util.PropertyReader;
import in.co.sunrays.util.ServletUtility;

import java.io.IOException;
import java.util.HashMap;
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

public class MostWantedCtl extends BaseCtl {

	public static final String OP_SAVE_UP = "Save";

	private static Logger log = Logger.getLogger(MostWantedCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {
		HashMap<String, String> toc = new HashMap<>();
		toc.put("Knife crime", "Knife crime");
		toc.put("Property crimes", "Property crimes");
		toc.put("Corporate crime", "Corporate crime‎");
		request.setAttribute("toc", toc);

	}

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("MostWantedCtl Method validate Started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("nameOfCriminal"))) {
			request.setAttribute("nameOfCriminal",
					PropertyReader.getValue("error.require", "Name Of Criminal"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("typeOfCrime"))) {
			request.setAttribute("typeOfCrime",
					PropertyReader.getValue("error.require", "Type Of Crime"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("complexion"))) {
			request.setAttribute("complexion",
					PropertyReader.getValue("error.require", "Complexion"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("markOfIdentification"))) {
			request.setAttribute("markOfIdentification",
					PropertyReader.getValue("error.require", "Mark Of Identification"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("age"))) {
			request.setAttribute("age",
					PropertyReader.getValue("error.require", "Age"));
			pass = false;
		}
	 
	        if (DataUtility.getInt(request.getParameter("age")) > 100) {
	            request.setAttribute("age", "Age Can Not Be Greater Than 100");
	            pass = false;
	        }
		if (DataValidator.isNull(request.getParameter("gender"))) {
			request.setAttribute("gender",
					PropertyReader.getValue("error.require", "Gender"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("height"))) {
			request.setAttribute("height",
					PropertyReader.getValue("error.require", "Height"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("status"))) {
			request.setAttribute("status",
					PropertyReader.getValue("error.require", "Status"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("photo"))) {
			request.setAttribute("photo", PropertyReader.getValue(
					"error.require", "Photo"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("policsStId"))) {
			request.setAttribute("policsStId",
					PropertyReader.getValue("error.require", "Police St Id"));
			pass = false;
		}
		
		log.debug("MostWantedCtl Method validate Ended");

		return pass;
	}

	@Override
	protected BaseModel populateModel(HttpServletRequest request) {

		log.debug("MostWantedCtl Method populatebean Started");

		MostWantedModel bean = new MostWantedModel();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		// bean.setRoleId(RoleBean.STUDENT);
		

		bean.setNameOfCriminal(DataUtility.getString(request
				.getParameter("nameOfCriminal")));

		bean.setTypeOfCrime(DataUtility.getString(request.getParameter("typeOfCrime")));
		bean.setComplexion(DataUtility.getString(request
				.getParameter("complexion")));
		bean.setMarkOfIdentification(DataUtility.getLong(request
				.getParameter("markOfIdentification")));
		bean.setAge(DataUtility.getInt(request
				.getParameter("age")));
		bean.setGender(DataUtility.getString(request.getParameter("gender")));
		bean.setHeight(DataUtility.getInt(request.getParameter("height")));

		bean.setStatus(DataUtility.getString(request.getParameter("status")));

		bean.setPhoto(DataUtility.getString(request
				.getParameter("photo")));

		bean.setPolicsStId(DataUtility.getLong(request.getParameter("policsStId")));

		populateDTO(bean, request);

		log.debug("MostWantedCtl Method populatebean Ended");

		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.debug("MostWantedCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		MostWantedModel model = (MostWantedModel) populateModel(request);

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
				ServletUtility.redirect(ORSView.MOSTWANTED_LIST_CTL, request,
						response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		} else {
			if (id > 0 || op != null) {
				MostWantedModel model1;
				try {
					model1 = model.findByPK(id);
					ServletUtility.setModel(model1, request);
				} catch (ApplicationException e) {

					ServletUtility.handleException(e, request, response);
					return;
				}
			}
		}
		ServletUtility.forwardView(ORSView.MOSTWANTED_VIEW, request, response);
		log.debug("MostWantedCtl Method doGet Ended");
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Long id = DataUtility.getLong(request.getParameter("id"));

		MostWantedModel model = new MostWantedModel();

		if (id > 0) {
			try {
				model = model.findByPK(id);
				ServletUtility.setModel(model, request);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forwardView(getView(), request, response);
	}

	@Override
	protected String getView() {
		return ORSView.MOSTWANTED_VIEW;
	}
}
