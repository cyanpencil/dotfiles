<!DOCTYPE html>
<html>
<body>

  <?php
    include '../common/revoke.php';

    revoke($_SERVER['SSL_CLIENT_S_DN_CN'], $_POST['cid'], 'crt');
  ?>

</body>
</html>
