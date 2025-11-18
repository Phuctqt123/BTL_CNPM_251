package com.example.BTL_CNPM.domain;





public interface Notifiable {
    /**
     * Phương thức bắt buộc để nhận và xử lý thông báo.
     * @param message Nội dung thông báo.
     * @return true nếu thông báo thành công, false nếu ngược lại.
     */
    boolean receiveNotification(String message);
}