package in.co.sunrays.ocha.model;

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

public class MissingPersonModel extends BaseModel {
	private static Logger log = Logger.getLogger(MostWantedModel.class);

	private long policeStId;

	private String name;
	private Integer age;
	private String gender;
	private Integer height;
	private Date dateOfMissing;
	private Date dateOfReporting;
	private String complexion;
	private String hair;
	private long markOfIdentification = 0;
	private String areaOfMissing;
	private long reportId = 0;
	private String photo;

	public long getPoliceStId() {
		return policeStId;
	}

	public void setPoliceStId(long policeStId) {
		this.policeStId = policeStId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Date getDateOfMissing() {
		return dateOfMissing;
	}

	public void setDateOfMissing(Date dateOfMissing) {
		this.dateOfMissing = dateOfMissing;
	}

	public Date getDateOfReporting() {
		return dateOfReporting;
	}

	public void setDateOfReporting(Date dateOfReporting) {
		this.dateOfReporting = dateOfReporting;
	}

	public String getComplexion() {
		return complexion;
	}

	public void setComplexion(String complexion) {
		this.complexion = complexion;
	}

	public String getHair() {
		return hair;
	}

	public void setHair(String hair) {
		this.hair = hair;
	}

	public long getMarkOfIdentification() {
		return markOfIdentification;
	}

	public void setMarkOfIdentification(long markOfIdentification) {
		this.markOfIdentification = markOfIdentification;
	}

	public String getAreaOfMissing() {
		return areaOfMissing;
	}

	public void setAreaOfMissing(String areaOfMissing) {
		this.areaOfMissing = areaOfMissing;
	}

	public long getReportId() {
		return reportId;
	}

	public void setReportId(long reportId) {
		this.reportId = reportId;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
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
		long pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO ST_MISSING_PERSON (ID,POLICE_ST_ID,NAME,AGE,GENDER,HEIGHT,"
							+ "DATE_OF_MISSING,DATE_OF_REPORTING,COMPLEXION,"
							+ "HAIR,MARK_OF_IDENTIFICATION,AREA_OF_MISSING,REPORT_ID,PHOTO)"
							+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			pstmt.setLong(1, pk);
			pstmt.setLong(2, policeStId);
			pstmt.setString(3, name);
			pstmt.setInt(4, age);
			pstmt.setString(5, gender);
			pstmt.setInt(6, height);
			java.util.Date date = new Date();
			pstmt.setDate(7, new java.sql.Date(getDateOfMissing().getTime()));
			pstmt.setDate(8, new java.sql.Date(getDateOfReporting().getTime()));
			pstmt.setString(9, complexion);
			pstmt.setString(10, hair);
			pstmt.setLong(11, markOfIdentification);
			pstmt.setString(12, areaOfMissing);
			pstmt.setLong(13, reportId);
			pstmt.setString(14, photo);

			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
			
			this.setId(pk);
			updateCreatedInfo();
	

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
					.prepareStatement("DELETE FROM ST_MISSING_PERSON  WHERE ID=?");

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
	 * Find Student by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */

	public MissingPersonModel findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM ST_MISSING_PERSON WHERE ID=?");

		log.info("SQL : " + sql);
		MissingPersonModel model = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				model = new MissingPersonModel();
				model.setId(rs.getLong(1));
				model.setPoliceStId(rs.getLong(2));
				model.setName(rs.getString(3));
				model.setAge(rs.getInt(4));
				model.setGender(rs.getString(5));
				model.setHeight(rs.getInt(6));
				model.setDateOfMissing(rs.getDate(7));
				model.setDateOfReporting(rs.getDate(8));

				model.setComplexion(rs.getString(9));
				model.setHair(rs.getString(10));

				model.setMarkOfIdentification(rs.getLong(11));
				model.setAreaOfMissing(rs.getString(12));
				model.setReportId(rs.getLong(13));

				model.setPhoto(rs.getString(14));

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

	public void update() throws ApplicationException {
		log.debug("Model update Started");
		Connection conn = null;
		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false); // Begin transaction

			PreparedStatement pstmt = conn
					.prepareStatement("UPDATE ST_MISSING_PERSON  SET POLICE_ST_ID =?,NAME = ?,AGE= ?,"
							+ "GENDER = ?,HEIGHT = ?,DATE_OF_MISSING = ?,DATE_OF_REPORTING = ?,"
							+ "COMPLEXION = ?,HAIR = ?,MARK_OF_IDENTIFICATION = ?,AREA_OF_MISSING =?,"
							+ "REPORT_ID = ?,PHOTO= ? WHERE ID=?");
			pstmt.setLong(1, policeStId);
			pstmt.setString(2, name);
			pstmt.setInt(3, age);
			pstmt.setString(4, gender);
			pstmt.setInt(5, height);
			pstmt.setDate(6, new java.sql.Date(dateOfMissing.getTime()));
			pstmt.setDate(7, new java.sql.Date(dateOfReporting.getTime()));
			pstmt.setString(8, complexion);
			pstmt.setString(9, hair);
			pstmt.setLong(10, markOfIdentification);
			pstmt.setString(11, areaOfMissing);
			pstmt.setLong(12, reportId);
			pstmt.setString(13, photo);
			pstmt.setLong(14, id);

			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
			updateModifiedInfo();
			
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

	public List search() throws ApplicationException {
		return search(0, 0);
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

	public List search(int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_MISSING_PERSON  WHERE 1=1");
			 
		if (id > 0) {
			sql.append(" AND id = " + id);
		}
		if (policeStId != 0 && policeStId > 0) {
			sql.append(" AND POLICE ID like '" + policeStId);
		}
		if (name != null && name.length() > 0) {
			sql.append(" AND NAME like '" + name + "%'");

		}
		if (age != null && age > 0) {
			sql.append(" AND AGE like '" + age + "%'");
		}

		if (gender != null && gender.length() > 0) {
			sql.append(" AND GENDER like '" + gender);
		}
		if (height != null && height > 0) {
			sql.append(" AND HEIGHT like '" + height);
		}
		if (dateOfMissing != null) {
			sql.append(" AND DATE_OF_MISSING like '" + dateOfMissing + "%'");
		}
		if (dateOfReporting != null) {
			sql.append(" AND DATE_OF_REPORTING like '" + dateOfReporting + "%'");
		}
		if (complexion != null && complexion.length() > 0) {
			sql.append(" AND COMPLEXION = " + complexion);
		}
		if (hair != null && hair.length() > 0) {
			sql.append(" AND HAIR like '" + hair);
		}
		if (markOfIdentification != 0 && markOfIdentification > 0) {
			sql.append(" AND MARK_OF_IDENTIFICATION like '"
					+ markOfIdentification + "%'");
		}
		if (areaOfMissing != null && areaOfMissing.length() > 0) {
			sql.append(" AND AREA OF MISSING like '" + areaOfMissing);
		}

		if (reportId != 0 && reportId > 0) {
			sql.append(" AND REPORT ID like '" + reportId);
		}
		if (photo != null && photo.length() > 0) {
			sql.append(" AND PHOTO like '" + photo);
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
				MissingPersonModel model = new MissingPersonModel();
				model.setId(rs.getLong(1));
				model.setPoliceStId(rs.getLong(2));
				model.setName(rs.getString(3));
				model.setAge(rs.getInt(4));
				model.setGender(rs.getString(5));
				model.setHeight(rs.getInt(6));
				model.setDateOfMissing(rs.getDate(7));
				model.setDateOfReporting(rs.getDate(8));
				model.setComplexion(rs.getString(9));
				model.setHair(rs.getString(10));
				model.setMarkOfIdentification(rs.getLong(11));
				model.setAreaOfMissing(rs.getString(12));
				model.setReportId(rs.getLong(13));
				model.setPhoto(rs.getString(14));

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
		return name;
	}

	@Override
	public String getTableName() {
		return "st_missing_person";
	}
}
