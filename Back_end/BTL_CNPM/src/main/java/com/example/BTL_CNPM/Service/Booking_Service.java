// Giả định Booking, Tutor và SinhVien đã được định nghĩa

/**
 * Lớp dịch vụ quản lý các hoạt động liên quan đến việc đặt chỗ (Booking).
 */
package com.example.BTL_CNPM.Service;
import com.example.BTL_CNPM.domain.Booking;
import com.example.BTL_CNPM.domain.SinhVien;
import com.example.BTL_CNPM.domain.Tutor;
public class Booking_Service {

    // 1. Check_booking_Student(booking: Booking, student: SinhVien)
    public boolean Check_booking_Student(Booking booking, SinhVien student) {
        if (booking == null || student == null) {
            System.out.println("[Booking] Thất bại: Thiếu thông tin Booking/Student.");
            return false;
        }
        // Logic: Kiểm tra xem Booking này có hợp lệ cho sinh viên này không
        // Ví dụ: Kiểm tra xem sinh viên này có phải là người tạo ra Booking không,
        // hoặc Booking có còn slot không.
        System.out.println("[Booking] Kiểm tra booking " + booking.get_buoituvan().getTen_buoi_van() + " cho sinh viên: Hợp lệ.");
        return true;
    }

    // 2. Check_booking_Tutor(booking: Booking, tutor: Tutor)
    public boolean Check_booking_Tutor(Booking booking, Tutor tutor) {
        if (booking == null || tutor == null) {
            System.out.println("[Booking] Thất bại: Thiếu thông tin Booking/Tutor.");
            return false;
        }
        // Logic: Kiểm tra xem Booking này có liên quan đến Tutor này không
        // Ví dụ: Kiểm tra xem buổi tư vấn trong Booking có phải do Tutor này phụ trách không.
        System.out.println("[Booking] Kiểm tra booking " + booking.get_buoituvan().getTen_buoi_van() + " cho Tutor: Hợp lệ.");
        return true;
    }
}