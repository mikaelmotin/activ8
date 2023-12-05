<script>
import { createEventDispatcher } from 'svelte';
const dispatch = createEventDispatcher();

export let newCard;
    export let index;

    function updateTerm(event) {
        dispatch('updateCard', { index, updatedCard: { ...newCard, term: event.target.value } });
    }

    function updateDefinition(event) {
        dispatch('updateCard', { index, updatedCard: { ...newCard, definition: event.target.value } });
    }
</script>

<div class="div">
    <div class="div-2">
        <div class="div-3">1</div>
        <div class="div-4"></div>
    </div>
    <div class="div-5">
        <div class="div-6">
            <div class="div-7">
                <input type="text" 
                value={newCard.term} on:input={updateTerm} placeholder="Enter a term">
            </div>
            <div class="div-8">Term</div>
        </div>
        <div class="div-9">
            <div class="div-10">
                <input type="text" 
                value={newCard.definition} on:input={updateDefinition} placeholder="Enter the definition">
            </div>
            <div class="div-11"></div>
            <div class="div-12">Definition</div>
        </div>
    </div>
    
</div>
<div class="div-13">
    <input type="text" placeholder="Enter some text...">
</div>

<style>
  .div {
    border-radius: 0px 0px 10px 10px;
    box-shadow: 0px 0px 4px 0px rgba(0, 0, 0, 0.25);
    background-color: #fff;
    display: flex;
    padding-bottom: 10px; /* Adjusted padding */
    flex-direction: column;
    z-index: 1;
  }

  .div-2 {
    border-radius: 10px;
    background-color: #f0f0f0;
    display: flex;
    width: 100%;
    align-items: start;
    justify-content: space-between;
    gap: 10px; /* Adjusted gap */
    padding: 7px 15px; /* Adjusted padding */
  }

  .div-3 {
    color: #000;
    text-align: center;
    font: 400 25px Inter, sans-serif; /* Adjusted font size */
  }

  .div-5 {
    display: flex;
    margin-top: 10px; /* Adjusted margin-top */
    width: 100%;
    align-items: start;
    justify-content: space-between;
    gap: 10px; /* Adjusted gap */
  }

  .div-6 {
    display: flex;
    flex-grow: 1;
    flex-basis: 0%;
    flex-direction: column;
    padding: 0 15px; /* Adjusted padding */
  }

  .div-7 {
    color: #bfc3c7;
    align-self: start;
    max-width: 300px; /* Adjusted max-width */
    font: 700 20px Inconsolata, sans-serif; /* Adjusted font size */
  }

  .div-8 {
    color: #000;
    align-self: start;
    margin-top: 8px; /* Adjusted margin-top */
    max-width: 200px; /* Adjusted max-width */
    font: 400 18px Inconsolata, sans-serif; /* Adjusted font size */
  }

  .div-9 {
    align-self: stretch;
    display: flex;
    flex-grow: 1;
    flex-basis: 0%;
    flex-direction: column;
    padding: 0 15px; /* Adjusted padding */
  }

  .div-10 {
    color: #bfc3c7;
    align-self: start;
    max-width: 300px; /* Adjusted max-width */
    font: 700 20px Inconsolata, sans-serif; /* Adjusted font size */
  }

  .div-11 {
    background-color: #000;
    margin-top: 8px; /* Adjusted margin-top */
    height: 2px; /* Adjusted height */
  }

  .div-12 {
    color: #000;
    align-self: start;
    margin-top: 6px; /* Adjusted margin-top */
    max-width: 200px; /* Adjusted max-width */
    font: 400 18px Inconsolata, sans-serif; /* Adjusted font size */
  }

  .div-13 {
    position: absolute;
    height: auto;
    top: -2147.21899835675px;
    left: -5666.737436181343px;
    width: 3727px;
  }

</style>