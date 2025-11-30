// data3.js – mô phỏng API trả về danh sách sự kiện
import StudentApi from "../api/StudentAPI.js";

function convertStatus(item, registeredIds) {
  if (registeredIds.includes(String(item.BuoiTuVan_id))) return "registered";

  if (item.Da_day_cho === true || (item.So_luong_dang_ky >= item.So_luong_toi_da)) return "full";

  if (item.Thoi_gian_ket_thuc) {
    const now = new Date();
    const endTime = new Date(item.Thoi_gian_ket_thuc);
    if (now > endTime) return "ended";
  }

  return "available";
}

async function getEvents() {
  try {
    const [upcomingData, registeredData] = await Promise.all([
      StudentApi.getUpComing(),
      StudentApi.getRegisteredList(window.thongtin.keyuser)
    ]);

    let upcomingList = [];
    if (Array.isArray(upcomingData)) upcomingList = upcomingData;
    else if (upcomingData && Array.isArray(upcomingData.data)) upcomingList = upcomingData.data;

    let registeredList = [];
    if (Array.isArray(registeredData)) registeredList = registeredData;
    else if (registeredData && Array.isArray(registeredData.data)) registeredList = registeredData.data;

    const registeredIds = registeredList.map(r => String(r.BuoiTuVan_id));

    window.myRegisteredSessions = registeredList;

    const mappedList = upcomingList.map(item => {
      let locationDisplay = "Chưa cập nhật";
      if (item.Hinh_thuc && item.Hinh_thuc.toLowerCase() === 'online') {
        locationDisplay = "Online (Google Meet)"; 
      } else {
        locationDisplay = item.Dia_chi;
      }

      // Xử lý giờ: Cắt chuỗi ISO "2026-01-10T11:00:00" -> lấy "11:00"
      const startTime = item.Thoi_gian_bat_dau ? item.Thoi_gian_bat_dau.split('T')[1].substring(0, 5) : "--";
      const endTime = item.Thoi_gian_ket_thuc ? item.Thoi_gian_ket_thuc.split('T')[1].substring(0, 5) : "--";
      const dateDisplay = item.Thoi_gian_bat_dau ? item.Thoi_gian_bat_dau.split('T')[0] : "--";

      return {
        id: item.BuoiTuVan_id,
        title: item.Ten_buoi_van,
        instructor: item.GiangVien_Ho_ten,
        date: dateDisplay,
        location: locationDisplay,
        time: `${startTime} - ${endTime}`,
        Thoi_gian_bat_dau: item.Thoi_gian_bat_dau,
        Thoi_gian_ket_thuc: item.Thoi_gian_ket_thuc,
        status: convertStatus(item, registeredIds)
      };
    });

    const filteredList = mappedList.filter(item => item.status !== 'registered');
    window.eventsData = filteredList;

    return filteredList;
  } catch (error) {
    console.error("Lỗi khi lấy danh sách sự kiện:", error);
    return [];
  }
}
// Hàm giả lập gọi API (có delay 300ms để giống thật)
// function getEvents() {
//   return new Promise((resolve) => {
//     setTimeout(() => {
//       const eventsData = [
//         {
//           id: "e1",
//           title: "HƯỚNG DẪN BTL CNPM",
//           instructor: "Lê Đình Thuận",
//           date: "30/10/2025",
//           location: "cơ sở 1 H6-201",
//           time: "9h-10h",
//           status: "registered"
//         },
//         {
//           id: "e2",
//           title: "HỖ TRỢ BTL CNX",
//           instructor: "Đặng Kiều Diễm",
//           date: "22/11/2025",
//           location: "Cơ sở 2 B2-305",
//           time: "14h-16h",
//           status: "available"
//         },
//         {
//           id: "e3",
//           title: "XỬ LÝ NGÔN NGỮ TỰ NHIÊN",
//           instructor: "Dương Hồ Nam",
//           date: "24/11/2025",
//           location: "cơ sở 1 C1-102",
//           time: "18h-20h",
//           status: "ended"
//         },
//         {
//           id: "e4",
//           title: "MỞ RỘNG MẠNG MÁY TÍNH",
//           instructor: "Nguyễn Phương Duy",
//           date: "19/11/2025",
//           location: "cơ sở 3 D3-401",
//           time: "13h-15h",
//           status: "full"
//         }
//       ];
//       resolve(eventsData);
//     }, 300); // delay 300ms giống gọi API thật
//   });
// }



async function signupEvent(eventId) {
    return StudentApi.registerSession({
      svKey: window.thongtin.keyuser, 
      buoiId: parseInt(eventId)
    });
    
};

window.getEvents = getEvents;
window.signupEvent = signupEvent;