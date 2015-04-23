package in.co.sunrays.ocha.model;

import in.co.sunrays.ocha.bean.UserBean;
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
 * Model contains Attendence attributes and its Create, Read, Update and Delete
 * methods.
 * 
 * @version 1.0
 * @since 01 Feb 2015
 * @author SUNRAYS Developer
 * @Copyright (c) sunRays Technologies. All rights reserved.
 * @URL www.sunrays.co.in
 */

public class AttendenceModel extends BaseModel {
	private static Logger log = Logger.getLogger(AttendenceModel.class);

	private long studentId;
	private long BranchId;
	private long subjectId;
	private String subject;
	private int attendence;
	private String studentName;
	private String BranchName;
	private int totalAttendence;


	public long getBranchId() {
		return BranchId;
	}

	public void setBranchId(long branchId) {
		BranchId = branchId;
	}

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	private Date createdOn = null;

	
	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public int getTotalAttendence() {
		return totalAttendence;
	}

	public void setTotalAttendence(int totalAttendence) {
		this.totalAttendence = totalAttendence;
	}
	public String getBranchName() {
		return BranchName;
	}

	public void setBranchName(String branchName) {
		BranchName = branchName;
	}
	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getAttendence() {
		return attendence;
	}

	public void setAttendence(int attendence) {
		this.attendence = attendence;
	}

	/**
	 * Adds a record
	 * 
	 * @return
	 * @throws ApplicationException
	 */
	public long add() throws ApplicationException {

		log.debug("Model add Started");
		UserModel smodel = new UserModel();
		UserBean bean = smodel.findByPK(studentId);
		AttendenceModel model=new AttendenceModel();
		model.setStudentName(bean.getFirstName());
		studentName=bean.getFirstName()+""+bean.getLastName();
		
		BranchModel branchModel=new BranchModel();
		branchModel=branchModel.findByPK(BranchId);
		BranchName=branchModel.getBranchName();
		
		SubjectModel subjectModel=new SubjectModel();
		subjectModel=subjectModel.findByPK(subjectId);
		subject=subjectModel.getSubjectName();
		Connection conn = null;
		long pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			conn.setAutoCommit(false); // Begin transaction

			String sql = "INSERT INTO " + getTableName()
					+ " VALUES(?,?,?,?,?,?,?,?,?,?)";
			log.info("SQL : " + sql);

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, pk);
			pstmt.setLong(2, studentId);
			pstmt.setString(3, studentName);
			pstmt.setString(4, subject);
			pstmt.setInt(5, attendence);
			pstmt.setDate(6, new java.sql.Date(createdOn.getTime()));
			pstmt.setString(7, BranchName);
			pstmt.setInt(8, totalAttendence);
			pstmt.setLong(9, BranchId);
			pstmt.setLong(10, subjectId);
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
					"Exception : Exception in add Attendence");
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
					"Exception : Exception in delete Attendence");
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
	public AttendenceModel findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPk Started");

		StringBuffer sql = new StringBuffer("SELECT * FROM " + getTableName()
				+ " WHERE ID=?");
		log.info("SQL : " + sql);

		AttendenceModel model = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				model = new AttendenceModel();
				model.setId(rs.getLong(1));
				model.setStudentId(rs.getLong(2));
				model.setStudentName(rs.getString(3));
				model.setSubject(rs.getString(4));
				model.setAttendence(rs.getInt(5));
				model.setCreatedOn(rs.getDate(6));
				model.setBranchName(rs.getString(7));
				model.setTotalAttendence(rs.getInt(8));
				model.setBranchId(rs.getLong(9));
				model.setSubjectId(rs.getLong(10));

			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in getting Attendence by PK");
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
					+ " SET subject=?,attendence=? WHERE ID=?";
			log.info("SQL : " + sql);

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, subject);
			pstmt.setInt(2, attendence);
			pstmt.setLong(3, id);
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
			throw new ApplicationException("Exception in updating Attendence ");
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
	public List search(AttendenceModel model, int pageNo, int pageSize)
			throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM " + getTableName()
				+ " WHERE 1=1");

		if (model != null) {
			if (id > 0) {
				sql.append(" AND id = " + id);
			}
			if (subject != null && subject.length() > 0) {
				sql.append(" AND subject like '" + subject + "%'");
			}
			if (studentName != null && studentName.length() > 0) {
				sql.append(" AND student_name like '" + studentName + "%'");
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
				model = new AttendenceModel();
				model.setId(rs.getLong(1));
				model.setStudentId(rs.getLong(2));
				model.setStudentName(rs.getString(3));
				model.setSubject(rs.getString(4));
				model.setAttendence(rs.getInt(5));
				model.setCreatedOn(rs.getDate(6));
				model.setBranchName(rs.getString(7));
				model.setTotalAttendence(rs.getInt(8));
				model.setBranchId(rs.getLong(9));
				model.setSubjectId(rs.getLong(10));
				list.add(model);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in search Attendence");
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
	public List search(AttendenceModel model) throws ApplicationException {
		return search(model, 0, 0);
	}

	
	/**
	 * Finds record by Student ID
	 * 
	 * @param pk
	 * @return
	 * @throws ApplicationException
	 */
	public List findByStudentPk(AttendenceModel model, int pageNo, int pageSize)
			throws ApplicationException {
		log.debug("Model list Started");
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer(
				"select * from  " + getTableName()
				+ " where studentId="
						+ model.getStudentId());
		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}
		log.info("SQL : " + sql);
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				model = new AttendenceModel();
				model.setId(rs.getLong(1));
				model.setStudentId(rs.getLong(2));
				model.setStudentName(rs.getString(3));
				model.setSubject(rs.getString(4));
				model.setAttendence(rs.getInt(5));
				model.setCreatedOn(rs.getDate(6));
				model.setBranchName(rs.getString(7));
				model.setTotalAttendence(rs.getInt(8));
				model.setBranchId(rs.getLong(9));
				model.setSubjectId(rs.getLong(10));
				list.add(model);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in getting list of users");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model list End");
		return list;
	}

	public List findByStudentPk(AttendenceModel model)
			throws ApplicationException {
		return findByStudentPk(model, 0, 0);
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
		return "ST_ATTENDENCE";
	}


}
