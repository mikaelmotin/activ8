<script>
    import RouteGuard from "../../../components/RouteGuard.svelte";

    // Studyset data:
    export let data;
</script>

<RouteGuard>

<!-- Studysets are displayed in below grid: -->
<div class="grid grid-cols-2 gap-y-8">
    {#if data.studysets == ""}
    <!-- Change w/h-screen to -full later-->
        <div class="w-screen h-screen flex flex-col justify-center items-center">
            <p class="font-bold text-3xl">This folder is empty</p>
            <button class="flex flex-col items-center">
                <img draggable="false" class="w-32 mt-12 hover:scale-105 duration-300" src="/new_studySet.svg" alt="">
                <p class="font-normal">Press to create a new studyset</p>
            </button>
        </div>


    {:else}
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
    {/if}
</div>

</RouteGuard>
