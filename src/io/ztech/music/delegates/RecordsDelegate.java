package io.ztech.music.delegates;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import io.ztech.music.beans.RecordsBean;
import io.ztech.music.dao.RecordsDao;
import java.util.logging.Logger;

public class RecordsDelegate {
	private RecordsDao recordDao;
	public static Logger logger = Logger.getLogger(RecordsDelegate.class.getName());
	public RecordsDelegate() {
		this.recordDao = new RecordsDao();
	}

	public RecordsDao getRecordDao() {
		return recordDao;
	}

	public void setRecordDao(RecordsDao recordDao) {
		this.recordDao = recordDao;
	}
	
	public JSONArray getAllRecords() {
		return new JSONArray(recordDao.getAllRecords());
	}
	
	public JSONObject getRecordByName(String recordName) {
		return new JSONObject(recordDao.getRecord(recordName));
	}
	
	public JSONObject insertRecord(HttpServletRequest request,HttpServletResponse response) {
		RecordsBean record = new RecordsBean();
		JSONObject result = new JSONObject();
		try {
			record.setRecordName(request.getParameter("recordName"));
			record.setReleaseDate(LocalDate.parse(request.getParameter("releaseDate")));
			record.setSoldCopies(Integer.parseInt(request.getParameter("soldCopies")));
			record.setRecordType(request.getParameter("recordType"));
			
			if(recordDao.insertRecord(record)) {
				result.put("result", true);
			}else {
				result.put("result", false);
			}
		}catch(Exception e) {
			logger.warning("couldn't parse input");
			result.put("result", false);
		}
		return result;
	}
}
