package in.co.sunrays.ocha.controller;

import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.model.ComplaintModel;
import in.co.sunrays.ocha.model.PoliceStationModel;
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

public class ComplaintCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(ComplaintCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {

		PoliceStationModel model = new PoliceStationModel();
		try {
			List l = model.search();
			request.setAttribute("policeStList", l);
		} catch (ApplicationException e) {
			log.error(e);
		}
	}

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("ComplaintCtl Method validate Started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("complaintId"))) {
			request.setAttribute("complaintId",
					PropertyReader.getValue("error.require", "ComplaintId"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("type"))) {
			request.setAttribute("type",
					PropertyReader.getValue("error.require", "Type"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("doc"))) {
			request.setAttribute("doc",
					PropertyReader.getValue("error.require", "Doc"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("policeStationid"))) {
			request.setAttribute("policeStationid",
					PropertyReader.getValue("error.require", "PoliceStationid"));
			pass = false;
		}

		log.debug("PoliceCtl Method validate Ended");

		return pass;
	}

	@Override
	protected ComplaintModel populateModel(HttpServletRequest request) {

		log.debug("ComplaintCtl Method populatemodel Started");

		ComplaintModel cmodel = new ComplaintModel();

		cmodel.setId(DataUtility.getLong(request.getParameter("id")));

		cmodel.setcomplaintId(DataUtility.getString(request
				.getParameter("complaintId")));

		cmodel.setType(DataUtility.getString(request.getParameter("type")));

		cmodel.setDoc(DataUtility.getDate(request.getParameter("doc")));

		cmodel.setPoliceStationId(DataUtility.getLong(request
				.getParameter("policeStationid")));

		PoliceStationModel pModel = new PoliceStationModel();

		try {
			
			pModel = pModel.findByPK(cmodel.getPoliceStationId());

			cmodel.setpoliceStationName(pModel.getNameOfPoliceStation());

		} catch (ApplicationException e) {
			log.error(e);
		}

		cmodel.setDoc1(DataUtility.getString(request.getParameter("doc1")));
		cmodel.setDoc2(DataUtility.getString(request.getParameter("doc2")));
		cmodel.setDoc3(DataUtility.getString(request.getParameter("doc3")));
		cmodel.setDoc4(DataUtility.getString(request.getParameter("doc4")));

		populateModel(cmodel, request);

		log.debug("ComplaintCtl Method populatemodel Ended");

		return cmodel;
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		long id = DataUtility.getLong(request.getParameter("id"));

		if (id > 0) {

			ComplaintModel model = new ComplaintModel();

			try {
				model = model.findByPK(id);
				ServletUtility.setModel(model, request);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forwardView(ORSView.COMPLAINT_VIEW, request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.debug("ComplaintCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		ComplaintModel model = new ComplaintModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op)) {

			model = (ComplaintModel) populateModel(request);

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

			ComplaintModel cmodel = (ComplaintModel) populateModel(request);
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

			if (id > 0 || op != null) {
				ComplaintModel cmodel;
				try {
					cmodel = model.findByPK(id);
					ServletUtility.setModel(cmodel, request);
				} catch (ApplicationException e) {
					log.error(e);
					ServletUtility.handleException(e, request, response);
					return;
				}
			}
		}

		ServletUtility.forwardView(ORSView.COMPLAINT_VIEW, request, response);

		log.debug("ComplaintCtl Method doGet Ended");
	}

	@Override
	protected String getView() {
		return ORSView.COMPLAINT_VIEW;
	}

}
