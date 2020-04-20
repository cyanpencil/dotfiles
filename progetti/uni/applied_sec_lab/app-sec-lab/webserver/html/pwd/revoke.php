<!DOCTYPE html>
<html>
<body>

  <?php
    include '../common/revoke.php';

    revoke($_SERVER['PHP_AUTH_USER'], $_POST['cid'], 'pwd');
  ?>

</body>
</html>
