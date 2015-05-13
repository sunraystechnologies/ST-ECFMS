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

public class MailModel extends BaseModel {
	/**
	 * Logger to log the messages.
	 */
	private static Logger log = Logger.getLogger(MailModel.class);

	private long id;
	private String receiver;
	private String sender;
	private String detail;
	private String attachment;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String to) {
		this.receiver = to;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
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
			String sql = "INSERT INTO ST_MAIL(ID,SENDER,DETAIL,ATTACHMENT,RECEIVER) VALUES(?,?,?,?,?)";

			log.info("SQL : " + sql);

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, pk);
			pstmt.setString(2, sender);
			pstmt.setString(3, detail);
			pstmt.setString(4, attachment);
			pstmt.setString(5, receiver);

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
			String sql = "DELETE FROM ST_MAIL WHERE ID=?";
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
	public MailModel findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPk Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_MAIL WHERE ID=?");

		log.info("SQL : " + sql);
		MailModel model = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				model = new MailModel();
				model.setId(rs.getLong(1));
				model.setSender(rs.getString(2));
				model.setDetail(rs.getString(3));
				model.setAttachment(rs.getString(4));
				model.setReceiver(rs.getString(5));
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
			String sql = "UPDATE ST_MAIL SET SENDER = ?,DETAIL=?,ATTACHMENT=?,RECEIVER=?  WHERE ID=?";

			log.info("SQL : " + sql);
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, sender);
			pstmt.setString(2, detail);
			pstmt.setString(3, attachment);
			pstmt.setString(4, receiver);
			pstmt.setLong(5, id);
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
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_MAIL WHERE 1=1 ");

		if (id > 0) {
			sql.append(" AND id = " + id);
		}

		if (sender != null && sender.length() > 0) {
			sql.append(" AND SENDER like '" + getSender() + "%'");
		}

		if (receiver != null && receiver.length() > 0) {
			sql.append(" AND RECEIVER like '" + getReceiver() + "%'");
		}

		if (attachment != null && attachment.length() > 0) {
			sql.append(" AND ATTACHMENT like '" + getAttachment() + "%'");
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
				MailModel model = new MailModel();
				model.setId(rs.getLong(1));
				model.setSender(rs.getString(2));
				model.setDetail(rs.getString(3));
				model.setAttachment(rs.getString(4));
				model.setReceiver(rs.getString(5));
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
		return sender;
	}

	/**
	 * Returns name of table
	 */
	@Override
	public String getTableName() {
		return "st_mail";
	}
}
