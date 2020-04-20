<?php

	$uid=$_GET['uid'];
	$pass=$_GET['pass'];

  $curl_request = curl_init();
  curl_setopt_array($curl_request, [
    CURLOPT_RETURNTRANSFER => 1,
    CURLOPT_URL => "https://core:1337/newuser?uid=" . $uid . "&pass=" . $pass,
    CURLOPT_SSL_VERIFYPEER => true,
    CURLOPT_SSL_VERIFYHOST => 2,
    CURLOPT_CAINFO => "/home/vagrant/certs2/cacert.pem"
  ]);
	$response = curl_exec($curl_request);
	if( $response === false) {
		echo "failed";
	}
	else {
		echo $response;
	}

	curl_close($curl_request);
?>
