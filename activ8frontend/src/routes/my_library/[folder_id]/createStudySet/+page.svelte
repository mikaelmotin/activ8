<script>
    import { onMount } from "svelte";

    //folder_id:
    export let data;

    import RouteGuard from "../../../../components/RouteGuard.svelte";
    import Subsetflashcard from "../../../../components/subsetflashcard.svelte";
    import FlashcardComponent from "../../../../components/FlashcardComponent.svelte";

    console.log(data);

    // Input values 
    let title = "";
    let description = "";


    // List with the flashcards:
    let flashcards = [
        {term: "", definition: ""}
    ]

    function addFlashcard() {
        flashcards = [...flashcards, {term: "", definition: ""}];
        console.log(flashcards)
    }








    // let newCard = { term: "", definition: "" };
    // let flashcards_list = [];

   
    onMount(() => {
        // Start with an empty flashcard in the list:
        // flashcards_list.push(newCard);
    })

    const fetchData = async () => {
        // Fetch data from the database if needed
    };

    const addCard = () => {
        
        // flashcards_list.push(newCard);
        
        // console.log("newCard", newFlashcard);

        // const flashcard = {
        //     term: newFlashcard.term,
        //     definition: newFlashcard.definition,
        // };

        // flashcards = [...flashcards, flashcard];

        // // Clear the input fields
        // console.log(flashcard);
    };

    const saveCards = () => {
        flashcards_list.forEach(async (flashcard) => {
            await saveToDatabase(flashcard);
        });
    };

    /*
  const saveToDatabase = async (flashcard) => {
    console.log('Saving to database:', flashcard);
    const response = await fetch('http://localhost:8080/api/flashcards/{studySetId}', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(flashcard),
      credentials: "include"
    });

    if (response.ok) {
      console.log('Flashcard saved successfully!');
    } else {
      console.error('Failed to save flashcard to the database');
    }
  }
*/
</script>

<RouteGuard>
    <div class="flex flex-col w-screen h-screen bg-[#ECF0F5] items-center">
        <nav class="flex w-full mt-4">
            <div style="width: 100%; height: 100%; position: relative">
                <div
                    style="width: 77px; height: 77px; left: 20px; top: 0px; position: absolute"
                >
                    <div
                        style="width: 77px; height: 77px; left: 0px; top: 0px; position: absolute; background: #967E76; border-radius: 9999px"
                    ></div>
                    <div
                        style="width: 40px; height: 40px; left: 11px; top: 10px; position: absolute; background: white; border-radius: 9999px"
                    ></div>
                </div>
                <div
                    style="width: 8px; height: 13px; left: 43px; top: 15px; position: absolute; color: #967E76; font-size: 24px; font-family: Impact; font-weight: 400; word-wrap: break-word"
                >
                    8
                </div>
            </div>

            <img
                class="h-14 ml-auto mr-12"
                src="/user.png"
                alt="user profile"
            />
        </nav>

        <!-- "White Canvas" -->
        <div class="flex flex-col mt-4 mx-12 bg-white w-[80%] h-full">
            <div class="flex justify-between w-full "> 
                <p class="mt-4 ml-4 text-4xl font-bold">Create a New Study Set</p>
                <button class="mt-4 mr-4 bg-blue-500 rounded-full p-2 font-semibold text-white hover:scale-110 duration-300">Create Study Set</button>
            </div>
            <div class="flex flex-col mt-12 mx-12 self-center">
                <p class="text-2xl">Title</p>
                <input bind:value={title} name="term" class="border-b-4 mb-8 border-black w-72 outline-none font-semibold text-lg text-center" type="text">

                <p class="text-2xl">Description</p>
                <textarea bind:value={description} name="definition" rows="3" class="border-b-4 border-black w-full outline-none font-thin resize-none"  type="text"></textarea>
            </div>
            <div class="mt-20 mx-2">
                {#each flashcards as flashcard }
                    <FlashcardComponent {flashcard} />                
                {/each}

            </div>
            <div class="self-center mt-12 border-dotted border-4 w-1/3 hover:scale-110 duration-300 outline-none">
                <button on:click={addFlashcard} class="text-center w-full font-semibold text-xl">+Add Flashcard</button>
            </div>
            <button class="mt-20 mr-4 bg-blue-500 rounded-full w-1/3 self-center p-2 font-semibold text-white hover:scale-110 duration-300">Create Study Set</button>
        </div>
        
</RouteGuard>
