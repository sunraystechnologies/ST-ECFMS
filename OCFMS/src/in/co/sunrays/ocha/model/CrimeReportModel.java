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

public class CrimeReportModel  extends BaseModel {
	private static Logger log = Logger.getLogger(CrimeReportModel.class);
	
	private long crId;
	
	private String typeOfCrime;
	
	private Date dateOfCrime;
	
	private Date time;
	
	private Date reportDate;
	
 	private String place;
 	
	private String policeStationName;
	
	private String detail;
	
	private String picture;
	
	private String document;
	
	private long policeStId;

	public long getCrId() {
		return crId;
	}

	public void setCrId(long crId) {
		this.crId = crId;
	}

	public String getTypeOfCrime() {
		return typeOfCrime;
	}

	public void setTypeOfCrime(String typeOfCrime) {
		this.typeOfCrime = typeOfCrime;
	}

	public Date getDateOfCrime() {
		return dateOfCrime;
	}

	public void setDateOfCrime(Date dateOfCrime) {
		this.dateOfCrime = dateOfCrime;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getPoliceStationName() {
		return policeStationName;
	}

	public void setPoliceStationName(String policeStationName) {
		this.policeStationName = policeStationName;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public long getPoliceStId() {
		return policeStId;
	}

	public void setPoliceStId(long policeStId) {
		this.policeStId = policeStId;
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
					.prepareStatement("INSERT INTO ST_CRIME_REPORT (ID,CR_ID,TYPE_OF_CRIME,DATE_OF_CRIME,"
							+ "TIME,REPORT_DATE,PLACE,POLICE_STATION_NAME,"
							+ "DETAIL,PICTURE,DOCUMENT,POLICE_ST_ID) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
							
						
			pstmt.setLong(1, pk);
			pstmt.setLong(2, crId);
			pstmt.setString(3, typeOfCrime);
			java.util.Date date = new Date();
		    pstmt.setDate(4, new java.sql.Date(getDateOfCrime().getTime()));
		    pstmt.setTimestamp(5, new java.sql.Timestamp(date.getTime()));
		    pstmt.setDate(6, new java.sql.Date(getReportDate().getTime()));
			pstmt.setString(7, place);
			pstmt.setString(8, policeStationName);
			pstmt.setString(9, detail);
			pstmt.setString(10, picture);
			pstmt.setString(11, document);
			pstmt.setLong(12, policeStId);

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
					.prepareStatement("DELETE FROM ST_CRIME_REPORT  WHERE ID=?");
		
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

	public CrimeReportModel findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM ST_CRIME_REPORT WHERE ID=?");
							 
		log.info("SQL : " + sql);
		CrimeReportModel model = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				model = new CrimeReportModel();
				model.setId(rs.getLong(1));
				model.setCrId(rs.getLong(2));
				model.setTypeOfCrime(rs.getString(3));
				model.setDateOfCrime(rs.getDate(4));
				model.setTime(rs.getTime(5));
				model.setReportDate(rs.getDate(6));
				model.setPlace(rs.getString(7));
				model.setPoliceStationName(rs.getString(8));
				model.setDetail(rs.getString(9));
				model.setPicture(rs.getString(10));
				model.setDocument(rs.getString(11));
				model.setPoliceStId(rs.getLong(12));

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

	public void update() throws ApplicationException
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
					.prepareStatement("UPDATE ST_CRIME_REPORT SET CR_ID= ?,"
							+ "TYPE_OF_CRIME = ?,DATE_OF_CRIME = ?,"
						 	+ "TIME = ?,REPORT_DATE = ?,PLACE= ?,POLICE_STATION_NAME = ?,"
							+ "DETAIL= ?,PICTURE= ?,DOCUMENT = ?,POLICE_ST_ID =? WHERE ID=?");
			pstmt.setLong(1, crId);
			pstmt.setString(2, typeOfCrime);
			pstmt.setDate(3, new java.sql.Date(dateOfCrime.getTime()));
			pstmt.setDate(4, new java.sql.Date(new Date().getTime()));
			pstmt.setDate(5, new java.sql.Date(reportDate.getTime()));
			pstmt.setString(6, place);
			pstmt.setString(7, policeStationName);
			pstmt.setString(8, detail);
			pstmt.setString(9, picture);
			pstmt.setString(10, document);
			pstmt.setLong(11, policeStId);
			pstmt.setLong(12, id);
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

	public List search(CrimeReportModel model) throws ApplicationException {
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

	public List search(int pageNo, int pageSize)
			throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM ST_CRIME_REPORT  WHERE 1=1");
							
			if (id > 0) {
				sql.append(" AND id = " + id);
			}
			if (crId != 0 &&crId > 0) {
				sql.append(" AND CR ID like '" + crId);
			}
			
			if (typeOfCrime != null && typeOfCrime.length() > 0) {
				sql.append(" AND TYPE_OF_CRIME like '" +typeOfCrime
						+ "%'");
			}
			if (dateOfCrime != null){
				sql.append(" AND DATE_OF_CRIME like '" + dateOfCrime + "%'");
			}
			if (time != null){
				sql.append(" AND TIME like '" + time + "%'");
			}
			if (reportDate != null){
				sql.append(" AND REPORT DATE like '" + reportDate + "%'");
			}
			if (place != null && place.length() > 0) {
				sql.append(" AND PLACE= " + place + "%'");
			}
			if (policeStationName != null
					&& policeStationName.length() > 0) {
				sql.append(" AND POLICE STATION NAME like '"
						+ policeStationName + "%'");
			}
			if (detail != null && detail.length() > 0) {
				sql.append(" AND DETAIL like '" + detail + "%'");
			}
			if (picture != null && picture.length() > 0) {
				sql.append(" AND PICTURE like '" + picture);
			}
			if (document != null && document.length() > 0) {
				sql.append(" AND DOCUMENT like '" + document);
			}

			if (policeStId != 0 && policeStId > 0) {
				sql.append(" AND POLICE ST ID like '" + policeStId);
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
				CrimeReportModel	model = new CrimeReportModel();
				model.setId(rs.getLong(1));
				model.setCrId(rs.getLong(2));
				model.setTypeOfCrime(rs.getString(3));
				model.setDateOfCrime(rs.getDate(4));
				model.setTime(rs.getTime(5));
				model.setReportDate(rs.getDate(6));
				model.setPlace(rs.getString(7));
				model.setPoliceStationName(rs.getString(8));
				model.setDetail(rs.getString(9));
				model.setPicture(rs.getString(10));
				model.setDocument(rs.getString(11));
				model.setPoliceStId(rs.getLong(12));

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
		return typeOfCrime;
	}

	@Override
	public String getTableName() {
		return "st_crime_report";
	}
}


