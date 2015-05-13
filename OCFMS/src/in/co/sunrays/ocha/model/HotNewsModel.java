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

public class HotNewsModel extends BaseModel {
	/**
	 * Logger to log the messages.
	 */
	private static Logger log = Logger.getLogger(CommentModel.class);
	private String news;
	private Date declaredDate;
	private Date time;
	private String authorizedPerson;

	public String getNews() {
		return news;
	}

	public void setNews(String news) {
		this.news = news;
	}

	public Date getDeclaredDate() {
		return declaredDate;
	}

	public void setDeclaredDate(Date declaredDate) {
		this.declaredDate = declaredDate;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getAuthorizedPerson() {
		return authorizedPerson;
	}

	public void setAuthorizedPerson(String authorizedPerson) {
		this.authorizedPerson = authorizedPerson;
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
			String sql = "INSERT INTO ST_HOT_NEWS (ID,NEWS,DECLARED_DATE,TIME,AUTHORIZED_PERSON) VALUES(?,?,?,?,?)";
							
						
			log.info("SQL : " + sql);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, pk);
			pstmt.setString(2, news);
			java.util.Date date = new Date();
			pstmt.setDate(3, new java.sql.Date(getDeclaredDate().getTime()));
			pstmt.setTimestamp(4, new java.sql.Timestamp(date.getTime()));
			pstmt.setString(5, authorizedPerson);
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
			String sql = "DELETE FROM ST_HOT_NEWS WHERE ID=?";
					
						 
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
	public HotNewsModel findByPK(long pk) throws ApplicationException {
		log.debug("Model findByName Started");
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM ST_HOT_NEWS WHERE ID=?");
							
							 
		log.info("SQL : " + sql);
		HotNewsModel model = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				model = new HotNewsModel();
				model.setId(rs.getLong(1));
				model.setNews(rs.getString(2));
				model.setDeclaredDate(rs.getDate(3));
				model.setTime(rs.getTime(4));
				model.setAuthorizedPerson(rs.getString(5));
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
			String sql = "UPDATE ST_HOT_NEWS SET NEWS=?,"
					+ "DECLARED_DATE = ?,TIME = ?,AUTHORIZED_PERSON=?  WHERE ID=?";
							
							
			log.info("SQL : " + sql);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, news);
			pstmt.setDate(2, new java.sql.Date(declaredDate
					.getTime()));
			pstmt.setDate(3, new java.sql.Date(time.getTime()));

			pstmt.setString(4, authorizedPerson);
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
	public List search(int pageNo, int pageSize)
			throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM ST_HOT_NEWS WHERE 1=1");
					
		if (id > 0) {
			sql.append(" AND id = " + id);
		}
		if (news != null && news.length() > 0) {
			sql.append(" AND NEWS like '" + news + "%'");
		}
		if (declaredDate != null) {
			sql.append(" AND DECLARED_DATE like '" + declaredDate + "%'");
		}
		if (authorizedPerson != null && authorizedPerson.length() > 0) {
			sql.append(" AND AUTHROIZED_PERSON like '" + authorizedPerson
					+ "%'");
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
				HotNewsModel model = new HotNewsModel();
				model.setId(rs.getLong(1));
				model.setNews(rs.getString(2));
				model.setDeclaredDate(rs.getDate(3));
				model.setTime(rs.getTime(4));
				model.setAuthorizedPerson(rs.getString(5));
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
	public List search(HotNewsModel model) throws ApplicationException {
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
		return news;
	}

	/**
	 * Returns name of table
	 */
	@Override
	public String getTableName() {
		return "st_hot_news";
	}
}
