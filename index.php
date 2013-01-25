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

// If there's not access token, go to the authentication page where one can be retreived
if ( empty($accessToken) || strpos($accessToken, ' ') !== false )
{
	header('Location: authentication.php');
	exit;
}

// If the data file is empty, we haven't gotten any accounts yet
// To force reloading of your accounts, simpley empty this file
if ( ! file_exists('data.txt') || ! file_get_contents('data.txt') )
{
	$authorizedAccountsJSON = $stagebloc->post('accounts/list', array());
	file_put_contents('data.txt', $authorizedAccountsJSON);
}

// We should always have accounts by now since we populated them if the data file was empty
$accounts = json_decode(file_get_contents('data.txt'));
$accounts = $accounts->response->items;

$accountOptionsHTML = '<select name="account" id="account">';
$accountToUse = ( isset($_COOKIE['account']) ? $_COOKIE['account'] : $accounts[0]->item->id ); // Default to use the first account
foreach ( $accounts as $account ) // Build a <select> drop down to use to switch between themes quickly
{
	$accountOptionsHTML .= '<option value="' . $account->item->id . '" ' . ( $accountToUse == $account->item->id ? 'selected' : '' ) . '>' . $account->item->name . '</option>';
	
	if ( $accountToUse == $account->item->id )
	{
		$accountUrl = $account->item->stagebloc_url;
	}
}
$accountOptionsHTML .= '</select>';

// Find all of the available themes we have
$themes = array_values(preg_grep('/^([^.])/', scandir('themes/'))); // Ignore hidden files
$themeOptionsHTML = '<select name="theme" id="theme">';
$themeToUse = ( isset($_COOKIE['theme']) ? $_COOKIE['theme'] : $themes[0] ); // Default to use the first theme
foreach ( $themes as $theme ) // Build a <select> drop down to use to switch between themes quickly
{
	if ( is_dir('themes/' . $theme) )
	{
		$themeOptionsHTML .= '<option value="' . $theme . '" ' . ( $themeToUse == $theme ? 'selected' : '' ) . '>' . $theme . '</option>';
	}
}
$themeOptionsHTML .= '</select>';
?>

<html>
	<head>
		<title>StageBloc Local Theme Development</title>
		
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.min.js"></script>
		<script src="assets/js/jquery.cookie.min.js"></script>
		<script src="assets/js/main.js"></script>
		
		<link rel="stylesheet" type="text/css" href="/assets/css/main.css" />
	</head>
	
	<body>
		<div id="console">			
			<form>
				<?php echo $themeOptionsHTML; ?>
				<?php echo $accountOptionsHTML; ?>
			</form>
		</div>
		
		<iframe id="renderedTheme" src="theme_view.php"></iframe>
	</body>
</html>