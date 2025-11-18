package com.example.BTL_CNPM.Service;

import java.util.ArrayList;
import java.util.List;

import com.example.BTL_CNPM.domain.Buoi_tu_van;
import com.example.BTL_CNPM.domain.TaiLieu;
import com.example.BTL_CNPM.domain.Tutor;

// Giả định Tutor, Buoi_tu_van và TaiLieu đã được định nghĩa

/**
 * Lớp dịch vụ quản lý các hoạt động dành cho Tutor (Giảng viên/Người tư vấn).
 */
public class TutorService {

    // 1. get_info(tutor: Tutor)
    public String get_info(Tutor tutor) {
        if (tutor == null) {
            return "Lỗi: Tutor không hợp lệ.";
        }
        // Logic: Lấy thông tin chi tiết của Tutor
        return "[Tutor] Thông tin của Tutor: " + tutor.get_ten();
    }

    // 2. get_dsbuoituvan(tutor: Tutor)
    public List<Buoi_tu_van> get_dsbuoituvan(Tutor tutor) {
        // Logic: Truy vấn database để lấy danh sách các buổi tư vấn do Tutor này phụ trách
        List<Buoi_tu_van> dsBuoi = new ArrayList<>();
        dsBuoi.add(new Buoi_tu_van());
        System.out.println("[Tutor] Tutor " + tutor.get_ten() + " có " + dsBuoi.size() + " buổi tư vấn.");
        return dsBuoi;
    }

    // 3. danh_gia(tutor: Tutor, buoituvan: Buoi_tu_van)
    public boolean danh_gia(Tutor tutor, Buoi_tu_van buoituvan) {
        if (tutor == null || buoituvan == null) {
            System.out.println("[Tutor] Thất bại: Thiếu thông tin đánh giá.");
            return false;
        }
        // Logic: Thêm logic đánh giá buổi tư vấn
        System.out.println("[Tutor] Đã đánh giá buổi tư vấn: " + buoituvan.getTen_buoi_van() + " thành công.");
        return true;
    }
    
    // 4. them_tailieu(tailieu: TaiLieu, buoituvan: Buoi_tu_van)
    public boolean them_tailieu(TaiLieu tailieu, Buoi_tu_van buoituvan) {
        if (tailieu == null || buoituvan == null) {
            System.out.println("[Tutor] Thất bại: Thiếu thông tin tài liệu hoặc buổi tư vấn.");
            return false;
        }
        // Logic: Thêm tài liệu vào danh sách tài liệu của buổi tư vấn
        // buoituvan.getDanh_sach_tai_lieu().add(tailieu); // Cần getter/setter thích hợp
        System.out.println("[Tutor] Đã thêm tài liệu '" + tailieu.getFilename() + "' vào buổi tư vấn '" + buoituvan.getTen_buoi_van() + "'.");
        return true;
    }
}