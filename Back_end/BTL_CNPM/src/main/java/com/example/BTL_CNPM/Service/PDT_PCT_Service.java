package com.example.BTL_CNPM.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.BTL_CNPM.controller.Database;


// Giả định Student là lớp con của User hoặc lớp riêng
// Giả định SinhVien là lớp con của User

/**
 * Lớp dịch vụ quản lý các hoạt động dành cho phòng Đào tạo/Phòng Công tác sinh viên.
 */
public class PDT_PCT_Service {
    //16. PdtDashboard (xem báo cáo tổng quan,Lấy thông tin trang chủ PDT (thống kê tổng quan))
    public List<Map<String, Object>> PdtDashboard(){
        try {
            String json = Database.apiPdtDashboard();
            return JSONUtil.toMap(json);
        } catch (Exception e) {
            System.err.println("❌ Lỗi khi xem báo cáo tổng quan: " + e.getMessage());
            return new ArrayList<>(); // trả về map rỗng nếu lỗi
        }
    }
    //17. get_ds_sinhvien (Lấy danh sách sinh viên)
    public List<Map<String, Object>> get_ds_sinhvien() {
        try {
            String json = Database.apiPdtListStudents();;
            return JSONUtil.toMap(json);
        } catch (Exception e) {
            System.err.println("❌ Lỗi khi lấy danh sách sinh viên: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    //18. PdtListSessions (Lấy danh sách buổi tư vấn (toàn hệ thống))
    public List<Map<String, Object>> PdtListSessions(){
        try {
            String json = Database.apiPdtListSessions();
            return JSONUtil.toMap(json);
        } catch (Exception e) {
            System.err.println("❌ Lỗi khi lấy danh sách buổi tư vấn: " + e.getMessage());
            return new ArrayList<>(); // trả về map rỗng nếu lỗi
        }
    }
    
    //19.PdtSessionDetail (Lấy chi tiết 1 buổi tư vấn) 
    public List<Map<String, Object>> PdtSessionDetail(int id) {
        try {
            String json = Database.apiPdtSessionDetail(id);
            return JSONUtil.toMap(json);
        } catch (Exception e) {
            System.err.println("❌ Lỗi khi lấy chi tiết 1 buổi tư vấn: " + e.getMessage());
            return new ArrayList<>(); // trả về map rỗng nếu lỗi
        }
    }
}