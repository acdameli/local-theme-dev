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

	// Change the size of the iFrame for mobile vs non-mobile
	$('#mobile').change(function()
	{
		$('#renderedTheme').toggleClass('mobile', $(this).val() === 'true');
	})

	// Make sure the user actually wants to update their theme
	$('#updateTheme').submit(function()
	{
		return confirm('Are you sure you want to update your theme?');
	});
});