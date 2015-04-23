package in.co.sunrays.ocha.model;
import in.co.sunrays.ocha.model.BaseModel;
import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.exception.DatabaseException;
import in.co.sunrays.ocha.exception.DuplicateRecordException;
import in.co.sunrays.util.JDBCDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * JDBC Implementation of Student Model
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */
public class MostWantedModel extends BaseModel {

	private static Logger log = Logger.getLogger(MostWantedModel.class);

	private String nameOfCriminal;

	private String typeOfCrime;

	private String complexion;

	private long markOfIdentification;

	private Integer age;

	private String gender;

	private Integer height;

	private String status;

	private String photo;

	private long policsStId;

	public String getNameOfCriminal() {
		return nameOfCriminal;
	}

	public void setNameOfCriminal(String nameOfCriminal) {
		this.nameOfCriminal = nameOfCriminal;
	}

	public String getTypeOfCrime() {
		return typeOfCrime;
	}

	public void setTypeOfCrime(String typeOfCrime) {
		this.typeOfCrime = typeOfCrime;
	}

	public String getComplexion() {
		return complexion;
	}

	public void setComplexion(String complexion) {
		this.complexion = complexion;
	}

	public long getMarkOfIdentification() {
		return markOfIdentification;
	}

	public void setMarkOfIdentification(long markOfIdentification) {
		this.markOfIdentification = markOfIdentification;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public long getPolicsStId() {
		return policsStId;
	}

	public void setPolicsStId(long policsStId) {
		this.policsStId = policsStId;
	}

	/**
	 * Add a Student
	 * 
	 * @param bean
	 * @throws DatabaseException
	 * 
	 */
	public long add() throws ApplicationException {
		log.debug("Model add Started");
		Connection conn = null;

		// get College Name
		// CollegeModel cModel = new CollegeModel();
		// CollegeBean collegeBean = cModel.findByPK(bean.getCollegeId());
		// bean.setCollegeName(collegeBean.getName());

		long pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO "
							+ getTableName()
							+ " VALUES(?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setLong(1, pk);
			pstmt.setString(2, nameOfCriminal);
			pstmt.setString(3, typeOfCrime);
			pstmt.setString(4, complexion);
			pstmt.setLong(5, markOfIdentification);
			pstmt.setInt(6, age);
			pstmt.setString(7, gender);
			pstmt.setInt(8, height);
			pstmt.setString(9, status);
			pstmt.setString(10, photo);
			pstmt.setLong(11, policsStId);

			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException(
						"Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException(
					"Exception : Exception in add Student");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}

	/**
	 * Delete a Student
	 * 
	 * @param bean
	 * @throws DatabaseException
	 */
	public void delete() throws ApplicationException {
		log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("DELETE FROM "
							+ getTableName()
							+ " WHERE ID=?");

			pstmt.setLong(1, getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException(
						"Exception : Delete rollback exception "
								+ ex.getMessage());
			}
			throw new ApplicationException(
					"Exception : Exception in delete Student");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete Started");
	}

	/**
	 * Find User by Student
	 * 
	 * @param Email
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */

	/**
	 * Find Student by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */

	public MostWantedModel findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM "
							+ getTableName()
							+ " WHERE ID=?");
		log.info("SQL : " + sql);
		MostWantedModel model = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				model = new MostWantedModel();
				model.setId(rs.getLong(1));
				model.setNameOfCriminal(rs.getString(2));
				model.setTypeOfCrime(rs.getString(3));
				model.setComplexion(rs.getString(4));
				model.setMarkOfIdentification(rs.getLong(5));
				model.setAge(rs.getInt(6));
				model.setGender(rs.getString(7));
				model.setHeight(rs.getInt(8));
				model.setStatus(rs.getString(9));
				model.setPhoto(rs.getString(10));
				model.setPolicsStId(rs.getLong(11));

			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in getting User by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return model;
	}

	/**
	 * Update a Student
	 * 
	 * @param bean
	 * @throws DatabaseException
	 */

	public void update(MostWantedModel model) throws ApplicationException
			 {
		log.debug("Model update Started");
		Connection conn = null;

		// get College Name
		// CollegeModel cModel = new CollegeModel();
		// CollegeBean collegeBean = cModel.findByPK(bean.getCollegeId());
		// bean.setCollegeName(collegeBean.getName());

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false); // Begin transaction

			PreparedStatement pstmt = conn
					.prepareStatement("UPDATE "
							+ getTableName()
							+ " "
							+ "SET NAME_OF_CRIMINAL= ?,TYPE_OF_CRIME = ?,COMPLEXION = ?,"
							+ "MARK_OF_IDENTIFICATION = ?,AGE = ?,GENDER = ?,HEIGHT = ?,"
							+ "STATUS = ?,PHOTO= ?,POLICE_ST_ID =? WHERE ID=?");
			pstmt.setString(1, model.getNameOfCriminal());
			pstmt.setString(2, model.getTypeOfCrime());
			pstmt.setString(3, model.getComplexion());
			pstmt.setLong(4, model.getMarkOfIdentification());
			pstmt.setInt(5, model.getAge());
			pstmt.setString(6, model.getGender());
			pstmt.setInt(7, model.getHeight());
			pstmt.setString(8, model.getStatus());
			pstmt.setString(9, model.getPhoto());
			pstmt.setLong(10, model.getPolicsStId());
			pstmt.setLong(11, model.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException(
						"Exception : Delete rollback exception "
								+ ex.getMessage());
			}
			throw new ApplicationException("Exception in updating Student ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}

	/**
	 * Search Student
	 * 
	 * @param bean
	 *            : Search Parameters
	 * @throws DatabaseException
	 */

	public List search(MostWantedModel model) throws ApplicationException {
		return search(model, 0, 0);
	}

	/**
	 * Search Student with pagination
	 * 
	 * @return list : List of Students
	 * @param bean
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * @throws DatabaseException
	 */

	public List search(MostWantedModel model, int pageNo, int pageSize)
			throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM "
							+ getTableName()
							+ " WHERE 1=1");

		if (model != null) {
			if (id > 0) {
				sql.append(" AND id = " + model.getId());
			}
			if (nameOfCriminal != null
					&& model.getNameOfCriminal().length() > 0) {
				sql.append(" AND NAME_OF_CRIMINAL like '"
						+ model.getNameOfCriminal() + "%'");
			}
			if (typeOfCrime != null && model.getTypeOfCrime().length() > 0) {
				sql.append(" AND TYPE_OF_CRIME like '" + model.getTypeOfCrime()
						+ "%'");
			}
			if (complexion != null && model.getComplexion().length() > 0) {
				sql.append(" AND COMPLEXTION = " + model.getComplexion());
			}
			if (model.getMarkOfIdentification() != 0
					&& model.getMarkOfIdentification() > 0) {
				sql.append(" AND MARK_OF_IDENTIFICATION like '"
						+ model.getMarkOfIdentification() + "%'");
			}
			if (model.getAge() != null && model.getAge() > 0) {
				sql.append(" AND AGE like '" + model.getAge() + "%'");
			}
			if (gender != null && model.getGender().length() > 0) {
				sql.append(" AND GENDER like '" + model.getGender());
			}
			if (model.getHeight() != null && model.getHeight() > 0) {
				sql.append(" AND HEIGHT like '" + model.getHeight());
			}
			if (model.getStatus() != null && model.getStatus().length() > 0) {
				sql.append(" AND STATUS like '" + model.getStatus());
			}

			if (model.getPolicsStId() != 0 && model.getPolicsStId() > 0) {
				sql.append(" AND POLICS ID like '" + model.getPolicsStId());
			}

		}

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}

		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				model = new MostWantedModel();
				model.setId(rs.getLong(1));
				model.setNameOfCriminal(rs.getString(2));
				model.setTypeOfCrime(rs.getString(3));
				model.setComplexion(rs.getString(4));
				model.setMarkOfIdentification(rs.getLong(5));
				model.setAge(rs.getInt(6));
				model.setGender(rs.getString(7));
				model.setHeight(rs.getInt(8));
				model.setStatus(rs.getString(9));
				model.setPhoto(rs.getString(10));
				model.setPolicsStId(rs.getLong(11));

				list.add(model);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in search Student");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model search End");
		return list;
	}

	@Override
	public String getKey() {
		return id + "";
	}

	@Override
	public String getValue() {
		return nameOfCriminal;
	}

	@Override
	public String getTableName() {
		return "st_most_wanted";
	}
}
