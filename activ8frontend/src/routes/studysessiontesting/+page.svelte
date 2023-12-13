<script>
  import SockJS from 'sockjs-client';
  import Stomp from 'stompjs';

  let stompClient;

  let DIFFICULTY = "HARD"

  function connect() {
    const socket = new SockJS('http://localhost:8080/ws');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, frame => {
      console.log('Connected: ' + frame);
      stompClient.subscribe('/topic/nextCard', message => {
        alert("Received: " + message.body);
      });
    });
  }

  function disconnect() {
    if (stompClient) {
      stompClient.disconnect();
      console.log("Disconnected");
    }
  }

  async function triggerTestMessage() {
    try {
      const response = await fetch('http://localhost:8080/ws/send-test-message', {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
        credentials: "include",
      });
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
      const response = await fetch('http://localhost:8080/api/studysessions/start/6579d1a402c1827917b505b1', {
        method: 'POST',
        headers: {
          "Content-Type": "application/json",
        },
        credentials: "include",

      })
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      
    } catch (error) {
      console.error("Starting studysession failed with error: ", error)
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
      })
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      console.log("Next card triggered successfully");
      let data = await response.json()
      console.log("apapapap" + data)
    } catch (error) {
      console.error("Getting next card failed with error: ", error)
      
    }
  }
    async function assignDifficulty() {
    try {
      const response = await fetch('http://localhost:8080/api/studysessions/assignDifficulty/6579d1a402c1827917b505b7', {
        method: 'POST',
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          difficulty: DIFFICULTY
        }),
        credentials: "include",
      })
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      console.log("Difficulty triggered successfully");
      let data = await response.json()
      console.log("apapapap" + data)
    } catch (error) {
      console.error("Changing difficulty failed with error: ", error)
    }
  }
</script>
<div class="flex flex-col gap-y-2">
  <button on:click={startSession}>Start Sesch</button>
  <button on:click={getNextCard}>GET NEXT CARD </button>
  <button on:click={assignDifficulty}>UPDATE DIFFICULTY </button>
  <button on:click={connect}>Connect WebSocket</button>
  <button on:click={triggerTestMessage}>Test WebSocket</button>
  <button on:click={disconnect}>Disconnect WebSocket</button>
</div>

