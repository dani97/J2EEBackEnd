package io.ztech.music.delegates;

import org.json.JSONArray;
import org.json.JSONObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ztech.music.beans.FanInfoBean;
import io.ztech.music.dao.FanInfoDao;

public class FanInfoDelegate {
	private FanInfoDao infoDao;
	public FanInfoDelegate() {
		this.infoDao = new FanInfoDao();
	}

	public FanInfoDao getInfoDao() {
		return infoDao;
	}

	public void setInfoDao(FanInfoDao infoDao) {
		this.infoDao = infoDao;
	}
	
	public JSONObject insertFanInfo(JSONObject jfanInfo) {
		FanInfoBean info = new FanInfoBean(jfanInfo);
		JSONObject result = new JSONObject();
		if(infoDao.insertFanInfo(info)) {
			result.append("result", true);
		}
		else {
			result.append("result", false);
		}
		return result;
	}
	
	public JSONArray getTopFans(HttpServletRequest request,HttpServletResponse response) {
		int limit = Integer.parseInt(request.getParameter("limit"));
		return new JSONArray(this.infoDao.getTopFans(limit));
		
	}
}
