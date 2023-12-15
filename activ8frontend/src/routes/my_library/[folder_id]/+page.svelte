<script>
    import RouteGuard from "../../../components/RouteGuard.svelte";

    // Studyset data:
    export let data;
    let editMode = false;

    const removeStudySet = async (studySetId) => {
    try {
        const response = await fetch(
            `http://localhost:8080/api/studysets/${studySetId}`,
            {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json",
                },
                credentials: "include",
            },
        );

        if (response.ok) {
            console.log("Study Set removed successfully");

            // Remove the study set from the local data
            const indexToRemove = data.studysets.findIndex((studyset) => studyset.id === studySetId);

            if (indexToRemove !== -1) {
                // Remove the study set from the local data
                data.studysets.splice(indexToRemove, 1);
                data.studysets = [...data.studysets]; // Ensure reactivity by creating a new array reference
            }
        } else {
            console.error("Request failed with status:", response.status);
        }
    } catch (error) {
        console.error("Network error:", error);
    }
};

    const toggleEditMode = () => {
        editMode = !editMode;
    };

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
            <div>
                <button
                    class="border border-gray-500 rounded"
                    style="width: 100px; height: 40px; position: absolute; right: 180px; top: 25px; margin-right: 10px;"
                    on:click={toggleEditMode}
                >
                    {#if editMode}
                        Done
                    {:else}
                        Edit study set
                    {/if}
                </button>
                <a href="{window.location.href}/createStudySet">
                    <button
                        class="border border-gray-500 rounded"
                        style="width: 100px; height: 40px; position: absolute; right: 300px; top: 25px;"
                    >
                        Add study set
                    </button>
                </a>
            </div>
        </nav>
        <div class="flex mt-4 mx-12 bg-white w-[80%] h-full justify-center">
            <!-- Study sets are created below -->
            <div
                class="flex mt-4 mx-auto bg-white w-[80%] h-full justify-center"
            >
                <!-- Studysets are displayed in below grid: -->

                {#if data.studysets == ""}
                    <!-- Change w/h-screen to -full later-->
                    <div
                        class="w-full h-full flex flex-col justify-center items-center border-2"
                    >
                        <p class="font-bold text-3xl">This folder is empty</p>
                        <a href="{window.location.href}/createStudySet">
                            <button class="flex flex-col items-center">
                                <img
                                    draggable="false"
                                    class="w-32 mt-12 hover:scale-105 duration-300"
                                    src="/new_studySet.svg"
                                    alt=""
                                />
                                <p class="font-normal">
                                    Press to create a new studyset
                                </p>
                            </button>
                        </a>
                    </div>
                {:else}
                    <div class="flex justify-center w-full">
                        <div class="grid mt-4 grid-cols-2 w-full h-full">
                            {#each data.studysets as studyset (studyset.id)}
                            <a
                        href={editMode ? "" : `${window.location.href}/${studyset.id}`}
                        class="flex flex-col bg-[#00D5AF] w-96 h-32 rounded-xl hover:scale-105 hover:shadow-xl duration-300 relative"
                    >
                            {#if editMode}
                                <button
                                    class="absolute z-10 top-2 right-2 p-2 text-red-500"
                                    on:click={() => removeStudySet(studyset.id)}
                                >
                                    &#10006;
                                </button>
                            {/if}

                                    <p
                                        class="truncate mt-4 ml-4 text-white text-xl font-semibold"
                                    >
                                        {studyset.title}
                                    </p>
                                    <p
                                        class="truncate mt-2 ml-4 text-white text-sm font-thin"
                                    >
                                        {studyset.description}
                                    </p>
                                    <p
                                        class="self-end mt-2 mr-4 font-thin text-white"
                                    >
                                        
                                    </p>
                                </a>
                            {/each}
                        </div>
                    </div>
                {/if}
            </div>
        </div>
    </div>
</RouteGuard>
