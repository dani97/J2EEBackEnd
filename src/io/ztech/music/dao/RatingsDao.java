package io.ztech.music.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

import io.ztech.music.beans.RatingBean;
import io.ztech.music.util.DBUtil;

public class RatingsDao {
	private DBUtil dbUtil;
	private static Logger logger = Logger.getLogger(RatingsDao.class.getName());
	public RatingsDao() {
		this.dbUtil = new DBUtil();
	}
	
	public ArrayList<RatingBean> getRatingForRecord(int recordId) {
		String query = "SELECT ratings.rating_id,ratings.ratings_100,rater.rater_name,records.record_name from  "
				+ "ratings join rater on rater.rater_id = ratings.rater_id join records "
				+ "on records.id = ratings.record_id where ratings.record_id=?;";
		System.out.println(query);
		try {
			PreparedStatement stmt = dbUtil.getConnection().prepareStatement(query);
			stmt.setInt(1, recordId);
			ResultSet rs = stmt.executeQuery();
			ArrayList<RatingBean> ratings = new ArrayList<RatingBean>();
			while(rs.next()) {
				RatingBean bean = new RatingBean();
				bean.setId(rs.getInt("ratings.rating_id"));
				bean.setRaterName(rs.getString("rater.rater_name"));
				bean.setRecordName(rs.getString("records.record_name"));
				bean.setRating(rs.getInt("ratings.ratings_100"));
				ratings.add(bean);
			}
			return ratings;
		}catch(SQLException e) {
			logger.warning("error in communication");
			logger.warning(e.getMessage());
		}finally {
			dbUtil.closeConnection();
		}
		return null;
	}
	
	public ArrayList<RatingBean> getRatingForRecord(String recordName) {
		String query = "SELECT ratings.rating_id,ratings.ratings_100,rater.rater_name,records.record_name "
				+ " from  ratings join rater on rater.rater_id = ratings.rater_id "
				+ "join records on records.id = ratings.record_id where records.record_name=?;";
		try {
			PreparedStatement stmt = dbUtil.getConnection().prepareStatement(query);
			stmt.setString(1, recordName);
			ResultSet rs = stmt.executeQuery();
			ArrayList<RatingBean> ratings = new ArrayList<RatingBean>();
			while(rs.next()) {
				RatingBean bean = new RatingBean();
				bean.setId(rs.getInt("ratings.rating_id"));
				bean.setRaterName(rs.getString("rater.rater_name"));
				bean.setRecordName(rs.getString("records.record_name"));
				bean.setRating(rs.getInt("ratings.ratings_100"));
				ratings.add(bean);
			}
			return ratings;
		}catch(SQLException e) {
			logger.warning("error in communication");
		}finally {
			dbUtil.closeConnection();
		}
		return null;
	}
	 
	public ArrayList<RatingBean> getAllRating() {
		String query = "SELECT ratings.rating_id,ratings.ratings_100,rater.rater_name,records.record_name "
				+ " from  ratings join rater on rater.rater_id = ratings.rater_id "
				+ "join records on records.id = ratings.record_id;";
		try {
			PreparedStatement stmt = dbUtil.getConnection().prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			ArrayList<RatingBean> ratings = new ArrayList<RatingBean>();
			while(rs.next()) {
				RatingBean bean = new RatingBean();
				bean.setId(rs.getInt("ratings.rating_id"));
				bean.setRaterName(rs.getString("rater.rater_name"));
				bean.setRecordName(rs.getString("records.record_name"));
				bean.setRating(rs.getInt("ratings.ratings_100"));
				ratings.add(bean);
			}
			return ratings;
		}catch(SQLException e) {
			logger.warning("error in communication");
		}finally {
			dbUtil.closeConnection();
		}
		return null;
	}
	
}
