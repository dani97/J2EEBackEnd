package io.ztech.music.beans;

import java.time.LocalDate;

public class FanTrackBean {
	private int fanCount;
	private LocalDate queryDate;
	private String mediaName;
	private String profileId;

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

	public int getFanCount() {
		return fanCount;
	}

	public void setFanCount(int fanCount) {
		this.fanCount = fanCount;
	}

	public LocalDate getQueryDate() {
		return queryDate;
	}

	public void setQueryDate(LocalDate queryDate) {
		this.queryDate = queryDate;
	}

}
