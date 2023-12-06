<script>
    export let flashcard;
  </script>
  

<div class="flex flex-col w-full h-48 mb-4 shadow-md rounded-lg">
    <div class="w-full bg-[#F0F0F0] p-2 rounded-t-lg">1</div>

    <div class="grid grid-cols-2 mt-2 p-2 ">
        <div>
            <textarea type="text" name="tern" placeholder="Term" bind:value={flashcard.term}
                   class="border-b-2 w-[80%] resize-none focus:border-[#008DD5] outline-none" />
        </div>
        <div>
            <textarea type="text" name="definition" placeholder="Definition" bind:value={flashcard.definition}
                   class="border-b-2 w-[80%] resize-none focus:border-[#008DD5] outline-none" />
            
        </div>
    </div>
    <div class="grid grid-cols-2 mt-2 p-2 mb-2 ">
        <p class="text-sm font-semibold">Term</p>
        <p class="text-sm font-semibold">Definition</p>
    </div>
</div>

  
  