import { fail, redirect } from '@sveltejs/kit';

export const load = async () => {
    // todo
};

const register = async ({ request }) => {
    const data = await request.formData();
    const username = data.get('username');
    const email = data.get('email');
    const password = data.get('password');

    if (
        typeof username !== 'string' ||
        typeof password !== 'string' ||
        !username ||
        !password
    ) {
        return fail(400, { invalid: true });
    }


    async function registerUser() {
        await fetch("https://localhost:8080/api/auth/signup", {
            method: "POST",
            headers: {
                'Content-Type': 'application\json'
            },
            body: {
                "username": username,
                "email": email,
                "password": password
            }
        })

        throw redirect(303, '/login2');
    }



};
export const actions = { register };


