<script>

</script>

<div class="main-container">
  <button class="right-button">Right</button>
  <button class="wrong-button">Wrong</button>
</div>

<style>
  .main-container {
    display: flex;
    align-items: center;
    justify-content: center; /* Center horizontally */
    padding: 20px;
    z-index: 1;
    margin-top: 45%;
    transform: translateX(-50%);
    margin-right: 10%;
  }

  .right-button,
  .wrong-button {
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
    width: 131px;
    height: 34px;
    flex-shrink: 0;
    font-family: Inconsolata, -apple-system, Roboto, Helvetica, sans-serif;
    background-color: rgb(99, 235, 138); /* Blue color for Right button, change as needed */
    margin-right: 50%; /* Add margin between buttons */
  }

  .wrong-button {
    background-color: rgb(194, 56, 56); /* Red color for Wrong button, change as needed */
  }
</style>

