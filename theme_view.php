<?php
require_once 'php-stagebloc-api/StageBloc.php';
require_once 'config.php'; // This will have to exist in the parent frame before this iFrame is loaded

$accounts = json_decode($accountData);
$accounts = $accounts->response->items;

$accountToUse = ( isset($_COOKIE['account']) ? $_COOKIE['account'] : $accounts[0]->item->id ); // Default to use the first account
foreach ( $accounts as $account ) // Get the URL of the account we're currently looking at
{
	if ( $accountToUse == $account->id )
	{
		$accountUrl = $account->stagebloc_url;
		break;
	}
}

// Find all of the available themes we have
$themes = array_values(preg_grep('/^([^.])/', scandir('themes/'))); // Ignore hidden files
$themeToUse = ( isset($_COOKIE['theme']) ? $_COOKIE['theme'] : $themes[0] ); // Default to use the first theme

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

	// Change all the anchor tags so links render through the API instead of elsewhere
	$renderedTheme = preg_replace('#href="((http:\/\/stagebloc\..+)?\/' . $accountUrl . '\/)#', 'href="?url=', $renderedTheme);

	// Output the final result
	echo $renderedTheme;
}
catch ( Services_StageBloc_Invalid_Http_Response_Code_Exception $e )
{
	die($e->getHttpBody());
}

?>