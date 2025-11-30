import StudentApi from "../api/StudentAPI.js";

async function getStudentProfile(id) {
  try{
    const data = await StudentApi.getProfile(id);

    // API trả về dạng: [ { ... } ]
    const user = data && data.length > 0 ? data[0] : null;

    if (!user) {
      console.error("Không tìm thấy dữ liệu từ API");
      return null;
    }

    // Mapping về đúng format giao diện đang dùng
    const studentProfile = {
      personalInfo: {
        "Họ và tên lót": user.Ho_va_ten_lot || "",
        "Tên": user.Ten || "",
        "Ngày sinh": user.Ngay_sinh || "",
        "Giới tính": user.Gioi_tinh || "",
        "MSSV (Mã số sinh viên)": user.Key_user || "",
        "Số điện thoại": user.Sdt || "",
        "Email sinh viên": user.Email || "",
        "Email cá nhân": user.Email || "",   // nếu backend không tách email cá nhân
        "Quốc tịch": user.Quoc_tich || "",
        "Tôn giáo": user.Ton_giao || "",
        "Dân tộc": user.Dan_toc || "",
        "Thời điểm bắt đầu": user.Start_time || ""
      },

      residenceInfo: {
        "Quốc gia": user.Quoc_gia || "",
        "Tỉnh/Thành phố": user.Tinh || "",
        "Quận/Huyện": user.Huyen || "--",     // backend chưa có, để "--"
        "Phường/Xã": user.Xa || "--",
        "Số nhà": user.So_nha || "--"
      }
    };

    return studentProfile;

  } catch (error) {
    console.error("Lỗi khi xử lý profile sinh viên:", error);
    return null;
  }
}

window.getStudentProfile = getStudentProfile;
