<!-- Folder.svelte -->
<script>
  export let imagePath = 'https://icons.iconarchive.com/icons/dtafalonso/yosemite-flat/512/Folder-icon.png';
  export let folderName = 'Untitled Folder';
</script>

<div class=".folder">
  <img src={imagePath} alt="Folder Icon" />
  <p>{folderName}</p>
</div>

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
