package in.co.sunrays.common.model;

import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.exception.DatabaseException;
import in.co.sunrays.ocha.exception.DuplicateRecordException;
import in.co.sunrays.ocha.model.BaseModel;
import in.co.sunrays.util.JDBCDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * JDBC Implementation of Role Model
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */
public class RoleModel extends BaseModel {

	private static Logger log = Logger.getLogger(RoleModel.class);

	/**
	 * Predefined Role constants
	 */
	public int GUEST = 1;
	public int USER = 2;
	public int INSPECTOR = 3;
	public int COMMISSIONER = 4;
	public int ADMIN = 5;

	/**
	 * Role Name
	 */

	private String name;

	/**
	 * Role Description
	 */
	private String description;

	/**
	 * accessor
	 */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKey() {
		return id + "";
	}

	public String getValue() {
		return name;
	}

	/**
	 * Add a Role
	 * 
	 * @param model
	 * @throws DatabaseException
	 * 
	 */
	public long add() throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		long pk = 0;

		RoleModel duplicataRole = findByName(name);
		// Check if create Role already exist
		if (duplicataRole != null) {
			throw new DuplicateRecordException("Role already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ST_ROLE(ID,NAME,DESCRIPTION) VALUES(?,?,?,?,?,?,?)");
					 
			pstmt.setLong(1, pk);
			pstmt.setString(2, name);
			pstmt.setString(3, description);
			
			pstmt.executeUpdate();
			this.setId(pk);
			updateCreatedInfo();
			updateModifiedInfo();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException(
						"Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Role");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}

	/**
	 * Delete a Role
	 * 
	 * @param model
	 * @throws DatabaseException
	 */
	public void delete() throws ApplicationException {
		log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM ST_ROLE WHERE ID=?");
				
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
					"Exception : Exception in delete Role");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete Started");
	}

	/**
	 * Find User by Role
	 * 
	 * @param name
	 *            : get parameter
	 * @return model
	 * @throws DatabaseException
	 */

	public RoleModel findByName(String name) throws ApplicationException {
		log.debug("Model findBy EmailId Started");
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM ST_ROLE WHERE NAME=?");
					
		RoleModel model = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				model = new RoleModel();
				model.setId(rs.getLong(1));
				model.setName(rs.getString(2));
				model.setDescription(rs.getString(3));
				model.setCreatedBy(rs.getString(4));
				model.setModifiedBy(rs.getString(5));
				model.setCreatedDatetime(rs.getTimestamp(6));
				model.setModifiedDatetime(rs.getTimestamp(7));

			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in getting User by emailId");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findBy EmailId End");
		return model;
	}

	/**
	 * Find Role by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return model
	 * @throws DatabaseException
	 */

	public RoleModel findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_ROLE WHERE ID=?");
					
		RoleModel model = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				model = new RoleModel();
				model.setId(rs.getLong(1));
				model.setName(rs.getString(2));
				model.setDescription(rs.getString(3));
				model.setCreatedBy(rs.getString(4));
				model.setModifiedBy(rs.getString(5));
				//model.setCreatedDatetime(rs.getTimestamp(6));
				//model.setModifiedDatetime(rs.getTimestamp(7));
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
	 * Update a Role
	 * 
	 * @param model
	 * @throws DatabaseException
	 */

	public void update() throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;

		RoleModel duplicataRole = findByName(name);
		// Check if updated Role already exist
		if (duplicataRole != null && duplicataRole.getId() != id) {
			throw new DuplicateRecordException("Role already exists");
		}
		try {
			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("UPDATE ST_ROLE  SET NAME=?,DESCRIPTION=?,CREATED_BY=?,"
							+ "MODIFIED_BY=?,CREATED_DATETIME=?,"
							+ "MODIFIED_DATETIME=? WHERE ID=?");
							
							
			pstmt.setString(1, name);
			pstmt.setString(2, description);
			
			pstmt.setLong(3, id);
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
			throw new ApplicationException("Exception in updating Role ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}

	/**
	 * Search Role
	 * 
	 * @param model
	 *            : Search Parameters
	 * @throws DatabaseException
	 */

	public List search() throws ApplicationException {
		return search(0, 0);
	}

	/**
	 * Search Role with pagination
	 * 
	 * @return list : List of Roles
	 * @param model
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
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_ROLE  WHERE 1=1");
					
	if (id > 0) {
				sql.append(" AND id = " + id);
			}
			if (name != null && name.length() > 0) {
				sql.append(" AND NAME like '" + name + "%'");
			}
			if (description != null
					&& description.length() > 0) {
				sql.append(" AND DESCRIPTION like '" + description
						+ "%'");
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
				RoleModel model = new RoleModel();
				model.setId(rs.getLong(1));
				model.setName(rs.getString(2));
				model.setDescription(rs.getString(3));
				model.setCreatedBy(rs.getString(4));
				model.setModifiedBy(rs.getString(5));
				//model.setCreatedDatetime(rs.getTimestamp(6));
				//model.setModifiedDatetime(rs.getTimestamp(7));
				list.add(model);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in search Role");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model search End");
		return list;
	}

	@Override
	public String getTableName() {
		return "ST_ROLE";
	}

}