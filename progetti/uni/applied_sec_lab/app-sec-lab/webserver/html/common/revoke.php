<?php
function revoke($uid, $cid, $redirect_dir) {
  $host = 'ca.imovies.com';

  $curl_request = curl_init();
  curl_setopt_array($curl_request, [
    CURLOPT_RETURNTRANSFER => 1,
    CURLOPT_URL => 'https://core:1337/revoke',
    CURLOPT_SSL_VERIFYPEER => true,
    CURLOPT_SSL_VERIFYHOST => 2,
    CURLOPT_CAINFO => "/home/vagrant/certs2/cacert.pem",
    CURLOPT_POST => 1,
    CURLOPT_POSTFIELDS => [ 'cid' => $cid ],
  ]);
  $response = curl_exec($curl_request);
  if($response === false) {
    echo "failed";
  }
  else {
    // echo $response;
    // get crl and hash from response
    $arr = explode('|', $response);
    $crl = $arr[0];
    $crl_hash = trim($arr[1]);
    // write crl
    $fp = fopen('/etc/apache2/crl/ca.crl', 'w');
    fwrite($fp, $response);
    fclose($fp);
    // create symlink to crl (not useful here, in case we have multiple CA's)
    exec('rm /etc/apache2/crl/' . $crl_hash . '.r0');
    exec('ln -s /etc/apache2/crl/ca.crl /etc/apache2/crl/' . $crl_hash . '.r0');

    $redirect_url = 'https://' . $host . '/' . $redirect_dir . '/listcerts.php';
    header('Location: ' . $redirect_url);
  }

  curl_close($curl_request);
}
?>
