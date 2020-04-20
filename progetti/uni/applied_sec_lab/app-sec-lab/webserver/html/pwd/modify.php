<?php


  $lastname=$_GET['lastname'];
  $firstname=$_GET['firstname'];
  $email=$_GET['email'];

  include '../common/modify.php';
  modify($_SERVER['PHP_AUTH_USER'], 'pwd', $lastname, $firstname, $email);

?>
