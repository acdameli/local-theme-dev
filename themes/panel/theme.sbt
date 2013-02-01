<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<title>{AccountName} - StageBloc</title>
	{CSS}
	{jQuery}
	{SBNav position="bottom left"}
</head>
<body>

	<div id="sidebar">
	{module:AccountAbout}

		{block:AccountPhoto}
			<a href="{Link-Home}" id="logo"><span alt="{AccountName}'s account photo" style="background-image:url({PhotoSource-Medium})"></span></a>
		{/block:AccountPhoto}

		<h1><a href="{Link-About}">{AccountName}</a></h1>

		{block:AccountAbout}
			<p>{AccountAboutCleaned}</p>
		{/block:AccountAbout}
	{/module:AccountAbout}

	{module:Navigation ignore="about" updates="Text"}
		<nav class="stackable"><ul>
			{block:NavigationItem}
				<li class="{CSSClass}"><a href="{Url}">{LinkText}</a></li>
			{/block:NavigationItem}
		</ul></nav>
	{/module:Navigation}
	</div>


	<div id="main">
	{page:ActivityStreamList}
		{module:ActivityStreamList embedWidth="650" supported="blog,statuses"}
			{block:ActivityStreamView}
				<article class="post {ActivityCSSClass}">
					{if:ActivityIsBlog}
						<header>
							<h2><a href="{ActivityUrl}">{ActivityTitle}</a></h2>
						</header>
					{/if:ActivityIsBlog}

					<div class="body">
						{if:ActivityIsBlog}
							{ActivityExcerpt}
						{/if:ActivityIsBlog}

						{if:ActivityIsStatus}
							<h2>{ActivityExcerpt} <a href="{ActivityUrl}" class="anchor">Permalink</a></h2>
						{/if:ActivityIsStatus}

						{if:ActivityIsRepost}
							<a class="repost" href="{RepostedFromAccountUrl}">
								<span class="image"><img src="{RepostedFromAccountPhotoUrl}" /></span>
								<span>originally posted by {RepostedFromAccountName}</span>
							</a>
						{/if:ActivityIsRepost}

					{if:ReadMore}
						<a class="read-more" href="{ActivityUrl}">Read more &rarr;</a>
					{/if:ReadMore}

					</div>

					<footer>
						{LikeLink likeText="Like" unlikeText="Unlike" class="liking"}
						{RepostLink repostText="Repost" unRepostText="UnRepost" class="reposting"}
					</footer>
				</article>
			{/block:ActivityStreamView}
		{Else}<h1>{AccountName}</h1><article class="no-content"><h2>:(</h2><p>There&rsquo;s nothing here</p></article>
		{/module:ActivityStreamList}
	{/page:ActivityStreamList}
	{page:BlogView}
		{module:BlogView}
			{block:BlogPost}
				<aside class="date">
					{CreatedDate format="F j, Y"}
				</aside>
				<article class="post {ActivityCSSClass}">
					<header>
						<h2><a href="{ActivityUrl}">{BlogPostTitle}</a></h2>
					</header>

					<div class="body">
						{BlogPostBody}
					</div>

					<footer>
						{LikeLink likeText="Like" unlikeText="Unlike" class="liking"}
						{RepostLink repostText="Repost" unRepostText="UnRepost" class="reposting"}
					</footer>
				</article>
			{/block:BlogPost}

			<article id="pagination">
				<a href="{Link-Home}" class="newer">&larr; Read More</a>
			</div>
		{Else}<h1>Blog</h1><article class="no-content"><h2>:(</h2><p>There&rsquo;s nothing here</p></article>
		{/module:BlogView}
	{/page:BlogView}
	{page:PhotoList}
		<h1>Photo Albums</h1>
		{module:PhotoAlbumList limit="100"}
			<ul class="albums">
				{block:PhotoAlbumView}
				<li>
					<a href="{PhotoAlbumUrl}">
						<span class="image"><img src="{PhotoAlbumPhotoUrl}" alt="{PhotoAlbumTitle} cover" title="{PhotoAlbumTitle} cover" /></span>
						<span class="title">{PhotoAlbumTitle}</span>
						{PhotoAlbumPhotoCount} photos
					</a>
				</li>
				{/block:PhotoAlbumView}
			</ul>
		{Else}<article class="no-content"><h2>:(</h2><p>There&rsquo;s nothing here</p></article>
		{/module:PhotoAlbumList}
	{/page:PhotoList}
	{page:EventUpcomingList}
		<h1>Events</h1>
		{module:EventList upcoming="true" past="false" limit="100"}
			<ul class="albums">
				{block:EventView}
				<li>
					<a href="{EventUrl}">
						<span class="title">{EventStartDate format="F j, Y"}{if:EventHasTitle} &mdash; {EventTitle}{/if:EventHasTitle}</span>
						{EventLocation}
					</a>
				</li>
				{/block:EventView}
			</ul>
		{Else}<article class="no-content"><h2>:(</h2><p>There&rsquo;s nothing here</p></article>
		{/module:EventList}
		<article id="pagination">
			<a href="{Link-EventsPast}" class="older">Past Events &rarr;</a>
		</article>
	{/page:EventUpcomingList}
	{page:EventPastList}
		<h1>Past Events</h1>
		{module:EventList upcoming="false" past="true" order="desc" limit="100"}
			<ul class="albums">
				{block:EventView}
				<li>
					<a href="{EventUrl}">
						<span class="title">{EventStartDate format="F j, Y"}{if:EventHasTitle} &mdash; {EventTitle}{/if:EventHasTitle}</span>
						{EventLocation}
					</a>
				</li>
				{/block:EventView}
			</ul>
		{Else}<article class="no-content"><h2>:(</h2><p>There&rsquo;s nothing here</p></article>
		{/module:EventList}
		<article id="pagination">
			<a href="{Link-Events}" class="newer">&larr; Upcoming Events</a>
		</article>
	{/page:EventPastList}
	{page:VideoList}
		<h1>Video Playlists</h1>
		{module:VideoPlaylistList limit="100"}
			<ul class="albums">
				{block:VideoPlaylistView}
				<li>
					<a href="{VideoPlaylistUrl}">
						<span class="title">{VideoPlaylistTitle}</span>
						{VideoPlaylistVideoCount} videos
					</a>
				</li>
				{/block:VideoPlaylistView}
			</ul>
		{Else}<article class="no-content"><h2>:(</h2><p>There&rsquo;s nothing here</p></article>
		{/module:VideoPlaylistList}
	{/page:VideoList}
	{page:AudioList}
		<h1>Audio Playlists</h1>
		{module:AudioPlaylistList limit="100"}
			<ul class="albums">
				{block:AudioPlaylistView}
				<li>
					<a href="{AudioPlaylistUrl}">
						<span class="title">{AudioPlaylistTitle}</span>
						{AudioPlaylistAudioCount} tracks
					</a>
				</li>
				{/block:AudioPlaylistView}
			</ul>
		{Else}<article class="no-content"><h2>:(</h2><p>There&rsquo;s nothing here</p></article>
		{/module:AudioPlaylistList}
	{/page:AudioList}
	{page:StoreItemList}
		<h1>Store Items</h1>
		{module:StoreItemList limit="100"}
			<ul class="albums">
				{block:StoreItemView}
				<li>
					<a href="{StoreItemUrl}">
						<span class="image"><img src="{StoreItemPhotoUrl}" alt="{StoreItemTitle} cover" title="{StoreItemTitle} cover" /></span>
						<span class="title">{StoreItemTitle}</span>
						${StoreItemPrice}
					</a>
				</li>
				{/block:StoreItemView}
			</ul>
		{Else}<article class="no-content"><h2>:(</h2><p>There&rsquo;s nothing here</p></article>
		{/module:StoreItemList}
	{/page:StoreItemList}
	{page:About}
	<h1>{AccountName}</h1>
	<article>
	{module:AccountAbout}
		{block:AccountAbout}
			{AccountAbout}
		{/block:AccountAbout}

		<nav><ul>
			<li><a href="{Link-Archive}" target="_blank">Archive</a></li>
			{block:AccountLink}
				<li><a href="{LinkUrl}" target="_blank">{LinkTitle}</a></li>
			{/block:AccountLink}
		</ul></nav>

	{/module:AccountAbout}
	</article>
	{/page:About}
	{page:Error404}
	<h1>Error 404 &mdash; The Force is not strong with this one</h1>
	<article class="no-content"><h2>:&lsquo;(</h2><p>These aren&rsquo;t the droids you&rsquo;re looking for</p></article>
	{/page:Error404}

	{module:Pagination}
		<article id="pagination">
			{block:NextPage}
			<a href="{NextPage}" class="older">Continue &rarr;</a>
			{/block:NextPage}
			{block:PreviousPage}
			<a href="{PreviousPage}" class="newer">&larr; Newer</a>
			{/block:PreviousPage}
		</div>
	{/module:Pagination}
	</div>

{JS}
</body>
</html>