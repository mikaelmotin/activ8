<!-- +page.svelte -->
<script>
  import Flashcard from '../../../src/components/flashcard.svelte';
  import DifficultyButtons from '../../../src/components/difficultybuttons.svelte';
  import EndSessionButton from '../../../src/components/endsessionbutton.svelte';
  import Multiplechoicecard from '../../components/multiplechoicecard.svelte';
  import Writtenflashcard from '../../components/writtenflashcard.svelte';

  import { isPreviewing, RenderContent } from '@builder.io/sdk-svelte';
  import { BUILDER_PUBLIC_API_KEY } from '../../apiKey';

  // this data comes from the function in `+page.server.js`, which runs on the server only
  export let data;

  // we want to show unpublished content when in preview mode.
  const canShowContent = data.content || isPreviewing();
  let componentIndex = 0;

  const components = [Flashcard, Multiplechoicecard, Writtenflashcard];

  function toggleComponent() {
    componentIndex = (componentIndex + 1) % components.length;
  }

  // Get the current component based on the index
  $: currentComponent = components[componentIndex];
</script>

<svelte:head>
  <title>Home</title>
</svelte:head>

<main>
  <div class="div">
    <div class="div-2">
      <DifficultyButtons/>
      <EndSessionButton />

      <!-- Toggle button -->
      <button on:click={toggleComponent}>Toggle Component</button>

      <!-- Render the current component -->
      <svelte:component this={currentComponent} />

      <!-- Rest of your content -->
      <!-- svelte-ignore a11y-missing-attribute -->
      <img
        loading="lazy"
        src="https://cdn.builder.io/api/v1/image/assets/TEMP/5768c9e7-73d5-4358-8b1a-fcf302daecb8?apiKey=d16dfbfed95d4aa8b4c0577dbaa29d8f&"
        class="img"
      />
      <!-- svelte-ignore a11y-missing-attribute -->
      <img
        loading="lazy"
        src="https://cdn.builder.io/api/v1/image/assets/TEMP/db6f9b89-f132-4e8c-b28b-6229c52c0f16?apiKey=d16dfbfed95d4aa8b4c0577dbaa29d8f&"
        class="img-2"
      />
      <div class="subset">subset name</div>
    </div>
  </div>

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
    background-color: #ecf0f5;
    display: flex;
    flex-direction: column;
  }

  .div-2 {
    display: flex;
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

  .subset {
    position: absolute;
  color: #000;
  width: 551px;
  max-width: 100%;
  justify-content: space-evenly;
  gap: 30px;
  top: 10%;
  left: 50%;
  text-align: center;
  transform: translateX(-50%);/* Adjust the top position */
  
/* Center horizontally */
  font:300 25px/34px Inconsolata, -apple-system, Roboto, Helvetica, sans-serif;
  }

  @media (max-width: 991px) {
    .subset {
      flex-wrap: wrap;
      margin: 40px 10px 0 0;
    }
  }
  button {
    background-color: lightblue;
    padding: 10px;
    margin: 10px;
    z-index: 1;

  }
</style>