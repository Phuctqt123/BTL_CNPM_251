package com.example.BTL_CNPM.Service;
import java.util.ArrayList;
import java.util.List;

import com.example.BTL_CNPM.domain.Buoi_tu_van;
import com.example.BTL_CNPM.domain.SinhVien;
// Giả định SinhVien và Buoi_tu_van đã được định nghĩa

/**
 * Lớp dịch vụ quản lý các hoạt động dành cho SinhVien.
 */
public class StudentService {
    
    // 1. get_info(student: SinhVien)
    public String get_info(SinhVien sinhVien) {
        if (sinhVien == null) {
            return "Lỗi: Sinh viên không hợp lệ.";
        }
        // Logic: Lấy thông tin cá nhân của sinh viên
        return "[Student] Thông tin sinh viên: " + sinhVien.get_ten() + " - MSSV: " + sinhVien.get_key();
    }

    // 2. get_dsbuoituvan(student: SinhVien)
    public List<Buoi_tu_van> get_dsbuoituvan(SinhVien sinhVien) {
        // Logic: Truy vấn database để lấy danh sách các buổi tư vấn sinh viên đã đăng ký
        List<Buoi_tu_van> dsBuoi = new ArrayList<>();
        dsBuoi.add(new Buoi_tu_van());
        System.out.println("[Student] Sinh viên " + sinhVien.get_ten() + " đã đăng ký " + dsBuoi.size() + " buổi tư vấn.");
        return dsBuoi;
    }

    // 3. phan_hoi(sinhvien: SinhVien, buoituvan: Buoi_tu_van)
    public boolean phan_hoi(SinhVien sinhVien, Buoi_tu_van buoiTuVan) {
        if (sinhVien == null || buoiTuVan == null) {
            System.out.println("[Student] Thất bại: Thiếu thông tin phản hồi.");
            return false;
        }
        // Logic: Ghi lại phản hồi của sinh viên về buổi tư vấn
        System.out.println("[Student] Sinh viên " + sinhVien.get_ten() + " đã gửi phản hồi cho buổi: " + buoiTuVan.getTen_buoi_van());
        return true;
    }
}