<?php

require_once 'php-stagebloc-api/StageBloc.php';

// Check to see if they've configured their application
if ( file_exists('config.php') )
{
	require_once 'config.php';
}
else
{
	die('No config.php file found! Create an application on StageBloc, and then populate the config.php file.');
}

if ( isset($_GET['account_id']) )
{
	$response = $stagebloc->post('oauth2/token/edit', array('account_id' => $_GET['account_id']));	
	header('Location: index.php'); // Redirect back to the main page since out access token has been updated for a new account
	exit;
}
else
{
	die('Failed');
}

?>