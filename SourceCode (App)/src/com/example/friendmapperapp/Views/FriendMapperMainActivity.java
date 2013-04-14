package com.example.friendmapperapp.Views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.example.friendmapperapp.R;
import com.example.friendmapperapp.Controller.Controller;

public class FriendMapperMainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friend_mapper_main);
		final CheckBox ckh =  (CheckBox) findViewById(R.id.checkBox1);
		ckh.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				Controller cont = Controller.getInstance(FriendMapperMainActivity.this);
				
				if(ckh.isChecked()){
					Log.i("chk" , "yes");
					cont.setVisibility(true);
				}else{
					Log.i("chk" , "No");
					cont.setVisibility(false);
				}
			}
		});
	
		Button panic = (Button) findViewById(R.id.panicbutton);
			panic.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Controller cont = Controller.getInstance(FriendMapperMainActivity.this);
					cont.sendPanic();
				}
			});
	

			Button set = (Button) findViewById(R.id.settingButton);
				set.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(FriendMapperMainActivity.this, Settings.class);
						startActivity(intent);
						finish();
					}
				});
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.friend_mapper_main, menu);
		return true;
	}

}
