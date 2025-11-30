// [FILE: data.js]
import TutorApi from "../api/TutorAPI.js";// Đảm bảo đường dẫn này đúng

// Biến chứa dữ liệu (dùng làm mặc định nếu lỗi)
let _localProfileData = {
  personalInfo: {}, contactInfo: {}, residenceInfo: {}
};

// --- HÀM 1: Gọi API lấy dữ liệu thật ---
async function getTutorProfile(id) {
  try {
    // 1. Gọi API Backend (Sửa lại đường dẫn API nếu cần)
    // Giả sử TutorApi.getProfile(id) đã được cấu hình gọi tới: /api/tutor/profile/{id}
    // Nếu chưa có TutorApi, bạn có thể dùng fetch trực tiếp như sau:
    /*
    const response = await fetch(`https://backend-production-42b5.up.railway.app/api/tutor/profile/${id}`);
    const data = await response.json();
    */

    // Ở đây giả định bạn dùng TutorApi như trong code cũ
    const data = await TutorApi.getProfile(id);

    // 2. Map dữ liệu từ Backend sang Giao diện
    if (data && data.length > 0) {
      const user = data[0]; // Lấy phần tử đầu tiên

      // Helper format ngày
      const formatDate = (dateStr) => {
        if (!dateStr) return "";
        const [y, m, d] = dateStr.split('-');
        return `${d}/${m}/${y}`;
      }

      _localProfileData = {
        personalInfo: {
          "Họ và tên lót": user.Ho_va_ten_lot || "",
          "Tên": user.Ten || "",
          "Ngày sinh": formatDate(user.Ngay_sinh) || "",
          "Giới tính": user.Gioi_tinh || "",
          "Quốc tịch": user.Quoc_tich || "",
          "Tôn giáo": user.Ton_giao || "Không",
          "Dân tộc": user.Dan_toc || "",
          "Thời điểm bắt đầu": formatDate(user.Start_time) || "",
          "Chức danh": user.Chuc_danh || ""
        },
        contactInfo: {
          "Số điện thoại": user.Sdt || "",
          "Email giảng viên": user.Email || "",
          "Email cá nhân": user.Email_ca_nhan || "Chưa cập nhật"
        },
        residenceInfo: {
          "Quốc gia": user.Quoc_gia || "",
          "Tỉnh/Thành phố": user.Tinh || "",
          "Quận/Huyện": "Chưa cập nhật",
          "Phường/Xã": "Chưa cập nhật",
          "Số nhà": "Chưa cập nhật"
        }
      };
      return _localProfileData;
    }

    return _localProfileData; // Trả về rỗng/mặc định nếu không tìm thấy user
  } catch (error) {
    console.error("Lỗi API getTutorProfile:", error);
    return _localProfileData;
  }
}



// --- HÀM 2: Mock Data (Giữ lại để phòng hờ) ---
function fetchProfileData() {
  return new Promise(resolve => setTimeout(() => resolve(_localProfileData), 300));
}

// --- HÀM 3: Tạo Session (Giữ nguyên code của bạn) ---
function create_session(raw) {
  // Hàm format datetime thành: YYYY-MM-DD HH:mm:ss
  function formatDate(dt) {
    if (!dt) return null;
    const d = new Date(dt);
    const pad = (n) => n.toString().padStart(2, '0');

    return (
      d.getFullYear() + "-" +
      pad(d.getMonth() + 1) + "-" +
      pad(d.getDate()) + " " +
      pad(d.getHours()) + ":" +
      pad(d.getMinutes()) + ":" +
      pad(d.getSeconds())
    );
  }

  const data = {
    gvKey: raw.creator?.keyuser || null,
    tenBuoi: raw.title || null,
    hinhThuc: raw.format ? raw.format.charAt(0).toUpperCase() + raw.format.slice(1) : null,
    thoiGianBD: formatDate(raw.start_time),
    thoiGianKT: formatDate(raw.end_time),
    ghiChu: raw.notes || null,
    diaChi: raw.location || null,
    linkGgmeet: raw.online_link || null,
    slToiThieu: raw.min_students || null,
    slToiDa: raw.max_students || null
  };

  return TutorApi.createSession(data);
}

// --- PUBLIC RA GLOBAL ---
window.getTutorProfile = getTutorProfile;
window.fetchProfileData = fetchProfileData;
window.create_session = create_session;