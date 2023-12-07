export const load = async ({ fetch, params }) => {
    const studyset_id = params.studyset_id;
    const folder_id = params.folder_id;

    const fetchStudySetData = async () => {
        try {
            const response = await fetch(`http://localhost:8080/api/studysets/${studyset_id}`, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                },
                credentials: "include",
            });

            if (response.ok) {
                const studySetData = await response.json();
                return studySetData;
            } else {
                console.error("Request failed with status:", response.status);
                return null;
            }
        } catch (error) {
            console.error("Network error:", error);
            return null;
        }
    };

    const fetchFlashcards = async () => {
        try {
            const response = await fetch(`http://localhost:8080/api/flashcards/all/${studyset_id}`, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                },
                credentials: "include",
            });

            if (response.ok) {
                const data = await response.json();
                return data;
            } else {
                console.error("Request failed with status:", response.status);
                return null;
            }
        } catch (error) {
            console.error("Network error:", error);
            return null;
        }
    };

    const [studySetData, flashcards] = await Promise.all([fetchStudySetData(), fetchFlashcards()]);

    return {
        studySetData: studySetData,
        flashcards: flashcards,
        folder_id: folder_id,
        studyset_id: studyset_id,
    };
};