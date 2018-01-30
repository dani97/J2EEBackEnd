package io.ztech.music.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

import io.ztech.music.beans.FanInfoBean;
import io.ztech.music.util.DBUtil;


public class FanInfoDao {
	/*
	 * Insert Fan Information
	 */
	private DBUtil dbUtil;
	public static Logger logger = Logger.getLogger(FanInfoDao.class.getName());
	public FanInfoDao(){
		dbUtil = new DBUtil();
	}
	public DBUtil getDbUtil() {
		return dbUtil;
	}

	public void setDbUtil(DBUtil dbUtil) {
		this.dbUtil = dbUtil;
	}

	public boolean insertFanInfo(FanInfoBean fanInfo) {
		boolean result = false;
		String query = "insert into fan_info" + "(fan_name,category,country,email,points)" + "values(?,?,?,?,?)";
		try {
			PreparedStatement pStatement = dbUtil.getConnection().prepareStatement(query);
			pStatement.setString(1, fanInfo.getName());
			pStatement.setString(2, fanInfo.getCategory());
			pStatement.setString(3, fanInfo.getCountry());
			pStatement.setString(4, fanInfo.getEmail());
			pStatement.setInt(5, fanInfo.getPoints());
			if(pStatement.execute()) {
				result = true;
			}
			pStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.warning("couldn't insert fan Info");
		} finally {
			dbUtil.closeConnection();
		}
		return result;
	}

	/*
	 * Update points for fans
	 */
	public void updatePoints(String email, int points) {
		String query = "update table fan_info set points = ? where email = ?";
		try {
			PreparedStatement pStatement = dbUtil.getConnection().prepareStatement(query);
			pStatement.setString(1, email);
			pStatement.setInt(2, points);
			pStatement.execute();
		} catch (SQLException e) {
			logger.warning("couldn't update points for" + email);
		} finally {
			dbUtil.closeConnection();
		}
	}
	
	/*
	 * get top  fans
	 */
	
	public ArrayList<FanInfoBean> getTopFans(int limit) {
		String query = "select * from fan_info order by points desc limit ?";
		try {
			PreparedStatement pStatement = dbUtil.getConnection().prepareStatement(query);
			pStatement.setInt(1, limit);
			ResultSet rset = pStatement.executeQuery();
			ArrayList<FanInfoBean> fanlist = new ArrayList<FanInfoBean>();
			while(rset.next()) {
				FanInfoBean fan = new FanInfoBean();
				fan.setName(rset.getString("fan_name"));
				fan.setCategory(rset.getString("category"));
				fan.setCountry(rset.getString("country"));
				fan.setEmail(rset.getString("email"));
				fan.setPoints(rset.getInt("points"));
				fanlist.add(fan);
			}
			logger.info(Integer.toString(fanlist.size()));
			return fanlist;
		}catch(SQLException e) {
			logger.warning("could not fetch fanlist");
		}finally {
			dbUtil.closeConnection();
		}
		return null;
	}
}
