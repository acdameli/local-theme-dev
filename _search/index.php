<?php

if ('post' !== strtolower($_SERVER['REQUEST_METHOD'])) {
    header('HTTP/1.1 400 Bad Request', true, 400);
    exit;
}

require_once '../config.php';

// Get the account data returned by the API
$accounts = json_decode($accountData);
$accounts = $accounts->response->items;
$accountUrl = null;

$accountToUse = ( isset($_COOKIE['account']) ? $_COOKIE['account'] : $accounts[0]->id ); // Default to use the first account
foreach ($accounts as $account) { // Get the URL of the account we're currently looking at
    if ($accountToUse == $account->id) {
        $accountUrl = $account->stagebloc_url;
        break;
    }
}

$ch = curl_init("http://stagebloc.com/{$accountUrl}/_search");


curl_setopt($ch, CURLOPT_POST, true);
curl_setopt($ch, CURLOPT_POSTFIELDS, $_POST);
curl_setopt($ch, CURLOPT_FOLLOWLOCATION, true);
curl_setopt($ch, CURLOPT_HEADER, true);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
curl_setopt($ch, CURLOPT_USERAGENT, $_SERVER['HTTP_USER_AGENT']);

$response = curl_exec($ch);
$header_size = curl_getinfo($ch, CURLINFO_HEADER_SIZE);
$header = substr($response, 0, $header_size);
$contents = substr($response, $header_size);

curl_close($ch);

// Propagate headers to response.
foreach (preg_split('/[\r\n]+/', $header) as $header) {
    if (preg_match('/^(?:Content-Type|Content-Language|Set-Cookie):/i', $header)) {
        header($header);
    }
}

echo $contents;
