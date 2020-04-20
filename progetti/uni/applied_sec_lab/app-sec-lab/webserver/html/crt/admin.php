<!DOCTYPE html>
<html>
<body>
  <h1>Certificates statistics</h1>

  <?php
    if ($_SERVER['SSL_CLIENT_S_DN_OU'] == '1') {
      $curl_request = curl_init();
      curl_setopt_array($curl_request, [
        CURLOPT_RETURNTRANSFER => 1,
        CURLOPT_SSL_VERIFYPEER => true,
        CURLOPT_SSL_VERIFYHOST => 2,
        CURLOPT_CAINFO => "/home/vagrant/certs2/cacert.pem",
        CURLOPT_URL => 'https://core:1337/admin'
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
    }
    else {
      echo '<strong>Restricted to CA admins</strong>';
    }
  ?>

</body>
</html>
