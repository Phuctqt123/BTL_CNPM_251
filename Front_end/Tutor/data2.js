// data2.js - Toàn bộ dữ liệu và API giả lập cho phần Quản lý Buổi Bảo Vệ
import TutorApi from "../api/TutorAPI.js";
// const sessionsData = [
//     {
//         id: 1,
//         title: "HƯỚNG DẪN BTL CNPM",
//         lecturer: "Nguyễn Văn A",
//         date: "30/10/2025",
//         time: "10:00 - 13:00",
//         location: "H3 - 205",
//         students: 120,
//         status: "upcoming"
//     },
//     {
//         id: 2,
//         title: "HƯỚNG DẪN BTL CNPM",
//         lecturer: "Nguyễn Văn B",
//         date: "30/10/2025",
//         time: "13:00 - 16:00",
//         location: "H3 - 205",
//         students: 120,
//         status: "upcoming"
//     },
//     {
//         id: 3,
//         title: "HƯỚNG DẪN BTL CNPM",
//         lecturer: "Nguyễn Văn A",
//         date: "18/10/2025",
//         time: "10:00 - 13:00",
//         location: "H3 - 206",
//         students: 40,
//         status: "completed"
//     }
// ];

// Danh sách tài liệu theo buổi (sessionId)
// const documents = {
//     1: [
//         { name: "Hướng dẫn BTL CNPM.pdf" },
//         { name: "Mẫu báo cáo.docx" },
//         { name: "Slide giới thiệu.pptx" }
//     ],
//     2: [
//         { name: "Dữ liệu mẫu.xlsx" },
//         { name: "Tài liệu bổ sung.zip" }
//     ],
//     3: [
//         { name: "Video hướng dẫn.mp4" },
//         { name: "Ảnh minh họa.png" },
//         { name: "Ghi chú.txt" }
//     ]
// };

// Danh sách sinh viên tham gia buổi (chỉ có buổi completed mới cần đánh giá)
// const studentsData = {
//     3: [
//         { id: 1, mssv: '12312449', name: 'Phạm Hồng Nhân',       participated: 'Có',   rating: '', comment: '' },
//         { id: 2, mssv: '12312357', name: 'Nguyễn Cảnh Nguyên',   participated: 'Không', rating: '', comment: '' },
//         { id: 3, mssv: '12312664', name: 'Bùi Đình Phúc',        participated: 'Có',   rating: '', comment: '' },
//         { id: 4, mssv: '12310037', name: 'Trần Khánh An',        participated: 'Có',   rating: '', comment: '' },
//         { id: 5, mssv: '12312501', name: 'Nguyễn Ngô Uyên Nhi',  participated: 'Có',   rating: '', comment: '' },
//         { id: 6, mssv: '12312184', name: 'Phan Trần Trung Nam', participated: 'Có',   rating: '', comment: '' },
//         { id: 7, mssv: '12313303', name: 'Nguyễn Xuân Thịnh',    participated: 'Không', rating: '', comment: '' },
//     ]
// };

// Hàm giả lập delay mạng (300ms) để trải nghiệm giống API thật


// 1. Lấy danh sách tất cả các buổi 
async function getSessionsData(user_key) {
    const raw = await TutorApi.getCreatedSessions(user_key);

    return raw.map(item => ({
        id: item.BuoiTuVan_id,
        title: item.Ten_buoi_van,
        lecturer: item.GiangVien_Hien_thi,
        date: item.Ngay_dien_ra,
        time: item.Gio,
        location: item.Dia_chi,
        students: item.So_luong_dang_ky,
        status: normalizeStatus(item)   // 👈 trạng thái chuẩn hóa
    }));
}


function normalizeStatus(item) {
    const s = item.Trang_thai_hien_thi?.toLowerCase() || "";

    if (s.includes("sắp diễn ra") || item.Sap_dien_ra === true) return "upcoming";
    if (s.includes("đã diễn ra") || s.includes("đã hoàn thành")) return "completed";

    return "completed"; // fallback
}




// 2. Lấy danh sách tài liệu của một buổi cụ thể theo ID
async function getDocumentsBySessionId(sessionId) {
    const raw = await TutorApi.getDocuments(sessionId);

    // Chuyển đổi sang dạng [{ id: 1, name: "abcd.pdf" }, ...]
    return raw.map(item => ({
        id: item.TaiLieu_ID,      // hoặc item.TaiLieu_ID ?? item.id nếu có thể null/undefined
        name: item.filename
    }));
}


// 3. Lấy danh sách sinh viên của một buổi (chỉ có buổi completed mới có)
async function getStudentsBySessionId(sessionId) {
    const raw = await TutorApi.getliststudent(sessionId);

    // Chuyển đổi dữ liệu về format mong muốn
    return raw.map(item => ({
        id: item.id,
        mssv: item.mssv,
        name: item.name,
        participated: item.participated || '', // nếu backend chưa trả
        rating: item.rating || '',             // nếu backend chưa có điểm
        comment: item.comment || ''            // nếu backend chưa có comment
    }));
}

 
// 4. (Tùy chọn) Lưu đánh giá sinh viên – hiện tại chỉ lưu trong bộ nhớ
async function saveStudentEvaluation(id_gv, sessionId, studentId, ratingText, comment) {
    const rating = convertRating(ratingText);

    const data = {
        nguoiDanhGia: id_gv,
        buoiId: sessionId,
        diemSo: rating,
        nguoiDuocDg: studentId,
        noiDung: comment
    };

    return TutorApi.reviewStudent(data);
}

function convertRating(text) {
    const map = {
        "Xuất sắc": 5,
        "Tốt": 4,
        "Khá": 3,
        "Trung bình": 2,
        "Kém": 1
    };
    return map[text] ?? null;
}


function add_document(gvKey, buoiId, filename) {
    const data = {
        gvKey: gvKey,
        buoiId: buoiId,
        filename: filename
    };
    
    return TutorApi.addDocument(data);
}


function delete_document(gvKey, buoiId, taiLieuId) {
    const data = {
        gvKey: gvKey,
        buoiId: buoiId,
        taiLieuId: taiLieuId
    };
    
    return TutorApi.deleteDocument(data);
}


function huy_buoi_tuvan(gvKey, id_session){
    const data = {
        gvKey: gvKey,
        buoiId: id_session
    };
    return TutorApi.cancelSession(data);
}
// Thêm 1 tài liệu vào buổi tư vấn

// Export nếu dùng module (ES6), hoặc để global cũng được
// export { getSessionsData, getDocumentsBySessionId, getStudentsBySessionId, saveStudentEvaluation };

// Nếu không dùng module, các hàm sẽ nằm trong window để dùng toàn cục
window.huy_buoi_tuvan=huy_buoi_tuvan
window.add_document = add_document;
window.delete_document = delete_document;
window.getSessionsData = getSessionsData;
window.getDocumentsBySessionId = getDocumentsBySessionId;
window.getStudentsBySessionId = getStudentsBySessionId;
window.saveStudentEvaluation = saveStudentEvaluation;
