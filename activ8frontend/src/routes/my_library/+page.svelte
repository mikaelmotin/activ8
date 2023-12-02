<script>
    import Onefolder from "../../components/onefolder.svelte";
    import RouteGuard from "../../components/RouteGuard.svelte";

    let imagePath = 'https://icons.iconarchive.com/icons/dtafalonso/yosemite-flat/512/Folder-icon.png';

    let responseData = null;
    const fetchData = async () => {
    try {
      const response = await fetch("http://localhost:8080/api/studyfolders", {
            method: "GET",
            headers: {
                'Content-Type': 'application/json',
            },

            credentials: 'include',
        });

      if (response.ok) {
        responseData = await response.json();
        console.log('Data:', responseData);
      } else {
        console.error('Request failed with status:', response.status);
      }
    } catch (error) {
      console.error('Network error:', error);
    }
    console.log(responseData[0].title)
  };

</script>



<RouteGuard>
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
</RouteGuard>


