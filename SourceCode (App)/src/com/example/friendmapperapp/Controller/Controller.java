package com.example.friendmapperapp.Controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.CursorJoiner.Result;
import android.os.AsyncTask;

import com.example.friendmapperapp.Models.Member;
import com.example.friendmapperapp.utils.Constants;
import com.example.friendmapperapp.utils.JSONParser;

public class Controller {

	public ArrayList<Member> friends = new ArrayList<Member>();
	static Controller instance = null;
	Context c;

	private Controller(Context ctx) {
		this.c = ctx;

	}

	public static Controller getInstance(Context ctx) {
		if (instance == null) {
			instance = new Controller(ctx);
		}
		return instance;
	}

	public void getFriendsFromServer() {

		Thread th = new Thread() {
			public void run() {
				JSONParser jParser = new JSONParser();

				// getting JSON string from URL
				JSONObject json = jParser.getJSONFromUrl(Constants.url);

				try {
					// Getting Array of Contacts
					JSONArray ja = json.getJSONArray(Constants.TAG_Update);
					ArrayList<Member> fr_list = new ArrayList<Member>();
					// looping through All Contacts
					for (int i = 0; i < ja.length(); i++) {
						JSONObject c = ja.getJSONObject(i);
						String id = c.getString(Constants.TAG_ID);
						String name = c.getString(Constants.TAG_NAME);
						String ph = c.getString(Constants.TAG_PHONE);
						String lat = c.getString(Constants.TAG_Lat);
						String lng = c.getString(Constants.TAG_Lng);
						String visi = c.getString(Constants.TAG_Visibility);
						Member m = new Member(Integer.parseInt(id), name, ph,
								"1", Integer.parseInt(visi),
								Float.parseFloat(lat), Float.parseFloat(lng));
						fr_list.add(m);
					}
					if (fr_list.size() > 0) {
						friends.clear();
						friends.addAll(fr_list);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		};

		th.start();
		try {
			th.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setVisibility(final boolean chk) {

		Thread th = new Thread() {
			public void run() {
				SharedPreferences sharedPref = c.getSharedPreferences(
						"friend_mapper_preferences_registration",
						Context.MODE_PRIVATE);

				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs.add(new BasicNameValuePair("ID", sharedPref
						.getString("PhoneNumber", "0")));
				nameValuePairs.add(new BasicNameValuePair("visibility", String
						.valueOf(chk)));
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost(Constants.setvis);

				try {
					httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// Execute HTTP Post Request
				HttpResponse response = null;
				try {
					response = httpclient.execute(httppost);
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		};

		th.start();

	}

	
	
	
	

	public void sendPanic() {

		Thread th = new Thread() {
			public void run() {
				SharedPreferences sharedPref = c.getSharedPreferences(
						"friend_mapper_preferences_registration",
						Context.MODE_PRIVATE);

				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs.add(new BasicNameValuePair("ID", sharedPref
						.getString("PhoneNumber", "0")));
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost(Constants.sendpanic);

				try {
					httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// Execute HTTP Post Request
				HttpResponse response = null;
				try {
					response = httpclient.execute(httppost);
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		};

		th.start();

	}

}
