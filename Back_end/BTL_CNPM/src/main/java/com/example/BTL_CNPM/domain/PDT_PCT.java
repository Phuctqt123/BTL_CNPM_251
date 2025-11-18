package com.example.BTL_CNPM.domain;
import java.time.LocalDate;

// Giả định lớp PDT_PCT kế thừa từ User và thực thi Notifiable
// Cần đảm bảo file User.java và Notifiable.java đã được tạo
public class PDT_PCT extends User implements Notifiable {
    
    // Thuộc tính riêng (đã có trong User, nhưng được liệt kê lại ở đây, 
    // ta giữ lại như thuộc tính riêng của lớp này nếu muốn phân biệt với lớp cha)
    // Trong trường hợp này, ta giả định Taikhoan và Matkhau đã được kế thừa, 
    // nhưng để code độc lập và rõ ràng theo biểu đồ, ta có thể khai báo lại hoặc 
    // chỉ dùng thuộc tính của lớp cha.
    // Nếu ta dùng thuộc tính của lớp cha User:
    
    // Constructor
    public PDT_PCT(String Ten, String Ho_va_ten_lot, String Key_user, String Taikhoan, String Matkhau,
                   LocalDate Ngay_sinh, String Gioi_tinh, String Quoc_tich, String Ton_giao,
                   String Dan_toc, LocalDate Start_time, String Sdt, String Email,
                   String Quoc_gia, String Tinh) {
        super(Ten, Ho_va_ten_lot, Key_user, Taikhoan, Matkhau, Ngay_sinh, Gioi_tinh, Quoc_tich, 
              Ton_giao, Dan_toc, Start_time, Sdt, Email, Quoc_gia, Tinh);
    }
    
    // Constructor không tham số
    public PDT_PCT() {
        super();
    }

    // --- Phương thức ---

    // 1. Notify(message: string)
    // Thực thi phương thức từ interface Notifiable
    @Override
    public boolean receiveNotification(String message) {
        System.out.println("[PDT_PCT] Đã nhận thông báo: " + message);
        return true; // Giả định luôn nhận thông báo thành công
    }
    
    // Phương thức theo biểu đồ
    public void Notify(String message) {
        receiveNotification(message); // Gọi phương thức receiveNotification
    }

    // 2. get_taikhoan()
    // Giả định phương thức này lấy thuộc tính Taikhoan từ lớp cha User
    public String get_taikhoan() {
        // Cần có Getter trong lớp User để truy cập Taikhoan
        // Nếu không có getter, ta giả định gọi phương thức get_taikhoan() nếu nó được thêm vào User
        // Hoặc ta phải tạo getter trong lớp User cho thuộc tính Taikhoan
        return "Tài khoản của PDT_PCT"; // Thay thế bằng logic truy cập thực tế
    }

    // 3. get_matkhau()
    // Giả định phương thức này lấy thuộc tính Matkhau từ lớp cha User
    public String get_matkhau() {
        return "Mật khẩu của PDT_PCT"; // Thay thế bằng logic truy cập thực tế
    }
    
    // 4. Ghi đè phương thức trừu tượng get_Json() từ User
    @Override
    public String get_Json() {
        return "{ \"type\": \"PDT_PCT\", \"taikhoan\": \"" + get_taikhoan() + "\" }";
    }
}