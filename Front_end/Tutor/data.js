// data.js - Tất cả dữ liệu cá nhân được gộp vào một object duy nhất
const profileData = {
  personalInfo: {
    "Họ và tên lót": "Nguyễn Văn",
    "Tên": "ABC",
    "Ngày sinh": "1/1/1990",
    "Giới tính": "Nam",
    "Quốc tịch": "Việt Nam",
    "Tôn giáo": "Không",
    "Dân tộc": "Kinh",
    "Thời điểm bắt đầu": "18/9/2016"
  },

  contactInfo: {
    "Số điện thoại": "0123456789",
    "Email giảng viên": "nv.a@hcmut.edu.vn",
    "Email cá nhân": "nva@gmail.com"
  },

  residenceInfo: {
    "Quốc gia": "Việt Nam",
    "Tỉnh/Thành phố": "Hồ Chí Minh",
    "Quận/Huyện": "Quận 1",
    "Phường/Xã": "Phường Bến Nghé",
    "Số nhà": "123 Đường Nguyễn Huệ"
  }
};

/**
 * Hàm giả lập gọi API lấy dữ liệu hồ sơ
 * Trả về Promise để dễ dàng dùng với async/await sau này
 */
function fetchProfileData() {
  return new Promise((resolve) => {
    // Giả lập độ trễ mạng ~300ms
    setTimeout(() => {
      resolve(profileData);
    }, 300);
  });
}

// Nếu bạn muốn dùng ngay đồng bộ (trong trường hợp đơn giản):
function getProfileDataSync() {
  return profileData;
}

// Export nếu dùng module (ES6)
// export { fetchProfileData, getProfileDataSync, profileData };