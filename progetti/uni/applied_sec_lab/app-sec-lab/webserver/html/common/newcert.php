
<?php
function newcert($uid, $admin, $redirect_dir) {
  $host = 'ca.imovies.com';
  curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, true);
curl_setopt($ch, CURLOPT_SSL_VERIFYHOST, 2);

  $curl_request = curl_init();
  curl_setopt_array($curl_request, [
    CURLOPT_RETURNTRANSFER => 1,
    CURLOPT_SSL_VERIFYPEER => true,
    CURLOPT_SSL_VERIFYHOST => 2,
    CURLOPT_CAINFO => "/home/vagrant/certs2/cacert.pem",
    CURLOPT_URL => 'https://core:1337/newcert?user=' . $uid . '&admin=' . $admin
  ]);
  $response = curl_exec($curl_request);
  if($response === false) {
    echo "failed";
  }
  else {
    $redirect_url = 'https://' . $host . '/' . $redirect_dir . '/listcerts.php';
    header('Location: ' . $redirect_url);
  }

  curl_close($curl_request);
}
?>
