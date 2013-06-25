<?php

require_once 'php-stagebloc-api/StageBloc.php';

// These three pieces of information can be found be creating an Application on StageBloc
// In this case, the application has already been created by your friends at Stagebloc, you just need to authenticate with it
// If for any reason you need to use a different redirect URL, you can easily create your own application on StageBloc and use that instead
$clientId = '840c1d25a765312f1be2ec4a6167f0d8';
$clientSecret = 'f21789142cc2a76d5e93b29eae70c482';
$redirectUri = 'http://localhost';

// The folder that will contain your themes
// Each folder in this directory should be named what you want the themes name to be
// Each subfolder for a theme should have at least a theme.sbt, javascript.js, and style.css file
$themePath = './themes/';

// The folder that may contain extra theme view files
// Using {Include file="<theme_file_name>.sbt"} anywhere in your main theme.sbt file will include theme_file_name.sbt from this folder
// This can be used as a way to organize your theme a bit more and not have to have all the HTML in one file
// If null, it'll assume your entire theme is in theme.sbt
$themeViewsPath = null;

// The path to where CSS files are stored for each theme relative to the theme itself (i.e. you shouldn't need a beginning slash)
// Each CSS file in the folder specified by the path will be added in a <link> tag at the top of the theme (folder searching isn't recursive)
// If you use "Submit" theme, the CSS from the files in this directory will be appended into one, larger CSS dump before POSTing it to the server
// If null, it will look for /theme_dir/style.css
$cssPath = null;

// The path to where JS files are stored for each theme relative to the theme itself (i.e. you shouldn't need a beginning slash)
// Behaves similarily to $cssPath with regards to the other behavaviors
// If null, it will look for /theme_dir/javascript.js
$jsPath = null;

// Once you have the other three pieces of information, this can be found by using index.php to get an access token
// It will be placed here for you automatically once you login with your StageBloc credentials
$accessToken = null;

// This will be populated for your automagically upon authentication
// For those who are curious, it's basically just a log of the JSON returned from the API to have locally
// So if you ever need to "reset" your login, changing both this and $accessToken back to null will force another login
$accountData = null;

$inDevelopment = false;

// Setup our StageBloc OAuth object
$stagebloc = new Services_StageBloc($clientId, $clientSecret, $redirectUri, $inDevelopment);
$stagebloc->setAccessToken($accessToken);
$stagebloc->setResponseFormat('json');

?>