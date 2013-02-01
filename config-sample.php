<?php

// These three pieces of information can be found be creating an Application on StageBloc
// In this case, the application has already been created, and you just need to authenticate with it
$clientId = '840c1d25a765312f1be2ec4a6167f0d8';
$clientSecret = 'f21789142cc2a76d5e93b29eae70c482';
$redirectUri = 'http://localhost';

$themePath = './themes/'; // The folder that will contain your themes

// Once you have the other three pieces of information, this can be found by using index.php to get an access token
// It will be placed here for you automatically once you login with your StageBloc credentials
$accessToken = '<ACCESS TOKEN WILL BE INSERTED HERE>';

// This will be populated for your automagically upon authentication
$accountData = null;

$inDevelopment = false;

// Setup our StageBloc OAuth object
$stagebloc = new Services_StageBloc($clientId, $clientSecret, $redirectUri, $inDevelopment);
$stagebloc->setAccessToken($accessToken);
$stagebloc->setResponseFormat('json');

?>