package io.ztech.music.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneOptions;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class CommentsDao {
	private static Logger logger = Logger.getLogger(CommentsDao.class.getName());
	public JSONArray getInsightsFromTwitter(String profile,String album) {
		String comments = "";
	    ConfigurationBuilder cb = new ConfigurationBuilder();
	    try {
			InputStream input = getClass().getResourceAsStream("/io/ztech/music/properties/twitterconfig.properties");
			Properties prop = new Properties();
			prop.load(input);
			cb.setDebugEnabled(true)
		      .setOAuthConsumerKey(prop.getProperty("ConsumerKey"))
		      .setOAuthConsumerSecret(prop.getProperty("ConsumerSecret"))
		      .setOAuthAccessToken(prop.getProperty("AccessToken"))
		      .setOAuthAccessTokenSecret(prop.getProperty("AccessTokenSecret"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			logger.warning("property file missing");
		} catch(IOException e) {
			logger.warning("error loading property file");
		} catch(Exception e) {
			e.printStackTrace();
		}
	    
	    Twitter twitter = new TwitterFactory(cb.build()).getInstance();
	    Query query = new Query("@"+profile+" AND #"+album+" -filter:retweets");
	    int numberOfTweets = 20;
	    long lastID = Long.MAX_VALUE;
	    ArrayList<Status> tweets = new ArrayList<Status>();
	    while (tweets.size () < numberOfTweets) {
	      if (numberOfTweets - tweets.size() > 100)
	        query.setCount(100);
	      else 
	        query.setCount(numberOfTweets - tweets.size());
	      try {
	        QueryResult result = twitter.search(query);
	        tweets.addAll(result.getTweets());
	        logger.info("Gathered " + tweets.size() + " tweets"+"\n");
	        for (Status t: tweets) 
	          if(t.getId() < lastID) 
	              lastID = t.getId();

	      }

	      catch (TwitterException te) {
	        logger.info("Couldn't connect: " + te);
	      }; 
	      query.setMaxId(lastID-1);
	    }

	    for (int i = 0; i < tweets.size(); i++) {
	      Status t = (Status) tweets.get(i);

	     // GeoLocation loc = t.getGeoLocation();
	      String msg = t.getText();
	      //String time = "";
	      //if (loc!=null) {
	        //Double lat = t.getGeoLocation().getLatitude();
	        //Double lon = t.getGeoLocation().getLongitude();*/
	       comments+=msg;
	      }
	    logger.info(comments);
	    ToneAnalyzer service = null;
	    try {
			InputStream input = getClass().getResourceAsStream("/io/ztech/music/properties/watsonconfig.properties");
			Properties prop = new Properties();
			prop.load(input);
		    service = new ToneAnalyzer(prop.getProperty("version"));
			service.setUsernameAndPassword(prop.getProperty("username"), prop.getProperty("password"));
	    } catch(FileNotFoundException e) {
	    	
	    } catch(IOException e) {
	    	
	    } catch(Exception e) {
	    	
	    }
		ToneOptions toneOptions = new ToneOptions.Builder().html(comments).build();
		ToneAnalysis tone = service.tone(toneOptions).execute();
		JSONObject tonejson = new JSONObject(tone);
		JSONArray emotionalTones = tonejson.getJSONObject("documentTone").getJSONArray("toneCategories").getJSONObject(0).getJSONArray("tones");
		for(int j = 0 ; j <emotionalTones.length(); j++ ) {
			JSONObject emotion = emotionalTones.getJSONObject(j);
			logger.info("emotion name - "+emotion.getString("toneName")+" "+emotion.getDouble("score")*100+"%");
		}
	    return emotionalTones;
	}
}
