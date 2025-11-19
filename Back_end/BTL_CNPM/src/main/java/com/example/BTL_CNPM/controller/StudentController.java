package com.example.BTL_CNPM.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    // 9. Lấy thông báo chung trên trang chủ
    @GetMapping("/notifications")
    public Map<String, Object> getNotifications() {
        Map<String, Object> data = new HashMap<>();
        data.put("notifications", java.util.List.of(
            Map.of("title", "Thông báo nghỉ Tết", "date", "2025-11-18", "important", true),
            Map.of("title", "Lịch tư vấn tuần này", "date", "2025-11-15", "important", false)
        ));
        return data;
    }

    // 10. Lấy thông tin cá nhân Sinh viên
    @GetMapping("/profile")
    public Map<String, Object> getStudentProfile() {
        Map<String, Object> data = new HashMap<>();
        data.put("studentId", "SV2023001");
        data.put("name", "Trần Văn Nam");
        data.put("email", "nam.tv@student.edu.vn");
        data.put("major", "Công nghệ thông tin");
        data.put("year", 3);
        return data;
    }

    // 11. Lấy danh sách buổi tư vấn đã đăng ký
    @GetMapping("/registered-sessions")
    public Map<String, Object> getRegisteredSessions() {
        Map<String, Object> data = new HashMap<>();
        data.put("sessions", java.util.List.of(
            Map.of("sessionId", "SESS001", "tutorName", "Nguyễn Văn A", "subject", "Java Advanced", "date", "2025-11-20 14:00", "status", "confirmed"),
            Map.of("sessionId", "SESS002", "tutorName", "Lê Thị B", "subject", "Database", "date", "2025-11-22 09:00", "status", "pending")
        ));
        return data;
    }

    // 12. Đăng ký buổi tư vấn
    @PostMapping("/session/register")
    public Map<String, Object> registerSession(@RequestBody Map<String, Object> body) {
        Map<String, Object> data = new HashMap<>();
        data.put("success", true);
        data.put("registrationId", "REG001234");
        data.put("message", "Đăng ký buổi tư vấn thành công, chờ gia sư xác nhận");
        return data;
    }

    // 13. Lấy danh sách lịch sử buổi tư vấn của sinh viên
    @GetMapping("/history")
    public Map<String, Object> getStudentHistory() {
        Map<String, Object> data = new HashMap<>();
        data.put("history", java.util.List.of(
            Map.of("sessionId", "SESS0001", "tutorName", "Phạm Văn C", "date", "2025-10-15", "ratingGiven", true),
            Map.of("sessionId", "SESS0002", "tutorName", "Nguyễn Thị D", "date", "2025-11-01", "ratingGiven", false)
        ));
        return data;
    }

    // 14. Xem tài liệu của 1 buổi tư vấn (sinh viên)
    @GetMapping("/session/{sessionId}/documents")
    public Map<String, Object> getSessionDocuments(@PathVariable String sessionId) {
        Map<String, Object> data = new HashMap<>();
        data.put("sessionId", sessionId);
        data.put("documents", java.util.List.of(
            Map.of("docId", "DOC001", "name", "SpringBoot_Note.pdf", "uploadedBy", "tutor"),
            Map.of("docId", "DOC002", "name", "MyQuestion.docx", "uploadedBy", "me")
        ));
        return data;
    }

    // 15. Hủy 1 buổi tư vấn (sinh viên)
    @PostMapping("/session/{sessionId}/cancel")
    public Map<String, Object> cancelSession(@PathVariable String sessionId) {
        Map<String, Object> data = new HashMap<>();
        data.put("success", true);
        data.put("sessionId", sessionId);
        data.put("message", "Hủy đăng ký thành công");
        return data;
    }

    // 16. Đánh giá buổi tư vấn
    @PostMapping("/session/{sessionId}/review")
    public Map<String, Object> submitReview(@PathVariable String sessionId, @RequestBody Map<String, Object> body) {
        Map<String, Object> data = new HashMap<>();
        data.put("success", true);
        data.put("sessionId", sessionId);
        data.put("message", "Cảm ơn bạn đã đánh giá!");
        return data;
    }
}