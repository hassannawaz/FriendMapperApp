  <?php
	  //Start session
	  session_start();
	  
	  //Array to store validation errors
	  $errmsg_arr = array();
	  
	  //Validation error flag
	  $errflag = false;
	  
	  //Connect to mysql server
	  $link = mysqli_connect("localhost:3306", "root", "" ,"friendmapper" );
	  
	  //Function to sanitize values received from the form. Prevents SQL injection
	  function clean($str) {
		  $str = @trim($str);
		  if(get_magic_quotes_gpc()) {
			  $str = stripslashes($str);
		  }
		  return (str_replace("'","''",$str));
	  }
	  
	  //Sanitize the POST values
	  $login = clean($_POST['login']);
	  $password = clean($_POST['password']);
	  
	  //Input Validations
	  if($login == '') {
		  $errmsg_arr[] = 'Login ID missing';
		  $errflag = true;
	  }
	  if($password == '') {
		  $errmsg_arr[] = 'Password missing';
		  $errflag = true;
	  }
	  
	  //If there are input validations, redirect back to the login form
	  if($errflag) {
		  $_SESSION['ERRMSG_ARR'] = $errmsg_arr;
		  session_write_close();
		  header("location: login-failed.php");
		  exit();
	  }
	  
	  //Create query
	  $sql = "SELECT * FROM  friendmapper.members WHERE PhoneNumber='".$login."' AND PIN='".$password."'";
	  $stmt = mysqli_query($link,$sql);
	  
	  if( $stmt === false) {
	  die( print_r( mysqli_error(), true) );
	  }
  
	  $row_count = 0;	
	  while( $row = mysqli_fetch_array( $stmt, MYSQLI_NUM) ) {
	   $row_count = $row_count + 1;
	  }
	  
	  $sql1 = "SELECT * FROM  friendmapper.members WHERE PhoneNumber='".$login."' AND PIN='".$password."'";
	  $stmt1 = mysqli_query($link,$sql1);
	  
	  //Check whether the query was successful or not
		  if($stmt1) {
			  if($row_count == 1) {
			  //Login Successful
			  session_regenerate_id();
			  $member = mysqli_fetch_array($stmt1, MYSQLI_ASSOC);
			  $_SESSION['SESS_MEMBER_ID'] = $member['id'];
			  $_SESSION['SESS_FIRST_NAME'] = $member['Name'];
			  session_write_close();
  
  	header("location: member-index.php");
			  exit();
		  }else {
			  //Login failed
			  header("location: login-failed.php");
			  exit();
		  }
		  }else {
		  die("Query failed");
	  }
  ?>