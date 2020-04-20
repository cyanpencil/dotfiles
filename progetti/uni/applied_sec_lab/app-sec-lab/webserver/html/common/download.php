<?php
function download($uid, $cid, $redirect_dir) {
  $host = 'ca.imovies.com';

  $curl_request = curl_init();
  curl_setopt_array($curl_request, [
    CURLOPT_RETURNTRANSFER => 1,
    CURLOPT_BINARYTRANSFER => 1,
    CURLOPT_URL => 'https://core:1337/convert',
    CURLOPT_SSL_VERIFYPEER => true,
    CURLOPT_SSL_VERIFYHOST => 2,
    CURLOPT_CAINFO => "/home/vagrant/certs2/cacert.pem",
    CURLOPT_POST => 1,
    CURLOPT_POSTFIELDS => [ 'cid' => $cid ]
  ]);
  $response = curl_exec($curl_request);
  if($response === false) {
    echo "failed";
  }
  else {
    header('Content-type: application/x-pkcs12');
    header('Content-Disposition: attachment; filename=' . $uid . '.pfx');
    echo $response;
  }

  curl_close($curl_request);
}
?>
