<?php

// Simply set the variables back to null to be "logged out"
$code = file_get_contents('config.php');
$code = preg_replace('/\$accessToken\ \=\ \'(.*?)\'\;/', '$accessToken = null;', $code);
$code = preg_replace('/\$accountData\ \=\ \'(.*?)\'\;/', '$accountData = null;', $code);
file_put_contents('config.php', $code);

?>