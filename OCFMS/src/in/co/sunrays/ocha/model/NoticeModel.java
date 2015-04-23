package in.co.sunrays.ocha.model;

import in.co.sunrays.ocha.bean.BaseBean;

import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.exception.DatabaseException;
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
 * Model contains Notice attributes and its Create, Read, Update and Delete
 * methods.
 * 
 * @version 1.0
 * @since 01 Feb 2015
 * @author SUNRAYS Developer
 * @Copyright (c) sunRays Technologies. All rights reserved.
 * @URL www.sunrays.co.in
 */
public class NoticeModel extends BaseModel {

	private static Logger log = Logger.getLogger(NoticeModel.class);

	private String subject = null;
	private String details = null;
	private Timestamp createdOn = null;
	private Date expireDate;

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
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

			String sql = "INSERT INTO " + getTableName() + " VALUES(?,?,?,?,?)";
			log.info("SQL : " + sql);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, pk);
			pstmt.setString(2, subject);
			pstmt.setString(3, details);
			java.util.Date date = new Date();
			pstmt.setTimestamp(4, new java.sql.Timestamp(date.getTime()));
			pstmt.setDate(5, new java.sql.Date(getExpireDate().getTime()));
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
					"Exception : Exception in add Notice");
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
			String sql = "DELETE FROM  " + getTableName() + " WHERE ID=?";
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
					"Exception : Exception in delete Notice");
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
	
	public NoticeModel findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		
		StringBuffer sql = new StringBuffer("SELECT * FROM " + getTableName() + " WHERE ID=?");
		log.info("SQL : " + sql);

		NoticeModel model = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				model = new NoticeModel();
				model.setId(rs.getLong(1));
				model.setSubject(rs.getString(2));
				model.setDetails(rs.getString(3));
				model.setCreatedOn(rs.getTimestamp(4));
				model.setExpireDate(rs.getDate(5));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in getting EResource by PK");
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
			String sql = "UPDATE " + getTableName() + " SET SUBJECT=?,DETAILS=?,EXPIRE_DATE=? WHERE ID=?";
			log.info("SQL : " + sql);

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, subject);
			pstmt.setString(2, details);
			pstmt.setDate(3, new java.sql.Date(getExpireDate().getTime()));
			pstmt.setLong(4, id);
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
			throw new ApplicationException("Exception in updating Notice ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
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
	public List search(int pageNo, int pageSize)
			throws ApplicationException {
		
		log.debug("Model search Started");
		
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM " + getTableName()
				+ " WHERE 1=1 AND DATE(NOW()) <= DATE(EXPIRE_DATE)");

			if (id > 0) {
				sql.append(" AND id = " + id);
			}
			if (subject != null && subject.length() > 0) {
				sql.append(" AND SUBJECT like '" + subject + "%'");
			}
			if (details != null && details.length() > 0) {
				sql.append(" AND DETAILS like '" + details + "%'");
			}
			if (expireDate != null) {
				sql.append(" AND EXPIRE_DATE like '" + expireDate + "%'");
			}

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}

		log.info("SQL : " + sql);
		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				NoticeModel	model = new NoticeModel();
				model.setId(rs.getLong(1));
				model.setSubject(rs.getString(2));
				model.setDetails(rs.getString(3));
				model.setCreatedOn(rs.getTimestamp(4));
				model.setExpireDate(rs.getDate(5));
				list.add(model);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in search Notice");
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
	public List search() throws ApplicationException {
		return search( 0, 0);
	}


	/**
	 * Returns Drop Down List key
	 */
	@Override
	public String getKey() {
		return id + "";
	}

	/**
	 * Returns Drop Down List Value
	 */
	@Override
	public String getValue() {
		return subject;
	}

	/**
	 * Returns name of table
	 */
	@Override
	public String getTableName() {
		return "ST_NOTICE";
	}

}
