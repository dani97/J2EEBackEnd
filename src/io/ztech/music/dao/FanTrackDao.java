package io.ztech.music.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Logger;

import io.ztech.music.beans.FanTrackBean;
import io.ztech.music.util.DBUtil;

public class FanTrackDao {
	
	private DBUtil dbUtil;
	public static Logger logger = Logger.getLogger(FanInfoDao.class.getName());
	public FanTrackDao(){
		dbUtil = new DBUtil();
	}
	public DBUtil getDbUtil() {
		return dbUtil;
	}

	public void setDbUtil(DBUtil dbUtil) {
		this.dbUtil = dbUtil;
	}
	public ArrayList<FanTrackBean> getFanCounts(int profileId) {
		String query = "SELECT * FROM fans_tracker join social_media_profiles "
				+ " on fans_tracker.user_media_id = social_media_profiles.id "
				+ " join social_media on social_media_profiles.media_id "
				+ "= social_media.media_id where social_media_profiles.id =?;";
		try {
			PreparedStatement pStatement = dbUtil.getConnection().prepareStatement(query);
			pStatement.setInt(1,profileId);
			ResultSet rs = pStatement.executeQuery();
			ArrayList<FanTrackBean> fanTrackList = new ArrayList<FanTrackBean>();
			while(rs.next()) {
				FanTrackBean fanTrack = new FanTrackBean();
				fanTrack.setFanCount(rs.getInt("fans_tracker.fans_count"));
				fanTrack.setMediaName(rs.getString("social_media.media_name"));
				fanTrack.setProfileId(rs.getString("social_media_profiles.user_profile"));
				fanTrack.setQueryDate(rs.getObject("fans_tracker.query_date",LocalDate.class));
				fanTrackList.add(fanTrack);
			}
			return fanTrackList;
		}catch(SQLException e) {
			logger.warning("could not extract details");
		}finally {
			dbUtil.closeConnection();
		}
		return null;
	}
	
	public ArrayList<FanTrackBean> getAllFanCounts() {
		String query = "SELECT * FROM fans_tracker join social_media_profiles "
				+ " on fans_tracker.user_media_id = social_media_profiles.id "
				+ " join social_media on social_media_profiles.media_id "
				+ "= social_media.media_id;";
		try {
			PreparedStatement pStatement = dbUtil.getConnection().prepareStatement(query);
			ResultSet rs = pStatement.executeQuery();
			ArrayList<FanTrackBean> fanTrackList = new ArrayList<FanTrackBean>();
			while(rs.next()) {
				FanTrackBean fanTrack = new FanTrackBean();
				fanTrack.setFanCount(rs.getInt("fans_tracker.fans_count"));
				fanTrack.setMediaName(rs.getString("social_media.media_name"));
				fanTrack.setProfileId(rs.getString("social_media_profiles.user_profile"));
				fanTrack.setQueryDate(rs.getObject("fans_tracker.query_date",LocalDate.class));
				fanTrackList.add(fanTrack);
			}
			return fanTrackList;
		}catch(SQLException e) {
			logger.warning("could not extract details");
		}finally {
			dbUtil.closeConnection();
		}
		return null;
	}
}
