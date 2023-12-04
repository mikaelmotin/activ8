<script>
  
  import { isPreviewing, RenderContent } from '@builder.io/sdk-svelte';
  import { BUILDER_PUBLIC_API_KEY } from '../../apiKey';
  import createflashcards from '../../components/createflashcards.svelte';
  import Createflashcards from '../../components/createflashcards.svelte';

  // Create an array of your custom components and their properties
  const CUSTOM_COMPONENTS = [
    {
      component: createflashcards,
      name: 'createflashcardds',
      inputs: [
        {
          name: 'Term',
          type: 'string',
          defaultValue: 'Enter a term',
        },
      ],
    },
  ]
  
  // this data comes from the function in `+page.server.js`, which runs on the server only
  export let data;
  export let content = undefined;
  let titleInput = '';
  let descriptionInput = '';
  let termInput = '';
  let definitionInput = '';
  // we want to show unpublished content when in preview mode.
  const canShowContent = data.content || isPreviewing();
</script>

<RenderContent
  model="page"
  {content}
  apiKey={BUILDER_PUBLIC_API_KEY}
  customComponents={CUSTOM_COMPONENTS}
/>



<svelte:head>
  <title>Home</title>
</svelte:head>

<div class="div">
  <Createflashcards/>
  <div class="div-2"><div class="div-3">8</div></div>
  
</div>

<div class="div-6">
  <!-- Make "Start study session" a button -->
  <button class="start-session-button" on:click={startStudySession}>
    Start study session
  </button>

</div>
<div class="div-2">
  <!-- Make "Enter a Title" a text box -->
  <input
    type="text"
    bind:value={titleInput}
    placeholder="Enter a Title"
    class="title-input"
  />
</div>

<div class="div-9">
  <!-- Make "Add a description" a text box -->
  <textarea
    bind:value={descriptionInput}
    placeholder="Add a description"
    class="description-input"
  ></textarea>
</div>

<div class="flashcard">
  <div class="flashcard-header">
    <div class="flashcard-number">1</div>
    <button on:click={deleteFlashcard}>&#128465;</button> <!-- Garbage can icon -->
  </div>
  <div class="flashcard-content">
    <!-- Text field for "Enter a term" -->
    <input type="text" bind:value={termInput} placeholder="Enter a term" />
    <!-- Text field for "Enter the description" -->
    <textarea bind:value={definitionInput} placeholder="Enter the description"></textarea>
  </div>
</div>


<style>
  .div {
    background-color: #ecf0f5;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: start;
    padding: 50px 20px;
  }
  .div-2 {
    border-radius: 50%;
    display: flex;
    width: 77px;
    max-width: 100%;
    flex-direction: column;
    align-items: start;
    margin: 11px 0 733px 180px;
    padding: 10px 11px 27px;
  }
  @media (max-width: 991px) {
    .div-2 {
      margin: 0 0 40px 10px;
    }
  }
  .div-3 {
    color: #967e76;
    white-space: nowrap;
    border-radius: 50%;
    justify-content: center;
    align-items: start;
    padding: 11px 12px;
    font: 400 24px Impact, sans-serif;
  }
  @media (max-width: 991px) {
    .div-3 {
      white-space: initial;
    }
  }

  .start-session-button {
    color: #000;
    text-align: center;
    border-radius: 15px;
    background-color: #d2e0fb;
    flex-grow: 1;
    padding: 9px 20px 19px;
    font: 400 35px Inter, sans-serif;
    cursor: pointer;
    border: none;
    outline: none;
  }
  .description-input {
    width: 100%;
    padding: 10px;
    font-size: 16px;
    margin-bottom: 10px;
  }
 /* Add your styling for the flashcard component */
 .flashcard {
    /* Add your styling for the flashcard container */
    border-radius: 0px 0px 10px 10px;
    box-shadow: 0px 0px 4px 0px rgba(0, 0, 0, 0.25);
    background-color: #fff;
    display: flex;
    margin-top: 41px;
    padding-bottom: 19px;
    flex-direction: column;
  }

  .flashcard-header {
    /* Add your styling for the flashcard header */
    max-width: 100%;
      flex-wrap: wrap;
      padding: 0 20px;
  }

  .flashcard-number {
    /* Add your styling for the flashcard number */
  }

  .flashcard-content {
    /* Add your styling for the flashcard content */
    display: flex;
    margin-top: 28px;
    align-items: start;
    justify-content: space-between;
    gap: 20px;
  }
  @media (max-width: 991px) {
    .div-16 {
      max-width: 100%;
      flex-wrap: wrap;
    }
  }
  

  /* Add styling for the garbage can button */
  button {
    cursor: pointer;
    background: none;
    border: none;
    font-size: 20px;
    color: red;
  }
</style>
