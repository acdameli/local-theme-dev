<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		{CSS}
		{jQuery}
		{jPlayer}
	</head>
	<body>
		<header id="masthead">
			{module:Navigation}
				<nav id="main-nav">
					<ol>
					{block:NavigationItem}
						<li class="{CSSClass}"><a href="{Url}">{LinkText}</a></li>
					{/block:NavigationItem}
					</ol>
				</nav>
			{/module:Navigation}
			<h1><a href="{Link-Home}">{AccountName}</a></h1>
		</header>

		<div id="content">
			{page:ActivityStreamList}
				<div id="main-content">
					{module:ActivityStreamList embedWidth="610" supported="audio,blog,events,photos,statuses,videos,store"}
						{block:ActivityStreamView}
							<article class="post {ActivityCSSClass}">
								<header>
									{if:ActivityIsBlog||ActivityIsAudio||ActivityIsVideo||ActivityIsAudio||ActivityIsStoreItem}
										<h1><a href="{ActivityUrl}">{ActivityTitle}</a></h1>
									{/if:ActivityIsBlog||ActivityIsAudio||ActivityIsVideo||ActivityIsAudio||ActivityIsStoreItem}

									<div class="post-date">
										<span class="post-day">{ActivityDate format="D"}</span>
										<span class="post-month">{ActivityDate format="M j"}</span>
									</div>
								</header>

								<div class="post-body">
									{if:ActivityIsBlog||ActivityIsVideo||ActivityIsStoreItem}
										{ActivityExcerpt}
									{/if:ActivityIsBlog||ActivityIsVideo||ActivityIsStoreItem}
									{if:ActivityIsAudio}
										<div id="jquery_jplayer{ActivityId}" class="jp-jplayer"></div>
										<div id="jp_container{ActivityId}" class="jp-audio jp-audio-single jp-audio-activity-stream" data-id="{ActivityId}" data-url="{ActivityExcerpt}">
											<ul class="jp-controls">
												<li><a href="javascript:;" class="jp-play" tabindex="1"><span>play</span></a></li>
												<li><a href="javascript:;" class="jp-pause" tabindex="1"><span>pause</span></a></li>
											</ul>
											<div class="jp-current-time"></div>
											<div class="jp-progress"><div class="jp-seek-bar"><div class="jp-play-bar"></div></div></div>
											<div class="jp-time-left"></div>
											<div class="clear"></div>
										</div>
									{/if:ActivityIsAudio}
									{if:ActivityIsStatus}
										<a href="{ActivityUrl}"><span class="status-quote-mark">&ldquo;</span>{ActivityExcerpt}</a>
									{/if:ActivityIsStatus}
									{if:ActivityIsEvent}
										{ActivityTitle}
									<div>
										{module:EventList upcoming="true" past="true" dateadded="{ActivityDate format="gmdate"}" limit="8"}
											<ol class="events-list compact">
											{block:EventView}
												<li onclick="window.location = '{EventUrl}';">
													<span class="event-date">{ActivityDate format="M j"}</span>
													<span class="event-location">{EventLocation}</span>
													<small class="event-actions">
														 <a class="event-more" href="{EventUrl}">more info</a>
													 </small>
												</li>
											{/block:EventView}
											</ol>
										{/module:EventList}
									</div>
									{/if:ActivityIsEvent}

									{if:ActivityIsPhotoAlbum}
									<div>
										New photos added to <a href="{ActivityUrl}">{ActivityTitle}</a>!
										{module:PhotoList limit="6" albumid="{ActivityId}"}
										<div id="photos-list-compact">
											{block:PhotoView}
												<a href="{PhotoUrl}"><img src="{Source-Thumb}" width="98px" height="98px" /></a>
											{/block:PhotoView}
										</div>
										{/module:PhotoList}
									</div>
									{/if:ActivityIsPhotoAlbum}
									{if:ActivityIsRepost}
										<div class="repost">
											<a href="{RepostedFromAccountUrl}" class="repost-account-image-link">
												<img src="{RepostedFromAccountPhotoUrl}" class="repost-account-image" />
											</a>
											<div class="repost-info">
												<a href="{RepostedFromAccountUrl}">{RepostedFromAccountName}</a>
												{RepostedContentTimeAgo}
											</div>
										</div>
									{/if:ActivityIsRepost}

								{if:ReadMore}
									<a class="read-more" href="{ActivityUrl}">read more</a>
								{/if:ReadMore}

								</div>

								<footer>
									<span class="footer-info">
										{LikeLink likeText="Like" unlikeText="Unlike"}
										{if:ActivityIsBlog}
											{RepostLink repostText="Repost" unRepostText="UnRepost"}
										{/if:ActivityIsBlog}
										{if:ActivityIsStatus}
											{RepostLink repostText="Repost" unRepostText="UnRepost"}
										{/if:ActivityIsStatus}
										{if:ActivityIsPhotoAlbum}
											{ActivityPhotoCount} photos <a href="{ActivityUrl}">view all photos</a>
										{/if:ActivityIsPhotoAlbum}
									</span>
									<a href="{ActivityUrl}" class="datetime-link" title="{ActivityDate format="D M j Y g:i A"}">{ActivityDate format="relative"}</a>
								</footer>
							</article>
						{/block:ActivityStreamView}
					{Else}
						<article class="post nocontent">
							<p>Oh snap! We haven't written anything yet.</p>
						</article>
					{/module:ActivityStreamList}

					{module:Pagination}
						<div id="pagination">
							{block:NextPage}
							<a href="{NextPage}">Older</a>
							{/block:NextPage}
							{block:PreviousPage}
							<a href="{PreviousPage}">Newer</a>
							{/block:PreviousPage}
						</div>
					{/module:Pagination}

				</div>
				<div id="sub-content">
					{module:EventList upcoming="true" past="false" limit="8"}
						<section id="events-sidebar">
							<h2>Events</h2>
							<a href="{Link-Events}" class="events-viewall">more</a>
								<ol>
									{block:EventView}
									<li>
										<a href="{EventUrl}">
											<span class="event-date">{EventStartDate format="M j"}</span>
											<span class="event-location">{EventLocation}</span>
										</a>
									</li>
									{/block:EventView}
								</ol>
						</section>
					{/module:EventList}

					<section id="photos-sidebar">
						{module:PhotoList limit="9"}
							<div>
								{block:PhotoView}
									<a href="{PhotoUrl}"><img src="{Source-Thumb}" alt="{PhotoTitle}" title="{PhotoTitle}" width="60" height="60" /></a>
								{/block:PhotoView}
							</div>
							<a class="text-right photos-viewall" href="{Link-Photos}">more photos</a>
						{/module:PhotoList}
					</section>
				</div>
			{/page:ActivityStreamList}
			{page:BlogView}
				<div id="main-content" class="individual-blog-content">
					{module:BlogView}
						{block:BlogPost}
							<article class="post blog individual">
								<header>
									<h1>{BlogPostTitle}</h1>
								</header>
								{BlogPostBody}
								<footer>
									<span class="footer-info">
										{LikeLink likeText="Like" unlikeText="Unlike"}
										{RepostLink repostText="Repost" unRepostText="UnRepost"}
									</span>
									{PublishedDate format="D M j Y g:i A"}
								</footer>
								<div id="single-item-navigation">
									{if:HasNextBlogPost}
									<div><a href="{NextBlogPostUrl}">Newer</a></div>
									{/if:HasNextBlogPost}
									{if:HasPreviousBlogPost}
									<div><a href="{PreviousBlogPostUrl}">Older</a></div>
									{/if:HasPreviousBlogPost}
								</div>
							</article>
							{if:HasTags}
								<section id="tags-sidebar" class="blog-post-tags">
									<h2>Tags</h2>
									{module:TagList}
										{block:TagView}
											<span class="tag">{Tag}</span>
										{/block:TagView}
									{/module:TagList}
								</section>
							{/if:HasTags}
						{/block:BlogPost}
					{Else}
						<div class="nocontent">
							<p>Sorry, but this blog post was not found in our system!</p>
						</div>
					{/module:BlogView}
				</div>
			{/page:BlogView}

			{page:StatusView}
				<div id="main-content" class="individual-blog-content">
					{module:StatusView}
						{block:StatusPost}
							<article class="post status individual">
								<div class="post-body">
									<a><span class="status-quote-mark">&ldquo;</span>{StatusText}</a>
								</div>
								<footer>
									<span class="footer-info">
										{LikeLink likeText="Like" unlikeText="Unlike"}
										{RepostLink repostText="Repost" unRepostText="UnRepost"}
									</span>
									{CreatedDate format="D M j Y g:i A"}
								</footer>
							</article>
						{/block:StatusPost}
					{Else}
						<div class="nocontent">
							<p>Sorry, but this status was not found in our system!</p>
						</div>
					{/module:StatusView}
				</div>
			{/page:StatusView}

			{page:StoreItemList}
				{module:StoreItemList}
					<div id="photo-albums">
						<h2>Store Items</h2>
						<ul>
							{block:StoreItemView}
							<li>
								<a href="{StoreItemUrl}">
									<img src="{StoreItemPhotoUrl}" alt="{StoreItemTitle} cover" title="{StoreItemTitle} cover" width="100" height="100" />
									<span class="title">{StoreItemTitle}</span>
									{if:StoreItemCanBeSold}
									${StoreItemPrice}
									{if:Else}
										{if:StoreItemCanBeDownloadedForFree}
											Free
										{/if:StoreItemCanBeDownloadedForFree}
									{/if:StoreItemCanBeSold}
								</a>
							</li>
							{/block:StoreItemView}
						</ul>
					</div>
				{Else}
					<div class="nocontent">
						<p>No store items yet! Check back later. :)</p>
					</div>
				{/module:StoreItemList}
				{module:Pagination}
					<div id="pagination">
						{block:NextPage}<a href="{NextPage}">Older</a>{/block:NextPage}
						{block:PreviousPage}<a href="{PreviousPage}">Newer</a>{/block:PreviousPage}
					</div>
				{/module:Pagination}
			{/page:StoreItemList}
			{page:StoreItemView}
				{module:StoreItemView}
					<div class="audio-container">
						{block:StoreItemView}
							<div class="single-info storeItem">
								{if:StoreItemCanBeSold}
								<div class="buy-link">
									{StoreItemAddToCartLink storeitemid="{StoreItemId}" text="Buy Now"}<br />
									{if:StoreItemCanNamePrice}min {/if:StoreItemCanNamePrice}${StoreItemPrice} USD
								</div>
								{/if:StoreItemCanBeSold}
								{if:StoreItemCanBeDownloadedForFree}
								<div class="buy-link">
									{StoreItemFreeDownloadLink storeitemid="{StoreItemId}" text="Download"}
								</div>
								{/if:StoreItemCanBeDownloadedForFree}
								<h2>{StoreItemTitle}</h2>

								<div class="description long">
									<img class="inlineImage" src="{StoreItemPhotoUrl size="small"}" />
									{StoreItemDescription}
								</div>
							</div>

							{if:HasTags}
							<section id="tags-sidebar" class="store-tags">
								<h2>Tags</h2>
								{module:TagList}
									{block:TagView}
										<span class="tag">{Tag}</span>
									{/block:TagView}
								{/module:TagList}
							</section>
							{/if:HasTags}
						{/block:StoreItemView}
					</div>
				{Else}
				<div class="nocontent">
					<p>Sorry, but this store item was not found in our system!</p>
				</div>
				{/module:StoreItemView}
			{/page:StoreItemView}

			{page:EventUpcomingList}
				<div id="events-container">
					{module:EventList upcoming="true" past="false"}
						<h2>Upcoming Events</h2>
						{if:HasPastEvents}
						<a href="{Link-EventsPast}" id="other-events">view past events</a>
						{/if:HasPastEvents}
						<ol class="events-list">
							{block:EventView}
							<li onclick="window.location = '{EventUrl}';">
								<span class="event-date">{EventStartDate format="M j"}</span>
								<span class="event-venue"><a href="{EventUrl}">{VenueName}</a></span>
								<span class="event-location">{EventLocation}</span>
								 <span class="event-actions">
									 {if:EventHasTicketsBuyLink}
									 <a class="event-tickets" href="{TicketsBuyLink}">tickets</a>
									 {/if:EventHasTicketsBuyLink}
									 <a class="event-more" href="{EventUrl}">more</a>
								 </span>
							</li>
							{/block:EventView}
						</ol>
					{Else}
						<h2>Upcoming Events</h2>
						{if:HasPastEvents}
						<a href="{Link-EventsPast}" id="other-events">view past events</a>
						{/if:HasPastEvents}
						<div class="nocontent">
							<p>Uh oh! No upcoming events...</p>
						</div>
					{/module:EventList}

					{module:Pagination}
						<div id="pagination">
							{block:NextPage}
							<a href="{NextPage}">Older</a>
							{/block:NextPage}
							{block:PreviousPage}
							<a href="{PreviousPage}">Newer</a>
							{/block:PreviousPage}
						</div>
					{/module:Pagination}
				</div>
			{/page:EventUpcomingList}

			{page:EventPastList}
				<div id="events-container">
					{module:EventList upcoming="false" past="true" order="desc"}
						<h2>Past Events</h2>
						<a href="{Link-Events}" id="other-events">view upcoming events</a>
						<ol class="events-list">
							{block:EventView}
							<li onclick="window.location = '{EventUrl}';">
								<span class="event-date">{EventStartDate format="M j"}</span>
								<span class="event-venue"><a href="{EventUrl}">{VenueName}</a></span>
								<span class="event-location">{EventLocation}</span>
								 <span class="event-actions">
									 <a class="event-more" href="{EventUrl}">more</a>
								 </span>
							</li>
							{/block:EventView}
						</ol>
					{Else}
						<h2>Past Events</h2>
						<a href="{Link-Events}" id="other-events">view upcoming events</a>
						<div class="nocontent">
							<p>Uh oh! No past events...</p>
						</div>
					{/module:EventList}

					{module:Pagination}
						<div id="pagination">
							{block:NextPage}
							<a href="{NextPage}">Older</a>
							{/block:NextPage}
							{block:PreviousPage}
							<a href="{PreviousPage}">Newer</a>
							{/block:PreviousPage}
						</div>
					{/module:Pagination}
				</div>
			{/page:EventPastList}

			{page:EventView}
					{module:EventView}
						<div id="event">
							{block:EventView}
								<div id="event-datetime">
									<span id="event-month">{EventStartDate format="M"}</span>
									<span id="event-day">{EventStartDate format="j"}</span>
									<span id="event-year">{EventStartDate format="Y"}</span>

									<div id="event-datetime-sub">
										{EventStartDate format="l"}<br />
										{EventStartDate format="g:i a"}<br />
										{if:EventHasMinimumAge}{EventAges}{/if:EventHasMinimumAge}
									</div>
								</div>

								<div class="event-details">
									<div id="event-header">
										{if:EventHasTitle}
										<span id="event-venue">{EventTitle}</span>, {VenueName},
										{if:Else}
										<span id="event-venue">{VenueName}</span>,
										{/if:EventHasTitle}
										{EventLocation}
									</div>
									{if:HasSupportingActs}
									<div class="event-details">
										<span id="event-supportingacts">with {SupportingActs}</span>
									</div>
									{/if:HasSupportingActs}
									<div class="event-details">
										{if:EventHasPrice}
										<span id="event-price">${EventPrice}</span>
										{/if:EventHasPrice}
										{if:EventHasTicketsBuyLink}
										<a href="{TicketsBuyLink}">buy tickets</a>
										{/if:EventHasTicketsBuyLink}
										<div id="event-description">{EventDescription}</div>
									</div>
								</div>
								{if:HasTags}
									<section id="tags-sidebar" class="event-tags">
										<h2>Tags</h2>
										{module:TagList}
											{block:TagView}
												<span class="tag">{Tag}</span>
											{/block:TagView}
										{/module:TagList}
									</section>
								{/if:HasTags}
							{/block:EventView}
					{Else}
						<div class="nocontent">
							<p>Sorry, this event wasn't found in our system!</p>
						</div>
					{/module:EventView}
				</div>
			{/page:EventView}

			{page:VideoList}
				{module:VideoList featured="true" embedWidth="660"}
					<div class="video-container">
					{block:VideoView}
						<div class="video">
							{VideoEmbedCode}
							<h2><a href="{VideoUrl}">{VideoTitle}</a></h2>
							{VideoDescription}
						</div>

						{if:HasTags}
						<section id="tags-sidebar" class="photo-tags">
							<h2>Tags</h2>
							{module:TagList}
								{block:TagView}
									<span class="tag">{Tag}</span>
								{/block:TagView}
							{/module:TagList}
						</section>
						{/if:HasTags}
					{/block:VideoView}
					</div>
				{/module:VideoList}

				{module:VideoPlaylistList}
					<div id="video-playlist-list-container">
						<h2>Playlists</h2>
						{block:VideoPlaylistView}
							<div class="playlist-block" id="playlist{AudioPlaylistId}"><a href="{VideoPlaylistUrl}">
								{if:VideoPlaylistHasThumbnail}
									<img src="{VideoPlaylistPhotoUrl}" title="{VideoPlaylistTitle}" />
								{/if:VideoPlaylistHasThumbnail}
								<span class="total">{VideoPlaylistVideoCount} videos</span>
								<h3>{VideoPlaylistTitle}</h3>
							</a></div>
						{/block:VideoPlaylistView}
					</div>
				{Else}
					<div class="nocontent">
						<p>Sorry, but we haven't added any video playlists yet!</p>
					</div>
				{/module:VideoPlaylistList}
			{/page:VideoList}

			{page:VideoPlaylistView}
				{module:VideoPlaylistView}
					{block:VideoPlaylistView}
						<div class="video-container"></div>
						<div id="playlistTitle">
							Playlist: <span class="title">{VideoPlaylistTitle}</span><span class="total">{VideoPlaylistVideoCount} videos</span>
						</div>
						<div id="video-grid">
						{module:VideoList videoplaylistid="{VideoPlaylistId}" limit="12" embedWidth="660"}
							<script>
							sbVideoList = {};
							$(document).ready(function()
							{
								$('#video-playlist-list-container #block{VideoPlaylistId}').attr('id','current');
							});
							</script>
							{block:VideoView}
								<div class="video-block"><a href="{VideoUrl}" data-id="{VideoId}">
									<div class="image"><img src="{VideoThumbnailUrl}" title="{VideoTitle}" /></div>
									<span>{VideoTitle}</span><span class="nowPlaying">Now Playing</span>
								</a></div>

								<script>sbVideoList[{VideoId}] = '<div class="video">{VideoJavaScriptEscapedEmbedCode}<h2><a href="{VideoUrl}">{VideoJavaScriptEscapedTitle}</a></h2>{VideoJavaScriptEscapedDescription}</div>{if:HasTags block="VideoView"}<section id="tags-sidebar" class="photo-tags"><h2>Tags</h2>{module:TagList}{block:TagView}<span class="tag">{Tag}</span>{/block:TagView}{/module:TagList}</section>{/if:HasTags}'</script>
							{/block:VideoView}
						{/module:VideoList}
						{module:Pagination}
							<div id="pagination">
								{block:PreviousPage}
								<a href="{PreviousPage}">Newer</a>
								{/block:PreviousPage}
								{block:NextPage}
								<a href="{NextPage}">Older</a>
								{/block:NextPage}
							</div>
						{/module:Pagination}
						{if:HasTags}
						<section id="tags-sidebar" class="photo-tags">
							<h2>Tags</h2>
							{module:TagList}
								{block:TagView}
									<span class="tag">{Tag}</span>
								{/block:TagView}
							{/module:TagList}
						</section>
						{/if:HasTags}
						</div>
					{/block:VideoPlaylistView}
				{Else}
					<div class="nocontent">
						<p>Sorry, but this video playlist was not found in our system!</p>
					</div>
				{/module:VideoPlaylistView}

				{module:VideoPlaylistList}
					<div id="video-playlist-list-container">
						<h2>Playlists</h2>
						{block:VideoPlaylistView}
							<div class="playlist-block" id="block{VideoPlaylistId}"><a href="{VideoPlaylistUrl}">
								{if:VideoPlaylistHasThumbnail}
									<img src="{VideoPlaylistPhotoUrl}" title="{VideoPlaylistTitle}" />
								{/if:VideoPlaylistHasThumbnail}
								<span class="total">{VideoPlaylistVideoCount} videos</span>
								<h3>{VideoPlaylistTitle}</h3>
							</a></div>
						{/block:VideoPlaylistView}
					</div>
				{/module:VideoPlaylistList}
			{/page:VideoPlaylistView}

			{page:VideoView}
				{module:VideoView embedWidth="660"}
					<div class="video-container">
					{block:VideoView}
						<div class="video">
							{VideoEmbedCode}
							<h2><a href="{VideoUrl}">{VideoTitle}</a></h2>
							{VideoDescription}
						</div>

						{if:HasTags}
						<section id="tags-sidebar" class="photo-tags">
							<h2>Tags</h2>
							{module:TagList}
								{block:TagView}
									<span class="tag">{Tag}</span>
								{/block:TagView}
							{/module:TagList}
						</section>
						{/if:HasTags}
					{/block:VideoView}
					</div>
				{Else}
					<div class="nocontent">
						<p>Sorry, but this video was not found in our system!</p>
					</div>
				{/module:VideoView}
			{/page:VideoView}

			{page:PhotoList}
				{module:PhotoAlbumList}
					<div id="photo-albums">
						<h2>Photo Albums</h2>
						<ul>
							{block:PhotoAlbumView}
							<li>
								<a href="{PhotoAlbumUrl}">
									<img src="{PhotoAlbumPhotoUrl}" alt="{PhotoAlbumTitle} cover" title="{PhotoAlbumTitle} cover" width="100" height="100" />
									<span class="title">{PhotoAlbumTitle}</span>
									{PhotoAlbumPhotoCount} photos
								</a>
							</li>
							{/block:PhotoAlbumView}
						</ul>
					</div>
				{Else}
					<div class="nocontent">
						<p>No photos yet! Check back later. :)</p>
					</div>
				{/module:PhotoAlbumList}
			{/page:PhotoList}

			{page:PhotoView}
				{module:PhotoView}
					<div id="photo-container">
						{block:PhotoView}
							<img id="photo-image" src="{Source-Large}" alt="{PhotoTitle}" title="{PhotoTitle}" />
							<h1 id="photo-title">{PhotoTitle}</h1>
							<p id="photo-backtoalbum">{PhotoAlbumPhotoCount} photos in <a href="{PhotoAlbumUrl}">{PhotoAlbumTitle}</a></p>
							<div id="photo-description">
								{PhotoDescription}
							</div>
							<div id="single-item-navigation">
								{if:HasNextPhoto}
								<div><a href="{NextPhotoUrl}">Newer</a></div>
								{/if:HasNextPhoto}
								{if:HasPreviousPhoto}
								<div><a href="{PreviousPhotoUrl}">Older</a></div>
								{/if:HasPreviousPhoto}
							</div>
							<footer>
								<span class="footer-info">
									{LikeLink likeText="Like" unlikeText="Unlike"}
								</span>
								{CreatedDate format="D M j Y g:i A"}
							</footer>

							{if:HasTags}
								<section id="tags-sidebar" class="photo-tags">
									<h2>Tags</h2>
									{module:TagList}
										{block:TagView}
											<span class="tag">{Tag}</span>
										{/block:TagView}
									{/module:TagList}
								</section>
							{/if:HasTags}
						{/block:PhotoView}
					</div>
				{Else}
					<div class="nocontent">
						<p>Sorry, but this photo couldn't be found! <a href="{Link-Photos}">View other photos instead?</a></p>
					</div>
				{/module:PhotoView}
			{/page:PhotoView}

			{page:PhotoAlbumView}
				{module:PhotoAlbumView}
					<div id="main-content2">
						{block:PhotoAlbumView}
							<div class="photos-section">
								<div id="album-info">
									<img id="album-cover" src="{PhotoAlbumPhotoUrl}" alt="{PhotoAlbumTitle} cover" title="{PhotoAlbumTitle} cover" width="100" height="100" />
									<span id="album-title">{PhotoAlbumTitle}</span>
									<div id="album-last-updated">last updated {ModifiedDate format="F j, Y"}</div>
									<div id="description">{PhotoAlbumDescription}</div>
									<div id="album-photo-count">
										<span id="album-photo-count-number">{PhotoAlbumPhotoCount}</span>
										photos
									</div>
								</div>
							</div>
							{module:PhotoList albumid="{PhotoAlbumId}" limit="24"}
								<div class="photos-section">
									<div id="photos-list">
										{block:PhotoView}
										<a href="{PhotoUrl}" title="{PhotoTitle}"><img src="{Source-Thumb}" alt="{PhotoTitle}" title="{PhotoTitle}" width="100" height="100" /></a>
										{/block:PhotoView}
									</div>
								</div>
							{/module:PhotoList}

							{module:Pagination}
								<div id="pagination">
									{block:NextPage}
									<a href="{NextPage}">Older</a>
									{/block:NextPage}
									{block:PreviousPage}
									<a href="{PreviousPage}">Newer</a>
									{/block:PreviousPage}
								</div>
							{/module:Pagination}

							{if:HasTags}
								<section id="tags-sidebar" class="photo-album-tags">
									<h2>Tags</h2>
									{module:TagList}
										{block:TagView}
											<span class="tag">{Tag}</span>
										{/block:TagView}
									{/module:TagList}
								</section>
							{/if:HasTags}
						{/block:PhotoAlbumView}
					</div>
				{Else}
					<div class="nocontent">
						<p>Sorry, but this photo album was not found in our system!</p>
					</div>
				{/module:PhotoAlbumView}
			{/page:PhotoAlbumView}

			{page:AudioList}
				{module:AudioPlaylistList featured="true"}
					<div class="audio-container">
						{block:AudioPlaylistView}
							<div class="playlist-info">
								{if:AudioPlaylistHasThumbnail}
									<img src="{AudioPlaylistPhotoUrl}" class="cover-photo" title="{AudioPlaylistTitle}" />
								{/if:AudioPlaylistHasThumbnail}
								{if:AudioPlaylistCanBeSold}
								<div class="buy-link">
									{AudioPlaylistAddToCartLink audioPlaylistId="{AudioPlaylistId}" text="Buy Now"}<br />
									{if:AudioPlaylistCanNamePrice}min {/if:AudioPlaylistCanNamePrice}${AudioPlaylistPrice} USD
								</div>
								{/if:AudioPlaylistCanBeSold}
								{if:AudioPlaylistCanBeDownloadedForFree}
								<div class="buy-link">
									{AudioPlaylistFreeDownloadLink audioPlaylistId="{AudioPlaylistId}" text="Download"}<br />
									{AudioPlaylistFreeDownloadQuality}
								</div>
								{/if:AudioPlaylistCanBeDownloadedForFree}
								<h2>{AudioPlaylistTitle}</h2>
								<p>{AudioPlaylistArtist}</p>

								<div class="description">
									{AudioPlaylistDescription}
									<p>{if:AudioPlaylistHasReleaseDate}<strong>Released</strong> on {AudioPlaylistReleaseDate format="F n, Y"}{/if:AudioPlaylistHasReleaseDate}
									{if:AudioPlaylistHasLabel} by {AudioPlaylistLabel}{/if:AudioPlaylistHasLabel}

									{module:BuyLinkList}
										<span class="buy-links">{Block:BuyLinkView}<a href="{BuyLink}">{BuyLinkTitle}</a>{/Block:BuyLinkView}</span>
									{/module:BuyLinkList}</p>
								</div>
							</div>

							<div id="jquery_jplayer" class="jp-jplayer"></div>
							<div id="jp_container" class="jp-audio">
								<ul class="jp-controls">
									<li><a href="javascript:;" class="jp-previous" tabindex="1"><span>previous</span></a></li>
									<li><a href="javascript:;" class="jp-play" tabindex="1"><span>play</span></a></li>
									<li><a href="javascript:;" class="jp-pause" tabindex="1"><span>pause</span></a></li>
									<li><a href="javascript:;" class="jp-next" tabindex="1"><span>next</span></a></li>
								</ul>
								<div class="jp-current-time"></div>
								<div class="jp-progress"><div class="jp-seek-bar"><div class="jp-play-bar"></div></div></div>
								<div class="jp-time-left"></div>
								<div class="clear"></div>
							</div>

							{module:AudioList audioplaylistid="{AudioPlaylistId}"}
							<ul id="playlist-tracks">
								{block:AudioView}
								<li data-url="{AudioStreamUrl}">
									<span class="number">{AudioOrderNumber}</span>
									<span class="title">{AudioTitle}</span>
									<div class="links">
										{if:AudioCanBeSold}{AudioAddToCartLink audioId="{AudioId}" text="buy"}{/if:AudioCanBeSold}
										{if:AudioCanBeDownloadedForFree}{AudioFreeDownloadLink audioId="{AudioId}"}{/if:AudioCanBeDownloadedForFree}
										<a href="{AudioLink}">details</a>
									</div>
								</li>
								{/block:AudioView}
							</ul>
							{/module:AudioList}
							<script>$(function() { $('#playlist{AudioPlaylistId}').attr('id', 'current'); });</script>
						{/block:AudioPlaylistView}
					</div>
				{/module:AudioPlaylistList}

				{module:AudioPlaylistList}
					<div id="audio-playlist-list-container">
						<h2>Playlists</h2>
						{block:AudioPlaylistView}
							<div class="playlist-block" id="playlist{AudioPlaylistId}"><a href="{AudioPlaylistUrl}">
								{if:AudioPlaylistHasThumbnail}
									<img src="{AudioPlaylistPhotoUrl}" title="{AudioPlaylistTitle}" />
								{/if:AudioPlaylistHasThumbnail}
								<span class="total">{AudioPlaylistAudioCount} tracks</span>
								<h3>{AudioPlaylistTitle}</h3>
							</a></div>
						{/block:AudioPlaylistView}
					</div>
					{Else}
					<div class="nocontent">
						<p>We haven't uploaded any audio tracks yet, check back later!</p>
					</div>
				{/module:AudioPlaylistList}
			{/page:AudioList}

			{page:AudioPlaylistView}
				{module:AudioPlaylistView}
					<div class="audio-container">
						{block:AudioPlaylistView}
							<div class="playlist-info">
								{if:AudioPlaylistHasThumbnail}
									<img src="{AudioPlaylistPhotoUrl}" class="cover-photo" title="{AudioPlaylistTitle}" />
								{/if:AudioPlaylistHasThumbnail}
								{if:AudioPlaylistCanBeSold}
								<div class="buy-link">
									{AudioPlaylistAddToCartLink audioPlaylistId="{AudioPlaylistId}" text="Buy Now"}<br />
									{if:AudioPlaylistCanNamePrice}min {/if:AudioPlaylistCanNamePrice}${AudioPlaylistPrice} USD
								</div>
								{/if:AudioPlaylistCanBeSold}
								{if:AudioPlaylistCanBeDownloadedForFree}
								<div class="buy-link">
									{AudioPlaylistFreeDownloadLink audioPlaylistId="{AudioPlaylistId}" text="Download"}<br />
									{AudioPlaylistFreeDownloadQuality}
								</div>
								{/if:AudioPlaylistCanBeDownloadedForFree}
								<h2>{AudioPlaylistTitle}</h2>
								<p>{AudioPlaylistArtist}</p>

								<div class="description">
									{AudioPlaylistDescription}
									<p>{if:AudioPlaylistHasReleaseDate}<strong>Released</strong> on {AudioPlaylistReleaseDate format="F n, Y"}{/if:AudioPlaylistHasReleaseDate}
									{if:AudioPlaylistHasLabel} by {AudioPlaylistLabel}{/if:AudioPlaylistHasLabel}

									{module:BuyLinkList}
										<span class="buy-links">{Block:BuyLinkView}<a href="{BuyLink}">{BuyLinkTitle}</a>{/Block:BuyLinkView}</span>
									{/module:BuyLinkList}</p>
								</div>
							</div>

							<div id="jquery_jplayer" class="jp-jplayer"></div>
							<div id="jp_container" class="jp-audio">
								<ul class="jp-controls">
									<li><a href="javascript:;" class="jp-previous" tabindex="1"><span>previous</span></a></li>
									<li><a href="javascript:;" class="jp-play" tabindex="1"><span>play</span></a></li>
									<li><a href="javascript:;" class="jp-pause" tabindex="1"><span>pause</span></a></li>
									<li><a href="javascript:;" class="jp-next" tabindex="1"><span>next</span></a></li>
								</ul>
								<div class="jp-current-time"></div>
								<div class="jp-progress"><div class="jp-seek-bar"><div class="jp-play-bar"></div></div></div>
								<div class="jp-time-left"></div>
								<div class="clear"></div>
							</div>

							{module:AudioList audioplaylistid="{AudioPlaylistId}"}
							<ul id="playlist-tracks">
								{block:AudioView}
								<li data-url="{AudioStreamUrl}">
									<span class="number">{AudioOrderNumber}</span>
									<span class="title">{AudioTitle}</span>
									<div class="links">
										{if:AudioCanBeSold}{AudioAddToCartLink audioId="{AudioId}" text="buy"}{/if:AudioCanBeSold}
										{if:AudioCanBeDownloadedForFree}{AudioFreeDownloadLink audioId="{AudioId}"}{/if:AudioCanBeDownloadedForFree}
										<a href="{AudioLink}">details</a>
									</div>
								</li>
								{/block:AudioView}
							</ul>
							{Else}
							<div class="empty-audio-playlist">
								<p>Sorry, this playlist is empty.</p>
							</div>
							{/module:AudioList}
							<script>$(function() { $('#playlist{AudioPlaylistId}').attr('id', 'current'); });</script>

							{if:HasTags}
							<section id="tags-sidebar" class="photo-tags">
								<h2>Tags</h2>
								{module:TagList}
									{block:TagView}
										<span class="tag">{Tag}</span>
									{/block:TagView}
								{/module:TagList}
							</section>
							{/if:HasTags}
						{/block:AudioPlaylistView}
					</div>
				{Else}
					<div class="nocontent">
						<p>Sorry, but this playlist was not found in our system!</p>
					</div>
				{/module:AudioPlaylistView}

				{module:AudioPlaylistList}
					<div id="audio-playlist-list-container">
						<h2>Playlists</h2>
						{block:AudioPlaylistView}
							<div class="playlist-block" id="playlist{AudioPlaylistId}"><a href="{AudioPlaylistUrl}">
								{if:AudioPlaylistHasThumbnail}
									<img src="{AudioPlaylistPhotoUrl}" title="{AudioPlaylistTitle}" />
								{/if:AudioPlaylistHasThumbnail}
								<span class="total">{AudioPlaylistAudioCount} tracks</span>
								<h3>{AudioPlaylistTitle}</h3>
							</a></div>
						{/block:AudioPlaylistView}
					</div>
				{/module:AudioPlaylistList}
			{/page:AudioPlaylistView}

			{page:AudioView}
				{module:AudioView}
					<div class="audio-container">
						{block:AudioView}
							<div class="single-info">
								{if:AudioCanBeSold}
								<div class="buy-link">
									{AudioAddToCartLink audioId="{AudioId}" text="Buy Now"}<br />
									{if:AudioCanNamePrice}min {/if:AudioCanNamePrice}${AudioPrice} USD
								</div>
								{/if:AudioCanBeSold}
								{if:AudioCanBeDownloadedForFree}
								<div class="buy-link">
									{AudioFreeDownloadLink audioId="{AudioId}" text="Download"}<br />
									{AudioFreeDownloadQuality}
								</div>
								{/if:AudioCanBeDownloadedForFree}
								<h2>{AudioTitle}</h2>
								<p>{AudioArtist}</p>

								<div class="description">
									{AudioDescription}
									<p>{if:AudioHasRecordedDate}
										<strong>Recorded</strong> on {AudioRecordedDate format="F n, Y"}
									{/if:AudioHasRecordedDate}

									{module:BuyLinkList}
										<span class="buy-links">{Block:BuyLinkView}<a href="{BuyLink}">{BuyLinkTitle}</a>{/Block:BuyLinkView}</span>
									{/module:BuyLinkList}</p>
								</div>
							</div>

							<div id="jquery_jplayer" class="jp-jplayer"></div>
							<div id="jp_container" class="jp-audio jp-audio-single" data-url="{AudioStreamUrl}">
								<ul class="jp-controls">
									<li><a href="javascript:;" class="jp-play" tabindex="1"><span>play</span></a></li>
									<li><a href="javascript:;" class="jp-pause" tabindex="1"><span>pause</span></a></li>
								</ul>
								<div class="jp-current-time"></div>
								<div class="jp-progress"><div class="jp-seek-bar"><div class="jp-play-bar"></div></div></div>
								<div class="jp-time-left"></div>
								<div class="clear"></div>
							</div>
							{if:AudioHasLyrics}
							<a href="#lyrics" class="toggleLyrics">Lyrics</a><div class="lyrics">{AudioLyrics}</div>
							{/if:AudioHasLyrics}

							{if:HasTags}
							<section id="tags-sidebar" class="photo-tags">
								<h2>Tags</h2>
								{module:TagList}
									{block:TagView}
										<span class="tag">{Tag}</span>
									{/block:TagView}
								{/module:TagList}
							</section>
							{/if:HasTags}
						{/block:AudioView}
					</div>
				{Else}
				<div class="nocontent">
					<p>Sorry, but this audio file was not found in our system!</p>
				</div>
				{/module:AudioView}
			{/page:AudioView}

			{page:About}
				<div id="about-account">
					{module:AccountAbout}
						<div class="sub">
							{block:AccountPhoto}
								<img alt="{AccountName}'s account photo" id="account-photo" src="{PhotoSource-Small}" />
							{/block:AccountPhoto}
							<ul id="account-links">
								{block:AccountLink}
									<li><a href="{LinkUrl}" target="_blank">{LinkTitle}</a></li>
								{/block:AccountLink}
							</ul>
						</div>
						<div class="main">
							{block:AccountAbout}
								{AccountAbout}
							{/block:AccountAbout}
						</div>
					{Else}
						<p>We're sure you'll find our story fascinating, but we haven't written it yet!</p>
					{/module:AccountAbout}
				</div>
			{/page:About}
			{page:Error404}
				<div class="nocontent"><p>Error 404: This page doesn't exist.</p></div>
			{/page:Error404}
		</div>
		{JS}
	</body>
</html>