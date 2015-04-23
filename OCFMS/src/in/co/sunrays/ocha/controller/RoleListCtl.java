package in.co.sunrays.ocha.controller;

import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.model.BaseModel;
import in.co.sunrays.ocha.model.RoleModel;
import in.co.sunrays.util.DataUtility;
import in.co.sunrays.util.PropertyReader;
import in.co.sunrays.util.ServletUtility;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Role List functionality Controller. Performs operation for list, search 
 * operations of Role
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */

public class RoleListCtl extends BaseCtl {
	

	/**
	 * Logger to log the messages.
	 */
	private static Logger log = Logger.getLogger(RoleListCtl.class);

	@Override
	protected BaseModel populateModel(HttpServletRequest request) {
		RoleModel model = new RoleModel();
		model.setName(DataUtility.getString(request.getParameter("name")));
		return model;
	}

	/**
	 * Handles GET request.
	 * 
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.debug("RoleListCtl doGet Start");

		List list = null;

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;

		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader
				.getValue("page.size")) : pageSize;

		RoleModel model = (RoleModel) populateModel(request);


		String op = DataUtility.getString(request.getParameter("operation"));
		try {

			if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op)
					|| "Previous".equalsIgnoreCase(op)) {

				if (OP_SEARCH.equalsIgnoreCase(op)) {
					pageNo = 1;
				} else if (OP_NEXT.equalsIgnoreCase(op)) {
					pageNo++;
				} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
					pageNo--;
				}

			}
			list = model.search(model, pageNo, pageSize);
			ServletUtility.setList(list, request);
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No record found ", request);
			}
			ServletUtility.setList(list, request);

			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(ORSView.ROLE_LIST_VIEW, request, response);

		} catch (ApplicationException e) {
			log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
		}
		log.debug("RoleListCtl doGet End");
	}

	@Override
	protected String getView() {
		return ORSView.ROLE_LIST_VIEW;
	}

}