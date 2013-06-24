<?php

require_once 'config.php';

if ( isset($_COOKIE['theme']) )
{
	$themeToUse = $_COOKIE['theme'];
	
	// Pass out theme data to the API to be rendered
	$postData = array(
		'html' => file_get_contents($themePath . $themeToUse . '/theme.sbt'),
		'mobile' => filter_var($_POST['mobile'], FILTER_VALIDATE_BOOLEAN)
	);
	
	if ( $cssPath !== null ) // If this var isn't null, we'll check another folder for the CSS files
	{
		$css = '';
		$cssFiles = scandir($themePath . $themeToUse . '/' . $cssPath);
		foreach ( $cssFiles as $cssFile )
		{
			if ( strpos($cssFile, '.css') !== false )
			{
				$css .= file_get_contents($themePath . $themeToUse . '/' . $cssPath . $cssFile);
			}
		}
		$postData['css'] = $css;
	}
	else
	{
		$postData['css'] = file_get_contents($themePath . $themeToUse . '/style.css');
	}
	
	if ( $jsPath !== null ) // If this var isn't null, we'll check another folder for the CSS files
	{
		$js = '';
		$jsFiles = scandir($themePath . $themeToUse . '/' . $jsPath);
		foreach ( $jsFiles as $jsFile )
		{
			if ( strpos($jsFile, '.js') !== false )
			{
				$js .= file_get_contents($themePath . $themeToUse . '/' . $jsPath . $jsFile);
			}
		}
		$postData['js'] = $js;
	}
	else
	{
		$postData['js'] = file_get_contents($themePath . $themeToUse . '/javascript.js');
	}
	
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