$(function()
{
	$('.liking').click(function() { $(this).toggleClass('active', $(this).html() == 'Unlike'); })
	.each(function() { $(this).toggleClass('active', $(this).html() == 'Unlike'); });
});