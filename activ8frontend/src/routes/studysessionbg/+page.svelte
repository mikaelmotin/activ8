<script>
	import { isPreviewing, RenderContent } from '@builder.io/sdk-svelte';
	import { BUILDER_PUBLIC_API_KEY } from '../../apiKey';

	// Create an array of your custom components and their properties
	
	// this data comes from the function in `+page.server.js`, which runs on the server only
	export let data;

	// we want to show unpublished content when in preview mode.
	const canShowContent = data.content || isPreviewing();
</script>

<svelte:head>
	<title>Home</title>
</svelte:head>

<main>
    <div class="div">
        <div class="div-2">
          <img
            loading="lazy"
            src="https://cdn.builder.io/api/v1/image/assets/TEMP/5768c9e7-73d5-4358-8b1a-fcf302daecb8?apiKey=d16dfbfed95d4aa8b4c0577dbaa29d8f&"
            class="img"
          />
          <img
            loading="lazy"
            src="https://cdn.builder.io/api/v1/image/assets/TEMP/db6f9b89-f132-4e8c-b28b-6229c52c0f16?apiKey=d16dfbfed95d4aa8b4c0577dbaa29d8f&"
            class="img-2"
          />
          <div class="genetics">Genetics</div>
        </div>
      </div>

	<h1>Welcome to SvelteKit</h1>

	<div>Below is your Builder Content:</div>
	{#if canShowContent}
		<div>page Title: {data.content?.data?.title || 'Unpublished'}</div>
		<RenderContent
			model="page"
			content={data.content}
			apiKey={BUILDER_PUBLIC_API_KEY}
		/>
	{:else}
		Content Not Found
	{/if}
</main>

<footer>
	<p>Visit <a href="https://kit.svelte.dev">kit.svelte.dev</a> to read the documentation</p>
</footer>

<style>
	h1 {
		width: 100%;
		font-size: 2rem;
		text-align: center;
	}

	footer {
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		padding: 40px;
	}

	@media (min-width: 480px) {
		footer {
			padding: 40px 0;
		}
	}
  .div {
    background-color: #fff;
    display: flex;
    flex-direction: column;
  }
  .div-2 {
    disply: flex;
    flex-direction: column;
    fill: #ecf0f5;
    box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25) inset;
    overflow: hidden;
    position: relative;
    display: flex;
    min-height: 1117px;
    width: 100%;
    align-items: end;
    padding: 50px 80px 50px 48px;
  }
  @media (max-width: 991px) {
    .div-2 {
      max-width: 100%;
      padding: 0 20px;
    }
  }
  .img {
    position: absolute;
    inset: 0;
    height: 100%;
    width: 100%;
    object-fit: cover;
    object-position: center;
  }
  .img-2 {
    aspect-ratio: 1;
    object-fit: contain;
    object-position: center;
    width: 48px;
    stroke-width: 16px;
    stroke: #000;
    overflow: hidden;
    align-self: start;
    max-width: 100%;
  }
  .genetics {
    position: relative;
    color: #000;
    width: 551px;
    max-width: 100%;
    align-items: center;
    justify-content: space-between;
    gap: 20px;
    margin: 82px 297px 0 0;
    font: 600 32px/34px Inconsolata, -apple-system, Roboto, Helvetica,
      sans-serif;
  }
  @media (max-width: 991px) {
    .genetics {
      flex-wrap: wrap;
      margin: 40px 10px 0 0;
    }
  }
</style>