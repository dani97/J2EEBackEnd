package io.ztech.music.beans;

import org.json.JSONObject;

public class FanInfoBean {
	private String name;
	private String category;
	private String country;
	private String email;
	private int points;
	
	public FanInfoBean() {
		
	}
	
	public FanInfoBean(JSONObject fanInfo) {
		this.setCategory(fanInfo.getString("category"));
		this.setCountry(fanInfo.getString("country"));
		this.setEmail(fanInfo.getString("email"));
		this.setName(fanInfo.getString("name"));
		this.setPoints(fanInfo.getInt("points"));
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
}
