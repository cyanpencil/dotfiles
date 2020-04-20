
<?php
function userinfo($uid, $admin) {

  echo '<h1>User information</h1>';

  $host = "database";
  $user = "webserver";
  $pwd = getenv("WEB_PASS");

  $conn=mysqli_init();
  mysqli_ssl_set($conn,"/home/vagrant/mysql_certs/webserver-key.pem","/home/vagrant/mysql_certs/webserver-cert.pem","/home/vagrant/mysql_certs/ca.pem",NULL,NULL);
  mysqli_real_connect($conn, $host, $user, $pwd);

  // Check connection
  if ($conn->connect_error) {
      die("Connection failed: " . $conn->connect_error);
  }
  $sql = 'select firstname, lastname, email from imovies.users where uid = "' . mysqli_real_escape_string($conn, $uid)  . '";';
  $result = mysqli_query($conn, $sql);
  if (mysqli_num_rows($result) > 0) {
    $user = mysqli_fetch_assoc($result);
  } else {
    echo "0 results";
  }

	echo '
	<form action="modify.php">
    User ID: ' . $uid . '<br/>
    First name:<br/>
    <input name="firstname" placeholder="First name" value="' . $user['firstname'] . '"><br/>
    Last name:<br/>
    <input name="lastname" placeholder="Last name" value="' . $user['lastname'] . '"><br/>
    Email:<br/>
    <input name="email" placeholder="Email" value="' . $user['email'] . '"><br/>
    <input type="submit" value="Modify user info">
	</form>
	<br/>
	';

  echo '<a href="listcerts.php"><h4>Manage certificates</h4></a>';

  if ($admin == '1') {
    echo '<br/><br/><a href="admin.php">Certificates statistics</a>';
		echo '<br/><br/><a href="admin_list.php">See all certificates</a>';
  }

  $conn->close();
}

?>
