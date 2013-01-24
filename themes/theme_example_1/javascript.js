var firstClicked = false;
$('document').ready(function()
{
	$('.video-block a').click(function(e)
	{
		e.preventDefault();
		$(this).parents('.video-block').siblings('.current').removeClass('current').end().addClass('current');
		if ( firstClicked )
		{
			var pos = $('.video-container').offset();
			$('body').animate({ scrollTop: pos.top - 40});
		}
		else
		{
			firstClicked = true;
		}
		$('.video-container').html(sbVideoList[$(this).attr('data-id')]);
	}).first().click();

	$('.description:not(.long)').truncate();

	$('#playlist-tracks').each(function()
	{
		var self = this;

		$(".jp-jplayer").jPlayer({
			swfPath: "//stagebloc.com/assets/js/jplayer/",
			wmode: "transparent",
			supplied: "mp3",
			cssSelectorAncestor: "#jp_container"
		})
		.bind($.jPlayer.event.timeupdate + " " + $.jPlayer.event.loadedmetadata, function(event)
		{
			var status = event.jPlayer.status;
			$('#jp_container .jp-time-left').html($.jPlayer.convertTime(status.duration - status.currentTime));
		})
		.bind($.jPlayer.event.ended, function(event)
		{
			if ( $('#playlist-tracks li.current').next().length )
			{
				$('#playlist-tracks li.current').next().click();
			}
			else
			{
				$('li.current', self).removeClass('current');
				$(this).jPlayer("clearMedia");
			}
		})
		.bind($.jPlayer.event.play, function(event)
		{
			$(this).jPlayer('pauseOthers');
		});

		$('li', self).click(function()
		{
			$('.jp-jplayer').jPlayer('pauseOthers').jPlayer("setMedia", { mp3: $(this).attr('data-url') }).jPlayer("play");
			$('#playlist-tracks li.current').removeClass('current');
			$(this).addClass('current');
		});

		$('.jp-controls .jp-previous').click(function()
		{
			if ( $('li.current', self).prev().length )
				$('li.current', self).prev().click();
			else
				$('li', self).last().click();
		});
		$('.jp-controls .jp-next').click(function()
		{
			if ( $('li.current', self).next().length )
				$('li.current', self).next().click();
			else
				$('li', self).first().click();
		});
	});

	if ( $('#playlist-tracks').length )
	{
		$('#playlist-tracks').first().each(function()
		{
			$(this).find('.jp-jplayer').jPlayer("setMedia", { mp3: $('li', this).first().addClass('current').attr('data-url') });
		});
	}
	else if ( $('.jp-audio-single').length )
	{
		 $('.jp-audio-single').each(function()
		 {
		 	var id = $(this).attr('data-id') !== undefined ? $(this).attr('data-id') : '',
		 		url = $(this).attr('data-url');

		 	$("#jquery_jplayer" + id).jPlayer({ ready: function() { $(this).jPlayer("setMedia", { mp3: url }); },
		 		swfPath: "//stagebloc.com/assets/js/jplayer/", wmode: "transparent", supplied: "mp3", cssSelectorAncestor: "#jp_container" + id })
			.bind($.jPlayer.event.timeupdate + " " + $.jPlayer.event.loadedmetadata, function(event)
			{ $('#jp_container' + id + ' .jp-time-left').html($.jPlayer.convertTime(event.jPlayer.status.duration - event.jPlayer.status.currentTime)); })
			.bind($.jPlayer.event.play, function() { $(this).jPlayer("pauseOthers"); });
		});
	}

	$('#playlist-tracks li a').click(function(e)
	{
		e.stopPropagation();
	});

	if ( $('.toggleLyrics').length )
	{
		$('.lyrics').hide();
		$('.toggleLyrics').click(function(e)
		{
			e.preventDefault();
			$('.lyrics').toggle();
			$(this).html(($(this).html() == 'Lyrics' ? 'Hide ' : '') + 'Lyrics');
		})
	}
});

// Truncater, yo
(function(a){function h(b){var c=a(b);var d=c.children(":last");if(d&&d.is("p"))return d;return b}function g(b){var c=a(b);var d=c.children(":last");if(!d)return b;var e=d.css("display");if(!e||e=="inline")return c;return g(d)}function f(a){return a.replace(/\s+/g," ")}function e(c,d){var e=f(c.data);if(b)e=e.replace(/^ /,"");b=!!e.match(/ $/);var e=e.slice(0,d);e=a("<div/>").text(e).html();return e}function d(b,d){var b=a(b);var e=b.clone().empty();var f;b.contents().each(function(){var a=d-e.text().length;if(a==0)return;f=c(this,a);if(f)e.append(f)});return e}function c(a,b){return a.nodeType==3?e(a,b):d(a,b)}var b=true;a.fn.truncate=function(b){var d=a.extend({},a.fn.truncate.defaults,b);a(this).each(function(){var b=a.trim(f(a(this).text())).length;if(b<=d.max_length)return;var e=d.max_length-d.more.length-3;var i=c(this,e);var j=a(this).hide();i.insertAfter(j);g(i).append(' ... <a class="showmore" href="#show more content">'+d.more+"</a>");h(j).append(' ... <a class="showless" href="#show less content">'+d.less+"</a>");i.find("a:last").click(function(){i.hide();j.show();return false});j.find("a:last").click(function(){i.show();j.hide();return false})})};a.fn.truncate.defaults={max_length:160,more:"more",less:"less"};})(jQuery)