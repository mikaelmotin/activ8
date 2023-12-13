<script>
  import Flashcard from '../../../../../components/flashcard.svelte';

  let front = 'Question';
  let back = 'Answer';
  let isFlipped = false;

  function flipCard() {
    isFlipped = !isFlipped;
  }

  function handleKeyDown(event) {
    if (event.key === 'Enter') {
      flipCard();
    }
  }
  
</script>

<main class="bg-gray-100 flex flex-col min-h-screen">
  <div class="flex flex-col">
    <div class="flex flex-col bg-gray-100 inset-0 overflow-hidden relative min-h-[1117px] w-full items-end px-8 py-50 shadow-inner">
      <!-- Include the Flashcard component -->
      <Flashcard {front} {back} {isFlipped} {flipCard} {handleKeyDown} />

      <!-- Difficulty Level Buttons -->
      <div class="absolute top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 flex flex-col items-center p-5 z-10">
        <header class="text-xl font-bold mb-4">Difficulty Levels:</header>
        <div class="flex justify-between">
          <button class="easy-button px-4 py-3 font-bold text-center border rounded-md box-border focus:outline-none focus:ring focus:border-blue-300">
            Easy
          </button>
          <button class="medium-button px-4 py-3 font-bold text-center border rounded-md box-border focus:outline-none focus:ring focus:border-blue-300">
            Medium
          </button>
          <button class="hard-button px-4 py-3 font-bold text-center border rounded-md box-border focus:outline-none focus:ring focus:border-blue-300">
            Hard
          </button>
        </div>
      </div>
    </div>
  </div>
</main>
