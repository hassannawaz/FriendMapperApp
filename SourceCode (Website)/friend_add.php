<?php 
// Prevent caching.
header('Cache-Control: no-cache, must-revalidate');
header('Expires: Mon, 01 Jan 1996 00:00:00 GMT');

$User_ID = $_POST['User_ID'];
$Friend_ID = $_POST['Friend_ID'];


$con = mysqli_connect("mysql1.000webhost.com", "a3038225_mk", "friendmapper1" ,"a3038225_friend" );

	$sql = "SELECT count(PhoneNumber) FROM a3038225_friend.members WHERE PhoneNumber = '".$User_ID."'";
	
$stmt = mysqli_query($con,$sql);


if( $stmt === false) {
    die( print_r( mysqli_error(), true) );
}

$row = mysqli_fetch_array( $stmt, MYSQLI_NUM);

$User_ID_found = $row[0];

mysqli_close($con);


$con = mysqli_connect("mysql1.000webhost.com", "a3038225_mk", "friendmapper1" ,"a3038225_friend" );

	$sql = "SELECT count(PhoneNumber) FROM a3038225_friend.members WHERE PhoneNumber = '".$Friend_ID."'";
	
$stmt = mysqli_query($con,$sql);

if( $stmt === false) {
    die( print_r( mysqli_error(), true) );
}

$row = mysqli_fetch_array( $stmt, MYSQLI_NUM);

$Friend_ID_found = $row[0];

mysqli_close($con);

if ($User_ID_found == 1 && $Friend_ID_found == 1)
{
	$con = mysqli_connect("mysql1.000webhost.com", "a3038225_mk", "friendmapper1" ,"a3038225_friend" );

	$sql = "INSERT INTO a3038225_friend.friends (User_ID, Friend_ID) VALUES ('".$User_ID."', '".$Friend_ID."')";
	
mysqli_query($con,$sql);
mysqli_close($con);

}

?>