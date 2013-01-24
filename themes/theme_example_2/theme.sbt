<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>{AccountName} - StageBloc</title>
		{CSS}
	</head>
	<body>
		<header id="masthead">
			<h1><a href="{Link-Home}">{AccountName}</a></h1>
			{module:Navigation}
				<nav id="main-nav">
					<ol>
					{block:NavigationItem}
						<li class="{CSSClass}"><a href="{Url}">{LinkText}</a></li>
					{/block:NavigationItem}
					</ol>
				</nav>
			{/module:Navigation}
		</header>

		<div id="content">
			{page:ActivityStreamList}
				<div id="main-content">
					{module:ActivityStreamList supported="blog,statuses,blog_reposts,status_reposts,photos,events,videos"}
						{block:ActivityStreamView}
							<article class="post {ActivityCSSClass}">
								<header>
								<div class="post-date">
									{ActivityDate format="l, F j, Y"}
								</div>
									{if:ActivityIsBlog}
										<h1><a href="{ActivityUrl}">{ActivityTitle}</a></h1>
									{/if:ActivityIsBlog}
									{if:ActivityIsPhotoAlbum}
										<h1><a href="{ActivityUrl}">New photos added to &ldquo;{ActivityTitle}&rdquo;</a></h1>
									{/if:ActivityIsPhotoAlbum}
								</header>

								<div class="post-body">
									{if:ActivityIsBlog}
										{ActivityExcerpt}
									{/if:ActivityIsBlog}
									{if:ActivityIsStatus}
										<a href="{ActivityUrl}">{ActivityExcerpt}</a>
									{/if:ActivityIsStatus}
									{if:ActivityIsVideo}
										<a href="{ActivityUrl}">{ActivityTitle}</a>
										{ActivityExcerpt}
									{/if:ActivityIsVideo}
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
										{module:PhotoList limit="6" albumid="{ActivityId}"}
										<div class="photos-list">
											{block:PhotoView}
												<a href="{PhotoUrl}" title="{PhotoTitle}"><img src="{Source-Thumb}" alt="{PhotoTitle}" title="{PhotoTitle}" width="80" height="80" /></a>
											{/block:PhotoView}
										</div>
										{/module:PhotoList}
									{/if:ActivityIsPhotoAlbum}
									{if:ActivityIsRepost}
										<div class="repost">Originally posted {RepostedContentTimeAgo} by <a href="{RepostedFromAccountUrl}">{RepostedFromAccountName}</a></div>
									{/if:ActivityIsRepost}
								</div>

								{if:ReadMore}
									<a href="{ActivityUrl}" class="read-more">read more</a>
								{/if:ReadMore}
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
			{/page:ActivityStreamList}

			{page:BlogView}
				<div id="main-content" class="individual-blog-content">
					{module:BlogView}
						{block:BlogPost}
							<article class="post blog individual">
								<header>
									<div class="post-date">{PublishedDate format="l, F j, Y"} at {CreatedDate format="g:i A"}</div>
									<h1>{BlogPostTitle}</h1>
								</header>
								{BlogPostBody}
							</article>
							<div id="pagination">
							{if:HasNextBlogPost}
								<a href="{NextBlogPostUrl}">Older</a>
							{/if:HasNextBlogPost}
							{if:HasPreviousBlogPost}
								<a href="{PreviousBlogPostUrl}">Newer</a>
							{/if:HasPreviousBlogPost}
							</div>
						{/block:BlogPost}
					{Else}
						<div class="nocontent">
							<p>Hey, this blog post wasn't found! What are you trying to pull...</p>
						</div>
					{/module:BlogView}
				</div>
			{/page:BlogView}

			{page:StatusView}
				<div id="main-content" class="individual-blog-content">
					{module:StatusView}
						{block:StatusPost}
							<article class="post status individual">
								<div class="post-date">{PublishedDate format="l, F j, Y"} at {CreatedDate format="g:i A"}</div>
								<div class="post-body">{StatusText}</div>
							</article>
						{/block:StatusPost}
					{Else}
						<div class="nocontent">
							<p>Whoops! This status doesn't seem to exist.</p>
						</div>
					{/module:StatusView}
				</div>
			{/page:StatusView}

			{page:EventUpcomingList}
				<div id="events-container">
					<h1>Upcoming Events</h1>
					{if:HasPastEvents}<a href="{Link-EventsPast}" id="other-events">past</a>{/if:HasPastEvents}
					{module:EventList upcoming="true" past="false"}
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
					<h1>Past Events</h1>
					<a href="{Link-Events}" id="other-events">upcoming</a>
					{module:EventList upcoming="false" past="true" order="desc"}
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
				<div id="content">
					{module:EventView}
						<div id="event">
							{block:EventView}

								<ul class="event-info">

									<li>
										<div class="left">When?</div>
										<div class="right">{EventStartDate format="l, F j, Y"} at {EventStartDate format="g:i A"}</div>
									</li>
									<li>
										<div class="left">Where?</div>
										<div class="right">{VenueName}, {EventLocation}</div>
									</li>
									{if:HasSupportingActs}
									<li>
										<div class="left">With?</div>
										<div class="right">{SupportingActs}</div>
									</li>
									{/if:HasSupportingActs}
									{if:EventHasMinimumAge}
									<li>
										<div class="left">Who?</div>
										<div class="right">{EventAges} can attend</div>
									</li>
									{/if:EventHasMinimumAge}
									{if:EventHasPrice}
									<li>
										<div class="left">How much?</div>
										<div class="right">${EventPrice}{if:EventHasTicketsBuyLink} <a href="{TicketsBuyLink}">buy tickets</a>{/if:EventHasTicketsBuyLink}</div>
									</li>
									{/if:EventHasPrice}

								</ul>

								<div id="event-description">{EventDescription}</div>

							{/block:EventView}
						</div>
					{Else}
						<div class="nocontent">
							<p>Sorry, this event wasn't found in our system!</p>
						</div>
					{/module:EventView}
				</div>
			{/page:EventView}

			{page:PhotoList}
				{module:PhotoAlbumList}
					<div id="photo-albums">
						<h1>Photo Albums</h1>
						<ul>
							{block:PhotoAlbumView}
							<li>
								<a href="{PhotoAlbumUrl}">
									<img src="{PhotoAlbumPhotoUrl}" alt="{PhotoAlbumTitle} cover" title="{PhotoAlbumTitle} cover" width="42" height="42" />
									<span class="title">{PhotoAlbumTitle}</span>
									<span class="total">{PhotoAlbumPhotoCount} photos</span>
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
				<div id="photo-container">
					{module:PhotoView}
						{block:PhotoView}
						<article class="post">
							<div class="post-date">{CreatedDate format="l, F j, Y"} at {CreatedDate format="g:i A"}</div>
							<h1 id="photo-title">{PhotoTitle}</h1>
							<p id="photo-backtoalbum"><a href="{PhotoAlbumUrl}">back to album</a></p>
							<img id="photo-image" src="{Source-Large}" alt="{PhotoTitle}" title="{PhotoTitle}" />
							<div id="photo-description">
								{PhotoDescription}
							</div>
						</article>
							<div id="pagination">
								{if:HasNextPhoto}
								<a href="{NextPhotoUrl}">next</a>
								{/if:HasNextPhoto}
								{if:HasPreviousPhoto}
								<a href="{PreviousPhotoUrl}">previous</a>
								{/if:HasPreviousPhoto}
							</div>
						{/block:PhotoView}
					{Else}
						<div class="nocontent">
							<p>This photo couldn't be found! <a href="{Link-Photos}">Try finding it in the photos section.</a></p>
						</div>
					{/module:PhotoView}
				</div>
			{/page:PhotoView}

			{page:PhotoAlbumView}
				{module:PhotoAlbumView}
						{block:PhotoAlbumView}
							<div class="photos-section">
								<div id="album-info">
									<img id="album-cover" src="{PhotoAlbumPhotoUrl}" alt="{PhotoAlbumTitle} cover" title="{PhotoAlbumTitle} cover" width="48" height="48" />
									<h1 id="album-title">{PhotoAlbumTitle}</h1>
									<div id="album-last-updated">last updated {ModifiedDate format="F j, Y"}</div>
									<div id="total">| {PhotoAlbumPhotoCount} photos</div>
								</div>
								<div id="album-description">{PhotoAlbumDescription}</div>
							</div>
							{module:PhotoList albumid="{PhotoAlbumId}" limit="28"}
								<div class="photos-section">
									<div class="photos-list">
										{block:PhotoView}
										<a href="{PhotoUrl}" title="{PhotoTitle}"><img src="{Source-Thumb}" alt="{PhotoTitle}" title="{PhotoTitle}" width="80" height="80" /></a>
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
						{/block:PhotoAlbumView}
				{/module:PhotoAlbumView}
			{/page:PhotoAlbumView}

			{page:About}
				<div id="about-account">
					{module:AccountAbout}
						<div class="left">
							{block:AccountPhoto}
								<img alt="{AccountName}'s account photo" id="account-photo" src="{PhotoSource-Small}" />
							{/block:AccountPhoto}
						</div>
						<div class="right">
							{block:AccountAbout}
								{AccountAbout}
							{/block:AccountAbout}
						</div>
						<ul id="account-links">
							{block:AccountLink}
								<li><a href="{LinkUrl}" target="_blank">{LinkTitle}</a></li>
							{/block:AccountLink}
						</ul>
					{Else}
						<div class="nocontent">
							<p>We're sure you'll find our story fascinating, but we haven't written it yet!</p>
						</div>
					{/module:AccountAbout}
				</div>
			{/page:About}
			{page:Error404}
				<div class="nocontent"><p>Error 404: page not found.</p></div>
			{/page:Error404}
		</div>
	{JS}
	</body>
</html>