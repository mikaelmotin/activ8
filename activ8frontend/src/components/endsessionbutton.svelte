<!-- EndSessionButton.svelte -->

<script>
  // You can leave this script section empty if you don't need any logic for now
</script>

<style>
  .end-session-button {
    position: absolute;
    top: 10%;
    left: 67%;
    transform: translateX(-50%);
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
    background-color: rgb(223, 166, 105); /* Change the background color as needed */
    width: 131px;
    height: 34px;
    z-index: 1;
    flex-shrink: 0;
    cursor: pointer; /* Add this line to indicate that it's clickable */
  }
</style>

<button class="end-session-button">
  End Session
</button>