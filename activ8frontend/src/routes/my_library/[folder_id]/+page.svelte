<script>
    import RouteGuard from "../../../components/RouteGuard.svelte";

    // Studyset data:
    export let data;
</script>

<RouteGuard>

    <div class="flex flex-col w-screen h-screen bg-[#ECF0F5] items-center">
        <nav class="flex w-full mt-4">
          <img class="h-14 ml-12" src="/Activ8_logo.svg" alt="activ8 logo">
          <img class="h-14 ml-auto mr-12" src="/user.png" alt="user profile">
        </nav>
        <div class="flex mt-4 mx-12 bg-white w-[80%] h-full justify-center">
          <!-- Study sets are created below -->
                    <div class="flex w-full h-full justify-center">
                        <!-- Studysets are displayed in below grid: -->
                            
                                {#if data.studysets == ""}
                                <!-- Change w/h-screen to -full later-->
                                    <div class="w-full h-full flex flex-col justify-center items-center border-2">
                                        <p class="font-bold text-3xl">This folder is empty</p>
                                        <button class="flex flex-col items-center">
                                            <img draggable="false" class="w-32 mt-12 hover:scale-105 duration-300" src="/new_studySet.svg" alt="">
                                            <p class="font-normal">Press to create a new studyset</p>
                                        </button>
                                    </div>


                                {:else}
                                <div class="flex justify-center w-full">
                                <div class="grid mt-4 grid-cols-2 w-full h-full">
                                    {#each data.studysets as studyset (studyset.id)}
                                        <div
                                            class="flex flex-col bg-[#00D5AF] w-96 h-32 rounded-xl hover:scale-105 hover:shadow-xl duration-300 cursor-pointer"
                                        >
                                            <p class="truncate mt-4 ml-4 text-white text-xl font-semibold">
                                                {studyset.title}
                                            </p>
                                            <p class="truncate mt-2 ml-4 text-white text-sm font-thin">
                                                {studyset.description}
                                            </p>
                                            <p class="self-end mt-2 mr-4 font-thin text-white">12 cards</p>
                                        </div>
                                    {/each}
                                </div>
                                </div>
                                {/if}
                            

                      
                    </div>
        </div>
      </div>





</RouteGuard>
