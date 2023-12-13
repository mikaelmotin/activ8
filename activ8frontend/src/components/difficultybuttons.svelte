<script>
  // You can leave this script section empty if you don't need any logic for now
</script>

<div class="absolute top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 flex flex-col items-center p-5 z-10">
  <header class="text-xl font-bold mb-4">Difficulty Levels:</header>
  <div class="flex justify-between">
    <!-- When a difficulty button is clicked, call loadNextFlashcard -->
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