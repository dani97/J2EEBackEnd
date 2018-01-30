package io.ztech.music.beans;

import org.json.JSONObject;

public class ArtistProfilesBean {
	int id;
	String profileId;
	String mediaName;
	String userUrl;
	int fanCount;
	
	public ArtistProfilesBean() {
		
	}
	
	public ArtistProfilesBean(JSONObject artist){
		this.id = artist.getInt("id");
		this.profileId = artist.getString("profileId");
		this.mediaName = artist.getString("mediaName");
		this.userUrl = artist.getString("userUrl");
		this.fanCount = artist.getInt("fanCount");
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProfileId() {
		return profileId;
	}
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
	public String getMediaName() {
		return mediaName;
	}
	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}
	public String getUserUrl() {
		return userUrl;
	}
	public void setUserUrl(String userUrl) {
		this.userUrl = userUrl;
	}
	public int getFanCount() {
		return fanCount;
	}
	public void setFanCount(int fanCount) {
		this.fanCount = fanCount;
	}
	
	
}
