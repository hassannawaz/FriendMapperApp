package com.example.friendmapperapp.Views;

import com.example.friendmapperapp.R;
import com.example.friendmapperapp.Controller.Controller;
import com.example.friendmapperapp.R.layout;
import com.example.friendmapperapp.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;

public class MainActivity extends Activity implements Runnable {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new Thread(this).start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void run() {
		
		
		/* Shared Preferences and getting whether registered or not */
		
		SharedPreferences sharedPref = MainActivity.this.getSharedPreferences(
		        "friend_mapper_preferences_registration", Context.MODE_PRIVATE);
		
		String result = sharedPref.getString("Registered", "false");
			if(result.equalsIgnoreCase("true")){
				Controller cont = Controller.getInstance(MainActivity.this);
				cont.getFriendsFromServer();
			}else{
				try {
					Thread.sleep(2000);
			} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
		startActivity(intent);
		finish();
	}
}
