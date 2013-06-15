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

2. Rename `config-sample.php` to `config.php` and run `chmod 777 config.php` so that we can populate it with your access token upon authentication and other data.

3. That's it! You can edit themes in your IDE / text editor of choice. Simply load the `index` page of this project in your browser to see your themes as you would with any localhost.

## General Information
* **Adding themes**: Themes are stored in the `/themes/` directory. Simply add a folder there with an `HTML`, `CSS`, and `JS` file to populate your new theme in the dropdown selector. The `.sbt` extension is used for `HTML` files, meaning "StageBloc Theme".

## Feedback And Questions

Found a bug or missing a feature? Don't hesitate to create a new issue here on GitHub.
