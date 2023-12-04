export const load = ({ fetch, params }) => {
    console.log(params);
    const folder_id = params.folder_id;

    const fetchStudySets = async (id) => {
        try {
            const response = await fetch('http://localhost:8080/api/studysets/all/' + folder_id, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                },
                credentials: "include",
            });

            if (response.ok) {
                const data = await response.json();
                console.log("Data:", data);
                return data;
            } else {
                console.error("Request failed with status:", response.status);
                return false;
            }
        } catch (error) {
            console.error("Network error:", error);
            return false;
        }
    }


    return {
        studysets: fetchStudySets(params)
    };
}