<?php
	require_once('auth.php');
?>
<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <style type="text/css">
      html { height: 100% }
      body { height: 100%; margin: 0; padding: 0 }
      #map_canvas { height: 100% }
	  #legend {
    	background: white;
    	padding: 10px;
  		}
    </style>
    <script type="text/javascript"
      src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAQC18WoVVdut7ewRztziT-KfgM4F0Rv3o&sensor=false">
    </script>
    <script type="text/javascript">
	  
	  <?php 
$conn = mysqli_connect("localhost:3306", "root", "" ,"friendmapper" );
	  
$sql = "SELECT Latitude, Longitude, Visibility FROM friendmapper.members where id = '".$_SESSION['SESS_MEMBER_ID']."'";
$stmt = mysqli_query( $conn, $sql);

if( $stmt === false) {
    die( print_r( mysqli_error(), true) );
}
$row = mysqli_fetch_array( $stmt, MYSQLI_NUM);
$Lat = $row[0];
$Long = $row[1];	
$Vis = $row[2];	
?>

    function initialize() {
        var mapOptions = {
          center: new google.maps.LatLng(<?php echo "$Lat"?>,<?php echo "$Long"?>),
          zoom: 15,
          mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        var map = new google.maps.Map(document.getElementById("map_canvas"),
            mapOptions);
			
		
		var latlng = new google.maps.LatLng(<?php echo "$Lat"?>,<?php echo "$Long"?>);
		var myMarker = new google.maps.Marker({
		name: 'Location',
  		position: latlng,
        animation:google.maps.Animation.DROP,
  		icon:'./red.png',
  		map: map,
		category: 'Yes',
		visible: true
});	
arr.push(myMarker);
		<?php  
mysqli_free_result( $stmt);
?>
      }
	  
		
    </script>
  </head>
  <body onload="initialize()">
  	
    <div id="map_canvas" style="width:100%; height:90%"></div> 
<font color='black' face = 'Bell MT' size="3">
			<a href="./logout.php" >Logout</a>
		 | <?php if ($Vis == 0) { 
		 echo ("The Page is showing the last known location of the user since visibility is OFF on the phone"); }
		 else { 
		 echo ("The Page will refresh after every 15 seconds if the location is being updated through the phone");
		 } ?>
		</font>
          </body>
</html>

<meta http-equiv="refresh" content="15; url='map_tracking.php"> 