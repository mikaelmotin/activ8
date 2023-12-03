<script>
    import { isAuthenticated } from "../stores/authStore";
    import { goto } from "$app/navigation";
    import { browser } from '$app/environment';
    
    let isUserAuthenticated = false;

    function handleLogin() {
        isUserAuthenticated = sessionStorage.getItem(isAuthenticated);

        if(isUserAuthenticated && browser) {
            goto("/my_library")
        } else {
            goto("/signin")
        }
    }

</script>

<nav class="flex w-screen  h-14 border-b-2 border-black/60">
    <a class="mx-12 mt-1 flex gap-x-2" href="/">
        <p class="text-3xl">🦌</p>
        <img class="h-14 align-middle" src="/Activ8_logo.svg" alt="">
    </a>
    <div class="flex ml-auto  text-black text-center font-semibold items-center">
        <button on:click={handleLogin} class="w-28 h-10 rounded-full hover:bg-[#28A896] transition duration-500">Log In</button>
    </div>
    <a class="flex mr-12  mx-8 text-white text-center font-semibold items-center" href="/register">
        <button class=" bg-black w-28 h-10 rounded-full hover:opacity-90">Sign Up</button>
    </a>
</nav>