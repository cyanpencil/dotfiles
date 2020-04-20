<?php
	include '../common/modify.php';

  $lastname=$_GET['lastname'];
  $firstname=$_GET['firstname'];
  $email=$_GET['email'];

	modify($_SERVER['SSL_CLIENT_S_DN_CN'], 'crt', $lastname, $firstname, $email);
?>
