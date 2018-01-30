package io.ztech.music.beans;

import org.json.JSONObject;

public class RatingBean {
	private int id;
	private String recordName;
	private String raterName;
	private int rating;

	public RatingBean() {

	}

	public RatingBean(JSONObject obj) {
		this.setId(obj.getInt("id"));
		this.setRaterName("raterName");
		this.setRating(obj.getInt("rating"));
		this.setRecordName(obj.getString("recordName"));
	}

	public String getRecordName() {
		return recordName;
	}

	public void setRecordName(String recordName) {
		this.recordName = recordName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRaterName() {
		return raterName;
	}

	public void setRaterName(String raterName) {
		this.raterName = raterName;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

}
