// data2.js – API giả lập chi tiết buổi tư vấn (Phòng Đào Tạo)

function getEventDetailById(eventId) {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      const events = {
        e1: {
          title: "HƯỚNG DẪN BTL CNPM1",
          details: [
            { icon: "fas fa-user-tie",        label: "Giảng viên",      value: "ThS. Lê Đình Thuận" },
            { icon: "fas fa-calendar-alt",   label: "Ngày diễn ra",    value: "30/10/2025" },
            { icon: "fas fa-clock",          label: "Thời gian",       value: "09:00 - 10:30" },
            { icon: "fas fa-map-marker-alt", label: "Địa điểm",        value: "Cơ sở 1 - H6-201" },
            { icon: "fas fa-users",          label: "Số lượng đăng ký", value: "38/50" },
            { icon: "fas fa-check-circle",   label: "Trạng thái",      value: "Còn chỗ" }
          ],
          documents: [
            { name: "Hướng dẫn BTL CNPM1.pdf" },
            { name: "Mẫu báo cáo nhóm.docx" },
            { name: "Slide hướng dẫn.pptx" },
            { name: "Danh sách nhóm.xlsx" },
            { name: "Tài liệu tham khảo.zip" },
            { name: "Ảnh minh họa dự án.png" },
            { name: "Video hướng dẫn.mp4" },
            { name: "Ghi chú quan trọng.txt" },
            { name: "Thư mục mẫu dự án", isFolder: true }
          ]
        },
        e2: {
          title: "HỖ TRỢ BTL CÔNG NGHỆ PHẦN MỀM",
          details: [
            { icon: "fas fa-user-tie",        label: "Giảng viên",      value: "TS. Đặng Kiều Diễm" },
            { icon: "fas fa-calendar-alt",   label: "Ngày diễn ra",    value: "22/11/2025" },
            { icon: "fas fa-clock",          label: "Thời gian",       value: "14:00 - 16:00" },
            { icon: "fas fa-map-marker-alt", label: "Địa điểm",        value: "Cơ sở 2 - B2-305" },
            { icon: "fas fa-users",          label: "Số lượng đăng ký", value: "45/60" },
            { icon: "fas fa-clock",          label: "Trạng thái",      value: "Còn chỗ" }
          ],
          documents: [
            { name: "Hướng dẫn BTL CNX.pdf" },
            { name: "Template báo cáo.docx" },
            { name: "Slide hỗ trợ.pptx" },
            { name: "Danh sách sinh viên tham gia.xlsx" }
          ]
        },
        e3: {
          title: "XỬ LÝ NGÔN NGỮ TỰ NHIÊN VÀ AI",
          details: [
            { icon: "fas fa-user-tie",        label: "Giảng viên",      value: "TS. Dương Hồ Nam" },
            { icon: "fas fa-calendar-alt",   label: "Ngày diễn ra",    value: "24/11/2025" },
            { icon: "fas fa-clock",          label: "Thời gian",       value: "18:00 - 20:30" },
            { icon: "fas fa-map-marker-alt", label: "Địa điểm",        value: "Cơ sở 1 - C1-102" },
            { icon: "fas fa-users",          label: "Số lượng đăng ký", value: "72/80" },
            { icon: "fas fa-check-circle",   label: "Trạng thái",      value: "Đã kết thúc" }
          ],
          documents: [
            { name: "Slide NLP & AI 2025.pptx" },
            { name: "Code mẫu.ipynb" },
            { name: "Dataset xử lý tiếng Việt.zip" },
            { name: "Video buổi học.mp4" }
          ]
        }
        // Thêm các buổi khác ở đây sau này...
      };

      if (events[eventId]) {
        resolve(events[eventId]);
      } else {
        reject(new Error("Không tìm thấy buổi tư vấn"));
      }
    }, 600);
  });
}