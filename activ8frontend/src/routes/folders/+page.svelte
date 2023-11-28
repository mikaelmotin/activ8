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
        src="https://cdn.builder.io/api/v1/image/assets/TEMP/268b476b-1d5b-4233-bfae-4929d72a06c3?apiKey=d16dfbfed95d4aa8b4c0577dbaa29d8f&"
        class="img"
      />
      <img
        loading="lazy"
        src="https://cdn.builder.io/api/v1/image/assets/TEMP/ebef2882-e956-431f-8081-2d6662211cc7?apiKey=d16dfbfed95d4aa8b4c0577dbaa29d8f&"
        class="img-2"
      />
    </div>
    <div class="div-3">
      <div class="div-4">My Folders</div>
      <div class="div-5">
        <img
          loading="lazy"
          src="https://cdn.builder.io/api/v1/image/assets/TEMP/894b54a2-3b68-46fa-9be2-49c741172ad5?apiKey=d16dfbfed95d4aa8b4c0577dbaa29d8f&"
          class="img-3"
        />
        <div class="div-6">Edit Folders</div>
      </div>
      <div class="div-7">
        <img
          loading="lazy"
          src="https://cdn.builder.io/api/v1/image/assets/TEMP/cd03c599-4bc6-414d-a53a-d8aa8f346850?apiKey=d16dfbfed95d4aa8b4c0577dbaa29d8f&"
          class="img-4"
        />
        <div class="div-8">Add Folders</div>
      </div>
    </div>
    <div class="div-9">
      <div class="div-10">
        <div class="div-11">
          <div class="div-12">
            <div class="column"></div>
            <div class="column-2"></div>
            <div class="column-3"></div>
          </div>
        </div>
        <div class="div-13"><div class="div-14"></div></div>
        <div class="div-15"><div class="div-16"></div></div>
      </div>
      <div class="div-17"></div>
    </div>
  </div>

  <h1>Welcome to SvelteKit</h1>

  <div>Below is your Builder Content:</div>
  {#if canShowContent}
  <div>page Title: {data.content?.data?.title || 'Unpublished'}</div>
  <RenderContent
    model="page"
    content="{data.content}"
    apiKey="{BUILDER_PUBLIC_API_KEY}"
  />
  {:else} Content Not Found {/if}
</main>

<footer>
  <p>
    Visit <a href="https://kit.svelte.dev">kit.svelte.dev</a> to read the
    documentation
  </p>
</footer>

<style>
  .div {
      background-color: #ecf0f5;
      display: flex;
      flex-direction: column;
      align-items: center;
      padding: 0 28px 0 59px;
    }
    @media (max-width: 991px) {
      .div {
        padding: 0 20px;
      }
    }
    .div-2 {
      align-self: stretch;
      display: flex;
      padding-right: 28px;
      align-items: start;
      justify-content: space-between;
      gap: 20px;
      margin: -1px 0 -2px;
    }
    @media (max-width: 991px) {
      .div-2 {
        max-width: 100%;
        flex-wrap: wrap;
        padding-right: 20px;
        margin-top: 40px;
      }
    }
    .img {
      aspect-ratio: 1;
      object-fit: contain;
      object-position: center;
      width: 70px;
      overflow: hidden;
      max-width: 100%;
    }
    .img-2 {
      aspect-ratio: 1;
      object-fit: contain;
      object-position: center;
      width: 100%;
      fill: #000;
      overflow: hidden;
      align-self: stretch;
      max-width: 5%;
    }
    .div-3 {
      display: flex;
      margin-top: 74px;
      width: 100%;
      max-width: 1530px;
      align-items: start;
      justify-content: space-between;
      gap: 20px;
    }
    @media (max-width: 991px) {
      .div-3 {
        max-width: 100%;
        flex-wrap: wrap;
        justify-content: center;
        margin-top: 40px;
      }
    }
    .div-4 {
      color: #000;
      flex-grow: 1;
      flex-basis: auto;
      margin: -3px 0 -2px;
      font: 700 48px/50px Inconsolata, -apple-system, Roboto, Helvetica,
        sans-serif;
    }
    @media (max-width: 991px) {
      .div-4 {
        font-size: 40px;
        line-height: 47px;
      }
    }
    .div-5 {
      display: flex;
      gap: 20px;
    }
    .img-3 {
      aspect-ratio: 0.03;
      object-fit: contain;
      object-position: center;
      width: 2px;
      stroke-width: 1px;
      stroke: #000;
      overflow: hidden;
      max-width: 100%;
    }
    .div-6 {
      color: #000;
      align-self: center;
      margin-bottom: auto;
      font: 400 32px/34px Inconsolata, -apple-system, Roboto, Helvetica,
        sans-serif;
    }
    .div-7 {
      display: flex;
      justify-content: space-between;
      gap: 20px;
    }
    .img-4 {
      aspect-ratio: 0.1;
      object-fit: contain;
      object-position: center;
      width: 1px;
      stroke-width: 1px;
      stroke: #000;
      overflow: hidden;
      max-width: 100%;
    }
    .div-8 {
      color: #000;
      margin-bottom: auto;
      font: 400 32px/34px Inconsolata, -apple-system, Roboto, Helvetica,
        sans-serif;
    }
    .div-9 {
      align-self: stretch;
      display: flex;
      align-items: start;
      justify-content: space-between;
      gap: 0px;
      margin: 0 -2px 17px 0;
    }
    @media (max-width: 991px) {
      .div-9 {
        max-width: 100%;
        flex-wrap: wrap;
      }
    }
    .div-10 {
      background-color: #fff;
      display: flex;
      flex-grow: 1;
      flex-basis: 0%;
      flex-direction: column;
      align-items: center;
      margin-left: -4px;
      padding: 50px 57px 50px 58px;
    }
    @media (max-width: 991px) {
      .div-10 {
        max-width: 100%;
        padding: 0 20px;
      }
    }
    .div-11 {
      width: 100%;
      max-width: 1396px;
    }
    @media (max-width: 991px) {
      .div-11 {
        max-width: 100%;
      }
    }
    .div-12 {
      gap: 20px;
      display: flex;
    }
    @media (max-width: 991px) {
      .div-12 {
        flex-direction: column;
        align-items: stretch;
        gap: 0px;
      }
    }
    .column {
      display: flex;
      flex-direction: column;
      line-height: normal;
      width: 33%;
      margin-left: 0px;
    }
    @media (max-width: 991px) {
      .column {
        width: 100%;
      }
    }
    .column-2 {
      display: flex;
      flex-direction: column;
      line-height: normal;
      width: 33%;
      margin-left: 20px;
    }
    @media (max-width: 991px) {
      .column-2 {
        width: 100%;
      }
    }
    .column-3 {
      display: flex;
      flex-direction: column;
      line-height: normal;
      width: 33%;
      margin-left: 20px;
    }
    @media (max-width: 991px) {
      .column-3 {
        width: 100%;
      }
    }
    .div-13 {
      border-radius: 10px 10px 0px 0px;
      background-color: #967e76;
      align-self: start;
      display: flex;
      width: 142px;
      max-width: 100%;
      flex-direction: column;
      margin: 105px 0 0 61px;
    }
    @media (max-width: 991px) {
      .div-13 {
        margin: 40px 0 0 10px;
      }
    }
    .div-14 {
      border-radius: 10px 10px 0px 0px;
      background-color: #967e76;
      display: flex;
      flex-direction: column;
    }
    .div-15 {
      background-color: #967e76;
      align-self: start;
      display: flex;
      width: 367px;
      max-width: 100%;
      flex-direction: column;
      margin: 0 0 36px 61px;
    }
    @media (max-width: 991px) {
      .div-15 {
        margin-left: 10px;
      }
    }
    .div-16 {
      background-color: #967e76;
      display: flex;
      flex-direction: column;
    }
    .div-17 {
      filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25));
      border-radius: 50%;
      align-self: end;
      display: flex;
      margin-top: 732px;
      flex-direction: column;
      align-items: center;
      padding: 35px 20px 25px;
    }
    @media (max-width: 991px) {
      .div-17 {
        margin-top: 40px;
      }
    }
</style>