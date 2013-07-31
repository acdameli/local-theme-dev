$(function()
{
	$('#theme').change(function()
	{
		$.cookie('theme', $(this).val(), {expires: 30}); // Store this theme as the one to use
		document.getElementById('renderedTheme').contentDocument.location.reload(true);
	});

	$('#account').change(function()
	{
		$.cookie('account', $(this).val(), {expires: 30}); // Store this account ID as the one to use

		// Make a call to change the account registered for our OAuth token
		$.ajax({
			url: 'change_account.php',
			type: 'GET',
			data: { 'account_id' : $(this).val() },
			success: function()
			{
				document.getElementById('renderedTheme').contentDocument.location.reload(true);
			}
		});
	});
	
	$('#actions').change(function()
	{		
		if ( $(this).val() == 'logout' )
		{
			$.ajax({
				url: 'logout.php',
				success: function(data)
				{
					window.location.reload(); // Reload the page to get the login prompt back
				}
			});
		}
		else if ( $(this).val() == 'reset' )
		{
			$.removeCookie('account');
			$.removeCookie('account');
			window.location.reload();
		}
		else if ( $(this).val() == 'generate' )
		{
			var form = $(this).parents('form');
			form.attr('action', form.attr('action') + '?generate=1');
			form.submit();
		}
	});

	// Change the size of the iFrame for mobile vs non-mobile
	$('#mobile').change(function()
	{
		$.cookie('mobile', $(this).val(), {expires: 30}); // Store this account ID as the one to use
		$('#renderedTheme').toggleClass('mobile', $(this).val() === 'true');
	})
	$('#renderedTheme').toggleClass('mobile', $.cookie('mobile') == 'true');

	// Make sure the user actually wants to update their theme
	$('#updateTheme').submit(function()
	{
		if ( $(this).attr('action').indexOf('generate=1') == -1 )
		{
			return confirm('Are you sure you want to update your theme?');
		}
	});
});