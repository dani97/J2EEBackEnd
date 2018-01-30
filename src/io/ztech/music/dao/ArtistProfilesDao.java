package io.ztech.music.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;

import io.ztech.music.beans.ArtistProfilesBean;
import io.ztech.music.util.DBUtil;

public class ArtistProfilesDao {
	private DBUtil dbUtil;
	public static Logger logger = Logger.getLogger(ArtistProfilesDao.class.getName());
	
	public ArtistProfilesDao(){
		dbUtil = new DBUtil();
	}
	public DBUtil getDbUtil() {
		return dbUtil;
	}

	public void setDbUtil( DBUtil dbUtil) {
		this.dbUtil = dbUtil;
	}

	/*
	 * insert artist profile details
	 */
	public boolean insertArtistProfile(ArtistProfilesBean bean) {
		String query = "insert into social_media (media_name) values (?)";
		Connection connection = dbUtil.getConnection();
		boolean result = false;
		try {
			PreparedStatement queryStatement1 = connection.prepareStatement(query);
			queryStatement1.setString(1, bean.getMediaName());
			queryStatement1.execute();
			Statement stmt = dbUtil.getConnection().createStatement();
			int mediaId = -1;
			ResultSet rs = stmt.executeQuery("SELECT `AUTO_INCREMENT`\r\n" + "FROM  INFORMATION_SCHEMA.TABLES\r\n"
					+ "WHERE TABLE_SCHEMA = 'music'\r\n" + "AND   TABLE_NAME   = 'social_media';");
			while (rs.next()) {
				mediaId = rs.getInt(1);
			}
			String query2 = "insert into social_media_profiles " + "(user_profile,media_id,user_url,fan_count)"
					+ "values (?,?,?,?)";
			PreparedStatement queryStatement2 = connection.prepareStatement(query2);
			queryStatement2.setString(1, bean.getProfileId());
			queryStatement2.setInt(2, mediaId - 1);
			queryStatement2.setString(3, bean.getUserUrl());
			queryStatement2.setInt(4, bean.getFanCount());
			queryStatement2.execute();
			result=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.warning("Data Error");
		}catch (Exception e){
			logger.warning("Error");
		}finally {
			dbUtil.closeConnection();
		}
		return result;
	}
	
	/*get All Profiles of artist as a list*/
	
	public ArrayList<ArtistProfilesBean> getAllArtistProfiles() {
		String query = "select * from social_media join social_media_profiles "
				+ "on social_media.media_id = social_media_profiles.media_id";
		try {
			Statement stmt = this.getDbUtil().getConnection().createStatement();
			ResultSet results = stmt.executeQuery(query);
			ArrayList<ArtistProfilesBean> artistList = new ArrayList<ArtistProfilesBean>();
			while(results.next()) {
				ArtistProfilesBean artist = new ArtistProfilesBean();
				artist.setMediaName(results.getString("social_media.media_name"));
				artist.setProfileId(results.getString("social_media_profiles.user_profile"));
				artist.setId(results.getInt("social_media_profiles.id"));
				artist.setUserUrl(results.getString("social_media_profiles.user_url"));
				artist.setFanCount(results.getInt("social_media_profiles.fan_count"));
				artistList.add(artist);
			}
			return artistList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.warning("couldn't fetch artist profiles");
		}
		return null;
	}
	
	/*get artistprofiles by ID*/
	public ArtistProfilesBean getArtistProfile(int id) {
		String query = "select * from social_media join social_media_profiles "
				+ "on social_media.media_id = social_media_profiles.media_id where social_media_profiles.id = ?";
		try {
			PreparedStatement stmt = this.getDbUtil().getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			ResultSet results = stmt.executeQuery();
			ArtistProfilesBean artist = new ArtistProfilesBean();			
			while(results.next()) {
				artist.setMediaName(results.getString("media_name"));
				artist.setProfileId(results.getString("user_profile"));
				artist.setId(results.getInt("id"));
				artist.setUserUrl(results.getString("user_url"));
				artist.setFanCount(results.getInt("fan_count"));
			}
			return artist;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.warning("couldn't fetch artist profile by id");
			e.printStackTrace();
		}
		return null;
		
	}
}
