<?php 
// Prevent caching.
header('Cache-Control: no-cache, must-revalidate');
header('Expires: Mon, 01 Jan 1996 00:00:00 GMT');

$Name = $_POST['Name'];
$Phone_Number = $_POST['PhoneNumber'];
$PIN = $_POST['PIN'];
$Registered = $_POST['Registered'];
$Visibility = $_POST['Visibility'];
$Latitude = $_POST['Latitude'];
$Longitude = $_POST['Longitude'];

 //Connect to mysql server""
$con = mysqli_connect("mysql1.000webhost.com", "a3038225_mk", "friendmapper1" ,"a3038225_friend" );
	//Create query
	$sql = "INSERT INTO a3038225_friend.members (Name, PhoneNumber, PIN, Registered, Visibility, Latitude, Longitude) VALUES ('".$Name."', '".$Phone_Number."', '".$PIN."', '".$Registered."', '".$Visibility."', '".$Latitude."', '".$Longitude."')";
	
mysqli_query($con,$sql);
mysqli_close($con);
?>