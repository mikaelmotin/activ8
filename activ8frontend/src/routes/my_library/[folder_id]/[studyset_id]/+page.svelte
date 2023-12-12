<script>
    import { onMount } from "svelte";
    import { goto } from "$app/navigation";
    import RouteGuard from "../../../../components/RouteGuard.svelte";
    import FlashcardComponent from "../../../../components/FlashcardComponent.svelte";

    //folder_id:

    export let data;
    console.log(data.studyset.title)
    console.log("flashcards" + data.flashcards)
    console.log("Study Set ID:", data.studyset_id);
    console.log("Study Folder ID:", data.studyset_id);



    
 
    console.log(data);


    // Error message:
    let errormsg = false;

    function displayErrorMsg() {
        errormsg = true;
    }
    function removeErrorMsg() {
        errormsg = false;
    }

    onMount(() => {

        
    });

    // Input values
    let title =  data.studyset.title
    let description = data.studyset.description;

    // List with the flashcards (initializing with old flashcards)
    let flashcards = data.flashcards || [];

    // List with the flashcards:

    // Update the removeFlashcard function
    /*function removeFlashcard(index) {
        flashcards = flashcards.filter((_, i) => i !== index);
        flashcards = [...flashcards]; // Ensure reactivity by creating a new array reference
    }*/

    // Update the addFlashcard function
    function addFlashcard() {
        flashcards = [...flashcards, { term: "", definition: "" }];
        flashcards = [...flashcards]; // Ensure reactivity by creating a new array reference
        console.log(flashcards);
    }

    const saveStudySet = async () => {
    if (
        !title.trim() ||
        !description.trim() ||
        flashcards.some(
            (card) => !card.term.trim() || !card.definition.trim(),
        ) ||
        flashcards.length <= 0
    ) {
        displayErrorMsg();
        console.error(
            "Please fill in all the fields for study set and flashcards.",
        );
        return;
    }

    try {
        // Create new flashcards
        const createdFlashcards = await createFlashcards(data.studyset_id, flashcards);

        // Save or update the study set
        const response = await fetch(
            `http://localhost:8080/api/studysets/` + data.studyset_id,
            {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                },
                credentials: "include",
                body: JSON.stringify({
                    studyFolderId: data.folder_id,
                    title: title,
                    description: description,
                    flashcards: createdFlashcards,
                }),
            },
        );

        if (response.ok) {
            try {
                // Attempt to parse the response as JSON
                const updatedStudySet = await response.json();
                if (updatedStudySet) {
                    // Log the updated study set
                    console.log("Updated Study Set:", updatedStudySet);

                    // Redirect to the updated study set
                    goto("./" + updatedStudySet.id);
                } else {
                    console.error("Response did not contain valid JSON:", response.status, response.statusText);
                }
            } catch (jsonError) {
                console.error("Error parsing JSON from response:", jsonError);
            }
        } else {
            console.error("Request failed with status:", response.status, response.statusText);
        }
    } catch (error) {
        console.error("Network error:", error);
    }
};

const createFlashcards = async (studySetId, flashcards) => {
    // Array to store promises for each flashcard request
    const flashcardPromises = [];

    // Iterate through each flashcard
    for (const flashcard of flashcards) {
        if (flashcard.id) {
            // If the flashcard has an ID, update the existing flashcard
            const updatePromise = fetch(
                `http://localhost:8080/api/flashcards/` + flashcard.id,
                {
                    method: "PUT",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify({
                        flashcardId: flashcard.id,
                        studySetId: data.studyset_id,
                        term: flashcard.term,
                        definition: flashcard.definition,
                    }),
                    credentials: "include",
                },
            );

            flashcardPromises.push(updatePromise);
        } else {
            // If no ID is present, create a new flashcard
            const createPromise = fetch(
                `http://localhost:8080/api/flashcards/` + studySetId,
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
            )
                .then(response => response.json()) // Assuming the server responds with the created flashcard
                .then(createdFlashcard => {
                    console.log(`New flashcard created with ID: ${createdFlashcard.id}`);
                    return createdFlashcard;
                })
                .catch(error => {
                    console.error("Error creating flashcard:", error);
                    return null;
                });

            flashcardPromises.push(createPromise);
        }
    }

    // Wait for all flashcard requests to complete
    const flashcardResponses = await Promise.all(flashcardPromises);

    // Extract the updated or created flashcards from the responses
    const resultFlashcards = flashcardResponses.filter(Boolean); // Filter out any null values

    return resultFlashcards;
};

async function removeFlashcard(index) {
        const flashcardToDelete = flashcards[index];

        if (flashcardToDelete.id) {
            // If the flashcard has an ID, send a DELETE request to remove it from the server
            try {
                const response = await fetch(
                    `http://localhost:8080/api/flashcards/` + flashcardToDelete.id,
                    {
                        method: "DELETE",
                        credentials: "include",
                    }
                );

                if (!response.ok) {
                    console.error("Failed to delete flashcard:", response.status, response.statusText);
                    return;
                }
            } catch (error) {
                console.error("Network error:", error);
                return;
            }
        }

        // Remove the flashcard from the local state
        flashcards = flashcards.filter((_, i) => i !== index);
        flashcards = [...flashcards]; // Ensure reactivity by creating a new array reference
    }


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
                    Edit Study Set
                </p>
                <!-- Edit button below, should start study session. Needs one test and one-->
                <button
                    on:click={saveStudySet}
                    class="mt-4 mr-4 bg-blue-500 rounded-full p-2 font-semibold text-white hover:scale-110 duration-300"
                    >Start Session</button
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
                    bind:value={title}
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
                {#each flashcards as flashcard, index}
                    <div on:input={removeErrorMsg}>
                        <FlashcardComponent {flashcard} {removeFlashcard} {index} />
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
                on:click={saveStudySet}
                class="mt-20 mr-4 bg-blue-500 rounded-full w-1/3 self-center p-2 font-semibold text-white hover:scale-110 duration-300"
                >Save Study Set</button
            >
        </div>
    </div></RouteGuard
>
