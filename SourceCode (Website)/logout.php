<?php
	//Start session
	session_start();
	
	//Unset the variables stored in session
	unset($_SESSION['SESS_MEMBER_ID']);
	unset($_SESSION['SESS_FIRST_NAME']);	
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"[]>
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="en-US" xml:lang="en">
<head>
    <!--
    Created by Artisteer v3.0.0.45570
    Base template (without user's data) checked by http://validator.w3.org : "This page is valid XHTML 1.0 Transitional"
    -->
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Logout Successful - FriendMapper</title>



    <link rel="stylesheet" href="style.css" type="text/css" media="screen" />
    
	<LINK rel="stylesheet" type="text/css" href="map.css" /> 
<link rel="shortcut icon" href="icon.png" type="image/x-icon" />
    <script type="text/javascript" src="jquery.js"></script>
    <script type="text/javascript" src="script.js"></script>
</head>
<body>
<center>
  <font size="12" color = "maroon"  face="Bell MT">Logout Successful</font>
</center><br />
<center>
<div class="art-postcontent"><font size="2">
  You will now be automatically redirected to the Login page.
</font> </div></center>
</body>
</html>

<meta http-equiv="refresh" content="3; url='index.php"> 



