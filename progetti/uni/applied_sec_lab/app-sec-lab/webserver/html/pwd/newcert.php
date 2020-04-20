<!DOCTYPE html>
<html>
<body>

  <?php
    include '../common/newcert.php';

    newcert($_SERVER['PHP_AUTH_USER'], 0, 'pwd');
  ?>

</body>
</html>
