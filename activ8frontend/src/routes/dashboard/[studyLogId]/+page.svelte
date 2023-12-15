<script>
    import { onMount } from "svelte";
      import { get } from "svelte/store";
      import { goto } from '$app/navigation';
  
  
    let points = 0;
    let username = "";
    let recentFolders;
    let studylogs;
  
  
    onMount(() => {
      getUsername();
      getPoints();
      getRecentFolders();
      getStudylogs();
      console.log(getStudylogs())
  
    });
  
  
    async function getUsername() {
      try {
        const response = await fetch(
          "http://localhost:8080/api/userData/username",
          {
            method: "GET",
            headers: {},
            credentials: "include",
          },
        );
        if (response.ok) {
          username = await response.text();
        }
      } catch (error) {
        console.error(error);
      }
    }
    async function getPoints() {
      try {
        const response = await fetch(
          "http://localhost:8080/api/userData/points",
          {
            method: "GET",
            headers: {},
            credentials: "include",
          },
        );
        if (response.ok) {
          points = await response.text();
        }
      } catch (error) {
        console.error(error);
      }
    }
    async function getRecentFolders() {
      try {
        const response = await fetch("http://localhost:8080/api/studyfolders", {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
          },
          credentials: "include",
        });
        if (response.ok) {
          recentFolders = await response.json();
          recentFolders = recentFolders.slice(0, 5);
        }
      } catch (error) {
        console.error(error);
      }
    }
    async function getStudylogs() {
    try {
      const response = await fetch("http://localhost:8080/api/userData/studylogs", {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
        credentials: "include",
      });
  
      if (response.ok) {
        const logs = await response.json();
  
        // Iterate through study logs and fetch study set titles
        for (const log of logs) {
          const studySetTitleResponse = await fetch(`http://localhost:8080/api/studysets/${log.studySetId}`, {
            method: "GET",
            headers: {
              "Content-Type": "application/json",
            },
            credentials: "include",
          });
  
          if (studySetTitleResponse.ok) {
            const studySetTitleData = await studySetTitleResponse.json();
            log.title = studySetTitleData.title;
          }
        }
  
        studylogs = logs;
      }
    } catch (error) {
      console.error(error);
    }
  }
  
  </script>
  <div>
  {#if studylogs && studylogs.length > 0}
    {#each studylogs as log (log.id)}
      <div>
        <h1>{log.title}</h1>
        <h1>number of cards: {log.numberOfCards}</h1>
        <h1>number of cards gone through: {log.numberOfCardsIteratedThrough}</h1>
        <h1>startDate: {log.startDate}</h1>
        <h1>endDate: {log.endDate}</h1>
        <h1>time spent: {log.timeSpentInMinutes} minutes</h1>


      </div>
    {/each}
  {:else}
    <p>No study logs available.</p>
  {/if}
</div>