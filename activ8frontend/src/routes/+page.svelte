<script>
    import MainpageNavbar from "../components/MainpageNavbar.svelte";


</script>


<div class="  bg-gradient-to-r from-blue-50 to-emerald-100">
    <MainpageNavbar/>

    <div class="grid grid-cols-2 min-h-screen z-10">
        <div class="items-center self-center ">
            <p class="lg:text-8xl mx-12 md:text-6xl sm:text-4xl ">Flashcards have never been the same</p>
            <a class="flex mr-12 mt-12 mx-12 text-white text-center font-semibold" href="/register">
                <button class=" bg-black w-28 h-10 rounded-full hover:opacity-90">Get Started</button>
            </a>
        </div>
        <div class="flex mx-12">
            <img src="/Flashcard.svg" alt="">
        </div>
    </div>
</div>