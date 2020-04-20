<!DOCTYPE html>
<html>
<body>

  <?php
    include '../common/newcert.php';

    newcert($_SERVER['SSL_CLIENT_S_DN_CN'], $_SERVER['SSL_CLIENT_S_DN_OU'], 'crt');
  ?>

</body>
</html>
