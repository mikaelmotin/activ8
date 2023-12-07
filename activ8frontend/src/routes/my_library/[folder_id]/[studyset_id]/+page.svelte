<script>
    import { onMount } from "svelte";
    import { goto } from "$app/navigation";
    import RouteGuard from "../../../../components/RouteGuard.svelte";
    import FlashcardComponent from "../../../../components/FlashcardComponent.svelte";

    //folder_id:
    export let data;
    console.log(data);

    // Error message:
    let errormsg = false;

    function displayErrorMsg() {
        errormsg = true;
    }
    function removeErrorMsg() {
        errormsg = false;
    }

// Fetch study set data for editing
const fetchStudySetData = async () => {
};
    

    // Input values
    let title = "";
    let description = "";

    // List with the flashcards:
    let flashcards = [{ term: "", definition: "" }];

    // Update the removeFlashcard function
    function removeFlashcard(index) {
        flashcards = flashcards.filter((_, i) => i !== index);
        flashcards = [...flashcards]; // Ensure reactivity by creating a new array reference
    }

    // Update the addFlashcard function
    function addFlashcard() {
        flashcards = [...flashcards, { term: "", definition: "" }];
        flashcards = [...flashcards]; // Ensure reactivity by creating a new array reference
        console.log(flashcards);
        console.log("addFlashcards")
    }

    const createStudySet = async () => {
    console.log("Creating study set...");

    async function fetchStudySetData() {
        // Make API call to fetch study set data based on studyset_id
        // Update title and description with fetched data
        // Optionally, fetch existing flashcards and update flashcards array
        try {
        const response = await fetch(`http://localhost:8080/api/studysets`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            },
            credentials: "include",
        });

        if (response.ok) {
            const studySetData = await response.json();

            // Update title and description with fetched data
            title = studySetData.title;
            description = studySetData.description;

            // Optionally, fetch existing flashcards and update flashcards array
            // Implement this part based on your API endpoint for fetching flashcards
            // For example:
            const flashcardsResponse = await fetch(`http://localhost:8080/api/flashcards`, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                },
                credentials: "include",
            });

            if (flashcardsResponse.ok) {
                const flashcardsData = await flashcardsResponse.json();
                flashcards = flashcardsData; // Update flashcards array with fetched data
            }
        } else {
            console.error("Failed to fetch study set data:", response.status);
        }
    } catch (error) {
        console.error("Network error:", error);
    }
    }

    onMount(() => {
        fetchStudySetData();
    });


    // Validate input
    if (!title.trim() || !description.trim() || flashcards.length === 0) {
        console.error("Validation failed:", title, description, flashcards);
        displayErrorMsg();
        return;
    }

    // Validate flashcards
    if (flashcards.some((card) => !card.term.trim() || !card.definition.trim())) {
        console.error("Flashcard validation failed:", flashcards);
        displayErrorMsg();
        return;
    }

    try {
        const response = await fetch("http://localhost:8080/api/studysets", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            credentials: "include",
            body: JSON.stringify({
                studyFolderId: data.folder_id,
                title: title,
                description: description,
            }),
        });

        if (response.ok) {
            const newStudySet = await response.json();
            console.log("Study set created:", newStudySet);
            
            // Assuming `createFlashcards` is a function that creates flashcards
            createFlashcards(newStudySet.id);

            // Assuming `goto` is a function to navigate to a different page
            goto("./" + newStudySet.id);
        } else {
            console.error("Request failed with status:", response.status);
        }
    } catch (error) {
        console.error("Network error:", error);
    }
};

    const createFlashcards = async (studySetId) => {
        console.log("Creating flashcard...")
        flashcards.forEach(async (flashcard) => {
            let createdFlashcard = await fetch(
                "http://localhost:8080/api/flashcards/" + studySetId,
                {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify({
                        studySetId: studySetId,
                        term: flashcard.term,
                        definition: flashcard.definition,
                    }),
                    credentials: "include",
                },
            );
        });
    };
</script>

<RouteGuard>
    <div class="flex flex-col w-screen h-[100%] bg-[#ECF0F5] items-center">
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
            <div class="flex justify-between w-full">
                <p class="mt-4 ml-4 text-4xl font-bold">
                    Create a New Study Set
                </p>
                <button
                    on:click={createStudySet}
                    class="mt-4 mr-4 bg-blue-500 rounded-full p-2 font-semibold text-white hover:scale-110 duration-300"
                    >Create Study Set</button
                >
            </div>
            <div class="flex flex-col mt-12 mx-12 self-center">
                {#if errormsg}
                    <div class="text-center">
                        <p class="font-semibold text-red-500">Please fill in all the fields</p>
                        <p class="font-semibold text-red-500">Must include at least one flashcard</p>
                    </div>
                {/if}
                <p class="text-2xl">Title</p>

                <input
                on:input={removeErrorMsg}
                bind:value={data.studySetId}
                name="term"
                class="border-b-4 mb-8 border-black w-72 outline-none font-semibold text-lg text-center focus:border-[#008DD5]"
                type="text"
            />

                <p class="text-2xl">Description</p>
                <textarea
                    on:input={removeErrorMsg}
                    bind:value={description}
                    name="definition"
                    rows="3"
                    class="border-b-4 border-black w-full outline-none font-thin resize-none focus:border-[#008DD5]"
                    type="text"
                ></textarea>
            </div>
            <div class="mt-20 mx-2">
                {#each flashcards as flashcard, index (flashcard.term + flashcard.definition)}
                  <div on:input={removeErrorMsg}>
                    <FlashcardComponent
                      {flashcard}
                      {removeFlashcard}
                      {index}
                    />
                  </div>
                {/each}
              </div>
            <div
                class="self-center mt-12 border-dotted border-4 w-1/3 hover:scale-110 duration-300 outline-none"
            >
                <button
                    on:click={addFlashcard}
                    class="text-center w-full font-semibold text-xl"
                    >+Add Flashcard</button
                >
            </div>
            <button
                on:click={createStudySet}
                class="mt-20 mr-4 bg-blue-500 rounded-full w-1/3 self-center p-2 font-semibold text-white hover:scale-110 duration-300"
                >Create Study Set</button
            >
        </div>
    </div>

    
    
    </RouteGuard
>
