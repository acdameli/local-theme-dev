# StageBloc Local Theme Development

## Introduction

We've developed quite an extensive theming engine at StageBloc. That being said, sometimes creating themes within the browser can be a pain. We've created this codebase to allow for much easier local theme development via our API. By submitting a theme to our API, we'll render it into `HTML` and send it back.

This codebase makes use of our [StageBloc PHP API Wrapper](https://github.com/stagebloc/php-stagebloc-api).

## Requirements
1. In order to use this for local theme development, you'll need a localhost set up capable of running PHP.

2. You'll also need to [sign up for StageBloc](http://stagebloc.com/signup) and be an admin for at least one account.

## Getting Started

1. Once you've cloned the repo, run the following commands from the project's root directory to get the resources for the [StageBloc PHP API Wrapper](https://github.com/stagebloc/php-stagebloc-api) and [Bender](https://github.com/stagebloc/bender):

		git submodule init
		git submodule update

	1a. We've noticed issues may occur sometimes on Windows (or with WAMP) where the cURL requests made by the PHP API wrapper have SSL authentication issues. In that case you can add the following lines to `StageBloc.php` in the `_request()` method:
	
		$options[CURLOPT_SSL_VERIFYPEER] = false;
		$options[CURLOPT_SSL_VERIFYHOST] = false;
        	
2. Rename `config-sample.php` to `config.php` and run `chmod 777 config.php` so that we can populate it with your access token upon authentication and other data.

3. You can edit themes in your IDE / text editor of choice.
4. Run a local server by typing `php -S localhost:80` (linux or OSX) or any available port if 80 is not available, you may need to run this command as the super user (`sudo php -S localhost:80`). This requires you have php version 5.4.0 or newer installed.
5. Simply load `http://localhost/index.php` in your browser to see your themes (if you opted to use a port other than 80 on the previous line load `http://localhost:YOURPORT/index.php` instead).

## General Information
* **Adding themes**: Themes are stored in the `/themes/` directory. Simply add a folder there with an `HTML`, `CSS`, and `JS` file to populate your new theme in the dropdown selector. The `.sbt` extension is used for `HTML` files, meaning "StageBloc Theme". The theme path can be change in the `config.php` file using the `$themePath` variable.

* **Theme Views**: If you don't like having your entire site contained within a single `theme.sbt` file, you can create your own theme view structure using `theme.sbt` as the "manifest" (a list of .sbt files).  Set the `$themeViewsPath` config variable to a path relative to your theme, and save your `<theme_view_name>.sbt` files within that path.  The main `theme.sbt` file will now become your manifest, within which you can include individual views in the following manner:

		{Include file="[ optional/path/to/ ] <theme_view_name>.sbt"}

## Feedback And Questions

Found a bug or missing a feature? Don't hesitate to create a new issue here on GitHub.
