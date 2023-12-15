<script>
  import Flashcard from "../../../../../components/flashcard.svelte";
  import { onMount } from "svelte";
  import { goto } from "$app/navigation";
  import SockJS from "sockjs-client";
  import Stomp from "stompjs";

  let stompClient;
  let sessionId = "";
  let progressBarValue = 0;

  let front = "";
  let back = "";
  let isFlipped = false;
  export let data;
  let flashcardId;


  // Start the session when the component mounts
  onMount(() => {
    startSession(data.studyset_id);
    connect();
  });

  //CONNECT TO WEBSOCKET
  function connect() {
    const socket = new SockJS("http://localhost:8080/ws");
    stompClient = Stomp.over(socket);

    // IMPORTANT - generates a sessionId, without this nothing works
    sessionId = generateSessionId();

    stompClient.connect({}, (frame) => {
      console.log("Connected: " + frame);

      // Subscribe to the progress topic
      stompClient.subscribe("/topic/progress/" + sessionId, (message) => {
        // Handle the progress update
        const progressUpdate = JSON.parse(message.body);
        progressBarValue = (progressUpdate * 100).toFixed(2);
        console.log("Progress Update:", progressUpdate);

        // Update your application state or UI here
        // For example, updating a progress bar value
      });
    });
  }

  // DISCONNECT FROM WEBSOCKET
  function disconnect() {
    if (stompClient) {
      stompClient.disconnect();
      console.log("Disconnected");
    }
  }

  // GENERATE SESSION ID
  function generateSessionId() {
    return crypto.randomUUID().toString();
  }

  // CALL BACKEND TO LOG FLIPPED CARD
  async function logflippedCard() {
    console.log("LOGGED FLIPPED CARD")
    try {
      const response = await fetch(
        "http://localhost:8080/api/studysessions/flipCard",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            sessionId: sessionId,
            studySetId: data.studyset_id,
            flashcardId: flashcardId
          }),
          credentials: "include",
        },
      );
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      console.log("flipCard triggered successfully");
      let resp = await response.json();
    } catch (error) {
      console.error("Flipping card failed with error: ", error);
    }
  }

  // Annelies magic below

  async function endSession() {
    try {
      const response = await fetch(
        "http://localhost:8080/api/studysessions/endSession",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          credentials: "include",
        },
      );

      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      (async () => {
      try {
        disconnect()
      } catch (error) {
        console.error(error);
      }
    })();
      console.log("Session ended successfully");
      goto("./"); // Redirect to the desired page after ending the session
    } catch (error) {
      console.error("Failed to end study session with error:", error);
    }
  }

  async function startSession(studySetId) {
    try {
      const response = await fetch(
        `http://localhost:8080/api/studysessions/start/` + studySetId,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          credentials: "include",
        },
      );

      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }

      // If session starts successfully, fetch the first flashcard
      await nextCard();
      console.log("start studysetid", studySetId);
      console.log(studySetId);
    } catch (error) {
      console.error("Starting studysession failed with error: ", error);
    }
  }
  async function nextCard() {
    try {
      const response = await fetch(
        "http://localhost:8080/api/studysessions/nextCard/" +
          data.studyset_id +
          "/" +
          sessionId,
        {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
          },
          credentials: "include",
        },
      );

      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }

      console.log("Next card triggered successfully");

      const cardData = await response.json();
    

      // Set flashcardId when fetching the next card
      flashcardId = cardData.id;

      // Set card data to display on the front and back
      setCardData(cardData.term, cardData.definition);
    } catch (error) {
      console.error("Getting next card failed with error: ", error);
      await generateFlashcard();
    }
  }

  async function generateFlashcard() {
    // Recursively call itself if getting the next card fails
    await nextCard();
  }
  async function assignDifficulty(difficulty) {
    try {
      // Send the selected difficulty to the server
      const response = await fetch(
        "http://localhost:8080/api/studysessions/assignDifficulty/" +
          flashcardId,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            difficulty: difficulty,
            flashcardId: flashcardId,
          }),
          credentials: "include",
        },
      );

      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }

      console.log("Difficulty triggered successfully");


      // Generate the next flashcard after assigning difficulty
      await generateFlashcard();
    } catch (error) {
      console.error("Changing difficulty failed with error: ", error);
    }
  }

  function setCardData(term, definition) {
    front = term;
    back = definition;
  }

  // Styling Effects
  function flipCard() {
    (async () => {
      try {
        await logflippedCard()
      } catch (error) {
        console.error(error);
      }
    })();
    console.log("OOMPALOOPMA")
    console.log("CLICKEEEDDD")
    isFlipped = !isFlipped;
  }

  function handleKeyDown(event) {
    if (event.key === "Enter") {
      flipCard();
    }
  }
</script>

<main class="bg-gray-100 flex flex-col min-h-screen">
  <div class="flex flex-col">
    <div
      class="flex flex-col bg-gray-100 inset-0 overflow-hidden relative min-h-[1117px] w-full items-end px-8 py-50 shadow-inner"
    >
      <!-- Include the Flashcard component -->
      <button on:click={flipCard}>
      <Flashcard {front} {back} {isFlipped} {flipCard} {handleKeyDown} />
      </button>
      <!-- Difficulty Level Buttons -->
      <div
        class="absolute top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 flex flex-col items-center p-5 z-10"
      >
        <header class="text-xl font-bold mb-4">Difficulty Levels:</header>
        <div class="flex justify-between">
          <button
            on:click={() => assignDifficulty("EASY")}
            class="easy-button px-4 py-3 font-bold text-center border rounded-md box-border focus:outline-none focus:ring focus:border-blue-300"
          >
            Easy
          </button>
          <button
            on:click={() => assignDifficulty("MEDIUM")}
            class="medium-button px-4 py-3 font-bold text-center border rounded-md box-border focus:outline-none focus:ring focus:border-blue-300"
          >
            Medium
          </button>
          <button
            on:click={() => assignDifficulty("HARD")}
            class="hard-button px-4 py-3 font-bold text-center border rounded-md box-border focus:outline-none focus:ring focus:border-blue-300"
          >
            Hard
          </button>
        </div>
      </div>

      <!-- Progress Bar: -->
      <div class="w-full  flex flex-col justify-center items-center">
        <div
          class="w-64 bg-black/5 rounded-full border-2 border-black self-center"
        >
          <div
            class=" bg-gradient-to-r from-sky-200 to-emerald-500 text-xs font-medium text-blue-100 text-center p-0.5 leading-none rounded-full animate-pulse"
            style="width: {progressBarValue}%"
          >
            <p class="text-black w-full text-center">{progressBarValue}%</p>
          </div>
        </div>
        <p class="font-semibold text-xs">Keep studying! Get points when goal is reached</p>
      </div>

      <!-- End Session Button -->
      <button
        on:click={endSession}
        class="absolute top-4 right-4 px-4 py-2 bg-red-500 text-white rounded-md cursor-pointer"
        >End Session</button
      >
    </div>
  </div>
</main>
