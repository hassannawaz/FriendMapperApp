<?php 
// Prevent caching.
header('Cache-Control: no-cache, must-revalidate');
header('Expires: Mon, 01 Jan 1996 00:00:00 GMT');

$ID = $_POST['User_ID'];

//Connect to mysql server""
$conn = mysqli_connect("mysql1.000webhost.com", "a3038225_mk", "friendmapper1" ,"a3038225_friend" );

$sql = "SELECT User_ID, Friend_ID from a3038225_friend.friends where User_ID = '".$ID."'";

$stmt = mysqli_query( $conn, $sql);

if( $stmt === false) {
    die( print_r( mysqli_error(), true) );
}

$con1 = mysqli_connect("mysql1.000webhost.com", "a3038225_mk", "friendmapper1" ,"a3038225_friend" );

while ($row = mysqli_fetch_array( $stmt, MYSQLI_NUM)) {
	
	$sql1 = "INSERT INTO a3038225_friend.panic (User_ID, Friend_ID) VALUES ('".$row[0]."', '".$row[1]."')";

mysqli_query($con1,$sql1);
		
};

?>