package in.co.sunrays.ocha.controller;

import in.co.sunrays.ocha.bean.BaseBean;
import in.co.sunrays.ocha.bean.UserBean;
import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.model.UserModel;
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
 * User List functionality Controller. Performs operation for list, search and
 * delete operations of User
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */

public class UserListCtl extends BaseCtl {

	/**
	 * Logger to log the messages.
	 */
	private static Logger log = Logger.getLogger(UserListCtl.class);
	
	

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		UserBean bean = new UserBean();

		bean.setFirstName(DataUtility.getString(request
				.getParameter("firstName")));
		
		bean.setLastName(DataUtility.getString(request
				.getParameter("lastName")));

		bean.setLogin(DataUtility.getString(request.getParameter("login")));

		return bean;
	}

	/**
	 * Handles GET request.
	 * 
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.debug("UserListCtl doGet Start");

		List list = null;

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader
				.getValue("page.size")) : pageSize;

		UserBean bean = (UserBean) populateBean(request);

		String op = DataUtility.getString(request.getParameter("operation"));
		
		//get the selected checkbox ids array for delete list
		String[] ids = request.getParameterValues("ids");
		
		UserModel model = new UserModel();

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

			} else if (OP_NEW.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.USER_CTL, request, response);
				return;
			} else if (OP_DELETE.equalsIgnoreCase(op)) {
				pageNo = 1;
				if (ids != null && ids.length > 0) {
					UserBean deletebean = new UserBean();
					for (String id : ids) {
						deletebean.setId(DataUtility.getInt(id));
						model.delete(deletebean);
					}
				} else {
					ServletUtility.setErrorMessage(
							"Select at least one record", request);
				}
			}
			list = model.search(bean, pageNo, pageSize);
			ServletUtility.setList(list, request);
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No record found ", request);
			}
			ServletUtility.setList(list, request);

			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(ORSView.USER_LIST_VIEW, request, response);

		} catch (ApplicationException e) {
			log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
		}

		log.debug("UserListCtl doGet End");
	}

	/**
	 * Returns View page of Controller.
	 */
	@Override
	protected String getView() {
		return ORSView.USER_LIST_VIEW;
	}

}
