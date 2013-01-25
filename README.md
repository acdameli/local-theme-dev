# StageBloc Local Theme Development

## Introduction

We've developed quite an extensive theming engine at StageBloc. That being said, sometimes creating themes within the browser can be a pain. We've created this codebase to allow for much easier local theme development via our API. By submitting a theme to our API, we'll render it into `HTML` and send it back.

This codebase makes use of our [StageBloc PHP API Wrapper](https://github.com/stagebloc/php-stagebloc-api).

## Getting Started

To be able to develop themes locally, you'll first need to [sign up for a StageBloc account](http://www.stagebloc.com/signup). If you've already done that, you'll need to [register an application](http://stagebloc.com/account/admin/management/developers/) in order to receive a client ID and secret.

1. Once you've cloned the repo, run the following commands to get the resources for the [StageBloc PHP API Wrapper](https://github.com/stagebloc/php-stagebloc-api):

		git submodule init
		git submodule update

2. First, rename `config-sample.php` to `config.php` and fill in the client ID, client secret, and redirect URL from the application you created on your StageBloc account.

3. Then, create a `data.txt` file in the root folder of the repo. This will hold various information used to switch between accounts.
  
4. Next, load `/authentication.php` in a browser and click the link to make a connection. Once you've logged into your StageBloc account, you'll be redirected back to your local code, and your auth token will appear. Paste this code into your `config.php` file.
       
5. That's it! You can edit your theme in your editor of choice. Simply refresh the `index` page of this repo to see updates.

## General Information
* **Adding themes**: Themes are stored in the `/themes/` directory. Simply add a folder there with an `HTML`, `CSS`, and `JS` file to populate your new theme in the dropdown selector.


## Feedback And Questions

Found a bug or missing a feature? Don't hesitate to create a new issue here on GitHub.