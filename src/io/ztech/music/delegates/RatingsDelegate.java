package io.ztech.music.delegates;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import io.ztech.music.dao.RatingsDao;

public class RatingsDelegate {
	private RatingsDao dao;
	public RatingsDelegate() {
		this.dao = new RatingsDao();
	}
	
	public JSONArray getRatingForRecordById(HttpServletRequest request,HttpServletResponse response) {
		return new JSONArray(dao.getRatingForRecord(Integer.parseInt(request.getParameter("recordId"))));
	}
	
	public JSONArray getRatingForRecordByName(HttpServletRequest request,HttpServletResponse response) {
		return new JSONArray(dao.getRatingForRecord(request.getParameter("recordName")));
	}
	
	public JSONArray getAllRating(HttpServletRequest request,HttpServletResponse response) {
		return new JSONArray(dao.getAllRating());
	}
}
