<!-- Flashcard.svelte -->

<script>
  import { flashcardStore, initWebSocket } from "../stores/websocket";


  export let front = $flashcardStore;
  export let back = "Answer";
  let isFlipped = false;

  function flipCard() {
    isFlipped = !isFlipped;
  }

  function handleKeyDown(event) {
    // Check if the key pressed is the Enter key
    if (event.key === "Enter") {
      flipCard();
    }
  }
</script>

<div
  class="card"
  on:click={flipCard}
  on:keydown={handleKeyDown}
  tabindex="0"
  role="button"
>
  <!-- The tabindex="0" makes the div focusable, allowing it to receive keyboard events -->
  <div
    class="card-inner"
    style={`transform: rotateY(${isFlipped ? "180deg" : "0"})`}
  >
    <div class="card-front">{front}</div>
    <div class="card-back">{back}</div>
  </div>
</div>

<style>
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
    font:
      700 36px/44px Inter,
      sans-serif;
    padding: 20px;
    width: 550px; /* Adjust the width of the card */
    height: 350px; /* Adjust the height of the card */
    cursor: pointer;
    perspective: 1000px;
  }

  .card-inner {
    position: relative;
    width: 100%;
    height: 100%;
    transform-style: preserve-3d;
    transition: transform 0.5s;
  }

  .card-front,
  .card-back {
    position: absolute;
    width: 100%;
    height: 100%;
    backface-visibility: hidden;
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .card-back {
    transform: rotateY(180deg);
  }
</style>
