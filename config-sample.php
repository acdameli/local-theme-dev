<?php

// These three pieces of information can be found be creating an Application on StageBloc
// In this case, the application has already been created, and you just need to authenticate with it
$clientId = '19318d878e3c102786364f6da5609e2e';
$clientSecret = '88c39d4e2ae4c83c2b58a071b83207e0';
$redirectUri = 'http://localhost/localdev/authentication.php';

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