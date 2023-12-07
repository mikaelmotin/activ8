



// Handle Form Submit:
export const actions = {
    login: async ({ request }) => {
        const formData = await request.formData();
        const username = formData.get("username");
        const password = formData.get("password");

        // const response = await fetch('http://localhost:8080/api/auth/signin', {
        //     method: 'POST',
        //     headers: {
        //         'Content-Type': 'application/json',
        //     },
        //     body: JSON.stringify({
        //         username: username,
        //         password: password,
        //     }),
        //     credentials: 'include',
        // })


    }




};