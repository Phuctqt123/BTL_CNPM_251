
package com.example.BTL_CNPM.domain;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Giả định lớp SinhVien đã được định nghĩa
// import com.yourpackage.SinhVien; 

/**
 * Lớp đại diện cho một buổi tư vấn, chứa thông tin chi tiết về thời gian, địa điểm và tài liệu.
 */
public class Buoi_tu_van {
    
    // Thuộc tính
    // Quan hệ: Aggregation với SinhVien (1 Buoi_tu_van có List<SinhVien>)
    private List<SinhVien> Danh_sach_sinh_vien; // Danh sách sinh viên tham gia
    private int id; // ID buổi tư vấn
    private String Ten_buoi_van; // Tên buổi tư vấn
    private LocalDateTime Thoi_gian_bat_dau; // Thời gian bắt đầu
    private LocalDateTime Thoi_gian_ket_thuc; // Thời gian kết thúc
    private int So_luong_toi_thieu; // Số lượng sinh viên tối thiểu
    private int So_luong_toi_da; // Số lượng sinh viên tối đa
    private String Ghi_chu; // Ghi chú
    private String Dia_chi; // Địa chỉ
    private String Link_ggmeet; // Link Google Meet
    private String Hinh_thuc; // Hình thức (Online/Offline)
    private String Trang_thai; // Trạng thái (Ví dụ: Sắp diễn ra, Đã hủy, Đã hoàn thành)
    // Quan hệ: Composition/Aggregation với TaiLieu (1..* - Buoi_tu_van có ít nhất 1 TaiLieu)
    private List<TaiLieu> Danh_sach_tai_lieu; // Danh sách tài liệu liên quan

    // Constructor
    public Buoi_tu_van(int id, String Ten_buoi_van, LocalDateTime Thoi_gian_bat_dau,
                       LocalDateTime Thoi_gian_ket_thuc, int So_luong_toi_thieu, int So_luong_toi_da,
                       String Ghi_chu, String Dia_chi, String Link_ggmeet, String Hinh_thuc,
                       String Trang_thai, List<TaiLieu> Danh_sach_tai_lieu) {
        this.id = id;
        this.Ten_buoi_van = Ten_buoi_van;
        this.Thoi_gian_bat_dau = Thoi_gian_bat_dau;
        this.Thoi_gian_ket_thuc = Thoi_gian_ket_thuc;
        this.So_luong_toi_thieu = So_luong_toi_thieu;
        this.So_luong_toi_da = So_luong_toi_da;
        this.Ghi_chu = Ghi_chu;
        this.Dia_chi = Dia_chi;
        this.Link_ggmeet = Link_ggmeet;
        this.Hinh_thuc = Hinh_thuc;
        this.Trang_thai = Trang_thai;
        this.Danh_sach_tai_lieu = Danh_sach_tai_lieu;
        this.Danh_sach_sinh_vien = new ArrayList<>(); // Khởi tạo danh sách sinh viên rỗng
    }
    
    // Constructor không tham số (với khởi tạo danh sách rỗng)
    public Buoi_tu_van() {
        this.Danh_sach_sinh_vien = new ArrayList<>();
        this.Danh_sach_tai_lieu = new ArrayList<>();
    }

    // --- Các Phương thức ---

    // 1. get_danh_sach_sinh_vien()
    public List<SinhVien> get_danh_sach_sinh_vien() {
        return Danh_sach_sinh_vien;
    }

    // 2. get_Tutor()
    // Phương thức này ngụ ý trả về Tutor của buổi tư vấn.
    // Vì không có thuộc tính Tutor trong lớp, ta giả định có logic tìm kiếm hoặc thuộc tính Tutor đã bị ẩn.
    // Ta sẽ giả định có một thuộc tính Tutor private.
    public Tutor get_Tutor() {
        // [Logic: Tìm và trả về Tutor liên quan đến buổi tư vấn này]
        return new Tutor(); // Giả định trả về một đối tượng Tutor mới
    }

    // 3. get_info()
    public String get_info() {
        return String.format("Buổi tư vấn: %s\nThời gian: %s - %s\nĐịa điểm: %s",
                             Ten_buoi_van, Thoi_gian_bat_dau, Thoi_gian_ket_thuc, Dia_chi);
    }

    // 4. get_tailieu(Danh_sach_tai_lieu)
    // Phương thức này có vẻ thừa thãi vì đã có getter cho Danh_sach_tai_lieu.
    // Ta sẽ giả định nó là một phương thức truy cập.
    public List<TaiLieu> get_tailieu(List<TaiLieu> Danh_sach_tai_lieu) {
        return this.Danh_sach_tai_lieu;
    }

    // 5. get_json()
    // Phương thức chuyển đổi thông tin buổi tư vấn thành chuỗi JSON
    public String get_json() {
        // Đây là ví dụ đơn giản
        return String.format("{\"id\":%d, \"ten\":\"%s\", \"trang_thai\":\"%s\"}", 
                             id, Ten_buoi_van, Trang_thai);
    }
    
    // Các Getter và Setter cơ bản khác...
    public int getId() { return id; }
    public String getTen_buoi_van() { return Ten_buoi_van; }
    // ...
}