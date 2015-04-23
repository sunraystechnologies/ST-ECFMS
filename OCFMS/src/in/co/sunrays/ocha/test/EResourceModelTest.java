package in.co.sunrays.ocha.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.exception.DuplicateRecordException;
import in.co.sunrays.ocha.model.CommentModel;
import in.co.sunrays.ocha.model.EResourceModel;

public class EResourceModelTest {
	public static EResourceModel model = new EResourceModel();

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
		// testSearch();
		// testList();

	}

	public static void testAdd() throws DuplicateRecordException {

		try {
			model.setId(4l);
			model.setTablesContains("Testdfsg");
			model.setName("TestNamefdg");
			model.setDetail("testDetaildd");
			// model.setCreatedOn((new Timestamp(new Date().getTime())));

			long pk = model.add();
			System.out.println("Test add succ");
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	public static void testDelete() {

		try {
			long pk = 2L;
			model.setId(pk);
			model.delete();
			System.out.println("Test Delete succ");

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testUpdate() {

		try {
			model = model.findByPK(1L);
			model.setDetail("Test1");
			model.setTablesContains("Demo");
			model.setName("TestNamefdg");
			model.update();
			System.out.println("Test Update succ");

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	public static void testFindByPK() {
		try {
			long pk = 1L;
			model = model.findByPK(pk);
			if (model == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(model.getId());
			System.out.println(model.getDetail());
			System.out.println(model.getName());

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	public static void testSearch() {
		try {
			List list = new ArrayList();
			model.setName("Tes");
			// bean.setAddress("borawan");
			list = model.search(1, 10);
			if (list.size() < 0) {
				System.out.println("Test Search fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				model = (EResourceModel) it.next();
				System.out.println(model.getId());
				System.out.println(model.getTablesContains());
				System.out.println(model.getName());
				System.out.println(model.getDetail());
				System.out.println(model.getCreatedOn());
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testList() {

		try {
			List list = new ArrayList();
			list = model.search(1, 10);
			if (list.size() < 0) {
				System.out.println("Test list fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				model = (EResourceModel) it.next();
				System.out.println(model.getId());
				System.out.println(model.getTablesContains());
				System.out.println(model.getName());
				System.out.println(model.getDetail());
				System.out.println(model.getCreatedOn());
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}
}
