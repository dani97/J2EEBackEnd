package io.ztech.music.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Logger;

import io.ztech.music.beans.RecordsBean;
import io.ztech.music.util.DBUtil;

public class RecordsDao {
	private DBUtil dbUtil;
	public static Logger logger = Logger.getLogger(RecordsDao.class.getName());
	public RecordsDao(){
		dbUtil = new DBUtil();
	}
	public DBUtil getDbUtil() {
		return dbUtil;
	}

	public void setDbUtil(DBUtil dbUtil) {
		this.dbUtil = dbUtil;
	}

	/*
	 * insert record details
	 * 
	 */
	public boolean insertRecord(RecordsBean record) {
		String query = "insert into records (record_type,record_name,release_date,sold_copies)" + "values (?,?,?,?)";
		boolean result = false;
		try {
			PreparedStatement pStatement = dbUtil.getConnection().prepareStatement(query);
			pStatement.setString(1, record.getRecordType());
			pStatement.setString(2, record.getRecordName());
			pStatement.setObject(3, record.getReleaseDate());
			pStatement.setInt(4, record.getSoldCopies());
			if(pStatement.execute()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.warning("Could not insert record");
		}
		return result;

	}

	/*
	 * select a record
	 */

	public RecordsBean getRecord(String recordName) {
		String query = "select * from records where record_name = ?";
		try {
			PreparedStatement pStatement = dbUtil.getConnection().prepareStatement(query);
			pStatement.setString(1, recordName);
			ResultSet rs = pStatement.executeQuery();
			RecordsBean record = new RecordsBean();
			while (rs.next()) {
				record.setRecordName(rs.getString("record_name"));
				record.setRecordType(rs.getString("record_type"));
				record.setReleaseDate(rs.getObject("release_date",LocalDate.class));
				record.setSoldCopies(rs.getInt("sold_copies"));
			}
			return record;
		} catch (SQLException e) {
			logger.warning("could not get record details");
		} finally {
			dbUtil.closeConnection();
			
		}
		return null;
	}
	
	public ArrayList<RecordsBean> getAllRecords() {
		String query = "select * from records";
		ArrayList<RecordsBean> recordList = new ArrayList<RecordsBean>();
		try {
			PreparedStatement pStatement = dbUtil.getConnection().prepareStatement(query);
			ResultSet rs = pStatement.executeQuery();
			while (rs.next()) {
				RecordsBean record = new RecordsBean();
				record.setRecordName(rs.getString("record_name"));
				record.setRecordType(rs.getString("record_type"));
				record.setReleaseDate(rs.getObject("release_date",LocalDate.class));
				record.setSoldCopies(rs.getInt("sold_copies"));
				recordList.add(record);
			}
			
		} catch (SQLException e) {
			logger.warning("could not get record details");
		} finally {
			dbUtil.closeConnection();
		}
		return recordList;
	}
}
