// data3.js – mô phỏng API trả về danh sách sự kiện

// Hàm giả lập gọi API (có delay 300ms để giống thật)
function getEvents() {
  return new Promise((resolve) => {
    setTimeout(() => {
      const eventsData = [
        {
          id: "e1",
          title: "HƯỚNG DẪN BTL CNPM",
          instructor: "Lê Đình Thuận",
          date: "30/10/2025",
          location: "cơ sở 1 H6-201",
          time: "9h-10h",
          status: "registered"
        },
        {
          id: "e2",
          title: "HỖ TRỢ BTL CNX",
          instructor: "Đặng Kiều Diễm",
          date: "22/11/2025",
          location: "Cơ sở 2 B2-305",
          time: "14h-16h",
          status: "available"
        },
        {
          id: "e3",
          title: "XỬ LÝ NGÔN NGỮ TỰ NHIÊN",
          instructor: "Dương Hồ Nam",
          date: "24/11/2025",
          location: "cơ sở 1 C1-102",
          time: "18h-20h",
          status: "ended"
        },
        {
          id: "e4",
          title: "MỞ RỘNG MẠNG MÁY TÍNH",
          instructor: "Nguyễn Phương Duy",
          date: "19/11/2025",
          location: "cơ sở 3 D3-401",
          time: "13h-15h",
          status: "full"
        }
      ];
      resolve(eventsData);
    }, 300); // delay 300ms giống gọi API thật
  });
}