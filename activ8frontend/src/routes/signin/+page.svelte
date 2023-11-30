<script>
    import { onMount } from 'svelte';
    import { goto } from '$app/navigation';
  
    let form = {}; // Assuming you have some kind of form state
  
    const handleSubmit = async () => {
  const response = await fetch('http://localhost:8080/api/auth/signin', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({
      username: 'user102',
      password: 'apa123',
    }),
    credentials: 'include'
  });
  console.log(response.headers);
};

     
    //   if (response.ok) {
    //     // Parse the response JSON if needed
    //     const data = await response.json();
  
    //     // Set a session cookie
    //     document.cookie = `your-session-cookie=${data.sessionToken}; path=/`;
    //   }
    // };
  </script>
  
  <div class="flex min-h-screen w-screen bg-gradient-to-b from-slate-100 to-green-50">
  
      <div class="justify-center mx-auto rounded-lg self-center bg-white shadow-xl h-1/3 w-1/3">
          <div class="flex w-full mt-4">
              <img class="mx-auto" src="/Activ8_logo.svg" alt="">
          </div> 
          <form on:submit|preventDefault={handleSubmit}>
              <div class="flex w-full justify-center mt-4">
                  <input class="w-[50%] border-2 rounded-sm h-12" type="text" name="name" placeholder="Username">
              </div>
              <div class="flex w-full justify-center mt-4">
                  <input class="w-[50%] border-2 rounded-sm h-12" type="password" name="password" placeholder="Password">
              </div>
  
              {#if form && form.user}
              <p class="error">Username is taken.</p>
              {/if}
              <button class="flex mt-8 mb-8 bg-[#28A896] mx-auto w-[50%]  h-14 rounded-full hover:scale-105 transition duration-300 text-white text-center items-center"><p class="font-semibold text-xl mx-auto">Sign In</p></button>
          </form>
  
      </div>
  
  </div>