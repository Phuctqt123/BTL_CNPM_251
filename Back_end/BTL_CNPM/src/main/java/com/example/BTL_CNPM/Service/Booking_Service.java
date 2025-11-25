// Giả định Booking, Tutor và SinhVien đã được định nghĩa

/**
 * Lớp dịch vụ quản lý các hoạt động liên quan đến việc đặt chỗ (Booking).
 */
package com.example.BTL_CNPM.Service;
import java.sql.Timestamp;
import java.util.Map;

import com.example.BTL_CNPM.controller.Database;
public class Booking_Service {

    // 1. booking_Student
    public boolean booking_Student(Map<String, Object> body) {
        try {
            String svKey = body.get("svKey").toString();
            int buoiId = ((Number) body.get("buoiId")).intValue();

            String result = Database.apiStudentRegisterSession(svKey, buoiId);

            return result != null;

        } catch (Exception e) {
            System.err.println("❌ Lỗi khi sinh viên đăng ký buổi tư vấn: " + e.getMessage());
            return false;
        }
    }

    // 2. booking_Tutor
    public boolean booking_Tutor(Map<String, Object> body) {
        try {
            String gvKey = body.get("gvKey").toString();           
            String tenBuoi = body.get("tenBuoi").toString();
            String hinhThuc = body.get("hinhThuc").toString();   

            //Convert thời gian sang Timestamp
            Timestamp thoiGianBD = Timestamp.valueOf(body.get("thoiGianBD").toString());
            Timestamp thoiGianKT = Timestamp.valueOf(body.get("thoiGianKT").toString());

            String ghiChu = body.get("ghiChu") != null ? body.get("ghiChu").toString() : null;
            String diaChi = body.get("diaChi") != null ? body.get("diaChi").toString() : null;
            String linkGgmeet = body.get("linkGgmeet") != null ? body.get("linkGgmeet").toString() : null;

            Integer slToiThieu = body.get("slToiThieu") != null
                    ? ((Number) body.get("slToiThieu")).intValue()
                    : null;

            Integer slToiDa = body.get("slToiDa") != null
                    ? ((Number) body.get("slToiDa")).intValue()
                    : null;

            String result = Database.apiTutorCreateSession(
                    gvKey,
                    tenBuoi,
                    hinhThuc,
                    thoiGianBD,
                    thoiGianKT,
                    ghiChu,
                    diaChi,
                    linkGgmeet,
                    slToiThieu,
                    slToiDa
            );

            return result != null;

        } catch (Exception e) {
            System.err.println("❌ Lỗi khi tạo buổi tư vấn: " + e.getMessage());
            return false;
        }
    }
    
}