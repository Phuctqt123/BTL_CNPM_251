/**
 * Lớp đại diện cho một tài liệu hoặc nguồn tài nguyên.
 */

package com.example.BTL_CNPM.domain;
public class TaiLieu {
    // Thuộc tính
    private String filename; // Tên file
    private String filetype; // Kiểu file
    private String link;     // Đường dẫn/link tải

    // Constructor đầy đủ
    public TaiLieu(String filename, String filetype, String link) {
        this.filename = filename;
        this.filetype = filetype;
        this.link = link;
    }

    // Constructor không tham số
    public TaiLieu() {
    }

    // Các phương thức Getter (Public)
    public String getFilename() {
        return filename;
    }

    public String getFiletype() {
        return filetype;
    }

    public String getLink() {
        return link;
    }
    
    // Các phương thức Setter (Thêm vào để thay đổi thuộc tính)
    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public void setLink(String link) {
        this.link = link;
    }
}