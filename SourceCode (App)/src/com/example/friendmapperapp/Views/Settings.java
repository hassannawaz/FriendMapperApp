package com.example.friendmapperapp.Views;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.example.friendmapperapp.R;

public class Settings  extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.friend_mapper_main, menu);
		return true;
	}

}
