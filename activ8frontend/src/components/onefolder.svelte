<!-- Folder.svelte -->
<script>
  export let imagePath = 'https://icons.iconarchive.com/icons/dtafalonso/yosemite-flat/512/Folder-icon.png';
  export let folderName = 'Untitled Folder';

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

<div class="flex flex-col items-center">
  <img class="w-30 h-22" src={imagePath} alt="Folder Icon" />
  <p class="text-xl font-bold">{folderName}</p>
</div>
