<!-- Multiplechoicecard.svelte -->

<script>
    export let question = 'Question';
    export let options = ['Option A', 'Option B', 'Option C', 'Option D'];
    export let answer = ''; // To store the selected answer
  
    function handleKeyDown(event) {
      if (event.key === 'Enter') {
        flipCard();
      }
    }
  
    function selectAnswer(selectedOption) {
      answer = selectedOption;
      flipCard();
    }
</script>
  
<style>
  /* Styles similar to Flashcard component */
  .card {
    position: absolute;
    top: 30%;
    left: 50%;
    transform: translate(-50%, -50%);
    z-index: 1;
    background-color: #fff;
    border-radius: 20px;
    box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    font: 700 36px/44px 'Inter', sans-serif; /* Change the font here */
    padding: 20px;
    width: 550px; /* Adjust the width of the card */
    height: 350px; /* Adjust the height of the card */
    cursor: pointer;
    perspective: 1000px;
  }

  /* Additional styles for Multiple Choice Card */
  .options-container {
    display: grid;
    grid-template-rows: repeat(2, 1fr);
    grid-template-columns: repeat(2, 1fr);
    gap: 10px; /* Adjust the gap to increase spacing between options */
  }

  .option {
    display: flex;
    align-items: center;
    gap: 5px;
  }

  /* Change the font for the question and options */
  .card-front {
    font-weight: light;
    font-family: 'YourCustomFont', sans-serif; /* Change the font here */
  }

  .option label {
    font-size: 25px;
    font-weight: lighter;
    font-family: 'YourCustomFont', sans-serif; /* Change the font here */
  }
</style>
  
<div class="card" on:keydown={handleKeyDown} tabindex="0" role="button">
  <div class="card-front">{question}</div>
  <div class="card-back">
    <div class="options-container">
      {#each options as option (option)}
        <div class="option">
          <input
            type="radio"
            id={option}
            name="options"
            bind:group={answer}
            on:change={() => selectAnswer(option)}
          />
          <label for={option}>{option}</label>
        </div>
      {/each}
    </div>
  </div>
</div>