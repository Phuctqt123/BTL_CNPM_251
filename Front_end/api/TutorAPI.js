import axiosClient from "./axiosClient.js";

const TutorApi = {
    // Lấy thông tin cá nhân giảng viên
    getProfile(id) {
        return axiosClient.get(`/api/tutor/profile/${id}`);
    },

    // Tạo buổi tư vấn
    createSession(data) {
        return axiosClient.post(`/api/tutor/session/create`, data);
    },

    // Thêm 1 tài liệu vào buổi tư vấn
    addDocument(data) {
        return axiosClient.post(`/api/tutor/session/add_document`, data);
    },

    // Xóa 1 tài liệu trong buổi tư vấn
    deleteDocument(data) {
        return axiosClient.post(`/api/tutor/session/delete_document`, data);
    },

    // Xem tài liệu của buổi tư vấn
    getDocuments(sessionId) {
        return axiosClient.get(`/api/tutor/session/${sessionId}/documents`);
    },

    // Hủy buổi tư vấn
    cancelSession(data) {
        return axiosClient.post(`/api/tutor/session/cancel`, data);
    },

    // Lấy danh sách buổi tư vấn đã tạo
    getCreatedSessions(id) {
        return axiosClient.get(`/api/tutor/history/${id}`);
    },

    // Đánh giá sinh viên
    reviewStudent(data) {
        return axiosClient.post(`/api/tutor/reviews`, data);
    },
    // Lấy danh sách sinh viên tham gia buổi tư vấn đó
    getliststudent(id) {
        return axiosClient.get(`/api/tutor/students/${id}`);
    },
    
};

export default TutorApi;
