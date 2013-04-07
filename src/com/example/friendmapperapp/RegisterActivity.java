package com.example.friendmapperapp;

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
import org.json.JSONObject;

import android.os.Bundle;
import android.os.RemoteException;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class RegisterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		/* Getting Current Mobile Number */
		TelephonyManager phoneManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String phoneNumber = phoneManager.getLine1Number();
		TextView PhoneNumberText = (TextView) findViewById(R.id.Phone_number);
		PhoneNumberText.setText(phoneNumber);

		if (!PhoneNumberText.getText().equals(""))
			PhoneNumberText.setEnabled(isRestricted());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}

	public void SubmitRegistration(View view) throws InterruptedException {

		/* Shared Preferences */
		Context context = (Activity) this;
		SharedPreferences sharedPref = context.getSharedPreferences(
				"friend_mapper_preferences_registration", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();

		/* Text view and addition to phone database */
		TextView NameText = (TextView) findViewById(R.id.Name);
		TextView PhoneNumberText = (TextView) findViewById(R.id.Phone_number);
		TextView error = (TextView) findViewById(R.id.ErrorRegistration);

		ProgressBar Loading_submit = (ProgressBar) findViewById(R.id.submitLoading);

		/* Checking if Password Valid or not and add to the database accordingly */
		if (PhoneNumberText.getText().toString() == "") {
			error.setVisibility(TextView.VISIBLE);
		} else {
			// Creating a JSON Object
			JSONObject obj = new JSONObject();
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

			try {
				nameValuePairs.add(new BasicNameValuePair("Name", NameText
						.getText().toString()));
				nameValuePairs.add(new BasicNameValuePair("PhoneNumber",
						PhoneNumberText.getText().toString()));
				nameValuePairs
						.add(new BasicNameValuePair("Registered", "true"));
				nameValuePairs
						.add(new BasicNameValuePair("Visibility", "false"));
				nameValuePairs.add(new BasicNameValuePair("Latitude", "0"));
				nameValuePairs.add(new BasicNameValuePair("Longitude", "0"));
			} catch (Exception e) {
				e.printStackTrace();
			}

			// Forwarding Request To Server
			try {
				sendDatatoServer(
						"http://10.0.2.2:8080/friendmapper/register.php",
						nameValuePairs);

			} catch (Throwable e) {
				e.printStackTrace();
			}

			// Editing Shared Preferences
			editor.putString("Registered", "true");
			editor.putString("Name", NameText.getText().toString());
			editor.putString("PhoneNumber", PhoneNumberText.getText()
					.toString());
			editor.putString("Visiblity", "false");
			editor.putFloat("Latitude", 0);
			editor.putFloat("Longitude", 0);

			editor.commit();

			Loading_submit.setVisibility(ProgressBar.VISIBLE);
			Thread.sleep(3000);

			Intent intent = new Intent(this, FriendMapperMainActivity.class);
			startActivity(intent);
			finish();
		}

	}

	public void sendDatatoServer(final String url,
			final List<NameValuePair> nameValuePairs) {
		new Thread() {

			public void run() {
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost(url);

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
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();

	}

}
