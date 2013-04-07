package com.example.friendmapperapp;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.http.NameValuePair;
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
		DatabaseHandler temp_handler = null;
		
		/* Creating a SQLLITE Database */
		try {
			temp_handler = DatabaseHandler.getInstance(this);
		} catch (RemoteException e1) {
			e1.printStackTrace();
			Log.v("Exception", e1.getMessage());
		}
		
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
			
		/* Creating a PIN number */
		String temp_pin = UUID.randomUUID().toString();
		String pin_ = temp_pin.substring(0,5);
		
		// Creating a JSON Object 
		JSONObject obj=new JSONObject();
		 List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		       
			try {
				nameValuePairs.add(new BasicNameValuePair("Name", NameText.getText().toString()));
				nameValuePairs.add(new BasicNameValuePair("PhoneNumber", PhoneNumberText.getText().toString()));
				nameValuePairs.add(new BasicNameValuePair("PIN", pin_));
				nameValuePairs.add(new BasicNameValuePair("Registered", "true"));
				nameValuePairs.add(new BasicNameValuePair("Visibility", "false"));
				nameValuePairs.add(new BasicNameValuePair("Latitude", "0"));
				nameValuePairs.add(new BasicNameValuePair("Longitude", "0"));
			} catch (Exception e) {
				e.printStackTrace();
			}
	
			// Forwarding Request To Server
			try {
				temp_handler.sendDatatoServer("http://10.0.2.2:8080/friendmapper/register.php", nameValuePairs);
			
			} catch (Throwable e) {
				e.printStackTrace();
			}

			// Editing Shared Preferences
			editor.putString("Registered", "true");
			editor.putString("Name", NameText.getText().toString());
			editor.putString("PhoneNumber", PhoneNumberText.getText().toString());
			editor.putString("PIN", pin_);
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

}
