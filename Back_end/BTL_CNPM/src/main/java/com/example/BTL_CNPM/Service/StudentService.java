package com.example.BTL_CNPM.Service;
import java.util.HashMap;
import java.util.Map;
// Giả định SinhVien và Buoi_tu_van đã được định nghĩa

/**
 * Lớp dịch vụ quản lý các hoạt động dành cho SinhVien.
 */
public class StudentService {
    
    // 1. get_info
    // làm mẫu để chạy maven thử
    public Map<String, Object> get_info(String id) {
        return new HashMap<>();  // trả về map rỗng
    }

    // 2. get_dsbuoituvan
    // làm mẫu để chạy maven thử
    public Map<String, Object> get_dsbuoituvan(String id) {
        return new HashMap<>();  // trả về map rỗng
    }
    // 3. get_dsbuoituvan_lichsu
    // làm mẫu để chạy maven thử
    public Map<String, Object> get_dsbuoituvan_lichsu(String id) {
        return new HashMap<>();  // trả về map rỗng
    }
    // 4.phan hoi
    // làm mẫu để chạy maven thử
    public boolean phan_hoi(Map<String, Object> body) {
        return true;  // trả về true
    }
}