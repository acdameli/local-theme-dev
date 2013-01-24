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
if ( strpos($accessToken, ' ') !== false )
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

// Pass out theme data to the API to be rendered
$postData = array(
	'url' => ( isset($_GET['url']) ? $_GET['url'] : '' ),
	'html' => file_get_contents('themes/' . $themeToUse . '/theme.sbt'),
	'css' => file_get_contents('themes/' . $themeToUse . '/style.css')
	
	// We don't need to pass the CSS and JS since we can just add it in to the parsed theme we receive
	// That being said, if your CSS has Option vars, you should pass it
	//'css' => file_get_contents('themes/' . $themeToUse . '/style.css'),
	//'js' => file_get_contents('themes/' . $themeToUse . '/javascript.js'),
);

try
{
	$renderedTheme = $stagebloc->post('theme/render', $postData);
	
	// If we didn't pass the CSS, we'll append it to the rendered theme
	if ( ! isset($postData['css']) )
	{
		$renderedTheme = str_replace('</head>', '<style>' . file_get_contents('themes/' . $themeToUse . '/style.css') . '</style></head>', $renderedTheme);
	}
	
	// If we didn't pass the JS, we'll append it to the rendered theme
	if ( ! isset($postData['js']) )
	{
		$renderedTheme = str_replace('</head>', '<script>' . file_get_contents('themes/' . $themeToUse . '/javascript.js') . '</script></head>', $renderedTheme);
	}
	
	// Change all the anchor tags to render through our API
	$renderedTheme = preg_replace('#href="((http:\/\/stagebloc\..+)?\/' . $accountUrl . '\/)#', 'href="?url=', $renderedTheme);
	
	$console = <<<CONSOLE
	<div id="console">
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.min.js"></script>
		<script src="assets/js/jquery.cookie.min.js"></script>
		<script type="text/javascript">
			$.noConflict(); // Don't want our small amount of code to interfere with any projects
			jQuery(document).ready(function($) {
				$('#theme').change(function() {
					$.cookie('theme', $(this).val(), {expires: 30}); // Store this theme as the one to use
					window.location = document.URL; // Refresh the page
				});
				
				$('#account').change(function() {
					$.cookie('account', $(this).val(), {expires: 30}); // Store this account as the one to use
					window.location = 'change_accounts.php?account_id=' + $(this).val();
				});
			});
		</script>
		<form>
			{$themeOptionsHTML}
			{$accountOptionsHTML}
		</form>
	</div>
CONSOLE;
	
	echo $console . $renderedTheme;
}
catch ( Services_StageBloc_Invalid_Http_Response_Code_Exception $e )
{
	die($e->getHttpBody());
}

?>