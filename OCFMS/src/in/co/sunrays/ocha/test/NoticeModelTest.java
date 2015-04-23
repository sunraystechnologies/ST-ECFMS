package in.co.sunrays.ocha.test;

import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.exception.DuplicateRecordException;
import in.co.sunrays.ocha.model.NoticeModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NoticeModelTest {

	public static NoticeModel model = new NoticeModel();

	/**
	 * Main method to call test methods.
	 * 
	 * @param args
	 * @throws DuplicateRecordException
	 */
	public static void main(String[] args) throws DuplicateRecordException {
		// testAdd();
		// testDelete();
		// testUpdate();
		// testFindByPK();
		testSearch();
	}

	public static void testAdd() throws DuplicateRecordException {

		try {
			model.setId(4l);
			model.setSubject("Tests");
			model.setDetails("Demods");
			// model.setExpireDate("2015-02-22");
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
			model = model.findByPK(2L);
			model.setSubject("Testfs");
			model.setDetails("Demdsfods");
			// model.setExpireDate("2015-02-23");
			model.update();
			System.out.println("Test Update succ");

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	public static void testFindByPK() {
		try {
			long pk = 2L;
			model = model.findByPK(pk);
			if (model == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(model.getId());
			System.out.println(model.getSubject());
			System.out.println(model.getDetails());

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	public static void testSearch() {
		try {
			List list = new ArrayList();
			model.setSubject("Tes");
			// bean.setAddress("borawan");
			list = model.search(1, 10);
			if (list.size() < 0) {
				System.out.println("Test Search fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				model = (NoticeModel) it.next();
				System.out.println(model.getId());
				System.out.println(model.getSubject());
				System.out.println(model.getDetails());
				System.out.println(model.getCreatedOn());
				System.out.println(model.getExpireDate());
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

}
