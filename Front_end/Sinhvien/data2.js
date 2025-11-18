// data.js - Dữ liệu thông tin cá nhân sinh viên
const studentProfile = {
  personalInfo: {
    "Họ và tên lót": "Trần Khánh",
    "Tên": "Ann",
    "Ngày sinh": "05/01/2005",
    "Giới tính": "Nữ",
    "MSSV (Mã số sinh viên)": "2310037",
    "Số điện thoại": "0987654321",
    "Email sinh viên": "an.trankhanh@hcmut.edu.vn",
    "Email cá nhân": "khanhan501@gmail.com",
    "Quốc tịch": "Việt Nam",
    "Tôn giáo": "Không",
    "Dân tộc": "Kinh",
    "Thời điểm bắt đầu": "31/8/2023"
  },
  residenceInfo: {
    "Quốc gia": "Việt Nam",
    "Tỉnh/Thành phố": "Bình Thuận",
    "Quận/Huyện": "Phan Thiết",
    "Phường/Xã": "--",
    "Số nhà": "--"
  }
};

// Hàm giả lập API (giống như các file trước)
function delay(ms = 300) {
  return new Promise(resolve => setTimeout(resolve, ms));
}

async function getStudentProfile() {
  await delay();
  return studentProfile;
}

// Export global để HTML dùng được
if (typeof window !== 'undefined') {
  window.getStudentProfile = getStudentProfile;
}