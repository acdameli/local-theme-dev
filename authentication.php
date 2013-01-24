<?php

require_once 'php-stagebloc-api/StageBloc.php';
require_once 'config.php';

$stagebloc = new Services_StageBloc($clientId, $clientSecret, $redirectUri, $inDevelopment);

if ( isset($_GET['code']) )
{
	try
	{
	    $accessToken = $stagebloc->accessToken($_GET['code']);	
		echo 'Successfully authenticated! Your access token is ' . $accessToken['access_token'] . '<br/>' .
				'Please place this in your config.php file.';
	}
	catch ( Services_StageBloc_Invalid_Http_Response_Code_Exception $e )
	{
		die($e->getHttpBody());
	}
}
else
{
	$authUrl = $stagebloc->getAuthorizeUrl(array('scope' => 'non-expiring'));
	echo '<a href="' . $authUrl . '">Connect With StageBloc</a>';
}
?>