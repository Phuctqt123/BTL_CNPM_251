
package com.example.BTL_CNPM.controller;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BTL_CNPM.Service.Booking_Service;
import com.example.BTL_CNPM.Service.PDT_PCT_Service;

@RestController
@RequestMapping("/api/pdt")
public class PdtController {

    // 16. Lấy thông tin trang chủ PDT (thống kê tổng quan)
    @GetMapping("/dashboard")
    public Map<String, Object> getDashboard() {
        PDT_PCT_Service service = new PDT_PCT_Service();
        return service.PdtDashboard();
    }

    // 17. Lấy danh sách sinh viên
    @GetMapping("/students")
    public Map<String, Object> getStudents() {
        PDT_PCT_Service service = new PDT_PCT_Service();
        return service.get_ds_sinhvien();
    }

    // 18. Lấy danh sách buổi tư vấn (toàn hệ thống)
    @GetMapping("/sessions")
    public Map<String, Object> getAllSessions() {
        PDT_PCT_Service service = new PDT_PCT_Service();
        return service.PdtListSessions();
    }

    // 19. Lấy chi tiết 1 buổi tư vấn
    @GetMapping("/session/{sessionId}")
    public Map<String, Object> getSessionDetail(@PathVariable String sessionId) {
        PDT_PCT_Service service = new PDT_PCT_Service();
        return service.PdtSessionDetail(sessionId);
    }
}