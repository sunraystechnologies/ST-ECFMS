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
 * Model contains Comment attributes and its Create, Read, Update and Delete
 * methods.
 *
 * @version 1.0
 * @since 01 Feb 2015
 * @author SUNRAYS Developer
 * @Copyright (c) sunRays Technologies. All rights reserved.
 * @URL www.sunrays.co.in
 */

public class FeedbackModel extends BaseModel {
	/**
	 * Logger to log the messages.
	 */
	private static Logger log = Logger.getLogger(FeedbackModel.class);
	
	private long id;
	private String name;
	private long userId;
	private String userLogin;
	private String emailId;
	private String feedback;
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the feedback
	 */
	public String getFeedback() {
		return feedback;
	}

	/**
	 * @param feedback the feedback to set
	 */
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	/**
	 * @return the userId
	 */
	public long  getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * @return the userLogin
	 */
	public String getUserLogin() {
		return userLogin;
	}

	/**
	 * @param userLogin the userLogin to set
	 */
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	/**
	 * Adds a record
	 *
	 * @return
	 * @throws ApplicationException
	 */
	public long add() throws ApplicationException {
		Connection conn = null;
		long pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			// Get auto-generated next primary key
			pk = nextPK();
			conn.setAutoCommit(false); // Begin transaction
			String sql = "INSERT INTO "
							+ getTableName()
							+ " VALUES(?,?,?,?)";

			log.info("SQL : " + sql);
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, pk);
			pstmt.setString(2, name);
			pstmt.setString(3, emailId);
			pstmt.setString(4, feedback);
			
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
					"Exception : Exception in add Comment");
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
		System.out.println(id);
		log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			String sql = "DELETE FROM "
							+ getTableName()
							+ " WHERE ID=?";
			log.info("SQL : " + sql);
			PreparedStatement pstmt = conn.prepareStatement(sql);

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
	public FeedbackModel findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPk Started");
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM "
							+ getTableName()
							+ " WHERE ID=?");
		log.info("SQL : " + sql);
		FeedbackModel model = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				model = new FeedbackModel();
				model.setId(rs.getLong(1));
				model.setName(rs.getString(2));
				model.setEmailId(rs.getString(3));
				model.setFeedback(rs.getString(4));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in getting comment by PK");
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
	public void update(FeedbackModel model) throws ApplicationException {
		log.debug("Model update Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			String sql = "UPDATE "
							+ getTableName()
							+ " SET NAME=?,EMAIL_ID = ?,FEEDBACK=?  WHERE ID=?";
			log.info("SQL : " + sql);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, model.getName());
			pstmt.setString(2, model.getEmailId());
			pstmt.setString(3, model.getFeedback());
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
			throw new ApplicationException("Exception in updating Comment ");
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
	public List search(FeedbackModel model, int pageNo, int pageSize)
			throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM "
							+ getTableName()
							+ " WHERE 1=1 ");

		if (id > 0) {
			sql.append(" AND id = " + model.getId());
		}
		if (name != null && model.getName().length() > 0) {
			sql.append(" AND NAME like '" + model.getName() + "%'");
		}
		if (emailId != null && model.getEmailId().length() > 0) {
			sql.append(" AND EMAIL_ID like '" + model.getEmailId() + "%'");
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
				model = new FeedbackModel();
				model.setId(rs.getLong(1));
				model.setName(rs.getString(2));
				model.setEmailId(rs.getString(3));
				model.setFeedback(rs.getString(4));
				list.add(model);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in search Comment");
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
	public List search(FeedbackModel model) throws ApplicationException {
		return search(model, 0, 0);
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
		return feedback;
	}

	/**
	 * Returns name of table
	 */
	@Override
	public String getTableName() {
		return "st_feedback";
	}
}
