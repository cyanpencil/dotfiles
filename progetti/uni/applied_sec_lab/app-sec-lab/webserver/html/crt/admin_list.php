<!DOCTYPE html>
<html>
<body>
  <h1>Certificates statistics</h1>

  <?php
    if ($_SERVER['SSL_CLIENT_S_DN_OU'] == '1') {
      $curl_request = curl_init();
      curl_setopt_array($curl_request, [
        CURLOPT_RETURNTRANSFER => 1,
        CURLOPT_URL => 'https://core:1337/admin',
        CURLOPT_SSL_VERIFYPEER => true,
        CURLOPT_SSL_VERIFYHOST => 2,
        CURLOPT_CAINFO => "/home/vagrant/certs2/cacert.pem"
      ]);
      $response = curl_exec($curl_request);
      if($response === false) {
        echo "failed";
      }
      else {
        // echo $response;
        $stats = explode(',', $response);
        echo 'Number of issued certificates: ' . $stats[0] . '<br/>';
        echo 'Number of revoked certificates: ' . $stats[1] . '<br/>';
        echo 'Current serial number: ' . $stats[2] . '<br/>';
      }

      curl_close($curl_request);


	$host = "database";
	$user = "webserver";
	$pwd = getenv("WEB_PASS");


	$conn=mysqli_init();
	mysqli_ssl_set($conn,"/home/vagrant/mysql_certs/webserver-key.pem","/home/vagrant/mysql_certs/webserver-cert.pem","/home/vagrant/mysql_certs/ca.pem",NULL,NULL);
	mysqli_real_connect($conn, $host, $user, $pwd);

	$sql = 'select cid,uid,certificate,privkey,revoked from imovies.certificates;';
	$result = mysqli_query($conn, $sql);
	if (mysqli_num_rows($result) > 0) {
	  echo '<table style="width:100%">';

	  while($cert = mysqli_fetch_assoc($result)){
		echo '<tr>';
		echo '<td>' . $cert['cid'] . '</td>';
		echo '<td>' . $cert['uid'] . '</td>';
		echo '<td>' . $cert['certificate'] . '</td>';
		echo '<td>' . $cert['privkey'] . '</td>';
		//echo '<td>
		//<form action="download.php" method="post">
			//<input type="hidden" id="cid" name="cid" value="' . $cert['cid'] . '">
			//<input type="submit" value="Download">
		//</form>
		//</td>
		//';
		if ( $cert['revoked'] == 0 ) {
		  echo '<td>
		  <form action="revoke.php" method="post">
			<input type="hidden" id="cid" name="cid" value="' . $cert['cid'] . '">
			<input type="submit" value="Revoke">
		  </form>
		  </td>
		  ';
		}
		else {
		  echo '<td><strong>Revoked</strong></td>';
		}
		echo '</tr>';
	  }
	  echo '</table>';
	} else {
	  echo "0 results";
	}



    }
    else {
      echo '<strong>Restricted to CA admins</strong>';
    }
  ?>

</body>
</html>
