<script>
  import { onMount } from "svelte";
  import { goto } from "$app/navigation";
  import { enhance } from "$app/forms";
  import { isAuthenticated } from "../../stores/authStore";
  // import { isAuthenticated } from "../../stores/authStore"

  let form;
  let inputError = false;

  function handleInput() {
    inputError = false;
  }

  const handleSubmit = async (event) => {
    // Form data:
    const form = event.target;
    const formData = new FormData(form);
    const username = formData.get("username");
    const password = formData.get("password");

    // API call to our Sign In endpoint
    try {
      const response = await fetch("http://localhost:8080/api/auth/signin", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          username: username,
          password: password,
        }),
        credentials: "include",
      });

      // Check if the response is okay
      if (response.ok) {
        // Do something... idk, maybe redirect and save user state?
        isAuthenticated.set(true);
        sessionStorage.setItem("isAuthenticated", "true")
        sessionStorage.setItem(isAuthenticated, true);
        goto("/dashboard");
      } else {
        // Handle error cases
        console.error("Sign in failed:", response.status, response.statusText);
        inputError = true;
      }
    } catch (error) {
      // Handle fetch error (e.g., network issue)
      console.error("Fetch error:", error);
      error = true;
    }
  };
</script>

<div
  class="grid grid-cols-2 min-h-screen w-screen bg-gradient-to-b from-slate-100 to-green-50"
>
  <div class="absolute ml-12 mt-3">
    <a
      class="text-lg font-semibold hover:opacity-80 transition duration-300"
      href="/">Home</a
    >
  </div>

  <div
    class="justify-center mx-auto rounded-lg self-center bg-white shadow-xl h-1/2 w-1/2"
  >
    <div class="flex w-full mt-4">
      <img class="mx-auto" src="/Activ8_logo.svg" alt="" />
    </div>
    <form
      class="flex flex-col items-center justify-center h-[80%]"
      method="post"
      action="?/login"
      on:submit={handleSubmit}
      use:enhance
    >
      <div class="flex w-full justify-center mt-4">
        <input
          on:input={handleInput}
          required
          class="w-[75%] border-2 pl-4 rounded-full h-12"
          type="text"
          name="username"
          placeholder="Username"
        />
      </div>
      <div class="flex w-full justify-center mt-4">
        <input
          on:input={handleInput}
          required
          class="w-[75%] border-2 pl-4 rounded-full h-12"
          type="password"
          name="password"
          placeholder="Password"
        />
      </div>

      {#if inputError}
        <p class="mt-4 text-red-600">Wrong username or password</p>
      {/if}
      <button
        type="submit"
        class="flex mt-8 mb-8 bg-[#28A896] mx-auto w-[75%] h-14 rounded-full hover:scale-105 transition duration-300 text-white text-center items-center"
        ><p class="font-semibold text-xl mx-auto">Sign In</p></button
      >
    </form>
  </div>

  <div class="w-full">
    <img src="the_grind.jpg" class="w-full h-full object-cover" alt="" />
  </div>
</div>
