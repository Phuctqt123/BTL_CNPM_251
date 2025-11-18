// data2.js - Toàn bộ dữ liệu và API giả lập cho phần Quản lý Buổi Bảo Vệ

const sessionsData = [
    {
        id: 1,
        title: "HƯỚNG DẪN BTL CNPM",
        lecturer: "Nguyễn Văn A",
        date: "30/10/2025",
        time: "10:00 - 13:00",
        location: "H3 - 205",
        students: 120,
        status: "upcoming"
    },
    {
        id: 2,
        title: "HƯỚNG DẪN BTL CNPM",
        lecturer: "Nguyễn Văn B",
        date: "30/10/2025",
        time: "13:00 - 16:00",
        location: "H3 - 205",
        students: 120,
        status: "upcoming"
    },
    {
        id: 3,
        title: "HƯỚNG DẪN BTL CNPM",
        lecturer: "Nguyễn Văn A",
        date: "18/10/2025",
        time: "10:00 - 13:00",
        location: "H3 - 206",
        students: 40,
        status: "completed"
    }
];

// Danh sách tài liệu theo buổi (sessionId)
const documents = {
    1: [
        { name: "Hướng dẫn BTL CNPM.pdf" },
        { name: "Mẫu báo cáo.docx" },
        { name: "Slide giới thiệu.pptx" }
    ],
    2: [
        { name: "Dữ liệu mẫu.xlsx" },
        { name: "Tài liệu bổ sung.zip" }
    ],
    3: [
        { name: "Video hướng dẫn.mp4" },
        { name: "Ảnh minh họa.png" },
        { name: "Ghi chú.txt" }
    ]
};

// Danh sách sinh viên tham gia buổi bảo vệ (chỉ có buổi completed mới cần đánh giá)
const studentsData = {
    3: [
        { id: 1, mssv: '12312449', name: 'Phạm Hồng Nhân',       participated: 'Có',   rating: '', comment: '' },
        { id: 2, mssv: '12312357', name: 'Nguyễn Cảnh Nguyên',   participated: 'Không', rating: '', comment: '' },
        { id: 3, mssv: '12312664', name: 'Bùi Đình Phúc',        participated: 'Có',   rating: '', comment: '' },
        { id: 4, mssv: '12310037', name: 'Trần Khánh An',        participated: 'Có',   rating: '', comment: '' },
        { id: 5, mssv: '12312501', name: 'Nguyễn Ngô Uyên Nhi',  participated: 'Có',   rating: '', comment: '' },
        { id: 6, mssv: '12312184', name: 'Phan Trần Trung Nam', participated: 'Có',   rating: '', comment: '' },
        { id: 7, mssv: '12313303', name: 'Nguyễn Xuân Thịnh',    participated: 'Không', rating: '', comment: '' },
    ]
};

// Hàm giả lập delay mạng (300ms) để trải nghiệm giống API thật
function delay(ms = 300) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

// 1. Lấy danh sách tất cả các buổi bảo vệ
async function getSessionsData() {
    await delay();
    return sessionsData;
}

// 2. Lấy danh sách tài liệu của một buổi cụ thể theo ID
async function getDocumentsBySessionId(sessionId) {
    await delay();
    return documents[sessionId] || [];
}

// 3. Lấy danh sách sinh viên của một buổi bảo vệ (chỉ có buổi completed mới có)
async function getStudentsBySessionId(sessionId) {
    await delay();
    return studentsData[sessionId] || [];
}

// 4. (Tùy chọn) Lưu đánh giá sinh viên – hiện tại chỉ lưu trong bộ nhớ
async function saveStudentEvaluation(sessionId, studentId, rating, comment) {
    await delay(200);
    const students = studentsData[sessionId];
    if (!students) return false;

    const student = students.find(s => s.id === studentId);
    if (student) {
        student.rating = rating;
        student.comment = comment || '';
        return true;
    }
    return false;
}

// Export nếu dùng module (ES6), hoặc để global cũng được
// export { getSessionsData, getDocumentsBySessionId, getStudentsBySessionId, saveStudentEvaluation };

// Nếu không dùng module, các hàm sẽ nằm trong window để dùng toàn cục
if (typeof window !== 'undefined') {
    window.getSessionsData = getSessionsData;
    window.getDocumentsBySessionId = getDocumentsBySessionId;
    window.getStudentsBySessionId = getStudentsBySessionId;
    window.saveStudentEvaluation = saveStudentEvaluation;
}