package in.co.sunrays.ocha.model;

import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.util.JDBCDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * Model contains Complaint attributes and its Create, Read, Update and Delete
 * methods.
 * 
 * @version 1.0
 * @since 30 Mar 2015
 * @author SUNRAYS Developer
 * @Copyright (c) sunRays Technologies. All rights reserved.
 * @URL www.sunrays.co.in
 */

public class ComplaintModel extends BaseModel {

	/**
	 * Logger to log the messages.
	 */
	private static Logger log = Logger.getLogger(ComplaintModel.class);

	private static String columnNames = "ID,COMPLAINT_ID,TYPE,DOC,POLICE_STATION_ID,POLICE_STATION_NAME,DOC1,DOC2,DOC3,DOC4";

	private String complaintId;
	private String type;
	private Date doc;
	private long policeStationId;
	private String policeStationName;
	private String doc1;
	private String doc2;
	private String doc3;
	private String doc4;

	public String getcomplaintId() {
		return complaintId;
	}

	public void setcomplaintId(String complaintId) {
		this.complaintId = complaintId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDoc() {
		return doc;
	}

	public void setDoc(Date doc) {
		this.doc = doc;
	}

	public long getPoliceStationId() {
		return policeStationId;
	}

	public void setPoliceStationId(long policeStationId) {
		this.policeStationId = policeStationId;
	}

	public String getpoliceStationName() {
		return policeStationName;
	}

	public void setpoliceStationName(String policeStationName) {
		this.policeStationName = policeStationName;
	}

	public String getDoc1() {
		return doc1;
	}

	public void setDoc1(String doc1) {
		this.doc1 = doc1;
	}

	public String getDoc2() {
		return doc2;
	}

	public void setDoc2(String doc2) {
		this.doc2 = doc2;
	}

	public String getDoc3() {
		return doc3;
	}

	public void setDoc3(String doc3) {
		this.doc3 = doc3;
	}

	public String getDoc4() {
		return doc4;
	}

	public void setDoc4(String doc4) {
		this.doc4 = doc4;
	}

	/**
	 * Adds a record
	 * 
	 * @return
	 * @throws ApplicationException
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

			String sql = "INSERT INTO "+ getTableName() +" (" + columnNames + ")"
					+ " VALUES(?,?,?,?,?,?,?,?,?,?)";
			log.info("SQL : " + sql);

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, pk);
			pstmt.setString(2, complaintId);
			pstmt.setString(3, type);
			pstmt.setDate(4, new java.sql.Date(doc.getTime()));
			pstmt.setLong(5, policeStationId);
			pstmt.setString(6, policeStationName);
			pstmt.setString(7, doc1);
			pstmt.setString(8, doc2);
			pstmt.setString(9, doc3);
			pstmt.setString(10, doc4);
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException(
						"Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException(
					"Exception : Exception in add Complaint");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}

	/**
	 * Deletes a record
	 * 
	 * @throws ApplicationException
	 */

	public void delete() throws ApplicationException {
		log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction

			String sql = "DELETE FROM " + getTableName() + " WHERE ID=?";
			log.info("SQL : " + sql);

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
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
					"Exception : Exception in delete comment");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete Started");
	}

	/**
	 * Finds record by Primary Key ( ID)
	 * 
	 * @param pk
	 * @return
	 * @throws ApplicationException
	 */
	public ComplaintModel findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");

		StringBuffer sql = new StringBuffer("SELECT * FROM " + getTableName()
				+ " WHERE ID=?");
		log.info("SQL : " + sql);

		ComplaintModel model = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				model = new ComplaintModel();
				model.setId(rs.getLong(1));
				model.setcomplaintId(rs.getString(2));
				model.setType(rs.getString(3));
				model.setDoc(rs.getDate(4));
				model.setPoliceStationId(rs.getLong(5));
				model.setpoliceStationName(rs.getString(6));
				model.setDoc1(rs.getString(7));
				model.setDoc2(rs.getString(8));
				model.setDoc3(rs.getString(9));
				model.setDoc4(rs.getString(10));

			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in getting Complain by PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPk End");
		return model;
	}

	/**
	 * Updates a records
	 * 
	 * @throws ApplicationException
	 */
	public void update() throws ApplicationException {
		log.debug("Model update Started");
		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false); // Begin transaction

			String sql = "UPDATE "
					+ getTableName()
					+ " SET COMPLAINT_ID=?,type=?,doc=?,police_Station_Id=?,police_station_Name=?,doc1=?,doc2=?,doc3=?,doc4=? WHERE ID=?";
			log.info("SQL : " + sql);

			System.out.println(sql);

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, complaintId);
			pstmt.setString(2, type);
			pstmt.setDate(3, new java.sql.Date(doc.getTime()));
			pstmt.setLong(4, policeStationId);
			pstmt.setString(5, policeStationName);
			pstmt.setString(6, doc1);
			pstmt.setString(7, doc2);
			pstmt.setString(8, doc3);
			pstmt.setString(9, doc4);
			pstmt.setLong(10, id);

			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException(
						"Exception : Delete rollback exception "
								+ ex.getMessage());
			}
			throw new ApplicationException("Exception in updating Complaint ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}

	public List search(ComplaintModel model) throws ApplicationException {
		return search(model, 0, 0);
	}

	/**
	 * Searches records on the basis of model NOT NULL attributes with
	 * pagination.
	 * 
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws ApplicationException
	 */
	public List search(ComplaintModel model, int pageNo, int pageSize)
			throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT " + columnNames + " FROM "
				+ getTableName() + " WHERE 1=1");

		/*
		 * private String compiantId = null; private String type = null; private
		 * Date doc = null; private long policeStationId = 0; private String
		 * policeName = null;
		 */
		if (model != null) {
			System.out.println(model.getDoc());
			/*
			 * if (model.getId() > 0) { sql.append(" AND id = " +
			 * model.getId()); }
			 */
			if (model.getcomplaintId() != null
					&& model.getcomplaintId().length() > 0) {
				sql.append(" AND COMPLAINT_ID like '" + model.getcomplaintId()
						+ "%'");
			}
			if (model.getType() != null && model.getType().length() > 0) {
				sql.append(" AND TYPE like '" + model.getType() + "%'");
			}

			if (model.getDoc() != null) {
				sql.append(" AND DOC like '" + model.getDoc());

			}
			if (model.getPoliceStationId() != 0) {
				sql.append(" AND POLICE_STATION_ID = "
						+ model.getPoliceStationId());
			}
			if (model.getpoliceStationName() != null
					&& model.getpoliceStationName().length() > 0) {
				sql.append(" AND POLICE_STATION_NAME like '"
						+ model.getpoliceStationName() + "%'");
			}
		}

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}

		log.info("SQL : " + sql);

		ArrayList<ComplaintModel> list = new ArrayList<ComplaintModel>();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				model = new ComplaintModel();
				model.setId(rs.getLong(1));
				model.setcomplaintId(rs.getString(2));
				model.setType(rs.getString(3));
				model.setDoc(rs.getDate(4));
				model.setPoliceStationId(rs.getLong(5));
				model.setpoliceStationName(rs.getString(6));
				model.setDoc1(rs.getString(7));
				model.setDoc2(rs.getString(8));
				model.setDoc3(rs.getString(9));
				model.setDoc4(rs.getString(10));
				list.add(model);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in search complaint");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model search End");

		return list;
	}

	/**
	 * Searches records on the basis of model NOT NULL attributes
	 * 
	 * @return
	 * @throws ApplicationException
	 */

	/**
	 * Returns Drop Down List Value
	 */
	@Override
	public String getKey() {
		return id + "";
	}

	/**
	 * Returns name of table
	 */
	@Override
	public String getValue() {
		return policeStationName;
	}

	@Override
	public String getTableName() {
		return "st_complaint";
	}

}
