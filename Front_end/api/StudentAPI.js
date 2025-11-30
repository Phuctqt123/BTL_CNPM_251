import axiosClient from "./axiosClient.js";

const StudentApi = {
    getProfile(id) {
        return axiosClient.get(`/api/student/profile/${id}`);
    },
    registerSession(data) {
        return axiosClient.post(`/api/student/session/register`, data);
    },
    getDocuments(sessionId) {
        return axiosClient.get(`/api/student/session/${sessionId}/documents`);
    },
    cancelSession(data) {
        return axiosClient.post(`/api/student/session/cancel_student`, data);
    },
    getRegisteredList(id) {
        return axiosClient.get(`/api/student/registered-sessionn/${id}`);
    },
    getUpComing() {
        return axiosClient.get(`/api/student/registered-sessionn/upcoming`);
    },
    reviews(data) {
        return axiosClient.post(`/api/student/reviews`, data);
    }
};

export default StudentApi;