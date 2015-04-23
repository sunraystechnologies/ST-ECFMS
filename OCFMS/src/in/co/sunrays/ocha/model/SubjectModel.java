package in.co.sunrays.ocha.model;

import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.util.JDBCDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class SubjectModel extends BaseModel {

	private static Logger log = Logger.getLogger(SubjectModel.class);
	
	private long branchId;
	private String subjectName;
	
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

			String sql = "INSERT INTO " + getTableName()
					+ " VALUES(?,?,?)";
			log.info("SQL : " + sql);

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, pk);
			pstmt.setLong(2, branchId);
			pstmt.setString(3, subjectName);
		
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
					"Exception : Exception in add subject");
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
					"Exception : Exception in delete subject");
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
	public SubjectModel findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPk Started");

		StringBuffer sql = new StringBuffer("SELECT * FROM " + getTableName()
				+ " WHERE ID=?");
		log.info("SQL : " + sql);

		SubjectModel model = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				model = new SubjectModel();
				model.setId(rs.getLong(1));
				model.setBranchId(rs.getLong(2));
				model.setSubjectName(rs.getString(3));
	

			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in getting subject by PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByName End");
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
			String sql = "UPDATE " + getTableName()
					+ " SET SUBJECT_NAME=? WHERE ID=?";
			log.info("SQL : " + sql);

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, subjectName);
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
			throw new ApplicationException("Exception in updating Subject ");
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
	public List search(SubjectModel model, int pageNo, int pageSize)
			throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM " + getTableName()
				+ " WHERE 1=1");

		if (model != null) {
			if (id > 0) {
				sql.append(" AND id = " + id);
			}
			if (subjectName != null && subjectName.length() > 0) {
				sql.append(" AND SUBJECT_NAME like '" + subjectName + "%'");
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
		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				model = new SubjectModel();
				model.setId(rs.getLong(1));
				model.setBranchId(rs.getLong(2));
				model.setSubjectName(rs.getString(3));

				list.add(model);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in search subject");
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
	public List search(SubjectModel model) throws ApplicationException {
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
		return subjectName;
	}

	public long getBranchId() {
		return branchId;
	}

	public void setBranchId(long branchId) {
		this.branchId = branchId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	@Override
	public String getTableName() {
		return "ST_SUBJECT";
	}

}
