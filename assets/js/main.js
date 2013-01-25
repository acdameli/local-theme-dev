$(function() {
	$('#theme').change(function() {
		$.cookie('theme', $(this).val(), {expires: 30}); // Store this theme as the one to use
		document.getElementById('renderedTheme').contentDocument.location.reload(true);
	});

	$('#account').change(function() {
		$.cookie('account', $(this).val(), {expires: 30}); // Store this account ID as the one to use

		// Make a call to change the account registered for our OAuth token
		$.ajax({
			url: 'change_account.php',
			type: 'GET',
			data: { 'account_id' : $(this).val() },
			success: function() {
				document.getElementById('renderedTheme').contentDocument.location.reload(true);
			}
		});
	});
});