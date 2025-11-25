package com.example.BTL_CNPM.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BTL_CNPM.Service.Booking_Service;
import com.example.BTL_CNPM.Service.StudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    
    


    // 10. Lấy thông tin cá nhân Sinh viên
    @GetMapping("/profile/{id}")
    public List<Map<String, Object>> getStudentProfile(@PathVariable String id) {
        StudentService service = new StudentService();
        return service.get_info(id);
    }

    // 11. Lấy danh sách buổi tư vấn đăng ký
    @GetMapping("/registered-sessionn/{id}")
    public List<Map<String, Object>> getRegisteredSessions(@PathVariable String id) {
        StudentService service = new StudentService();
        return service.get_dsbuoituvan(id);
    }

    // 12. Đăng ký buổi tư vấn
    /*
        {
            "svKey": "SV123",
            "buoiId": 12
        }
     
     */
    @PostMapping("/session/register")
    public boolean registerSession(@RequestBody Map<String, Object> body) {
        Booking_Service service = new Booking_Service();
        return service.booking_Student(body);
    }

    // 13. Lấy danh sách lịch sử buổi tư vấn của sinh viên
    @GetMapping("/history/{id}")
    public List<Map<String, Object>> getStudentHistory(@PathVariable String id) {
        StudentService service = new StudentService();
        return service.get_dsbuoituvan_lichsu(id);
    }

    // 14. Xem tài liệu của 1 buổi tư vấn (sinh viên)-> Tutor đã có
    // @GetMapping("/session/{sessionId}/documents")
    // public Map<String, Object> getSessionDocuments(@PathVariable String sessionId) {
    //     TutorService service = new TutorService();
    //     return service.get_dstailieu(sessionId);
    // }

    // 15. Hủy 1 buổi tư vấn (sinh viên) ->Tutor_controller bao gồm
    // @PostMapping("/session/cancel_student")
    // public Map<String, Object> cancelSession(@RequestBody Map<String, Object> body) {
        
    // }

    // 16. Đánh giá buổi tư vấn -> Tutor controller bao gồm
    
}