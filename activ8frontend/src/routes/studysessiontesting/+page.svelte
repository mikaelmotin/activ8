<script>
  import SockJS from "sockjs-client";
  import Stomp from "stompjs";

  let stompClient;

  let DIFFICULTY = "HARD";

  async function triggerTestMessage() {
    try {
      const response = await fetch(
        "http://localhost:8080/ws/send-test-message",
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
      console.log("Test message triggered successfully");
    } catch (error) {
      console.error("Failed to trigger test message:", error);
    }
  }

  async function startSession() {
    try {
      const response = await fetch(
        "http://localhost:8080/api/studysessions/start/6579d1a402c1827917b505b1",
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
    } catch (error) {
      console.error("Starting studysession failed with error: ", error);
    }
  }
  async function getNextCard() {
    try {
      const response = await fetch(
        "http://localhost:8080/api/studysessions/nextCard/myIdIs6969/" + sessionId,
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
      let data = await response.json();
      console.log("apapapap" + data);
    } catch (error) {
      console.error("Getting next card failed with error: ", error);
    }
  }
  async function assignDifficulty() {
    try {
      const response = await fetch(
        "http://localhost:8080/api/studysessions/assignDifficulty/6579d1a402c1827917b505b7",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            difficulty: DIFFICULTY,
          }),
          credentials: "include",
        },
      );
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      console.log("Difficulty triggered successfully");
      let data = await response.json();
      console.log("apapapap" + data);
    } catch (error) {
      console.error("Changing difficulty failed with error: ", error);
    }
  }
  async function flipCard() {
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
            studySetId: "696969",
            flashcardId: "420420"
          }),
          credentials: "include",
        },
      );
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      console.log("flipCard triggered successfully");
      let data = await response.json();

    } catch (error) {
      console.error("Changing difficulty failed with error: ", error);
    }
  }
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
      console.log("endSession triggered successfully");
      let data = await response.json();

    } catch (error) {
      console.error("Changing difficulty failed with error: ", error);
    }
  }

  //PROGRESS BAR TESTING BELOW
  let progressValue = 0;
  let sessionId = ""; 

  function connect() {

    const socket = new SockJS("http://localhost:8080/ws"); // Replace with your server URL
    stompClient = Stomp.over(socket);

    sessionId= generateSessionId();

    console.log("apapapa")
    console.log('/topic/progress/' + sessionId)
    console.log("apapapa")
    
    stompClient.connect({}, frame => {
      console.log("Connected: " + frame);

      // Subscribe to the progress topic
      stompClient.subscribe('/topic/progress/' + sessionId, message => {
        
        // Handle the progress update
        const progressUpdate = JSON.parse(message.body);
        console.log("APAPAPAPA" + progressUpdate);
        progressValue = progressUpdate * 100;
        console.log("Progress Update:", progressUpdate);

      // Update your application state or UI here
      // For example, updating a progress bar value
    });
  });
}

function disconnect() {
  if (stompClient) {
    stompClient.disconnect();
    console.log("Disconnected");
  }
}
// SUPER IMPORTANT DON'T FORGET THIS IN REAL IMPLEMENTATION
function generateSessionId() {
  return crypto.randomUUID().toString();
}
  
</script>

<div class="flex flex-col gap-y-2">
  <button on:click={startSession}>Start Sesch</button>
  <button on:click={getNextCard}>GET NEXT CARD </button>
  <button on:click={assignDifficulty}>UPDATE DIFFICULTY </button>
  <button on:click={connect}>Connect WebSocket</button>
  <button on:click={triggerTestMessage}>Test WebSocket</button>
  <button on:click={flipCard}>Flip Card</button>
  <button on:click={endSession}>End Study Session</button>
  <button on:click={disconnect}>Disconnect WebSocket</button>

  <!-- Progress Bar: -->
  <div class="w-64 bg-black/5 rounded-full border-2 border-black self-center">
    <div
      class=" bg-gradient-to-r from-sky-200 to-emerald-500 text-xs font-medium text-blue-100 text-center p-0.5 leading-none rounded-full animate-pulse"
      style="width: {progressValue}%"
    >
      <p class="text-black w-full text-center">{progressValue}%</p>
    </div>
  </div>
</div>
