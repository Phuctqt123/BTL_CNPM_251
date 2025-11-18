package com.example.BTL_CNPM.domain;

import java.time.LocalDate;

// Giả định lớp Tutor kế thừa từ User và thực thi Notifiable
public class Tutor extends User implements Notifiable {
    
    // Constructor (Gọi constructor của lớp cha)
    public Tutor(String Ten, String Ho_va_ten_lot, String Key_user, String Taikhoan, String Matkhau,
                 LocalDate Ngay_sinh, String Gioi_tinh, String Quoc_tich, String Ton_giao,
                 String Dan_toc, LocalDate Start_time, String Sdt, String Email,
                 String Quoc_gia, String Tinh) {
        super(Ten, Ho_va_ten_lot, Key_user, Taikhoan, Matkhau, Ngay_sinh, Gioi_tinh, Quoc_tich, 
              Ton_giao, Dan_toc, Start_time, Sdt, Email, Quoc_gia, Tinh);
    }
    
    // Constructor không tham số
    public Tutor() {
        super();
    }

    // --- Phương thức ---

    // 1. Notify(message: string) - Thực thi Notifiable
    @Override
    public boolean receiveNotification(String message) {
        System.out.println("[Tutor] Đã nhận thông báo: " + message);
        return true;
    }
    
    // Phương thức theo biểu đồ
    public void Notify(String message) {
        receiveNotification(message);
    }

    // 2. get_Json() - Ghi đè phương thức trừu tượng từ User
    @Override
    public String get_Json() {
        // Giả định sử dụng thuộc tính Tên từ lớp cha
        return "{ \"type\": \"Tutor\", \"name\": \"" + get_ten() + "\" }"; 
    }
}