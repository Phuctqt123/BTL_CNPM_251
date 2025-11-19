
package com.example.BTL_CNPM.controller;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pdt")
public class PdtController {

    // 16. Lấy thông tin trang chủ PDT (thống kê tổng quan)
    @GetMapping("/dashboard")
    public Map<String, Object> getDashboard() {
        Map<String, Object> data = new HashMap<>();
        data.put("totalStudents", 1250);
        data.put("totalTutors", 45);
        data.put("totalSessionsThisMonth", 289);
        data.put("pendingRegistrations", 12);
        return data;
    }

    // 17. Lấy danh sách sinh viên
    @GetMapping("/students")
    public Map<String, Object> getStudents() {
        Map<String, Object> data = new HashMap<>();
        data.put("total", 1250);
        data.put("students", java.util.List.of(
            Map.of("studentId", "SV2023001", "name", "Trần Văn Nam", "major", "CNTT", "year", 3),
            Map.of("studentId", "SV2023002", "name", "Lê Thị Mai", "major", "Kỹ thuật phần mềm", "year", 2)
        ));
        return data;
    }

    // 18. Lấy danh sách buổi tư vấn (toàn hệ thống)
    @GetMapping("/sessions")
    public Map<String, Object> getAllSessions() {
        Map<String, Object> data = new HashMap<>();
        data.put("sessions", java.util.List.of(
            Map.of("sessionId", "SESS001", "studentName", "Nguyễn Văn A", "tutorName", "Cô Lan", "date", "2025-11-20 14:00", "status", "confirmed"),
            Map.of("sessionId", "SESS002", "studentName", "Trần Thị B", "tutorName", "Thầy Minh", "date", "2025-11-21 09:00", "status", "pending")
        ));
        return data;
    }

    // 19. Lấy chi tiết 1 buổi tư vấn
    @GetMapping("/session/{sessionId}")
    public Map<String, Object> getSessionDetail(@PathVariable String sessionId) {
        Map<String, Object> data = new HashMap<>();
        data.put("sessionId", sessionId);
        data.put("student", Map.of("id", "SV2023001", "name", "Trần Văn Nam"));
        data.put("tutor", Map.of("id", "T001", "name", "Nguyễn Thị Lan"));
        data.put("subject", "Lập trình web");
        data.put("dateTime", "2025-11-20 14:00");
        data.put("status", "confirmed");
        data.put("createdAt", "2025-11-10");
        data.put("documentsCount", 3);
        return data;
    }
}