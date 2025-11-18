package com.example.BTL_CNPM.domain;

import java.time.LocalDate;

/**
 * Lớp trừu tượng (abstract) đại diện cho một người dùng.
 * Chứa các thuộc tính cơ bản của người dùng và các phương thức truy cập.
 */
public abstract class User {
    // Thuộc tính (Attributes/Fields)
    private String Ten; // Tên
    private String Ho_va_ten_lot; // Họ và tên lót
    private String Key_user; // Khóa người dùng
    private String Taikhoan; // Tài khoản (Username)
    private String Matkhau; // Mật khẩu (Password)
    private LocalDate Ngay_sinh; // Ngày sinh
    private String Gioi_tinh; // Giới tính
    private String Quoc_tich; // Quốc tịch
    private String Ton_giao; // Tôn giáo
    private String Dan_toc; // Dân tộc
    private LocalDate Start_time; // Thời gian bắt đầu (Ví dụ: ngày tham gia hệ thống)
    private String Sdt; // Số điện thoại
    private String Email; // Email
    private String Quoc_gia; // Quốc gia
    private String Tinh; // Tỉnh/Thành phố

    // Constructor (Bạn có thể chọn tạo constructor đầy đủ hoặc không tham số tùy nhu cầu)
    // Ví dụ về constructor đầy đủ tham số:
    public User(String Ten, String Ho_va_ten_lot, String Key_user, String Taikhoan, String Matkhau,
                LocalDate Ngay_sinh, String Gioi_tinh, String Quoc_tich, String Ton_giao,
                String Dan_toc, LocalDate Start_time, String Sdt, String Email,
                String Quoc_gia, String Tinh) {
        this.Ten = Ten;
        this.Ho_va_ten_lot = Ho_va_ten_lot;
        this.Key_user = Key_user;
        this.Taikhoan = Taikhoan;
        this.Matkhau = Matkhau;
        this.Ngay_sinh = Ngay_sinh;
        this.Gioi_tinh = Gioi_tinh;
        this.Quoc_tich = Quoc_tich;
        this.Ton_giao = Ton_giao;
        this.Dan_toc = Dan_toc;
        this.Start_time = Start_time;
        this.Sdt = Sdt;
        this.Email = Email;
        this.Quoc_gia = Quoc_gia;
        this.Tinh = Tinh;
    }


    public User() {
    }


    public String get_ten() {
        return Ten;
    }

    public String get_key() {
        return Key_user;
    }

    public abstract String get_Json();
   
    public String getMatkhau() {
        return Matkhau;
    }
    

}

