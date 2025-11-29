package com.example.BTL_CNPM.controller;


import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BTL_CNPM.Service.Booking_Service;
import com.example.BTL_CNPM.Service.TutorService;

@RestController
@RequestMapping("/api/tutor")
@CrossOrigin(origins = "*")
public class TutorController {

    // 1. Lấy thông tin cá nhân Tutor
    @GetMapping("/profile/{id}")
    public List<Map<String, Object>> getTutorProfile(@PathVariable String id) {
        // Gọi service lấy thông tin
        TutorService service = new TutorService();
        return service.get_info(id);
    }

    // 2. Tạo buổi tư vấn
    /*
    body mẫu:

    {
        "gvKey": "GV001",
        "tenBuoi": "Buổi tư vấn Java nâng cao",
        "hinhThuc": "Offline", 
        "thoiGianBD": "2025-01-10 14:00:00",
        "thoiGianKT": "2025-01-10 16:00:00",
        "ghiChu": "Mang laptop",
        "diaChi": "Phòng Lab 301 - Đại học CNTT",
        "linkGgmeet": null,
        "slToiThieu": 5,
        "slToiDa": 20
    }

     */
    @PostMapping("/session/create")
    public List<Map<String, Object>> createSession(@RequestBody Map<String, Object> body) {
        Booking_Service service = new Booking_Service();
        return service.booking_Tutor(body);
    }

    // 3. Lấy danh sách lịch sử buổi tư vấn của Tutor
    @GetMapping("/history/{id}")
    public List<Map<String, Object>> getTutorHistory(@PathVariable String id) {
        TutorService service = new TutorService();
        return service.get_dsbuoituvan(id);
    }

    // 4. Xem tài liệu của 1 buổi tư vấn
    @GetMapping("/session/{sessionId}/documents")
    public List<Map<String, Object>> getSessionDocuments(@PathVariable int sessionId) {
        TutorService service = new TutorService();
        return service.get_dstailieu(sessionId);
    }

    // 5. Xóa 1 tài liệu
    /*
    {
        "gvKey": "GV001",
        "buoiId": 12,
        "taiLieuId": 1
    }
    
    */
    @PostMapping("/session/delete_document")
    public boolean deleteDocument(@RequestBody Map<String, Object> body) {
        TutorService service = new TutorService();
        return service.xoa_tailieu(body);
    }

    // 6. Thêm 1 tài liệu vào buổi tư vấn
    /*
    {
        "gvKey": "GV001",
        "buoiId": 12,
        "filename": "tai_lieu.pdf"
    }
    */
    @PostMapping("/session/add_document")
    public boolean addDocument(
            @RequestBody Map<String, Object> body
    ) {
        TutorService service = new TutorService();
        return service.them_tailieu(body);
    }

    // 7. Hủy 1 buổi tư vấn cho student và tutor
    /*
        {
            "gvKey": "GV001",
            "buoiId": 12
        }
    */
    @PostMapping("/session/cancel")
    public boolean cancelSession(@RequestBody Map<String, Object> body) {
        TutorService service = new TutorService();
        return service.huy_buoituvan(body);
    }

    // 8. Đánh giá 
    /*
    {
        "nguoiDanhGia": "GV001",
        "buoiId": 12,
        "loaiDanhGia": "Chấm điểm buổi học",
        "diemSo": 9,
        "nguoiDuocDg": "HS123",
        "noiDung": "Buổi học rất hiệu quả, học viên chăm chú và tích cực tham gia."
    }
    */
    @GetMapping("/reviews")
    public boolean TutorReviews(@RequestBody Map<String, Object> body) {
        TutorService service = new TutorService();
        return service.guidanhgia(body);
    }

    @GetMapping("/students/{sessionId}")
    public List<Map<String, Object>> getliststudent(@PathVariable int sessionId) {
        TutorService service = new TutorService();
        return service.get_dssinhvien(sessionId);
    }
}