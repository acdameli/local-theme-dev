<?php

require_once 'config.php';

if ( isset($_COOKIE['theme']) )
{
	$themeToUse = $_COOKIE['theme'];
	
	// Pass out theme data to the API to be rendered
	$postData = array(
		'html' => file_get_contents($themePath . $themeToUse . '/theme.sbt'),
		'css' => file_get_contents($themePath . $themeToUse . '/style.css'),
		'js' => file_get_contents($themePath . $themeToUse . '/javascript.js'),
		'mobile' => filter_var($_POST['mobile']), FILTER_VALIDATE_BOOLEAN)
	);
	
	try
	{
		// Send this theme data to the API
		$response = $stagebloc->post('theme/edit', $postData);
	}
	catch ( Services_StageBloc_Invalid_Http_Response_Code_Exception $e )
	{
		die($e->getHttpBody());
	}
	
	header('Location: index.php'); // Redirect back to the editor
	exit;
}

?>