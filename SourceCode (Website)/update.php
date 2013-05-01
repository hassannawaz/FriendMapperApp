<?php 
// Prevent caching.
header('Cache-Control: no-cache, must-revalidate');
header('Expires: Mon, 01 Jan 1996 00:00:00 GMT');

$ID = $_POST['Friend_ID'];

//Connect to mysql server""
$conn = mysqli_connect("mysql1.000webhost.com", "a3038225_mk", "friendmapper1" ,"a3038225_friend" );

$sql = "SELECT *
FROM a3038225_friend.members, a3038225_friend.friends
WHERE friends.Friend_ID = members.PhoneNumber
AND friends.USER_ID ='".$ID."' AND Confirmation = 1";

$stmt = mysqli_query( $conn, $sql);
if( $stmt === false) {
    die( print_r( mysqli_error(), true) );
}

while ($row = mysqli_fetch_array( $stmt, MYSQLI_ASSOC)) {
	$arr[] = $row;
}
echo ('{"Update" :');
echo json_encode($arr);
echo ('}');
?>