package com.example.BTL_CNPM.Service;
import java.util.ArrayList;
import java.util.List;

import com.example.BTL_CNPM.domain.SinhVien;



// Giả định Student là lớp con của User hoặc lớp riêng
// Giả định SinhVien là lớp con của User

/**
 * Lớp dịch vụ quản lý các hoạt động dành cho phòng Đào tạo/Phòng Công tác sinh viên.
 */
public class PDT_PCT_Service {

    // 1. xem_bao_cao(student: Student)
    // Giả định Student là lớp SinhVien
    public String xem_bao_cao(SinhVien sinhVien) {
        if (sinhVien == null) {
            return "Lỗi: Sinh viên không hợp lệ.";
        }
        // Logic: Truy vấn database để lấy các báo cáo liên quan đến sinhVien
        return "[PDT_PCT] Báo cáo chi tiết về sinh viên có Key: " + sinhVien.get_key();
    }

    // 2. get_ds_sinhvien()
    public List<SinhVien> get_ds_sinhvien() {
        // Logic: Truy vấn database để lấy toàn bộ danh sách sinh viên
        List<SinhVien> danhSach = new ArrayList<>();
        danhSach.add(new SinhVien()); // Ví dụ
        danhSach.add(new SinhVien()); // Ví dụ
        System.out.println("[PDT_PCT] Đã tải danh sách " + danhSach.size() + " sinh viên.");
        return danhSach;
    }
}