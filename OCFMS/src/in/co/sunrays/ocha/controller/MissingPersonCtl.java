package in.co.sunrays.ocha.controller;

import in.co.sunrays.common.model.UserModel;
import in.co.sunrays.ocha.model.BaseModel;
import in.co.sunrays.ocha.model.MissingPersonModel;
import in.co.sunrays.ocha.model.MostWantedModel;
import in.co.sunrays.ocha.model.PoliceStationModel;
import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.exception.DuplicateRecordException;
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
public class MissingPersonCtl extends BaseCtl {

	public static final String OP_SAVE_UP = "Save";

	private static Logger log = Logger.getLogger(MissingPersonCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {
		MissingPersonModel model = new MissingPersonModel();
		try {
			List l = model.search();
			request.setAttribute("roleList", l);
		} catch (ApplicationException e) {
			log.error(e);
		}
		
		PoliceStationModel stationModel = new PoliceStationModel();
		try {
			List l = stationModel.search();
			request.setAttribute("policeStList", l);
		} catch (ApplicationException e) {
			log.error(e);
		}
	}
	
	protected boolean validate(HttpServletRequest request) {

		log.debug("MissingPersonCtl Method validate Started");

		boolean pass = true;
		if (DataValidator.isNull(request.getParameter("policeStId"))) {
			request.setAttribute("policeStId",
					PropertyReader.getValue("error.require", "Police St Id"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name",
					PropertyReader.getValue("error.require", "Name"));
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
		if (DataValidator.isNull(request.getParameter("dateOfMissing"))) {
			request.setAttribute("dateOfMissing",
					PropertyReader.getValue("error.require", "Date Of Missing"));
			pass = false;

		}
		if (DataValidator.isNull(request.getParameter("dateOfReporting"))) {
			request.setAttribute("dateOfReporting", PropertyReader.getValue(
					"error.require", "Date Of Reporting"));
			pass = false;

		}
		if (DataValidator.isNull(request.getParameter("complexion"))) {
			request.setAttribute("complexion",
					PropertyReader.getValue("error.require", "Complexion"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("hair"))) {
			request.setAttribute("hair",
					PropertyReader.getValue("error.require", "Hair"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("markOfIdentification"))) {
			request.setAttribute("markOfIdentification", PropertyReader
					.getValue("error.require", "Mark Of Identification"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("areaOfMissing"))) {
			request.setAttribute("areaOfMissing",
					PropertyReader.getValue("error.require", "Area Of Missing"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("reportId"))) {
			request.setAttribute("reportId",
					PropertyReader.getValue("error.require", "Report Id"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("photo"))) {
			request.setAttribute("photo",
					PropertyReader.getValue("error.require", "Photo"));
			pass = false;
		}

		log.debug("MissingPersonCtl Method validate Ended");

		return pass;
	}

	@Override
	protected BaseModel populateModel(HttpServletRequest request) {

		log.debug("MissingPersonCtl Method populatebean Started");

		MissingPersonModel bean = new MissingPersonModel();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		// bean.setRoleId(RoleBean.STUDENT);

		bean.setPoliceStId(DataUtility.getLong(request
				.getParameter("policeStId")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setAge(DataUtility.getInt(request.getParameter("age")));
		bean.setGender(DataUtility.getString(request.getParameter("gender")));
		bean.setHeight(DataUtility.getInt(request.getParameter("height")));
		bean.setDateOfMissing(DataUtility.getDate(request
				.getParameter("dateOfMissing")));

		bean.setDateOfReporting(DataUtility.getDate(request
				.getParameter("dateOfReporting")));

		bean.setComplexion(DataUtility.getString(request
				.getParameter("complexion")));

		bean.setHair(DataUtility.getString(request.getParameter("hair")));

		bean.setMarkOfIdentification(DataUtility.getLong(request
				.getParameter("markOfIdentification")));

		bean.setAreaOfMissing(DataUtility.getString(request
				.getParameter("areaOfMissing")));

		bean.setReportId(DataUtility.getLong(request.getParameter("reportId")));
		bean.setPhoto(DataUtility.getString(request.getParameter("photo")));
		populateModel(bean, request);

		log.debug("MissingPersonCtl Method populatebean Ended");

		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.debug("MissingPersonCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		MissingPersonModel model = (MissingPersonModel) populateModel(request);

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
				ServletUtility.redirect(ORSView.MISSINGPERSON_LIST_CTL,
						request, response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		} else {
			if (id > 0 || op != null) {
				MissingPersonModel model1;
				try {
					model1 = model.findByPK(id);
					ServletUtility.setModel(model1, request);
				} catch (ApplicationException e) {

					ServletUtility.handleException(e, request, response);
					return;
				}
			}
		}
		ServletUtility.forwardView(ORSView.MISSINGPERSON_VIEW, request,
				response);
		log.debug("MostWantedCtl Method doGet Ended");
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Long id = DataUtility.getLong(request.getParameter("id"));

		MissingPersonModel model = new MissingPersonModel();

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
		return ORSView.MISSINGPERSON_VIEW;
	}
}
