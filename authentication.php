<?php

require_once 'php-stagebloc-api/StageBloc.php';
require_once 'config.php';

$stagebloc = new Services_StageBloc($clientId, $clientSecret, $redirectUri, $inDevelopment);

if ( isset($_GET['code']) )
{
	try
	{
	    $accessToken = $stagebloc->accessToken($_GET['code']);
		$content = 
			'<h3>Successfully authenticated!</h3><p>Your access token is: "' . $accessToken['access_token'] . '".</p>' .
			'<p>Please place this in your config.php file and then <a href="index.php">start developing</a>!</p>';
	}
	catch ( Services_StageBloc_Invalid_Http_Response_Code_Exception $e )
	{
		die($e->getHttpBody());
	}
}
else
{
	$authUrl = $stagebloc->getAuthorizeUrl(array('scope' => 'non-expiring'));
	$content = '<p><a class="btn btn-large btn-block btn-primary" href="' . $authUrl . '">Connect With StageBloc</a></p>';
}
?>
<!DOCTYPE html>
<html>
	<head>
		<title>StageBloc Local Theme Development - Authentication</title>
		
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
		<script src="assets/js/bootstrap.min.js"></script>
		<script src="assets/js/main.js"></script>

		<link rel="stylesheet" type="text/css" href="assets/css/main.css" />
		<link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="assets/css/bootstrap-responsive.min.css" />
	</head>

	<body id="authentication">
		<div class="hero-unit notification">
			<h2 class="center muted inset">Authentication</h2>
			<?php echo $content; ?>
		</div>
	</body>
</html>