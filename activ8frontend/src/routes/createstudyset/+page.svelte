<script>
  import Subsetflashcard from '../../components/subsetflashcard.svelte';
  import { onMount } from "svelte";

  let newCard = "";
  let flashcards = []; 


  const fetchData = async () => {
    // Fetch data from the database if needed
  }

  const addCard = () =>{
    console.log("newCard", newCard)
    console.log("newCard.term")
    console.log("newCard.definition")
    // add to flashcards


  }

  const saveToDatabase = async (flashcard) => {
    console.log('Saving to database:', flashcard);
    const response = await fetch('ändra', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(flashcard),
    });

    if (response.ok) {
      console.log('Flashcard saved successfully!');
    } else {
      console.error('Failed to save flashcard to the database');
    }
  }
  </script>
  
  <div class="main-container">
    <header class="header">
      <div class="study-set">
        <div class="create-study-set">Create a new study set</div>
        <button class="start-study-session">Start study session</button>
      </div>
  
      <div class="title-and-input">
        <div class="title">Title</div>
        <input type="text" placeholder="Enter title" class="subset-title" />
      </div>
      <div class="description-and-input">
        <div class="description">Description</div>
        <input type="text" placeholder="Enter description" class="subset-description" />
      </div>

      <div class = "list-bg">
        
        <Subsetflashcard bind:newCard={newCard}/>
  
      </div>

  
      <!-- Add Card button -->
      <button on:click={addCard} class="add-card-button">Add Card +</button>
    </header>
  </div>
  

  <style>
    .main-container {
      background-color:#ecf0f5;
      display: flex;
      flex-direction: column;
      justify-content: center;
    }
  
    .header {
      background-color: #fff;
      position: relative;
      display: flex;
      align-items: center;
      margin-top: 100px;
      width: 60%;
      max-width: 1269px;
      flex-direction: column;
      padding: 7px 0 15px 44px;
      margin-left: 20%;
    }
  
    @media (max-width: 991px) {
      .header {
        max-width: 100%;
        margin-top: 40px;
        padding-left: 20px;
      }
    }
  
    .study-set {
      align-self: stretch;
      display: flex;
      padding-right: 6px;
      align-items: start;
      justify-content: space-between;
      gap: 20px;
    }
  
    @media (max-width: 991px) {
      .study-set {
        max-width: 100%;
        flex-wrap: wrap;
      }
    }
  
    .create-study-set {
      color: #000;
      flex-grow: 1;
      flex-basis: auto;
      font: 700 35px Inconsolata, sans-serif;
    }
  
    @media (max-width: 991px) {
      .create-study-set {
        max-width: 100%;
      }
    }
  
    .start-study-session {
      color: #000;
      text-align: center;
      border-radius: 15px;
      background-color: #d2e0fb;


    }
  
    @media (max-width: 991px) {
      .start-study-session {
        padding: 0 20px;
      }
    }
  

    .title {
      color: #000;
      align-self: start;
      max-width: 280px;
      font: 400 30px Inconsolata, sans-serif;
    }
    .description {
      color: #000;
      align-self: start;
      max-width: 280px;
      margin: 6px 0 0 57px;
      font: 400 30px Inconsolata, sans-serif;
    }
  
    @media (max-width: 991px) {
      .description {
        margin-left: 10px;
      }
    }

    .add-card-button{
    width: 90%;
    height: 50px;
    border-radius: 0px 0px 10px 10px;
    background: #FFF;
    box-shadow: 0px 0px 4px 0px rgba(0, 0, 0, 0.25);
    }



  
    .title-and-input {
        margin-top: 20px; /* Adjust the margin as needed */
        margin-left: -60%;
    }

    .description-and-input {
        display: flex;
        flex-direction: column;
        margin-top: 20px; /* Adjust the margin as needed */
        margin-left: -60%;
    }

    .title,
    .description {
        color: #000;
        font: 400 30px Inconsolata, sans-serif;
        margin-left: 0; /* Align to the left */
        max-width: 280px;
    }

    .subset-title,
    .subset-description {
        margin-left: 0; /* Align to the left */
        padding: 8px;
    }
    .list-bg{
        background-color: #fff;
        width: auto;
        height: auto;
        overflow: auto; /* Enable scrolling */
    
    }
  </style>