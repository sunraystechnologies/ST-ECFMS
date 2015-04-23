package in.co.sunrays.ocha.controller;

import in.co.sunrays.ocha.bean.BaseBean;
import in.co.sunrays.ocha.bean.StudentBean;
import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.exception.DuplicateRecordException;
import in.co.sunrays.ocha.model.MostWantedModel;
import in.co.sunrays.ocha.model.PoliceStationModel;
import in.co.sunrays.ocha.model.StudentModel;
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
 * Station functionality Controller. Performs operation for add, update, delete
 * and get Station
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */

public class PoliceStationCtl extends BaseCtl {
	public static final String OP_SAVE_UP = "Save";
	private static Logger log = Logger.getLogger(PoliceStationCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {
		 PoliceStationModel model = new PoliceStationModel();
		
		 try {
				List l = model.search(null);
				request.setAttribute("roleList", l);
			} catch (ApplicationException e) {
				log.error(e);
			}
		}

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("PoliceStationCtl Method validate Started");

		boolean pass = true;
		
		if (DataValidator.isNull(request.getParameter("nameOfPoliceStation"))) {
			request.setAttribute("nameOfPoliceStation",
					PropertyReader.getValue("error.require", "Name Of Police Station"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("codeOfPoliceStation"))) {
			request.setAttribute("codeOfPoliceStation",
					PropertyReader.getValue("error.require", "Code Of Police Station"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("areaCovered"))) {
			request.setAttribute("areaCovered",
					PropertyReader.getValue("error.require", "Area Covered"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("contactNo"))) {
			request.setAttribute("contactNo",
					PropertyReader.getValue("error.require", "ContactNo"));
			pass = false;
		}

		log.debug("PoliceStationCtl Method validate Ended");
		System.out.println("flag " + pass);
		return pass;

	}

	@Override
	protected PoliceStationModel populateModel(HttpServletRequest request) {

		log.debug("PoliceStationCtl Method populatemodel Started");

		PoliceStationModel model = new PoliceStationModel();

		model.setId(DataUtility.getLong(request.getParameter("id")));

		model.setNameOfPoliceStation(DataUtility.getString(request.getParameter("nameOfPoliceStation")));

		model.setCodeOfPoliceStation(DataUtility.getString(request.getParameter("codeOfPoliceStation")));

		model.setAreaCovered(DataUtility.getString(request.getParameter("areaCovered")));

		model.setContactNo(DataUtility.getString(request
				.getParameter("contactNo")));

		populateDTO(model, request);

		log.debug("PoliceStationtCtl Method populatemodel Ended");

		return model;
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.debug("PoliceStationtCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		PoliceStationModel model = (PoliceStationModel) populateModel(request);

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
				ServletUtility.redirect(ORSView.POLICESTATION_LIST_CTL, request,
						response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		} else {
			if (id > 0 || op != null) {
				PoliceStationModel model1;
				try {
					model1 = model.findByPK(id);
					ServletUtility.setModel(model1, request);
				} catch (ApplicationException e) {

					ServletUtility.handleException(e, request, response);
					return;
				}
			}
		}
		ServletUtility.forwardView(ORSView.POLICESTATION_VIEW, request, response);
		log.debug("PoliceStationtCtl Method doGet Ended");
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Long id = DataUtility.getLong(request.getParameter("id"));

		PoliceStationModel model = new PoliceStationModel();

		if (id > 0) {
			try {
				model = model.findByPK(id);
				ServletUtility.setModel(model, request);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forwardView(ORSView.POLICESTATION_VIEW, request, response);
	}

	@Override
	protected String getView() {
		return ORSView.POLICESTATION_VIEW;
	}
}
