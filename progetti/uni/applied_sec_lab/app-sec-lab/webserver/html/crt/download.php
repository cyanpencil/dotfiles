<?php
  include '../common/download.php';

  download($_SERVER['SSL_CLIENT_S_DN_CN'], $_POST['cid'], 'crt');
?>
