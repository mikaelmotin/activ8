<!-- Folder.svelte -->
<script>
  export let imagePath = 'https://icons.iconarchive.com/icons/dtafalonso/yosemite-flat/512/Folder-icon.png';
  // export let folderName = 'Untitled Folder';

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


<button on:click={fetchData}>Get Folders</button>


{#if responseData}
<div class="w-screen grid grid-cols-4">
  {#each responseData as folder (folder.id)}
    <div class="h-32 w-screen gap-y-8">
      <img class="w-32 h-fit" src={imagePath} alt="Folder Icon" />
      <p>{folder.title}</p>
    </div>
  {/each}
</div>
{:else}
  <p>Press the button.</p>
{/if}










<!-- Din är kod kvar här nere -->
<!-- <div class=".folder">
  <img src={imagePath} alt="Folder Icon" />
  <p>{folderName}</p>
</div> -->

<style>
  .folders {
    margin-top: 20px;
    display: flex;
    flex-direction: column;
  }

  .div {
    gap: 20px;
    display: flex;
  }

  @media (max-width: 991px) {
    .div {
      flex-direction: column;
      align-items: stretch;
      gap: 0px;
    }
  }

  .column {
    display: flex;
    flex-direction: column;
    line-height: normal;
    width: 25%;
    margin-left: 0px;
  }

  @media (max-width: 991px) {
    .column {
      width: 100%;
    }
  }

  .onefolder {
    display: flex;
    flex-direction: column;
    position: relative;
    margin-top: 20px;
  }

  .column-2,
  .column-3,
  .column-4 {
    display: flex;
    flex-direction: column;
    line-height: normal;
    width: 25%;
    margin-left: 20px;
  }

  @media (max-width: 991px) {
    .column-2,
    .column-3,
    .column-4 {
      width: 100%;
    }
  }

</style>
