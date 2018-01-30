package io.ztech.music.delegates;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import io.ztech.music.dao.CommentsDao;

public class CommentsDelegate {
	private CommentsDao dao;
	public CommentsDelegate() {
		this.dao = new CommentsDao();
	}
	
	public JSONArray getInsightsFromTwitter(HttpServletRequest request,HttpServletResponse response) {
		String profile = request.getParameter("profile");
		String album = request.getParameter("album");
		return this.dao.getInsightsFromTwitter(profile,album);
	}
}
