<script>
  export let form;
</script>

<div class="flex min-h-screen w-screen bg-gradient-to-b from-slate-100 to-green-50">

    <div class="justify-center mx-auto rounded-lg self-center bg-white shadow-xl h-1/3 w-1/3">
        <div class="flex w-full mt-4">
            <img class="mx-auto" src="/Activ8_logo.svg" alt="">
        </div> 
        <form action="?/register" method="post">
            <div class="flex w-full justify-center mt-4">
                <input class="w-[50%] border-2 rounded-sm h-12" type="text" name="name" placeholder="Username">
            </div>
            <div class="flex w-full justify-center mt-4">
                <input class="w-[50%] border-2 rounded-sm h-12" type="email" name="email" placeholder="Email">
            </div>
            <div class="flex w-full justify-center mt-4">
                <input class="w-[50%] border-2 rounded-sm h-12" type="password" name="password" placeholder="Password">
            </div>

            {#if form && form.user}
            <p class="error">Username is taken.</p>
            {/if}
            <button class="flex mt-8 mb-8 bg-[#28A896] mx-auto w-[50%]  h-14 rounded-full hover:scale-105 transition duration-300 text-white text-center items-center"><p class="font-semibold text-xl mx-auto">Create Account</p></button>
        </form>

    </div>

</div>