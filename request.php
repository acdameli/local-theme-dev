<?php

require_once 'php-stagebloc-api/StageBloc.php';
require_once 'config.php';

// If sent here from authentication.php, we can grab the access token from there
// Note: You should copy this access token into the info.php file for further testing once you receive it
if ( ! empty($_GET) && isset($_GET['accessToken']) )
{
	$accessToken = $_GET['accessToken'];
}

// Setup our StageBloc OAuth object
$stagebloc = new Services_StageBloc($clientId, $clientSecret, $redirectUri, $inDevelopment);
$stagebloc->setAccessToken($accessToken);
$stagebloc->setResponseFormat('json'); // XML is default, JSON is also accepted

$themeToUse = 'theme_example_1';
$postData = array(
	'html' => file_get_contents('themes/' . $themeToUse . '/theme.sbt'),
	'css' => file_get_contents('themes/' . $themeToUse . '/style.css'),
	'js' => file_get_contents('themes/' . $themeToUse . '/javascript.js'),
	'url' => 'photos'
);

try
{
	$renderedTheme = $stagebloc->post('theme/render', $postData);
	echo $renderedTheme;
}
catch (Services_StageBloc_Invalid_Http_Response_Code_Exception $e)
{
	echo $e->getHttpBody();
}

?>