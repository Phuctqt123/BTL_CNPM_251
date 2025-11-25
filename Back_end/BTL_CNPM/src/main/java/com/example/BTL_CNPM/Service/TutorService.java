package com.example.BTL_CNPM.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.BTL_CNPM.controller.Database;

// Giả định Tutor, Buoi_tu_van và TaiLieu đã được định nghĩa

/**
 * Lớp dịch vụ quản lý các hoạt động dành cho Tutor (Giảng viên/Người tư vấn).
 */
public class TutorService {

    // 1. get_info
    public List<Map<String, Object>> get_info(String gvKey) {
        try {
            String json = Database.apiTutorGetProfile(gvKey);
            return JSONUtil.toMap(json);
        } catch (Exception e) {
            System.err.println("❌ Lỗi khi lấy thông tin tutor: " + e.getMessage());
            return new ArrayList<>(); // trả về map rỗng nếu lỗi
        }
    }

    // 2. get_dsbuoituvan
    public List<Map<String, Object>> get_dsbuoituvan(String gvKey) {
        try {
            String json = Database.apiTutorGetHistory(gvKey);
            return JSONUtil.toMap(json);
        } catch (Exception e) {
            System.err.println("❌ Lỗi khi lấy thông tin tutor: " + e.getMessage());
            return new ArrayList<>(); // trả về map rỗng nếu lỗi
        }
    }


    // 4.1. Xem_tailieu
    public List<Map<String, Object>> get_dstailieu(int id) {
        try {
            String json = Database.apiGetDocumentsOfSession(id);
            return JSONUtil.toMap(json);
        } catch (Exception e) {
            System.err.println("❌ Lỗi khi lấy thông tin tutor: " + e.getMessage());
            return new ArrayList<>();// trả về map rỗng nếu lỗi
        }
    }
    
    // 4.2. them_tailieu
    public boolean them_tailieu( Map<String, Object> body) {
        
        String gvKey = body.get("gvKey").toString();
        int id = ((Number) body.get("buoiId")).intValue();
        String filename = body.get("filename").toString();
        try {
            Database.apiTutorAddDocument(gvKey, id, filename);
            return true;
        } catch (Exception e) {
            System.err.println("❌ Lỗi khi thêm tài liệu: " + e.getMessage());
            return false;
        }
    }

    //4.3 xoa_tailieu
    public boolean xoa_tailieu( Map<String, Object> body) {
        String gvKey = body.get("gvKey").toString();
        int id = ((Number) body.get("buoiId")).intValue();
        int taiLieuId = ((Number) body.get("taiLieuId")).intValue();
        try {
            Database.apiTutorDeleteDocument(gvKey, id, taiLieuId);
            return true;
        } catch (Exception e) {
            System.err.println("❌ Lỗi khi xóa tài liệu: " + e.getMessage());
            return false;
        }
    }

    public boolean huy_buoituvan( Map<String, Object> body) {
        String gvKey = body.get("gvKey").toString();
        int id = ((Number) body.get("buoiId")).intValue();
        try {
            Database.apiCancelSession(gvKey, id);
            return true;
        } catch (Exception e) {
            System.err.println("❌ Lỗi khi xóa tài liệu: " + e.getMessage());
            return false;
        }
    }

    public boolean guidanhgia( Map<String, Object> body) {
        try {
            // Lấy dữ liệu từ body và ép kiểu
            String nguoiDanhGia = body.get("nguoiDanhGia").toString();
            int buoiId = ((Number) body.get("buoiId")).intValue();
            String loaiDanhGia = body.get("loaiDanhGia").toString();
            int diemSo = ((Number) body.get("diemSo")).intValue();
            String nguoiDuocDg = body.get("nguoiDuocDg").toString();
            String noiDung = body.get("noiDung").toString();

            // Gọi hàm static API
            String result = Database.apiSubmitRating(
                    nguoiDanhGia, buoiId, loaiDanhGia, diemSo, nguoiDuocDg, noiDung
            );

            // Kiểm tra kết quả trả về (tùy vào API, ví dụ trả về "OK" nếu thành công)
            return result != null && result.equalsIgnoreCase("OK");

        } catch (Exception e) {
            System.err.println("❌ Lỗi khi gửi đánh giá: " + e.getMessage());
            return false;
        }
    }
}