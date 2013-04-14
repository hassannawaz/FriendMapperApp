package com.example.friendmapperapp.Models;

public class Member {

	//private variables
	private String Registered;
	private String Name;
	private String PhoneNumber;
	private int Visibility;
	private float Latitude;
	private float Longitude;
	private int ID;
    
    // Empty constructor
    public Member(){
    }

    // constructor
    public Member(int ID, String Name, String PhoneNumber, String Registered, 
    		int Visibility, float Latitude, float Longitude){
    	this.Registered = Registered;
    	this.Name = Name;
    	this.PhoneNumber = PhoneNumber;
    	this.Visibility = Visibility;
    	this.Latitude = Latitude;
    	this.Longitude = Longitude;
    	this.ID = ID;
    }
    
    // Get ID
    public int getID() {
    	return this.ID;
    }
    
    // Set ID
    public void setID(int ID_) {
    	this.ID = ID_;
    }
    
    // constructor
    public Member(String Registered, String Name, String PhoneNumber){
    	this.Registered = Registered;
    	this.Name = Name;
    	this.PhoneNumber = PhoneNumber;
    }

    // Set Longitude
    public void setLongitude(float longitude) {
    	this.Longitude  = longitude;
    }
    
    // Get Longitude
    public float getLongitude() {
    	return this.Longitude;
    }
    
    // Set Latitude
    public void setLatitude(float Lat) {
    	this.Latitude = Lat;
    }
    
    // Get Latitude
    public float getLatitude() {
    	return this.Latitude;
    }
    
    // Set Visibility 
    public void setVisibility(int Visible) {
    	this.Visibility = Visible;
    }
    
    // Get Visibility
    public int getVisibility() {
    	return this.Visibility;
    }
    
    // Get Registered
    public String getRegistered() {
    	return this.Registered;
    }
    
    // Set Registered
    public void setRegistered(String reg) {
    	this.Registered = reg;
    }
    
    // getting name
    public String getName(){
        return this.Name;
    }
 
    // setting name
    public void setName(String name){
        this.Name = name;
    }
 
    // getting phone number
    public String getPhoneNumber(){
        return this.PhoneNumber;
    }
 
    // setting phone number
    public void setPhoneNumber(String phone_number){
        this.PhoneNumber = phone_number;
    }
    
    
}
