// data4.js – API giả lập danh sách sinh viên (Phòng Đào Tạo)
import PDTApi from "../api/PDTAPI.js";
function getStudentsData() {
  return PDTApi.getStudents().then((res) => {
    return res.map((s, index) => ({
      stt: s.stt ?? index + 1,
      mssv: s.mssv,
      name: s.name,
      sessions: s.sessions,
      score: s.score
    }));
  });
}

// function getStudentsData() {
//   return PDTApi.registerStudents();
//   // return new Promise((resolve) => {
//   //   setTimeout(() => {
//   //     const students = [
//   //       { stt: 1,  mssv: 2312449, name: 'Phạm Hồng Nhân',       sessions: 5,  score: 75 },
//   //       { stt: 2,  mssv: 2312357, name: 'Nguyễn Cảnh Nguyên',   sessions: 4,  score: 62 },
//   //       { stt: 3,  mssv: 2312664, name: 'Bùi Đình Phúc',        sessions: 7,  score: 88 },
//   //       { stt: 4,  mssv: 2312445, name: 'Trần Minh Quân',       sessions: 8,  score: 92 },
//   //       { stt: 5,  mssv: 2312556, name: 'Lê Hoàng Anh',         sessions: 6,  score: 79 },
//   //       { stt: 6,  mssv: 2312778, name: 'Vũ Thanh Huy',         sessions: 5,  score: 70 },
//   //       { stt: 7,  mssv: 2312889, name: 'Đặng Bảo Long',        sessions: 4,  score: 58 },
//   //       { stt: 8,  mssv: 2312334, name: 'Ngô Văn Toàn',         sessions: 9,  score: 95 },
//   //       { stt: 9,  mssv: 2312445, name: 'Hồ Quỳnh Anh',         sessions: 3,  score: 45 },
//   //       { stt: 10, mssv: 2312556, name: 'Phạm Thị Thanh',       sessions: 6,  score: 81 },
//   //       { stt: 11, mssv: 2312667, name: 'Cao Duy Khánh',        sessions: 5,  score: 73 },
//   //       { stt: 12, mssv: 2312778, name: 'Lý Minh Tú',           sessions: 4,  score: 60 },
//   //       { stt: 13, mssv: 2312889, name: 'Trương Gia Bảo',       sessions: 7,  score: 85 },
//   //       { stt: 14, mssv: 2312990, name: 'Nguyễn Tú Linh',       sessions: 8,  score: 90 },
//   //       { stt: 15, mssv: 2313001, name: 'Đỗ Thái Bình',         sessions: 5,  score: 72 },
//   //       { stt: 16, mssv: 2313112, name: 'Phan Văn Hiển',        sessions: 6,  score: 78 },
//   //       { stt: 17, mssv: 2313223, name: 'Vương Khắc Huy',       sessions: 4,  score: 59 },
//   //       { stt: 18, mssv: 2313334, name: 'Mai Thị Hương',        sessions: 7,  score: 87 },
//   //       { stt: 19, mssv: 2313445, name: 'Dương Văn Thắng',      sessions: 5,  score: 71 },
//   //       { stt: 20, mssv: 2313556, name: 'Kiều Anh Tuấn',        sessions: 6,  score: 80 },
//   //       { stt: 21, mssv: 2313667, name: 'Lạc Quốc Minh',        sessions: 8,  score: 93 },
//   //       { stt: 22, mssv: 2313778, name: 'Tạ Thị Phương Linh',   sessions: 5,  score: 74 },
//   //       { stt: 23, mssv: 2311111, name: 'Dương Hồ Nam',         sessions: 76, score: 1046 } 
//   //     ];
//   //     resolve(students);
//   //   }, 600); // delay để thấy loading đẹp
//   // });
// }
window.getStudentsData=getStudentsData;