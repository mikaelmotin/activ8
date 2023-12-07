<!-- Used to protect Routes when Cookie is not present -->

<script>
    import { onMount } from "svelte";
    import { browser } from '$app/environment';
    import { goto } from "$app/navigation";
    import { isAuthenticated } from "../stores/authStore"

    let isUserAuthenticated = false;

    onMount(() => {
        isUserAuthenticated = sessionStorage.getItem(isAuthenticated);

        if(!isUserAuthenticated && browser) {
            goto("/signin")
        }
    })
</script>

{#if isUserAuthenticated}
    <slot />
{/if}