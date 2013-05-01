<?php 
// Prevent caching.
header('Cache-Control: no-cache, must-revalidate');
header('Expires: Mon, 01 Jan 1996 00:00:00 GMT');

$ID = $_POST['User_ID'];

$conn = mysqli_connect("mysql1.000webhost.com", "a3038225_mk", "friendmapper1" ,"a3038225_friend" );

$sql = "UPDATE a3038225_friend.members SET visibility = 1 where PhoneNumber = '".$ID."'";

mysqli_query($conn,$sql);

?>