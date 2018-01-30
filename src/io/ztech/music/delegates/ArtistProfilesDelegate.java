package io.ztech.music.delegates;

import org.json.JSONArray;
import org.json.JSONObject;

import io.ztech.music.beans.ArtistProfilesBean;
import io.ztech.music.dao.ArtistProfilesDao;

public class ArtistProfilesDelegate {
	private ArtistProfilesDao profileDao;
	
	public ArtistProfilesDelegate() {
		this.profileDao = new ArtistProfilesDao();
	}

	public ArtistProfilesDao getProfileDao() {
		return profileDao;
	}

	public void setProfileDao(ArtistProfilesDao profileDao) {
		this.profileDao = profileDao;
	}
	
	public JSONObject insertArtistProfile(JSONObject jartist) {
		ArtistProfilesBean bean = new ArtistProfilesBean(jartist);
		JSONObject result = new JSONObject();
		if(profileDao.insertArtistProfile(bean)) {
			result.append("result", "success");
		}else {
			result.append("result", "failure");
		}
		return result;
	}
	
	public JSONArray getAllArtistProfiles() {
		return  new JSONArray(profileDao.getAllArtistProfiles());
	}
	
	public JSONObject getArtistProfile(int id) {
		return new JSONObject(profileDao.getArtistProfile(id));
	}
}
