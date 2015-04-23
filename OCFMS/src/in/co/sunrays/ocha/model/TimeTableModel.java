package in.co.sunrays.ocha.model;

import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.exception.DatabaseException;
import in.co.sunrays.util.JDBCDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.Date;

import org.apache.log4j.Logger;

public class TimeTableModel {
	private static Logger log = Logger.getLogger(TimeTableModel.class);
	private long id=0;
	private String sem;
	private byte[] fileName;
	public String getSem() {
		return sem;
	}
	public byte[] getFileName() {
		return fileName;
	}
	public void setFileName(byte[] fileName) {
		this.fileName = fileName;
	}
	public void setSem(String sem) {
		this.sem = sem;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement("SELECT MAX(ID) FROM ST_TIMETABLE");
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
	
	public long add() throws ApplicationException {

		log.debug("Model add Started");

		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			conn.setAutoCommit(false); // Begin transaction

			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO ST_TIMETABLE VALUES(?,?,?)");
			pstmt.setLong(1, pk);
			pstmt.setString(2, sem);
			pstmt.setBytes(3, fileName);
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
					"Exception : Exception in add College");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}

	
}
