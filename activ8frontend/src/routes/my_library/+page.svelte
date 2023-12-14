<script>
  import { onMount } from "svelte";
  import RouteGuard from "../../components/RouteGuard.svelte";
  import AddFolderPopUp from "../../components/AddFolderPopUp.svelte";

  let folderPopUp = { title: "", description: "" };

  export let responseData = null;
  let editMode = false;
  let toggleFolderAdd = false;


  function closeFolderPopUp() {
    toggleFolderAdd = false;
  }

  function openFolderPopUp() {
    toggleFolderAdd = true;
  }

  onMount(() => {
    fetchData();
  });

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

  const addFolder = async () => {
    try {
      const response = await fetch("http://localhost:8080/api/studyfolders", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        credentials: "include",
        body: JSON.stringify({
          title: folderPopUp.title,
          description: folderPopUp.description,
        }),
      });

      if (response.ok) {
        const newFolder = await response.json();
        console.log("New Folder:", newFolder);
        fetchData();
        toggleFolderAdd = false;
      } else {
        console.error("Request failed with status:", response.status);
      }
    } catch (error) {
      console.error("Network error:", error);
    }
  };
  const removeFolder = async (folderId) => {
    try {
      const response = await fetch(
        `http://localhost:8080/api/studyfolders/${folderId}`,
        {
          method: "DELETE",
          headers: {
            "Content-Type": "application/json",
          },
          credentials: "include",
        },
      );

      if (response.ok) {
        console.log("Folder removed successfully");
        fetchData();
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
  <div
    class="flex flex-col min-w-screen w-[100%] min-h-screen h-[100%] bg-[#ECF0F5] items-center"
  >
    {#if toggleFolderAdd}
      <AddFolderPopUp {folderPopUp} {closeFolderPopUp} {addFolder} />
    {/if}

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

      <div>
        <img class="h-14 ml-auto mr-12" src="/user.png" alt="user profile" onclick="window.location.href = window.location.href + '/dashboard'" />
    </div>
      <button
        on:click={toggleEditMode}
        class="border border-gray-500 rounded"
        style="width: 100px; height: 40px; position: absolute; right: 180px; top: 25px; margin-right: 10px;"
      >
        {#if editMode}
          Done
        {:else}
          Edit folders
        {/if}
      </button>
      <button
        on:click={openFolderPopUp}
        class="border border-gray-500 rounded"
        style="width: 100px; height: 40px; position: absolute; right: 300px; top: 25px;"
        >Add folders</button
      >
    </nav>

    <div class="flex mt-4 mx-12 bg-white w-[80%] h-full justify-center">
      <!-- Folders are created below -->
      <div class="flex mt-4 items-center w-full">
        <div class="grid grid-cols-3 w-full h-full">
          {#if responseData}
            {#each responseData as folder (folder.id)}
              <a
                class="flex flex-col mx-auto w-56 h-40 hover:scale-110 cursor-pointer duration-300 relative"
                href={editMode ? null : `/my_library/${folder.id}`}
              >
                {#if editMode}
                  <button
                    class="absolute z-10 top-2 right-2 p-2 text-red-500"
                    on:click={() => removeFolder(folder.id)}
                  >
                    &#10006;
                  </button>
                {/if}
                <p
                  class="z-10 ml-4 mt-6 text-xl max-w-[80%] text-white font-semibold truncate"
                >
                  {folder.title}
                </p>
                <p
                  class="z-10 max-w-[80%] h-[60%] ml-4 text-sm text-white font-light truncate"
                >
                  {folder.description}
                </p>
                <img
                  class="absolute z-0 max-w-full w-52 hover:shadow-lg duration-300"
                  src="/folder.png"
                  alt="folder"
                />
              </a>
            {/each}
          {/if}
        </div>
      </div>
    </div>
  </div>
</RouteGuard>
