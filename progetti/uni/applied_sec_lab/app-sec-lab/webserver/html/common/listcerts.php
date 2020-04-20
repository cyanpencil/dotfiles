
<?php
function listcerts($uid) {
  echo '<h1>Certificates</h1>';

  echo '
  <form action="newcert.php">
     <input type="submit" value="Generate new certificate">
  </form>
  ';

  $host = "database";
  $user = "webserver";
  $pwd = getenv("WEB_PASS");


  $conn=mysqli_init();
  mysqli_ssl_set($conn,"/home/vagrant/mysql_certs/webserver-key.pem","/home/vagrant/mysql_certs/webserver-cert.pem","/home/vagrant/mysql_certs/ca.pem",NULL,NULL);
  mysqli_real_connect($conn, $host, $user, $pwd);

  if ($conn->connect_error) {
      die("Connection failed: " . $conn->connect_error);
  }
  $sql = 'select cid,uid,certificate,revoked from imovies.certificates where uid = "' . mysqli_real_escape_string($conn, urldecode($uid)) . '";';
  //$sql2 = 'select cid,uid,certificate,revoked from imovies.certificates where uid = "' . mysqli_real_escape_string($conn, $uid) . '";';
  //echo $sql . "<br/>";
  //echo $sql2 . "<br/>";
  $result = mysqli_query($conn, $sql);
  if (mysqli_num_rows($result) > 0) {
    echo '<table style="width:100%">';

    while($cert = mysqli_fetch_assoc($result)){
      echo '<tr>';
      echo '<td>' . $cert['cid'] . '</td>';
      echo '<td>' . $cert['certificate'] . '</td>';
      echo '<td>
      <form action="download.php" method="post">
        <input type="hidden" id="cid" name="cid" value="' . $cert['cid'] . '">
        <input type="submit" value="Download">
      </form>
      </td>
      ';
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

  $conn->close();
}
?>
