package in.co.sunrays.ocha.model;

import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.exception.DatabaseException;
import in.co.sunrays.ocha.exception.DuplicateRecordException;
import in.co.sunrays.util.JDBCDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * JDBC Implementation of Student Model
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */
public class PoliceStationModel extends BaseModel {

	private static Logger log = Logger.getLogger(PoliceStationModel.class);

	/**
	 * First Name of policeStation
	 */
	private String nameOfPoliceStation;
	private String codeOfPoliceStation;
	private String areaCovered;
	private String contactNo;

	/**
	 * accessor
	 */
	public String getNameOfPoliceStation() {
		return nameOfPoliceStation;
	}

	public void setNameOfPoliceStation(String nameOfPoliceStation) {
		this.nameOfPoliceStation = nameOfPoliceStation;
	}

	public String getCodeOfPoliceStation() {
		return codeOfPoliceStation;
	}

	public void setCodeOfPoliceStation(String codeOfPoliceStation) {
		this.codeOfPoliceStation = codeOfPoliceStation;
	}

	public String getAreaCovered() {
		return areaCovered;
	}

	public void setAreaCovered(String areaCovered) {
		this.areaCovered = areaCovered;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	/**
	 * Add a police_station
	 * 
	 * @param bean
	 * @throws DatabaseException
	 * 
	 */
	public long add() throws ApplicationException {
		log.debug("Model add Started");
		Connection conn = null;

		// get policeStation Name
		// PoliceStationModel pModel = new PoliceStationModel();

		long pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			pk = nextPK();
			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO ST_POLICE_STATION (ID,NAME_OF_POLICE_STATION,"
							+ "CODE_OF_POLICE_STATION,AREA_COVERED,CONTACT_NO) VALUES (?,?,?,?,?)");
						
					

			pstmt.setLong(1, pk);
			pstmt.setString(2, nameOfPoliceStation);
			pstmt.setString(3, codeOfPoliceStation);
			pstmt.setString(4, areaCovered);
			pstmt.setString(5, contactNo);

			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
			
			this.setId(pk);
			updateCreatedInfo();
	

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException(
						"Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException(
					"Exception : Exception in add Station");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}

	/**
	 * Delete a police_station
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
			PreparedStatement pstmt = conn
					.prepareStatement("DELETE FROM ST_POLICE_STATION WHERE ID=?");
							
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
					"Exception : Exception in delete Station");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete Started");
	}

	/**
	 * Find Student by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */

	public PoliceStationModel findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM ST_POLICE_STATION WHERE ID=?");
							
		log.info("SQL : " + sql);
		PoliceStationModel model = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				model = new PoliceStationModel();
				model.setId(rs.getLong(1));
				model.setNameOfPoliceStation(rs.getString(2));
				model.setCodeOfPoliceStation(rs.getString(3));
				model.setAreaCovered(rs.getString(4));
				model.setContactNo(rs.getString(5));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in getting Station by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return model;
	}

	/**
	 * Update a Station
	 * 
	 * @param bean
	 * @throws DatabaseException
	 */

	public void update() throws ApplicationException {
		log.debug("Model update Started");
		Connection conn = null;
		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("UPDATE ST_POLICE_STATION  SET NAME_OF_POLICE_STATION=?,"
							+ "CODE_OF_POLICE_STATION=?,AREA_COVERED=?,"
							+ "CONTACT_NO=? WHERE ID=?");
			
			pstmt.setString(1, nameOfPoliceStation);
			pstmt.setString(2, codeOfPoliceStation);
			pstmt.setString(3, areaCovered);
			pstmt.setString(4, contactNo);
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
			throw new ApplicationException(
					"Exception in updating St_policestation ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}

	/**
	 * Search Station
	 * 
	 * @param bean
	 *            : Search Parameters
	 * @throws DatabaseException
	 */
	public List search() throws ApplicationException {
		return search(0, 0);
	}

	/**
	 * Search Station with pagination
	 * 
	 * @return list : List of Station
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
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM ST_POLICE_STATION WHERE 1=1");
							
		if (id > 0) {
				sql.append(" AND id = " + id);
			}

			if (nameOfPoliceStation != null
					&& nameOfPoliceStation.length() > 0) {
				sql.append(" AND NAME_OF_POLICE_STATION like '"
						+ nameOfPoliceStation + "%'");

			}
			if (codeOfPoliceStation != null
					&& codeOfPoliceStation.length() > 0) {
				sql.append(" AND CODE_OF_POLICE_STATION like '"
						+ codeOfPoliceStation + "%'");

			}
			if (areaCovered != null
					&& areaCovered.length() > 0) {
				sql.append(" AND AREA_COVERED like '" + areaCovered
						+ "%'");
			}
			if (contactNo != null
					&& contactNo.length() > 0) {
				sql.append(" AND CONTACT_NO like '" + contactNo
						+ "%'");
			}


		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}
		System.out.println("SQL" + sql);
		ArrayList<PoliceStationModel> list = new ArrayList<PoliceStationModel>();
		Connection conn = null;
		try {
			System.out.println("SQL is " + sql);
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				PoliceStationModel	model = new PoliceStationModel();
				model.setId(rs.getLong(1));
				model.setNameOfPoliceStation(rs.getString(2));
				model.setCodeOfPoliceStation(rs.getString(3));
				model.setAreaCovered(rs.getString(4));
				model.setContactNo(rs.getString(5));
				list.add(model);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in search Station");
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
		return nameOfPoliceStation;
	}

	@Override
	public String getTableName() {
		return "st_police_station";
	}
}
