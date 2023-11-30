<!-- testsessionbg.svelte -->

<script>
    import Rightwrongbuttons from '../../components/rightwrongbuttons.svelte';
    import EndSessionButton from '../../../src/components/endsessionbutton.svelte';
    import Flashcard from '../../../src/components/flashcard.svelte';
    import Writtenflashcard from '../../components/writtenflashcard.svelte';
    import Multiplechoicecard from '../../components/multiplechoicecard.svelte';

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
  
  <main>
    <div class="div">
      <div class="div-2">
        <Rightwrongbuttons/>
        <EndSessionButton />
        
        <button on:click={toggleComponent}>Toggle Component</button>
        
        <svelte:component this={currentComponent} />


  

        <!-- Your additional content goes here -->
  
      </div>
    </div>
  </main>
  
  <style>
  
    /* Styles from studysessionbg.svelte */
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


/* Your existing styles here */

button {
  background-color: lightblue;
  padding: 10px;
  margin: 10px;
  z-index: 1;
}
</style>
  

