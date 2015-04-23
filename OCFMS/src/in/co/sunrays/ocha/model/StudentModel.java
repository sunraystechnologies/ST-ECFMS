package in.co.sunrays.ocha.model;

import in.co.sunrays.ocha.bean.StudentBean;
import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.exception.DatabaseException;
import in.co.sunrays.ocha.exception.DuplicateRecordException;
import in.co.sunrays.util.JDBCDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * JDBC Implementation of Student Model
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */
public class StudentModel extends BaseModel {

	private static Logger log = Logger.getLogger(StudentModel.class);

	/**
	 * First Name of Student
	 */
	private String firstName;
	/**
	 * Last Name of Student
	 */
	private String lastName;
	/**
	 * Date of Birth of Student
	 */
	private Date dob;
	/**
	 * Mobile NO of Student
	 */
	private String mobileNo;
	/**
	 * Email of Student
	 */
	private String email;
	/**
	 * CollegeId of Student
	 */
	private long collegeId;
	/**
	 * College name of Student
	 */
	private String collegeName;

	/**
	 * accessor
	 */

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(Long collegeId) {
		this.collegeId = collegeId;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	/**
	 * Add a Student
	 * 
	 * @param bean
	 * @throws DatabaseException
	 * 
	 */
	public long add() throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;

		// get College Name
		// CollegeModel cModel = new CollegeModel();
		// CollegeBean collegeBean = cModel.findByPK(bean.getCollegeId());
		// bean.setCollegeName(collegeBean.getName());

		StudentBean duplicateName = findByEmailId(email);
		long pk = 0;

		if (duplicateName != null) {
			throw new DuplicateRecordException("Email already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO STUDENT VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setLong(1, pk);
			pstmt.setLong(2, collegeId);
			pstmt.setString(3, collegeName);
			pstmt.setString(4, firstName);
			pstmt.setString(5, lastName);
			pstmt.setDate(6, new java.sql.Date(dob.getTime()));
			pstmt.setString(7, mobileNo);
			pstmt.setString(8, email);
			pstmt.setString(9, createdBy);
			pstmt.setString(10, modifiedBy);
			pstmt.setTimestamp(11, createdDatetime);
			pstmt.setTimestamp(12, modifiedDatetime);
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException(
						"Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException(
					"Exception : Exception in add Student");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}

	/**
	 * Delete a Student
	 * 
	 * @param bean
	 * @throws DatabaseException
	 */
	public void delete() throws ApplicationException {
		log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM "
					+ getTableName() + " WHERE ID=?");
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
					"Exception : Exception in delete Student");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete Started");
	}

	/**
	 * Find User by Student
	 * 
	 * @param Email
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */

	public StudentBean findByEmailId(String Email) throws ApplicationException {
		log.debug("Model findBy Email Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM " + getTableName()
				+ " WHERE EMAIL=?");
		StudentBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, Email);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new StudentBean();
				bean.setId(rs.getLong(1));
				bean.setCollegeId(rs.getLong(2));
				bean.setCollegeName(rs.getString(3));
				bean.setFirstName(rs.getString(4));
				bean.setLastName(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setEmail(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));

			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in getting User by Email");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findBy Email End");
		return bean;
	}

	/**
	 * Find Student by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */

	public StudentBean findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM " + getTableName()
				+ " WHERE ID=?");
		StudentBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new StudentBean();
				bean.setId(rs.getLong(1));
				bean.setCollegeId(rs.getLong(2));
				bean.setCollegeName(rs.getString(3));
				bean.setFirstName(rs.getString(4));
				bean.setLastName(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setEmail(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
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
		return bean;
	}

	/**
	 * Update a Student
	 * 
	 * @param bean
	 * @throws DatabaseException
	 */

	public void update() throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;

		StudentBean beanExist = findByEmailId(email);

		// Check if updated Roll no already exist
		if (beanExist != null && beanExist.getId() != id) {
			throw new DuplicateRecordException("Email Id is already exist");
		}

		// get College Name
		// CollegeModel cModel = new CollegeModel();
		// CollegeBean collegeBean = cModel.findByPK(bean.getCollegeId());
		// bean.setCollegeName(collegeBean.getName());

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("UPDATE "
							+ getTableName()
							+ " SET COLLEGE_ID=?,COLLEGE_NAME=?,FIRST_NAME=?,LAST_NAME=?,DATE_OF_BIRTH=?,MOBILE_NO=?,EMAIL=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
			pstmt.setLong(1, collegeId);
			pstmt.setString(2, collegeName);
			pstmt.setString(3, firstName);
			pstmt.setString(4, lastName);
			pstmt.setDate(5, new java.sql.Date(dob.getTime()));
			pstmt.setString(6, mobileNo);
			pstmt.setString(7, email);
			pstmt.setString(8, createdBy);
			pstmt.setString(9, modifiedBy);
			pstmt.setTimestamp(10, createdDatetime);
			pstmt.setTimestamp(11, modifiedDatetime);
			pstmt.setLong(12, id);
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
			throw new ApplicationException("Exception in updating Student ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}

	/**
	 * Search Student
	 * 
	 * @param bean
	 *            : Search Parameters
	 * @throws DatabaseException
	 */

	public List search(StudentBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	/**
	 * Search Student with pagination
	 * 
	 * @return list : List of Students
	 * @param bean
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * @throws DatabaseException
	 */

	public List search(StudentBean bean, int pageNo, int pageSize)
			throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM " + getTableName()
				+ " WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
				sql.append(" AND FIRST_NAME like '" + bean.getFirstName()
						+ "%'");
			}
			if (bean.getLastName() != null && bean.getLastName().length() > 0) {
				sql.append(" AND LAST_NAME like '" + bean.getLastName() + "%'");
			}
			if (bean.getDob() != null && bean.getDob().getDate() > 0) {
				sql.append(" AND DOB = " + bean.getDob());
			}
			if (bean.getMobileNo() != null && bean.getMobileNo().length() > 0) {
				sql.append(" AND MOBILE_NO like '" + bean.getMobileNo() + "%'");
			}
			if (bean.getEmail() != null && bean.getEmail().length() > 0) {
				sql.append(" AND EMAIL like '" + bean.getEmail() + "%'");
			}
			if (bean.getCollegeName() != null
					&& bean.getCollegeName().length() > 0) {
				sql.append(" AND COLLEGE_NAME = " + bean.getCollegeName());
			}

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
				bean = new StudentBean();
				bean.setId(rs.getLong(1));
				bean.setCollegeId(rs.getLong(2));
				bean.setCollegeName(rs.getString(3));
				bean.setFirstName(rs.getString(4));
				bean.setLastName(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setEmail(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in search Student");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model search End");
		return list;
	}

	@Override
	public String getKey() {
		return id + "";
	}

	@Override
	public String getValue() {
		return firstName + " " + lastName;
	}

	@Override
	public String getTableName() {
		return "ST_STUDENT";
	}
}
