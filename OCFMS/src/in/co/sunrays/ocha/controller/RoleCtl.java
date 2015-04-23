package in.co.sunrays.ocha.controller;

import in.co.sunrays.ocha.bean.BaseBean;
import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.exception.DuplicateRecordException;
import in.co.sunrays.ocha.model.BaseModel;
import in.co.sunrays.ocha.model.RoleModel;
import in.co.sunrays.util.DataUtility;
import in.co.sunrays.util.DataValidator;
import in.co.sunrays.util.PropertyReader;
import in.co.sunrays.util.ServletUtility;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Role functionality Controller. Performs operation for add, update and
 * get Role
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */

public class RoleCtl extends BaseCtl {

	private static final long serialVersionUID = 1L;

	/**
	 * Logger to log the messages.
	 */

	private static Logger log = Logger.getLogger(RoleCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("RoleCtl Method validate Started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description", PropertyReader.getValue("error.require", "Description"));
			pass = false;
		}

		log.debug("RoleCtl Method validate Ended");

		return pass;
	}

	@Override
	protected BaseModel populateModel(HttpServletRequest request) {

		log.debug("RoleCtl Method populateModel Started");

		RoleModel model = new RoleModel();

		model.setId(DataUtility.getLong(request.getParameter("id")));

		model.setName(DataUtility.getString(request.getParameter("name")));
		model.setDescription(DataUtility.getString(request
				.getParameter("description")));
		
		populateDTO(model, request);

		log.debug("RoleCtl Method populateModel Ended");

		return model;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("RoleCtl Method doGet Started");

		System.out.println("In do get");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		RoleModel model = new RoleModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op)) {

			model = (RoleModel) populateModel(request);

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
			} catch (DuplicateRecordException e) {
				ServletUtility.setModel(model, request);
				ServletUtility.setErrorMessage("Role already exists",
						request);
			}

		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			 model = (RoleModel) populateModel(request);
			try {
				model.delete();
				ServletUtility.redirect(ORSView.ROLE_LIST_CTL, request,
						response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.ROLE_LIST_CTL, request, response);
			return;

		} else { // View page

			if (id > 0 || op != null) {
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

		ServletUtility.forward(ORSView.ROLE_VIEW, request, response);

		log.debug("RoleCtl Method doGet Ended");
	}

	@Override
	protected String getView() {
		return ORSView.ROLE_VIEW;
	}

}