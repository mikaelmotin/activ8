<script>
  import Flashcard from '../../../../../components/flashcard.svelte';
  import { onMount } from 'svelte';
  import SockJS from 'sockjs-client';
  import Stomp from 'stompjs';

  let front = '';
  let back = '';
  let isFlipped = false;

  export const load = ({params }) => {
    console.log(params);
    const folder_id = params.folder_id;
    const studyset_id = params.studyset_id;
    const user_id = params.user_id;

    return {
        folder_id: folder_id,
        studyset_id: studyset_id,
        user_id: user_id
    };
}
  // WebSocket and Stomp.js code
  let stompClient;

  function connect() {
    const socket = new SockJS('http://localhost:8080/ws');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, frame => {
      console.log('Connected: ' + frame);
      // You can add any initialization logic here if needed
    });
  }

  function disconnect() {
    if (stompClient) {
      stompClient.disconnect();
      console.log('Disconnected');
    }
  }

  async function startSession(studySetId) {
    try {
      const response = await fetch('http://localhost:8080/api/studysessions/start/' + studySetId, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        credentials: 'include',
      });
      if (!response.ok) {
        console.log('Started!');
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      connect(); // Connect to WebSocket when the session starts
      // Generate the first flashcard
      await generateFlashcard();
    } catch (error) {
      console.error('Starting studysession failed with error: ', error);
    }
  }

  async function getNextCard() {
  try {
    const response = await fetch('http://localhost:8080/api/studysessions/nextCard', {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
      },
      credentials: 'include',
    });

    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }

    const responseBody = await response.text();

    if (responseBody.trim() !== '') {
      const responseData = JSON.parse(responseBody);
      console.log('Next card triggered successfully');
      return responseData;
    } else {
      console.log('No card data found');
      return null;
    }
  } catch (error) {
    console.error('Getting next card failed with error: ', error);
    return null;
  }}

  async function assignDifficulty(difficulty) {
    try {
      const response = await fetch('http://localhost:8080/api/studysessions/assignDifficulty/6579ebf345d0d2509e1a8e03', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          //USERiD

          //FLÖASHCARDID
          difficulty: difficulty
        }),
        credentials: 'include',
      });
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      console.log('Difficulty triggered successfully');
      await generateFlashcard();
    } catch (error) {
      console.error('Changing difficulty failed with error: ', error);
    }
  }

  async function generateFlashcard() {
    const nextCardData = await getNextCard();

    if (nextCardData) {
      setCardData(nextCardData.term, nextCardData.definition);
    } else {
      console.log('No card data found. Trying again...');
      await generateFlashcard(); // Call itself again
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
    startSession('6579ebf345d0d2509e1a8e03'); // Replace with your actual study set ID
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
    </div>
  </div>
</main>