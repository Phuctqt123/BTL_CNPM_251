// import com.yourpackage.Buoi_tu_van; // Cần import nếu lớp Buoi_tu_van nằm ở package khác

/**
 * Lớp đại diện cho việc đặt chỗ/đăng ký một buổi tư vấn.
 */
package com.example.BTL_CNPM.domain;
public class Booking {

    private Buoi_tu_van buoituvan;

    public Booking(Buoi_tu_van buoituvan) {
        this.buoituvan = buoituvan;
    }

    public Booking() {
    }


    public Buoi_tu_van get_buoituvan() {
        return buoituvan;
    }

    public void setBuoituvan(Buoi_tu_van buoituvan) {
        this.buoituvan = buoituvan;
    }
}