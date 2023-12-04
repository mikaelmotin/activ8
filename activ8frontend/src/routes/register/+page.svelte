<script>
    import { goto } from "$app/navigation";
    import { isAuthenticated } from "../../stores/authStore";

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
        const email = formData.get("email");
        const password = formData.get("password");


        // API call to our Sign Up endpoint
        try {
            const response = await fetch(
                "http://localhost:8080/api/auth/signup",
                {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify({
                        username: username,
                        email: email,
                        password: password,
                    }),
                    credentials: "include",
                },
            );

            // Check if the response is okay
            if (response.ok) {
                // Do something... idk, maybe redirect and save user state?
                goto("/register/success");
            } else {
                // Handle error cases
                console.error(
                    "Sign Up failed:",
                    response.status,
                    response.statusText,
                );
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
    class="flex min-h-screen w-screen bg-gradient-to-b from-slate-100 to-green-50"
>
    <div class="absolute ml-12 mt-3">
        <a
            class="text-lg font-semibold hover:opacity-80 transition duration-300"
            href="/">Home</a
        >
    </div>

    <div
        class="justify-center mx-auto rounded-lg self-center bg-white shadow-xl h-1/3 w-1/3"
    >
        <div class="flex w-full mt-4">
            <img class="mx-auto" src="/Activ8_logo.svg" alt="" />
        </div>
        <form
            on:submit={handleSubmit}
        >
            <div class="flex w-full justify-center mt-4">
                <input
                    class="w-[75%] border-2 pl-4 rounded-full h-12"
                    type="text"
                    name="username"
                    placeholder="Username"
                />
            </div>
            <div class="flex w-full justify-center mt-4">
                <input
                    class="w-[75%] border-2 pl-4 rounded-full h-12"
                    type="email"
                    name="email"
                    placeholder="Email"
                />
            </div>
            <div class="flex w-full justify-center mt-4">
                <input
                    class="w-[75%] border-2 pl-4 rounded-full h-12"
                    type="password"
                    name="password"
                    placeholder="Password"
                    minlength="6"
                />
            </div>

            {#if inputError}
                <p class="mt-4 text-red-600 text-center">Username or email is already in use</p>
            {/if}
            <button
                class="flex mt-8 mb-8 bg-[#28A896] mx-auto w-[75%] h-14 rounded-full hover:scale-105 transition duration-300 text-white text-center items-center"
                ><p class="font-semibold text-xl mx-auto">
                    Create Account
                </p></button
            >
        </form>
    </div>
</div>
