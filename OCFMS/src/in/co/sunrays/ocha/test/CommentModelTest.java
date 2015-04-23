package in.co.sunrays.ocha.test;

import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.exception.DuplicateRecordException;
import in.co.sunrays.ocha.model.CommentModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Comment Model testcase tests CommentModel methods.
 * 
 * @version 1.0
 * @since 01 Feb 2015
 * @author SUNRAYS Developer
 * @Copyright (c) sunRays Technologies. All rights reserved.
 * @URL www.sunrays.co.in
 */

public class CommentModelTest {

	/**
	 * Main method to call test methods.
	 * 
	 * @param args
	 * @throws DuplicateRecordException
	 */
	public static void main(String[] args) throws DuplicateRecordException {
		testAdd();
		// testDelete();
		// testUpdate();
		// testFindByPK();
		testSearch();

	}

	/**
	 * Tests Add operation.
	 * 
	 * @throws DuplicateRecordException
	 */
	public static void testAdd() throws DuplicateRecordException {

		try {

			CommentModel model = new CommentModel();
			// bean.setId(2L);
			model.setId(3l);
			model.setResourceId(2l);
			model.setText("Test");
			// model.setCreatedOn((new Timestamp(new Date().getTime())));

			long pk = model.add();
			System.out.println("Comment successfully added");
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests Delete operation.
	 * 
	 * @throws DuplicateRecordException
	 */
	public static void testDelete() {

		CommentModel model = new CommentModel();
		try {
			long pk = 1L;
			model.setId(pk);
			model.delete();
			System.out.println("Test Delete succ");

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests Update operation.
	 * 
	 * @throws DuplicateRecordException
	 */

	public static void testUpdate() {

		CommentModel model = new CommentModel();

		try {
			model = model.findByPK(2L);
			model.setText("Demo");
			model.update();
			System.out.println("Test Update succ");

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests Find By PK operation.
	 * 
	 * @throws DuplicateRecordException
	 */

	public static void testFindByPK() {

		CommentModel model = new CommentModel();

		try {
			long pk = 3L;
			model = model.findByPK(pk);
			if (model == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(model.getId());
			System.out.println(model.getText());
			System.out.println(model.getResourceId());

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests Search operation.
	 * 
	 * @throws DuplicateRecordException
	 */

	public static void testSearch() {

		CommentModel model = new CommentModel();

		try {
			List list = new ArrayList();
			model.setText("Demo");
			// bean.setAddress("borawan");
			list = model.search(1, 10);
			if (list.size() < 0) {
				System.out.println("Test Search fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				model = (CommentModel) it.next();
				System.out.print(model.getId());
				System.out.print("\t" + model.getText());
				System.out.print("\t" + model.getResourceId());
				System.out.println("\t" + model.getCreatedOn());
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

}
