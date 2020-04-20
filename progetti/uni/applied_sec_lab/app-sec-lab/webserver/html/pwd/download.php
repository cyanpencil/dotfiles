<?php
  include '../common/download.php';

  download($_SERVER['PHP_AUTH_USER'], $_POST['cid'], 'crt');
?>
