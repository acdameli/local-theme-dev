<?php

// TODO: This should be named config.php once you've added the required information!!!

// These three pieces of information can be found be creating an Application on StageBLoc
$clientId = '<YOUR CLIENT ID>';
$clientSecret = '<YOUR CLIENT SECRET>';
$redirectUri = '<YOUR REDIRECT URI>'; // For development, it may make sense to use some sort of localhost here

// Once you have the other three pieces of information, this can be found by using authentication.php to get an access token
$accessToken = '<YOUR ACCESS TOKEN>';

$inDevelopment = false;

// Setup our StageBloc OAuth object
$stagebloc = new Services_StageBloc($clientId, $clientSecret, $redirectUri, $inDevelopment);
$stagebloc->setAccessToken($accessToken);
$stagebloc->setResponseFormat('json');

?>