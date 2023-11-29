<!-- DifficultyButtons.svelte -->

<script>
    // You can leave this script section empty if you don't need any logic for now
  </script>
  
  <div class="main-container">
    <header class="header">Difficulty Levels:</header>
    <div class="difficulty-container">
      <!-- When a difficulty button is clicked, call loadNextFlashcard -->
      <button class="easy-button">Easy</button>
      <button class="medium-button">Medium</button>
      <button class="hard-button">Hard</button>
    </div>
  </div>
  
  <style>
  .main-container {
    position: absolute;
    top: 52%;
    left: 50%;
    transform: translate(-50%, -50%);
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 20px;
    z-index: 1;
  }

  
    .difficulty-container {
      display: flex;
      justify-content: space-between;
      gap: 20px;
    }
  
    .easy-button,
    .medium-button,
    .hard-button {
      display: flex;
      justify-content: center;
      align-items: center;
      padding: 15px 20px;
      font-weight: bold;
      font-size: 15px;
      text-align: center;
      border-radius: 10px;
      box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
      color: #000;
      width: 131px;
      height: 34px;
      flex-shrink: 0;
      font-family: Inconsolata, -apple-system, Roboto, Helvetica, sans-serif;
      
    }
  
    .easy-button {
      background-color: rgba(215, 192, 174, 0.3);
    }
  
    .medium-button {
      background-color: rgba(215, 192, 174, 0.65);
    }
  
    .hard-button {
      background-color: #d7c0ae;
    }
  </style>
  