<!DOCTYPE html>
<html>
<body>
  <?php
    include '../common/main.php';

    userinfo($_SERVER['SSL_CLIENT_S_DN_CN'], $_SERVER['SSL_CLIENT_S_DN_OU']);
  ?>
</body>
</html>
