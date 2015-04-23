package in.co.sunrays.ocha.test;

import in.co.sunrays.ocha.model.TimeTableModel;

import java.io.File;
import java.io.FileInputStream;


public class TimeTableModelTest {
	public static void main(String[] args) throws Exception {
		TimeTableModel model = new TimeTableModel();

		// Using for add image in data base

		File file = new File("/home/ncs02/Downloads/Krishna.xls");
		byte[] bs = new byte[(int) file.length()];
		FileInputStream reader = new FileInputStream(file);
		reader.read(bs);
		reader.close();
		model.setId(2);;
		model.setSem("4");
		model.setFileName(bs);
		model.add();

		// Using for get Image from data base

		/*
		 * vo.setImageId(1);
		 * 
		 * vo = service.getImage(vo);
		 * 
		 * byte[] image = vo.getImage();
		 * 
		 * FileOutputStream fileOutputStream = new FileOutputStream(
		 * "C:\\icon23.gif"); fileOutputStream.write(image);
		 * fileOutputStream.close();
		 */

	}
}
