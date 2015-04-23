package in.co.sunrays.ocha.controller;

import in.co.sunrays.ocha.bean.UserBean;
import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.model.BaseModel;
import in.co.sunrays.ocha.model.FeedbackModel;
import in.co.sunrays.ocha.model.MailModel;
import in.co.sunrays.ocha.model.UserModel;
import in.co.sunrays.ocha.model.HotNewsModel;
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

public class MailListCtl extends BaseCtl {
	private static Logger log = Logger.getLogger(MailListCtl.class);

	@Override
	protected BaseModel populateModel(HttpServletRequest request) {
		MailModel model = new MailModel();
		model.setSender(DataUtility.getString(request.getParameter("to")));
		model.setSender(DataUtility.getString(request.getParameter("sender")));
		model.setDetail(DataUtility.getString(request.getParameter("detail")));
		model.setAttachment(DataUtility.getString(request.getParameter("attachment")));

		return model;
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.debug("MailListCtl doGet Start");

		List list = null;

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader
				.getValue("page.size")) : pageSize;

		MailModel model = (MailModel) populateModel(request);

		String op = DataUtility.getString(request.getParameter("operation"));

		// get the selected checkbox ids array for delete list
		String[] ids = request.getParameterValues("ids");

		try {

			if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op)
					|| "Previous".equalsIgnoreCase(op)) {

				if (OP_SEARCH.equalsIgnoreCase(op)) {
					pageNo = 1;
				} else if (OP_NEXT.equalsIgnoreCase(op)) {
					pageNo++;
				} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
					ServletUtility.redirect(ORSView.MAIL_LIST_CTL, request,
							response);
					return;
				}

			} else if (OP_NEW.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.MAIL_LIST_CTL, request,
						response);
				return;
			} else if (OP_DELETE.equalsIgnoreCase(op)) {
				pageNo = 1;

				if (ids != null && ids.length > 0) {

					System.out.println("in this loop");
					MailModel deletemodel = new MailModel();
					for (String id : ids) {
						System.out.println(id);
						deletemodel.setId(DataUtility.getInt(id));
						deletemodel.delete();

					}
				} else {
					ServletUtility.setErrorMessage(
							"Select at least one record", request);
				}
			}
			list = model.search(pageNo, pageSize);
			System.out.println("list size" + list.size());
			ServletUtility.setList(list, request);
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No record found ", request);

			}
			ServletUtility.setList(list, request);

			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forwardView(getView(), request, response);

		} catch (ApplicationException e) {
			log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
		}

		log.debug("MailListCtl doGet End");
	}

	@Override
	protected String getView() {
		return ORSView.MAIL_LIST_VIEW;
	}

}
