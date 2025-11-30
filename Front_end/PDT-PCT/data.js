// data.js – Mô phỏng API cho trang Phòng Đào Tạo
import PDTApi from "../api/PDTAPI.js";

// function getDashboardData() {
//   return PDTApi.getDashboard();
//   // return new Promise((resolve) => {
//   //   setTimeout(() => {
//   //     resolve({
//   //       stats: [
//   //         { number: 132, label: "Sinh viên" },
//   //         { number: 27, label: "Buổi tư vấn" },
//   //         { number: "98.7%", label: "Tỉ lệ tham dự" },
//   //         { number: 14, label: "Cố vấn" }
//   //       ],
//   //       updates: [
//   //         {
//   //           time: "Hôm nay - 16:45",
//   //           title: "Buổi tư vấn AI & ML thành công",
//   //           desc: "Hơn 40 sinh viên tham dự buổi tư vấn chuyên sâu về AI do ThS. Nguyễn Văn A chủ trì"
//   //         },
//   //         {
//   //           time: "Hôm nay - 10:30",
//   //           title: "3 sinh viên mới đăng ký",
//   //           desc: "Chào mừng các bạn tân sinh viên khóa 2025 đã tham gia hệ thống tư vấn"
//   //         },
//   //         {
//   //           time: "Hôm qua - 15:20",
//   //           title: "Cập nhật lịch tư vấn tuần tới",
//   //           desc: "Đã thêm 5 buổi tư vấn mới cho các ngành CNTT, Điện tử và Cơ khí"
//   //         },
//   //         {
//   //           time: "2 ngày trước - 08:00",
//   //           title: "Phản hồi tích cực từ sinh viên",
//   //           desc: "98% sinh viên đánh giá hài lòng với chất lượng buổi tư vấn gần đây"
//   //         }
//   //       ]
//   //     });
//   //   }, 500); // delay 500ms để thấy hiệu ứng loading đẹp
//   // });
// }

function getDashboardData() {
  return PDTApi.getDashboard().then((res) => {
    // res hiện tại là array => lấy phần tử đầu
    const data = Array.isArray(res) ? res[0] : res;

    return {
      stats: data.stats?.map(s => ({
        number: s.number,
        label: s.label
      })) || [],

      updates: data.updates?.map(u => ({
        time: u.time,
        title: u.title,
        desc: u.desc
      })) || []
    };
  });
}

window.getDashboardData=getDashboardData;