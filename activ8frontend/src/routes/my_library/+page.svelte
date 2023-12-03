<script>
  import Onefolder from "../../components/onefolder.svelte";
  import RouteGuard from "../../components/RouteGuard.svelte";

  let imagePath =
    "https://icons.iconarchive.com/icons/dtafalonso/yosemite-flat/512/Folder-icon.png";

  let responseData = null;
  const fetchData = async () => {
    try {
      const response = await fetch("http://localhost:8080/api/studyfolders", {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },

        credentials: "include",
      });

      if (response.ok) {
        responseData = await response.json();
        console.log("Data:", responseData);
      } else {
        console.error("Request failed with status:", response.status);
      }
    } catch (error) {
      console.error("Network error:", error);
    }
    console.log(responseData[0].title);
  };
</script>

<button on:click={fetchData}>click</button>

<RouteGuard>
  <div class="grid grid-cols-3">
    {#if responseData}
      {#each responseData as folder (folder.id)}
        <a class="flex flex-col w-56 h-40 hover:scale-110 cursor-pointer duration-300 relative" href="/my_library/{folder.id}">
          <p class="z-10 ml-4 mt-6 text-xl max-w-[80%] text-white font-semibold truncate">{folder.title}</p>
          <p class="z-10 max-w-[80%] h-[60%] ml-4 text-sm text-white font-light truncate">{folder.description}</p>
          <img
            class="absolute z-0  max-w-full w-52 hover:shadow-lg duration-300"
            src="/folder.png"
            alt="folder"
          />
          
        </a>
      {/each}
    {/if}
  </div>
</RouteGuard>

<!-- <RouteGuard>
<h1 class="pl-12 pt-12">Nu har man loggats in och man kan visa folders här</h1>
<button class="mb-20" on:click={fetchData}>Press Me!</button>

{#if responseData}
<div class="grid grid-cols-3 gap-x-12 w-screen h-32 ">
    {#each responseData as folder (folder.id)}
    <img src={imagePath} class="w-32 h-32 object-cover" alt="folder icon">
    <p>{folder.title}</p>
{/each}
</div>
{/if}
</RouteGuard> -->
