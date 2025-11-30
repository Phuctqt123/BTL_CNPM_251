// data.js - Dữ liệu có thể chỉnh sửa độc lập

// 1. Video configuration
const videoConfig = {
    src: "/Video_m3u8/videoplayback-1.m3u8"
};

// 2. Navigation Cards
const navCards = [
    {
        href: "/sv/thongtincanhan",
        icon: "fas fa-user-circle",
        title: "Thông Tin Cá Nhân",
        description: "Xem và cập nhật thông tin cá nhân, hồ sơ sinh viên của bạn"
    },
    {
        href: "/sv/dangkybuoituvan",
        icon: "fas fa-calendar-check",
        title: "Đăng Ký Buổi Tư Vấn",
        description: "Tìm và đăng ký các buổi tư vấn, hỗ trợ học tập phù hợp với bạn"
    },
    {
        href: "/sv/lichsudangky",
        icon: "fas fa-history",
        title: "Lịch Sử Đăng Ký",
        description: "Xem lịch sử đăng ký buổi tư vấn và các buổi học đã tham gia"
    }
];

// 3. Notifications
const notifications = [
    {
        time: "2/11/2025 - 20:50",
        text: 'Mở buổi học "Định hướng nghề nghiệp tương laiabc".'
    },
    {
        time: "1/11/2025 - 19:30",
        text: 'Buổi học "Hỗ trợ BTL3 PPL" đã có sự thay đổi.'
    },
    {
        time: "1/11/2025 - 9:00",
        text: 'Tài liệu có thể bạn quan tâm "Tài liệu UML" đã đăng tải.'
    }
];
window.videoConfig = videoConfig;
window.navCards=navCards;
window.notifications=notifications;