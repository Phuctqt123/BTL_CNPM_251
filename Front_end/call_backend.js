
const BASE_URL = "https://backend-production-42b5.up.railway.app";

export async function getStudentProfile(id) {
    try {
        const response = await fetch(`${BASE_URL}/api/student/profile/${id}`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        });

        if (!response.ok) {
            throw new Error(`Lỗi API: ${response.status}`);
        }

        return await response.json();

    } catch (error) {
        console.error("Lỗi khi gọi API getStudentProfile:", error);
        return null;
    }
}
