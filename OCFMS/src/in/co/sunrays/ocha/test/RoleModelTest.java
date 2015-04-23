package in.co.sunrays.ocha.test;

import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.exception.DuplicateRecordException;
import in.co.sunrays.ocha.model.RoleModel;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Role Model Test classes
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 * 
 */
public class RoleModelTest {

	/**
	 * Model object to test
	 */

	public static RoleModel model = new RoleModel();

	/**
	 * Main method to call test methods.
	 * 
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
		// testAdd();
		// testDelete();
		// testUpdate();
		// testFindByPK();
		// testFindByName();
		// testSearch();
		testList();

	}

	/**
	 * Tests add a Role
	 * 
	 * @throws ParseException
	 */
	public static void testAdd() throws ParseException {

		try {
			RoleModel model = new RoleModel();
			// SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

			// bean.setId(1L);
			model.setName("shivam");
			model.setDescription("kumar");
			long pk = model.add();
			RoleModel addedmodel = model.findByPK(pk);
			if (addedmodel == null) {
				System.out.println("Test add fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests delete a Role
	 */
	public static void testDelete() {

		try {
			RoleModel model = new RoleModel();
			long pk = 3L;
			model.setId(pk);
			model.delete();
			RoleModel deletedmodel = model.findByPK(pk);
			if (deletedmodel != null) {
				System.out.println("Test Delete fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests update a Role
	 */
	public static void testUpdate() {

		try {
			RoleModel model = new RoleModel();
			model = model.findByPK(6L);
			model.setName("12");
			model.setDescription("Ejjjjjjjjng");
			model.update();

			RoleModel updatedbean = model.findByPK(6L);
			if (!"12".equals(updatedbean.getName())) {
				System.out.println("Test Update fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests find a User by PK.
	 */
	public static void testFindByPK() {
		try {
			RoleModel model = new RoleModel();
			long pk = 5L;
			model = model.findByPK(pk);
			if (model == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(model.getId());
			System.out.println(model.getName());
			System.out.println(model.getDescription());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests find a User by Name.
	 */
	public static void testFindByName() {
		try {
			RoleModel model = new RoleModel();
			model = model.findByName("admin");
			if (model == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(model.getId());
			System.out.println(model.getName());
			System.out.println(model.getDescription());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests get Search
	 */
	public static void testSearch() {

		try {
			RoleModel model = new RoleModel();
			List list = new ArrayList();
			model.setName("student");
			list = model.search(model, 0, 0);
			if (list.size() < 0) {
				System.out.println("Test Serach fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				model = (RoleModel) it.next();
				System.out.println(model.getId());
				System.out.println(model.getName());
				System.out.println(model.getDescription());
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests get List.
	 */
	public static void testList() {

		try {
			RoleModel model = new RoleModel();
			List list = model.search(model, 1, 10);
			if (list.size() < 0) {
				System.out.println("Test list fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				model = (RoleModel) it.next();
				System.out.println(model.getId());
				System.out.println(model.getName());
				System.out.println(model.getDescription());
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}
}
