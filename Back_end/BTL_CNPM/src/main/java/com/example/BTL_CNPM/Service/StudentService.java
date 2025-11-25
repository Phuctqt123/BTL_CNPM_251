package com.example.BTL_CNPM.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.BTL_CNPM.controller.Database;
// Giả định SinhVien và Buoi_tu_van đã được định nghĩa

/**
 * Lớp dịch vụ quản lý các hoạt động dành cho SinhVien.
 */
public class StudentService {
    
    // 1. get_info
    // làm mẫu để chạy maven thử
    public List<Map<String, Object>> get_info(String id) {
        try {
            String json = Database.apiStudentGetProfile(id);
            return JSONUtil.toMap(json);
        } catch (Exception e) {
            System.err.println("❌ Lỗi khi lấy thông tin student: " + e.getMessage());
            return new ArrayList<>(); // trả về map rỗng nếu lỗi
        }
    }

    // 2. get_dsbuoituvan
    // làm mẫu để chạy maven thử
    public List<Map<String, Object>> get_dsbuoituvan(String id) {
        try {
            String json = Database.apiStudentGetHistory(id);
            return JSONUtil.toMap(json);
        } catch (Exception e) {
            System.err.println("❌ Lỗi khi lấy thông tin student: " + e.getMessage());
            return new ArrayList<>(); // trả về map rỗng nếu lỗi
        }
    }
    
    // 4.phan hoi
    // làm mẫu để chạy maven thử
    public boolean phan_hoi(Map<String, Object> body) {
        try {
            String nguoiDanhGia = body.get("nguoiDanhGia").toString(); 
            int buoiId = ((Number) body.get("buoiId")).intValue();     
            int diemSo = ((Number) body.get("diemSo")).intValue();     
            String noiDung = body.get("noiDung").toString();         

            String loaiDanhGia = "SV_ve_Buoi";
            String nguoiDuocDg = null;

            String result = Database.apiSubmitRating(
                    nguoiDanhGia,
                    buoiId,
                    loaiDanhGia,
                    diemSo,
                    nguoiDuocDg,
                    noiDung
            );

            return result != null ;

        } catch (Exception e) {
            System.err.println("❌ Lỗi khi sinh viên gửi phản hồi: " + e.getMessage());
            return false;
        }
    }

    public boolean huy_buoituvan_student( Map<String, Object> body) {
        try {
            String svKey = body.get("svKey").toString();
            int id = ((Number) body.get("buoiId")).intValue();
            
            String result = Database.apiCancelSession(svKey, id);
            
            System.out.println("LOG DB HỦY: " + result);

            if (result != null && (result.contains("cancelled") || result.equalsIgnoreCase("OK"))) {
                return true;
            }
            
            return false; 
        } catch (Exception e) {
            System.err.println("❌ Lỗi khi hủy: " + e.getMessage());
            return false;
        }
    }
}