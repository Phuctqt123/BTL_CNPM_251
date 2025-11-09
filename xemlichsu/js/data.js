// Global data storage
const studentsData = {
  3: [
    { id: 1, mssv: "12312449", name: "Phạm Hồng Nhân", participated: "Không", rating: "", comment: "" },
    { id: 2, mssv: "12312357", name: "Nguyễn Cảnh Nguyên", participated: "Không", rating: "", comment: "" },
    { id: 3, mssv: "12312664", name: "Bùi Đình Phúc", participated: "Có", rating: "", comment: "" },
    { id: 4, mssv: "12310037", name: "Trần Khánh An", participated: "Có", rating: "", comment: "" },
    { id: 5, mssv: "12312501", name: "Nguyễn Ngô Uyên Nhi", participated: "Có", rating: "", comment: "" },
    { id: 6, mssv: "12312184", name: "Phan Trần Trung Nam", participated: "Có", rating: "", comment: "" },
    { id: 7, mssv: "12313303", name: "Nguyễn Xuân Thịnh", participated: "Không", rating: "", comment: "" },
  ],
}

const documents = {
  1: ["Initial code_1", "Đặc tả_1", "File mẫu_1"],
  2: ["Initial code_2", "Đặc tả_2", "File mẫu_2"],
  3: ["Initial code_1", "Đặc tả_1", "File mẫu_1"],
}

const sessionsList = [
  {
    id: 1,
    title: "HƯỚNG DẪN BTL CNPM",
    instructor: "Nguyễn Văn A",
    date: "30/10/2025",
    time: "10:00 - 13:00",
    location: "H3 - 205",
    students: 120,
    status: "upcoming",
    className: "upcoming-card",
  },
  {
    id: 2,
    title: "HƯỚNG DẪN BTL CNPM",
    instructor: "Nguyễn Văn B",
    date: "30/10/2025",
    time: "13:00 - 16:00",
    location: "H3 - 205",
    students: 120,
    status: "upcoming",
    className: "upcoming-card",
  },
  {
    id: 3,
    title: "HƯỚNG DẪN BTL CNPM",
    instructor: "Nguyễn Văn A",
    date: "18/10/2025",
    time: "10:00 - 13:00",
    location: "H3 - 206",
    students: 40,
    status: "completed",
    className: "completed",
  },
]
