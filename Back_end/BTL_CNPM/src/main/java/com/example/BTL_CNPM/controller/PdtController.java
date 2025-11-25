package com.example.BTL_CNPM.controller;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BTL_CNPM.Service.PDT_PCT_Service;

@RestController
@RequestMapping("/api/pdt")
@CrossOrigin(origins = "*")
public class PdtController {

    // 16. Lấy thông tin trang chủ PDT (thống kê tổng quan)
    @GetMapping("/dashboard")
    public List<Map<String, Object>> getDashboard() {
        PDT_PCT_Service service = new PDT_PCT_Service();
        return service.PdtDashboard();
    }

    // 17. Lấy danh sách sinh viên
    @GetMapping("/students")
    public List<Map<String, Object>> getStudents() {
        PDT_PCT_Service service = new PDT_PCT_Service();
        return service.get_ds_sinhvien();
    }

    // 18. Lấy danh sách buổi tư vấn (toàn hệ thống)
    @GetMapping("/sessions")
    public List<Map<String, Object>> getAllSessions() {
        PDT_PCT_Service service = new PDT_PCT_Service();
        return service.PdtListSessions();
    }

    // 19. Lấy chi tiết 1 buổi tư vấn
    @GetMapping("/session/{sessionId}")
    public List<Map<String, Object>> getSessionDetail(@PathVariable int sessionId) {
        PDT_PCT_Service service = new PDT_PCT_Service();
        return service.PdtSessionDetail(sessionId);
    }
}