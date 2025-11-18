// data4.js – Mô phỏng API trả về lịch sử đăng ký

function getHistoryData() {
  return new Promise((resolve) => {
    setTimeout(() => {
      const historyData = {
        sessions: [
          {
            id: 1,
            title: "Hội thảo Lập trình Web",
            status: "completed",
            lecturer: "Nguyễn Văn A",
            date: "30/10/2025",
            time: "10:00 - 13:00",
            location: "H3 - 205",
            attendees: "120 sinh viên",
            documents: ["Slide_Web.pdf", "Code_mẫu.zip", "Hướng dẫn.docx"]
          },
          {
            id: 2,
            title: "Tư vấn Tuyển sinh 2026",
            status: "upcoming",
            lecturer: "Nguyễn Văn A",
            date: "25/11/2025",
            time: "14:00 - 16:00",
            location: "H3 - 205",
            attendees: "150 sinh viên",
            documents: ["Thông tin tuyển sinh.pdf", "Học bổng.xlsx"]
          },
          {
            id: 3,
            title: "Hướng dẫn đồ án tốt nghiệp",
            status: "completed",
            lecturer: "Trần Thị B",
            date: "15/10/2025",
            time: "09:00 - 11:30",
            location: "C1-301",
            attendees: "85 sinh viên",
            documents: ["Mẫu đề cương.docx", "Quy định đồ án.pdf", "Video hướng dẫn.mp4"]
          },
          {
            id: 4,
            title: "Hội thảo AI & Machine Learning",
            status: "upcoming",
            lecturer: "Phạm Văn C",
            date: "28/11/2025",
            time: "18:00 - 20:30",
            location: "Phòng Hội thảo lớn - Tầng 10",
            attendees: "200+ sinh viên",
            documents: []
          }
        ]
      };
      resolve(historyData);
    }, 400); // delay nhẹ để thấy loading
  });
}