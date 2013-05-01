<?php 
// Prevent caching.
header('Cache-Control: no-cache, must-revalidate');
header('Expires: Mon, 01 Jan 1996 00:00:00 GMT');

$ID = $_POST['Friend_ID'];

//Connect to mysql server""
$conn = mysqli_connect("mysql1.000webhost.com", "a3038225_mk", "friendmapper1" ,"a3038225_friend" );

$sql = "SELECT User_ID from a3038225_friend.panic where Friend_ID = '".$ID."'";

$stmt = mysqli_query( $conn, $sql);

if( $stmt === false) {
    die( print_r( mysqli_error(), true) );
}

$i=0;
while ($row = mysqli_fetch_array( $stmt, MYSQLI_NUM)) {
	$arr[$i] = $row[0];
	$i++;
}
echo implode(',' , $arr);
mysqli_close($conn);

$conn = mysqli_connect("mysql1.000webhost.com", "a3038225_mk", "friendmapper1" ,"a3038225_friend" );

$sql = "DELETE FROM a3038225_friend.panic where Friend_ID = '".$ID."'";

mysqli_query($conn,$sql);

?>