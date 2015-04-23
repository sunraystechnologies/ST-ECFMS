package in.co.sunrays.ocha.model;

import in.co.sunrays.ocha.bean.DropdownListBean;
import in.co.sunrays.ocha.exception.DatabaseException;
import in.co.sunrays.util.JDBCDataSource;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import org.apache.log4j.Logger;

/**
 * Base Model containing common attributes and methods.
 * 
 * It implements Comparable interface that compares two objects on the basis of
 * primary key ID.
 * 
 * It implements DropdownListBean interface, that is used to make HTML Drop List
 * from Model collection.
 * 
 * @version 1.0
 * @since 01 Feb 2015
 * @author SUNRAYS Developer
 * @Copyright (c) sunRays Technologies. All rights reserved.
 * @URL www.sunrays.co.in
 */

public abstract class BaseModel implements Serializable, DropdownListBean,
		Comparable<BaseModel> {

	private static Logger log = Logger.getLogger(BaseModel.class);

	/**
	 * Non Business primary key
	 */
	protected long id;

	/**
	 * User name that creates this record.
	 */
	protected String createdBy;

	/**
	 * User name that modifies this record.
	 */
	protected String modifiedBy;

	/**
	 * Created timestamp of record
	 */
	protected Timestamp createdDatetime;

	/**
	 * Modified timestamp of record
	 */
	protected Timestamp modifiedDatetime;

	/**
	 * accessor methods
	 */
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(Timestamp createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public Timestamp getModifiedDatetime() {
		return modifiedDatetime;
	}

	public void setModifiedDatetime(Timestamp modifiedDatetime) {
		this.modifiedDatetime = modifiedDatetime;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * Compares IDs ( Primary Key). If keys are equals then objects are equals.
	 * 
	 */
	public int compareTo(BaseModel next) {
		return (int) (id - next.getId());
	}

	/**
	 * created next PK of record
	 * 
	 * @throws DatabaseException
	 */

	public long nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		long pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement("SELECT MAX(ID) FROM " + getTableName());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		return pk + 1;
	}

	/**
	 * Gets table name of Model
	 * 
	 * @return
	 */
	public abstract String getTableName();

}
