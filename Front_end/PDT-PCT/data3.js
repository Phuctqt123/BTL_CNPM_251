// data3.js – API giả lập danh sách buổi tư vấn (Phòng Đào Tạo)

function getConsultingEvents() {
  return new Promise((resolve) => {
    setTimeout(() => {
      const events = [
        {
          id: "e1",
          title: "HƯỚNG DẪN BTL CNPM1",
          instructor: "Lê Đình Thuận",
          date: "30/10/2025",
          location: "Cơ sở 1 - H6-201",
          time: "09:00 - 10:30",
          status: "registered"
        },
        {
          id: "e2",
          title: "HỖ TRỢ BTL CÔNG NGHỆ PHẦN MỀM",
          instructor: "Đặng Kiều Diễm",
          date: "22/11/2025",
          location: "Cơ sở 2 - B2-305",
          time: "14:00 - 16:00",
          status: "available"
        },
        {
          id: "e3",
          title: "XỬ LÝ NGÔN NGỮ TỰ NHIÊN VÀ AI",
          instructor: "Dương Hồ Nam",
          date: "24/11/2025",
          location: "Cơ sở 1 - C1-102",
          time: "18:00 - 20:30",
          status: "ended"
        },
        {
          id: "e4",
          title: "MỞ RỘNG MẠNG MÁY TÍNH NÂNG CAO",
          instructor: "Nguyễn Phương Duy",
          date: "19/11/2025",
          location: "Cơ sở 3 - D3-401",
          time: "13:00 - 15:00",
          status: "full"
        },
        {
          id: "e5",
          title: "HƯỚNG DẪN ĐỒ ÁN TỐT NGHIỆP CNTT",
          instructor: "Trần Thị Minh Châu",
          date: "28/11/2025",
          location: "Cơ sở 1 - Hội trường A",
          time: "08:30 - 11:30",
          status: "available"
        },
        {
          id: "e6",
          title: "TƯ VẤN CHỌN NGÀNH & HỌC BỔNG",
          instructor: "Phạm Văn Tuấn",
          date: "15/11/2025",
          location: "Online - Zoom",
          time: "19:00 - 20:30",
          status: "available"
        }
      ];
      resolve(events);
    }, 500); // delay nhẹ để thấy loading đẹp
  });
}