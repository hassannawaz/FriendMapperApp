<?php 
// Prevent caching.
header('Cache-Control: no-cache, must-revalidate');
header('Expires: Mon, 01 Jan 1996 00:00:00 GMT');

$User_ID = $_POST['User_ID'];
$Friend_ID = $_POST['Friend_ID'];

$con = mysqli_connect("mysql1.000webhost.com", "a3038225_mk", "friendmapper1" ,"a3038225_friend" );

	$sql = "DELETE FROM a3038225_friend.friends WHERE User_ID = '".$User_ID."' AND Friend_ID = '".$Friend_ID."'";
	
mysqli_query($con,$sql);
mysqli_close($con);


?>