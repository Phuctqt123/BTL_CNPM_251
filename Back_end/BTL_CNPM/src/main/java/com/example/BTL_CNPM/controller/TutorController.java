package com.example.BTL_CNPM.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tutor")
public class TutorController {

    // 1. Lấy thông tin cá nhân Tutor
    @GetMapping("/profile")
    public Map<String, Object> getTutorProfile() {
        Map<String, Object> data = new HashMap<>();
        data.put("tutorId", "T001");
        data.put("name", "Nguyễn Văn A");
        data.put("email", "tutor.a@example.com");
        data.put("phone", "0901234567");
        data.put("specialty", "Lập trình Java");
        data.put("rating", 4.8);
        return data;
    }

    // 2. Tạo buổi tư vấn
    @PostMapping("/session/create")
    public Map<String, Object> createSession(@RequestBody Map<String, Object> body) {
        Map<String, Object> data = new HashMap<>();
        data.put("success", true);
        data.put("sessionId", "SESS001234");
        data.put("message", "Tạo buổi tư vấn thành công");
        return data;
    }

    // 3. Lấy danh sách lịch sử buổi tư vấn của Tutor
    @GetMapping("/history")
    public Map<String, Object> getTutorHistory() {
        Map<String, Object> data = new HashMap<>();
        data.put("total", 25);
        data.put("sessions", java.util.List.of(
            Map.of("sessionId", "SESS001", "studentName", "Trần Thị B", "date", "2025-11-15", "status", "completed"),
            Map.of("sessionId", "SESS002", "studentName", "Lê Văn C", "date", "2025-11-18", "status", "upcoming")
        ));
        return data;
    }

    // 4. Xem tài liệu của 1 buổi tư vấn
    @GetMapping("/session/{sessionId}/documents")
    public Map<String, Object> getSessionDocuments(@PathVariable String sessionId) {
        Map<String, Object> data = new HashMap<>();
        data.put("sessionId", sessionId);
        data.put("documents", java.util.List.of(
            Map.of("docId", "DOC001", "name", "Slide_Javascript.pdf", "uploadedBy", "tutor"),
            Map.of("docId", "DOC002", "name", "Code_sample.zip", "uploadedBy", "student")
        ));
        return data;
    }

    // 5. Xóa 1 tài liệu
    @DeleteMapping("/document/{docId}")
    public Map<String, Object> deleteDocument(@PathVariable String docId) {
        Map<String, Object> data = new HashMap<>();
        data.put("success", true);
        data.put("docId", docId);
        data.put("message", "Xóa tài liệu thành công");
        return data;
    }

    // 6. Thêm 1 tài liệu vào buổi tư vấn
    @PostMapping("/session/{sessionId}/document")
    public Map<String, Object> addDocument(@PathVariable String sessionId, @RequestBody Map<String, Object> body) {
        Map<String, Object> data = new HashMap<>();
        data.put("success", true);
        data.put("docId", "DOC003");
        data.put("sessionId", sessionId);
        data.put("message", "Thêm tài liệu thành công");
        return data;
    }

    // 7. Hủy 1 buổi tư vấn
    @PostMapping("/session/{sessionId}/cancel")
    public Map<String, Object> cancelSession(@PathVariable String sessionId) {
        Map<String, Object> data = new HashMap<>();
        data.put("success", true);
        data.put("sessionId", sessionId);
        data.put("message", "Hủy buổi tư vấn thành công");
        return data;
    }

    // 8. Xem đánh giá (Tutor xem các đánh giá nhận được)
    @GetMapping("/reviews")
    public Map<String, Object> getTutorReviews() {
        Map<String, Object> data = new HashMap<>();
        data.put("averageRating", 4.8);
        data.put("totalReviews", 42);
        data.put("reviews", java.util.List.of(
            Map.of("studentName", "Nguyễn Thị D", "rating", 5, "comment", "Giảng hay, nhiệt tình!"),
            Map.of("studentName", "Phạm Văn E", "rating", 4, "comment", "Rất hữu ích")
        ));
        return data;
    }
}