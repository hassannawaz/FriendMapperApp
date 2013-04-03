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

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.RemoteException;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// SingleTon
	static DatabaseHandler DATABASE_HANDLER = null;

	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "friend_mapper_db";

	// Contacts table name
	private static final String TABLE_NAME = "Friend_table";

	// Contacts Table Columns names
	private static final String KEY_ID = "ID";
	private static final String KEY_NAME = "Name";
	private static final String KEY_PH_NO = "PhoneNumber";
	private static final String KEY_REG = "Registered";
	private static final String KEY_VIS = "Visibility";
	private static final String KEY_PAN = "Panic";
	private static final String KEY_LAT = "Latitude";
	private static final String KEY_LONG = "Longitude";

	public static DatabaseHandler getInstance(Context context_)
			throws RemoteException {
		if (DATABASE_HANDLER == null) {
			DATABASE_HANDLER = new DatabaseHandler(
					context_.getApplicationContext());
		}
		return DATABASE_HANDLER;
	}

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_MEMBERS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
				+ KEY_PH_NO + " TEXT," + KEY_REG + " TEXT," + KEY_VIS
				+ " INTEGER," + KEY_PAN + " INTEGER," + KEY_LAT + " FLOAT,"
				+ KEY_LONG + " FLOAT" + ")";
		db.execSQL(CREATE_MEMBERS_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

		// Create tables again
		onCreate(db);
	}

	// Adding new member
	public void addContact(Member Member_) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_LONG, Member_.getLongitude()); // Longitude
		values.put(KEY_LAT, Member_.getLatitude()); // Latitude
		values.put(KEY_PAN, Member_.getPanic()); // Panic
		values.put(KEY_VIS, Member_.getVisibility()); // Visibility
		values.put(KEY_REG, Member_.getRegistered()); // Registered
		values.put(KEY_NAME, Member_.getName()); // Name
		values.put(KEY_PH_NO, Member_.getPhoneNumber()); // Phone Number

		// Inserting Row
		db.insert(TABLE_NAME, null, values);
		db.close(); // Closing database connection
	}

	// Getting a new Member using PhoneNumber
	public Member getMember(String Phone_) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_NAME, new String[] { KEY_ID, KEY_NAME,
				KEY_PH_NO, KEY_REG, KEY_VIS, KEY_PAN, KEY_LAT, KEY_LONG },
				KEY_PH_NO + "=?", new String[] { Phone_ }, null, null, null,
				null);
		if (cursor != null)
			cursor.moveToFirst();

		Member contact = new Member(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getString(2), cursor.getString(3),
				Integer.parseInt(cursor.getString(4)), Integer.parseInt(cursor
						.getString(5)), Integer.parseInt(cursor.getString(6)),
				Integer.parseInt(cursor.getString(7)));
		// return contact
		return contact;
	}

	// Getting All Contacts
	public List<Member> getAllMembers() {
		List<Member> memberList = new ArrayList<Member>();

		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_NAME;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Member member_ = new Member();
				member_.setID(Integer.parseInt(cursor.getString(0)));
				member_.setName(cursor.getString(1));
				member_.setPhoneNumber(cursor.getString(2));
				member_.setRegistered(cursor.getString(3));
				member_.setVisibility(Integer.parseInt(cursor.getString(4)));
				member_.setPanic(Integer.parseInt(cursor.getString(5)));
				member_.setLatitude(Float.parseFloat(cursor.getString(6)));
				member_.setLongitude(Float.parseFloat(cursor.getString(7)));

				// Adding contact to list
				memberList.add(member_);
			} while (cursor.moveToNext());
		}

		// return contact list
		return memberList;
	}

	// Getting Members Count
	public int getMembersCount() {
		String countQuery = "SELECT  * FROM " + TABLE_NAME;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		// return count
		return cursor.getCount();
	}

	// Updating single Member
	public int updateMember(Member Member_) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_LONG, Member_.getLongitude()); // Longitude
		values.put(KEY_LAT, Member_.getLatitude()); // Latitude
		values.put(KEY_PAN, Member_.getPanic()); // Panic
		values.put(KEY_VIS, Member_.getVisibility()); // Visibility
		values.put(KEY_REG, Member_.getRegistered()); // Registered
		values.put(KEY_NAME, Member_.getName()); // Name

		// updating row
		return db.update(TABLE_NAME, values, KEY_PH_NO + " = ?",
				new String[] { Member_.getPhoneNumber() });
	}

	// Deleting single contact
	public void deleteMember(Member Member_) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_NAME, KEY_PH_NO + " = ?",
				new String[] { Member_.getPhoneNumber() });
		db.close();
	}

	public void sendDatatoServer(final String url, final List<NameValuePair> nameValuePairs) {
		new Thread() {

			public void run() {
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost(url);

				Log.e("stst", "in");
				try {
					httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Log.e("stst", "in");
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
