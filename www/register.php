<?php 
// Prevent caching.
header('Cache-Control: no-cache, must-revalidate');
header('Expires: Mon, 01 Jan 1996 00:00:00 GMT');

$Name = $_POST['Name'];
$Phone_Number = $_POST['PhoneNumber'];
$Registered = $_POST['Registered'];
$Visibility = $_POST['Visibility'];
$Latitude = $_POST['Latitude'];
$Longitude = $_POST['Longitude'];

 //Connect to mysql server""
$con = mysqli_connect("localhost:3306", "root", "" ,"friendmapper" );
	//Create query
	$sql = "INSERT INTO friendmapper.members (Name, PhoneNumber, Registered, Visibility, Latitude, Longitude) VALUES ('".$Name."', '".$Phone_Number."', '".$Registered."', '".$Visibility."', '".$Latitude."', '".$Longitude."')";
	
mysqli_query($con,$sql);
mysqli_close($con);
?>