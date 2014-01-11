<?php

require_once 'config.php';

if ( isset($_COOKIE['theme']) )
{
	$themeToUse = $_COOKIE['theme'];

	// Get the HTML to pass along to the API
	$html = file_get_contents($themePath . $themeToUse . '/theme.sbt');
	if ( $themeViewsPath !== null )
	{
		// Find all of the Includes inside of the theme
		preg_match_all('#\{(Includ[^:{}"]+)(\s[^{}]+\=[^{}]*)?\}#ims', $html, $includes);
		foreach ( $includes[0] as $key => $option_variable_string )
		{
			if ( preg_match_all('#([a-zA-Z]*)="([^"]*)"#', $option_variable_string, $options_matches) )
			{
				// For each include, find which file we should be putting in its place
				foreach ( $options_matches[1] as $key => $option )
				{
					if ( strtolower($option) === 'file' )
					{
						$html = str_replace($option_variable_string, file_get_contents($themePath . $themeToUse . '/' . $themeViewsPath . $options_matches[2][$key]), $html);
					}
				}
			}
		}
	}

	$postData = array(
		'html' => $html,
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

    if ( strpos($postData['css'], '@') === 0 )
    {
        die('The first character in your CSS is an "@". For some reason, this breaks submitting the theme. If you\'d like to help fix this, please see here: https://github.com/stagebloc/local-theme-dev/issues/8');
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

	if ( isset($_GET['generate']) && filter_var($_GET['generate'], FILTER_VALIDATE_BOOLEAN) ): ?>

<html>
	<head></head>
	<body>
		You can copy and paste the following text into files to have your entire theme...<br/>
		<a href="index.php">Back To Theme Development</a><br/>
		<textarea rows="20" cols="100" readonly><?php echo htmlspecialchars($postData['html']); ?></textarea><br/>
		<textarea rows="20" cols="100" readonly><?php echo htmlspecialchars($postData['css']); ?></textarea><br/>
		<textarea rows="20" cols="100" readonly><?php echo htmlspecialchars($postData['js']); ?></textarea>
	</body>
</html>

	<?php else:
		try
		{
			$stagebloc->post('theme/edit', $postData);
		}
		catch ( Services_StageBloc_Invalid_Http_Response_Code_Exception $e )
		{
			die('Error: ' . $e->getHttpBody());
		}

		exit(header('Location: index.php')); // Redirect back to the editor
	endif;
}
else
{
    die('No theme cookie set! Try switching themes to a different one and then back.');
}
