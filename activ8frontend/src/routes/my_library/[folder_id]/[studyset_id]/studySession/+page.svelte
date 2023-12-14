<script>
  import Flashcard from '../../../../../components/flashcard.svelte';
  import { onMount } from 'svelte';
  import { goto } from "$app/navigation";

  let front = '';
  let back = '';
  let isFlipped = false;
  export let data;
  let difficulty = "MEDIUM";

  console.log("Studyset Id", data.studyset_id, data)

  async function endSession() {
    // You may want to add additional logic for ending the session on the server
    console.log('Session ended');
    goto("./");
  }

  async function startSession(studySetId) {
    try {
      const response = await fetch(`http://localhost:8080/api/studysessions/start/${studySetId}`, {
        method: 'POST',
        headers: {
          "Content-Type": "application/json",
        },
        credentials: "include",
      });

      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }

      // If session starts successfully, fetch the first flashcard
      await getNextCard();
    } catch (error) {
      console.error("Starting studysession failed with error: ", error);
    }
  }
  async function getNextCard() {
    try {
      const response = await fetch('http://localhost:8080/api/studysessions/nextCard', {
        method: 'GET',
        headers: {
          "Content-Type": "application/json",
        },
        credentials: "include",
      });

      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }

      console.log("Next card triggered successfully");
      // Set card data to display on the front and back
      const cardData = await response.json();
      console.log(cardData);

      // Set card data to display on the front and back
      setCardData(cardData.term, cardData.definition);
    } catch (error) {
      console.error("Getting next card failed with error: ", error);

      // If getting the next card fails, call itself again
      await generateFlashcard();
    }
  }

  async function generateFlashcard() {
    // Recursively call itself if getting the next card fails
    await getNextCard();
  } 
  async function assignDifficulty(difficulty, flashcardId) {
    try {
      // Send the selected difficulty to the server
      const response = await fetch('http://localhost:8080/api/studysessions/assignDifficulty/' + flashcardId, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          difficulty: difficulty,
          flashcardId: flashcardId
        }),
        credentials: 'include',
      });
      
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }

      console.log('Difficulty triggered successfully');
      
      // Generate the next flashcard after assigning difficulty
      await generateFlashcard();
    } catch (error) {
      console.error('Changing difficulty failed with error: ', error);
    }
  }


  function setCardData(term, definition) {
    front = term;
    back = definition;
  }

  // Styling Effects
  function flipCard() {
    isFlipped = !isFlipped;
  }

  function handleKeyDown(event) {
    if (event.key === 'Enter') {
      flipCard();
    }
  }

  // Start the session when the component mounts
  onMount(() => {
    startSession(data.studyset_id);
  });
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
          <button on:click={() => assignDifficulty('EASY')} class="easy-button px-4 py-3 font-bold text-center border rounded-md box-border focus:outline-none focus:ring focus:border-blue-300">
            Easy
          </button>
          <button on:click={() => assignDifficulty('MEDIUM')} class="medium-button px-4 py-3 font-bold text-center border rounded-md box-border focus:outline-none focus:ring focus:border-blue-300">
            Medium
          </button>
          <button on:click={() => assignDifficulty('HARD')} class="hard-button px-4 py-3 font-bold text-center border rounded-md box-border focus:outline-none focus:ring focus:border-blue-300">
            Hard
          </button>
        </div>
      </div>
      <!-- End Session Button -->
      <button on:click={endSession} class="absolute top-4 right-4 px-4 py-2 bg-red-500 text-white rounded-md cursor-pointer">End Session</button>
    </div>
  </div>
</main>