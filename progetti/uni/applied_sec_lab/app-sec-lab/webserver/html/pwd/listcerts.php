<!DOCTYPE html>
<html>
<body>

  <?php
    include '../common/listcerts.php';

    listcerts($_SERVER['PHP_AUTH_USER']);
  ?>

</body>
</html>
