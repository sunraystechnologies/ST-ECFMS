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

public class CommentModel extends BaseModel {

	/**
	 * Logger to log the messages.
	 */
	private static Logger log = Logger.getLogger(CommentModel.class);

	private long resourceId = 0;
	private long userId = 0;
	private String text = null;
	private String name = null;
	private Timestamp createdOn = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getResourceId() {
		return resourceId;
	}

	public void setResourceId(long resourceId) {
		this.resourceId = resourceId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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

		Connection conn = null;
		long pk = 0;

		try {
			conn = JDBCDataSource.getConnection();

			// Get auto-generated next primary key
			pk = nextPK();

			conn.setAutoCommit(false); // Begin transaction

			String sql = "INSERT INTO " + getTableName()
					+ " VALUES(?,?,?,?,?,?)";
			log.info("SQL : " + sql);

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, pk);
			pstmt.setLong(2, resourceId);
			pstmt.setString(3, text);
			java.util.Date date = new Date();
			pstmt.setTimestamp(4, new java.sql.Timestamp(date.getTime()));
			pstmt.setLong(5, userId);
			pstmt.setString(6, name);
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
	public CommentModel findByPK(long pk) throws ApplicationException {
		log.debug("Model findByName Started");

		StringBuffer sql = new StringBuffer("SELECT * FROM " + getTableName()
				+ " WHERE ID=?");
		log.info("SQL : " + sql);

		CommentModel model = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				model = new CommentModel();
				model.setId(rs.getLong(1));
				model.setResourceId(rs.getLong(2));
				model.setText(rs.getString(3));
				model.setCreatedOn(rs.getTimestamp(4));
				model.setUserId(rs.getLong(5));
				model.setName(rs.getString(6));
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
	public void update() throws ApplicationException {

		log.debug("Model update Started");

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false); // Begin transaction

			String sql = "UPDATE " + getTableName() + " SET text=? WHERE ID=?";
			log.info("SQL : " + sql);

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, text);
			pstmt.setLong(2, id);
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
	public List search(int pageNo, int pageSize) throws ApplicationException {

		log.debug("Model search Started");

		StringBuffer sql = new StringBuffer("SELECT * FROM " + getTableName()
				+ " WHERE 1=1");

		if (id > 0) {
			sql.append(" AND id = " + id);
		}
		if (text != null && text.length() > 0) {
			sql.append(" AND text like '" + text + "%'");
		}
		if (resourceId > 0) {
			sql.append(" AND resourceId =" + id);
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
				CommentModel model = new CommentModel();
				model.setId(rs.getLong(1));
				model.setResourceId(rs.getLong(2));
				model.setText(rs.getString(3));
				model.setCreatedOn(rs.getTimestamp(4));
				model.setUserId(rs.getLong(5));
				model.setName(rs.getString(6));
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
	public List search() throws ApplicationException {
		return search(0, 0);
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
		return text;
	}

	/**
	 * Returns name of table
	 */
	@Override
	public String getTableName() {
		return "st_comment";
	}

}
