<?php
	require_once('auth.php');
?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"[]>
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="en-US" xml:lang="en">
<head>
   
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Login Successful - FriendMapper </title>

    <link rel="stylesheet" href="style.css" type="text/css" media="screen" />
    
	<LINK rel="stylesheet" type="text/css" href="map.css" />
    </head>
<body>
<center>
  <font size="12" color = "maroon"  face="Bell MT">Login Successful</font>
</center><br />
<center>
<div class="art-postcontent"><font size="2">
  Welcome <?php echo $_SESSION['SESS_FIRST_NAME'];?>. You will now be automatically redirected to the Tracking page.
 </font> </div></center>
</body>
</html>

<meta http-equiv="refresh" content="3; url='map_tracking.php"> 
