package io.ztech.music.beans;

import java.time.LocalDate;

import org.json.JSONObject;


public class RecordsBean {
	private String recordName;
	private String recordType;
	private LocalDate releaseDate;
	private int soldCopies;
	
	public RecordsBean() {
		
	}
	
	public RecordsBean(JSONObject record) {
		this.setRecordName(record.getString("recordName"));
		this.setRecordType(record.getString("recordType"));
		this.setSoldCopies(record.getInt("soldCopies"));
		this.setReleaseDate((LocalDate)record.get("releaseDate"));
	}
	public String getRecordName() {
		return recordName;
	}

	public void setRecordName(String recordName) {
		this.recordName = recordName;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public int getSoldCopies() {
		return soldCopies;
	}

	public void setSoldCopies(int soldCopies) {
		this.soldCopies = soldCopies;
	}
}
