<!-- WrittenFlashcard.svelte -->

<script>
    export let question = 'Written Question';
    export let answer = ''; // To store the written answer
  
    function handleKeyDown(event) {
      if (event.key === 'Enter') {
        // Handle the submission or any other logic
      }
    }
  
    function handleInputChange(event) {
      answer = event.target.value;
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
      font: 700 36px/44px 'Inter', sans-serif;
      padding: 20px;
      width: 550px; /* Adjust the width of the card */
      height: 350px; /* Adjust the height of the card */
      cursor: pointer;
      perspective: 1000px;
      z-index: 1;

    }
  
    /* Additional styles for Written Flashcard */
    .answer-input {
      margin-top: 60px;
      padding: 10px;
      font-size: 18px;
      width: 80%;
    }
  </style>
  
  <div class="card" on:keydown={handleKeyDown} tabindex="0" role="button">
    <div>{question}</div>
    <input
      type="text"
      bind:value={answer}
      placeholder="Type your answer here"
      class="answer-input"
      on:input={handleInputChange}
    />
  </div>