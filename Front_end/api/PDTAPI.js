import axiosClient from "./axiosClient.js";

const PDTApi = {
    getDashboard() {
        return axiosClient.get(`/api/pdt/dashboard`);
    },
    getStudents() {
        return axiosClient.get(`/api/pdt/students`);
    },
    getSessions() {
        return axiosClient.get(`/api/pdt/sessions`);
    },
    getDetailSession(sessionId) {
        return axiosClient.get(`/api/pdt/session/${sessionId}`);
    }
};

export default PDTApi;