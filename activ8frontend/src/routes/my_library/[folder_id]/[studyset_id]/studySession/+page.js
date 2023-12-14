export const load = ({ fetch, params }) => {
    const studyset_id = params.studyset_id;
    
    // Perform any necessary data fetching or processing here
    const fetchFlashcards = async (id) => {
        try {
            const response = await fetch('http://localhost:8080/api/flashcards/all/' + studyset_id, {
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
        flashcards: fetchFlashcards(params),
        studyset_id: studyset_id,
    };


};