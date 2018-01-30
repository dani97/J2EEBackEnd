package io.ztech.music.delegates;

import org.json.JSONArray;

import io.ztech.music.dao.FanTrackDao;

public class FanTrackDelegate {
	private FanTrackDao fanDao;
	public FanTrackDelegate(){
		this.fanDao = new FanTrackDao();
	}
	
	public FanTrackDao getFanDao() {
		return fanDao;
	}

	public void setFanDao(FanTrackDao fanDao) {
		this.fanDao = fanDao;
	}
	
	public JSONArray getFanCounts(int profileId) {
		JSONArray fanCount = new JSONArray(fanDao.getFanCounts(profileId));
		return fanCount;
	}
	
	public JSONArray getAllFanCounts() {
		return new JSONArray(fanDao.getAllFanCounts());
	}
}
