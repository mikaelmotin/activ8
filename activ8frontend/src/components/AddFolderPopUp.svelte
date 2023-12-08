<script>
    export let folderPopUp;
    export let addFolder;
    export let closeFolderPopUp;
</script>

<div class="fixed inset-0 z-50 flex items-center justify-center">
    <div class="absolute inset-0 bg-black opacity-50"></div>
    <div class="z-10 p-4 bg-white rounded-md w-1/3 h-fit">
        <div class="flex justify-end">
            <button
                class="absolute hover:text-red-500 duration-300"
                on:click={() => closeFolderPopUp()}
            >
                <svg
                    xmlns="http://www.w3.org/2000/svg"
                    fill="none"
                    viewBox="0 0 24 24"
                    stroke-width="1.5"
                    stroke="currentColor"
                    class="w-6 h-6"
                >
                    <path
                        stroke-linecap="round"
                        stroke-linejoin="round"
                        d="M9.75 9.75l4.5 4.5m0-4.5l-4.5 4.5M21 12a9 9 0 11-18 0 9 9 0 0118 0z"
                    />
                </svg>
            </button>
        </div>

        <p class="text-center font-semibold text-xl">Create New Folder</p>
        <div class="mt-8 flex flex-col w-full items-center gap-y-12">
            <input
                class="w-[80%] border-b-2 outline-none focus:border-[#008DD5]"
                type="text"
                placeholder="Title"
                bind:value={folderPopUp.title}
            />
            <input
                class="w-[80%] border-b-2 outline-none focus:border-[#008DD5]"
                type="text"
                placeholder="Description"
                bind:value={folderPopUp.description}
            />
            <button
                on:click={() => addFolder()}
                class="bg-[#008DD5] p-3 mb-4 rounded-full text-white font-semibold hover:scale-110 duration-300"
                >Create Folder</button
            >
        </div>

        <slot />
    </div>
</div>
