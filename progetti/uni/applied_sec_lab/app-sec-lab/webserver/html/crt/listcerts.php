<!DOCTYPE html>
<html>
<body>

  <?php
    include '../common/listcerts.php';

    listcerts($_SERVER['SSL_CLIENT_S_DN_CN']);
  ?>

</body>
</html>
