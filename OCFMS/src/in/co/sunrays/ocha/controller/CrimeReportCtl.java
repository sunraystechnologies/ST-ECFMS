package in.co.sunrays.ocha.controller;

import in.co.sunrays.common.model.UserModel;
import in.co.sunrays.ocha.model.AppRoles;
import in.co.sunrays.ocha.model.BaseModel;
import in.co.sunrays.ocha.model.CrimeReportModel;
import in.co.sunrays.ocha.model.HotNewsModel;
import in.co.sunrays.ocha.model.PoliceStationModel;
import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.exception.DuplicateRecordException;
import in.co.sunrays.util.AccessUtility;
import in.co.sunrays.util.DataUtility;
import in.co.sunrays.util.DataValidator;
import in.co.sunrays.util.PropertyReader;
import in.co.sunrays.util.ServletUtility;

import java.io.IOException;
import java.util.ArrayList;
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
public class CrimeReportCtl extends BaseCtl {

	public static final String OP_SAVE_UP = "Save";

	private static Logger log = Logger.getLogger(CrimeReportCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {

		HashMap<String, String> toc = new HashMap<>();
		toc.put("Knife crime", "Knife crime");
		toc.put("Property crimes", "Property crimes");
		toc.put("Corporate crime", "Corporate crime‎");
		request.setAttribute("toc", toc);

		PoliceStationModel stationModel = new PoliceStationModel();

		List l = new ArrayList();
		try {
			l = stationModel.search();
		} catch (ApplicationException e) {
			log.error(e);
		}
		request.setAttribute("stationList", l);

	}

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("CrimeReportCtl Method validate Started");

		boolean pass = true;
		if (DataValidator.isNull(request.getParameter("crId"))) {
			request.setAttribute("crId",
					PropertyReader.getValue("error.require", "Cr Id"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("typeOfCrime"))) {
			request.setAttribute("typeOfCrime",
					PropertyReader.getValue("error.require", "Type Of Crime"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("dateOfCrime"))) {
			request.setAttribute("dateOfCrime",
					PropertyReader.getValue("error.require", "Date Of Crime"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("time"))) {
			request.setAttribute("time",
					PropertyReader.getValue("error.require", "Time"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("reportDate"))) {
			request.setAttribute("reportDate",
					PropertyReader.getValue("error.require", "Report Date"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("place"))) {
			request.setAttribute("place",
					PropertyReader.getValue("error.require", "Place"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("detail"))) {
			request.setAttribute("detail",
					PropertyReader.getValue("error.require", "Detail"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("policeStId"))) {
			request.setAttribute("policeStId",
					PropertyReader.getValue("error.require", "Police St Id"));
			pass = false;
		}

		log.debug("CrimeReportCtl Method validate Ended");

		return pass;
	}

	@Override
	protected BaseModel populateModel(HttpServletRequest request) {

		log.debug("CrimeReportCtl Method populatebean Started");

		CrimeReportModel model = new CrimeReportModel();

		model.setId(DataUtility.getLong(request.getParameter("id")));

		// bean.setRoleId(RoleBean.STUDENT);
		model.setCrId(DataUtility.getLong(request.getParameter("crId")));
		model.setTypeOfCrime(DataUtility.getString(request
				.getParameter("typeOfCrime")));
		model.setDateOfCrime(DataUtility.getDate(request
				.getParameter("dateOfCrime")));
		model.setTime(DataUtility.getTimestamp(request.getParameter("time")));
		model.setReportDate(DataUtility.getDate(request
				.getParameter("reportDate")));

		model.setPlace(DataUtility.getString(request.getParameter("place")));

		model.setPoliceStId(DataUtility.getLong(request
				.getParameter("policeStId")));

		try {
			PoliceStationModel stationModel = new PoliceStationModel();
			stationModel = stationModel.findByPK(model.getPoliceStId());
			model.setPoliceStationName(stationModel.getNameOfPoliceStation());
		} catch (ApplicationException e) {
			log.error(e);
		}

		model.setDetail(DataUtility.getString(request.getParameter("detail")));

		model.setPicture(DataUtility.getString(request.getParameter("picture")));

		model.setDocument(DataUtility.getString(request
				.getParameter("document")));

		populateModel(model, request);

		log.debug("CrimeReportCtl Method populatebean Ended");

		return model;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.debug("CrimeReportCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		CrimeReportModel model = (CrimeReportModel) populateModel(request);

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
				ServletUtility.redirect(ORSView.CRIMEREPORT_LIST_CTL, request,
						response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		} else {
			if (id > 0 || op != null) {
				CrimeReportModel model1;
				try {
					model1 = model.findByPK(id);
					ServletUtility.setModel(model1, request);
				} catch (ApplicationException e) {

					ServletUtility.handleException(e, request, response);
					return;
				}
			}
		}
		ServletUtility.forwardView(ORSView.CRIMEREPORT_VIEW, request, response);
		log.debug("CrimeReportCtl Method doGet Ended");
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Long id = DataUtility.getLong(request.getParameter("id"));

		CrimeReportModel model = new CrimeReportModel();

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
		return ORSView.CRIMEREPORT_VIEW;
	}
}


