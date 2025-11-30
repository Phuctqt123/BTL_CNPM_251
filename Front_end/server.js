const express = require("express");
const path = require("path");

// =====================================
// =========== SERVER PDT-PCT ==========
// =====================================
const appPDT = express();
const portPDT = 8083;

appPDT.use(express.static(path.join(__dirname)));

appPDT.use((req, res, next) => {
    console.log(`[PDT ${new Date().toISOString()}] ${req.method} ${req.url}`);
    next();
});

// --- Định tuyến PDT-PCT ---
appPDT.get("/pdt/home", (req, res) => {
    res.sendFile(path.join(__dirname, "PDT-PCT/index.html"));
});

appPDT.get("/pdt/danhsachsinhvien", (req, res) => {
    res.sendFile(path.join(__dirname, "PDT-PCT/PDT_Xemdssinhvien.html"));
});

appPDT.get("/pdt/danhsachtuvan", (req, res) => {
    res.sendFile(path.join(__dirname, "PDT-PCT/PDT_Xemdstuvan.html"));
});

appPDT.get("/pdt/chitietbuoituvan", (req, res) => {
    res.sendFile(path.join(__dirname, "PDT-PCT/PDT_Xemthongtinchitietbuoituvan.html"));
});

// JS PDT
appPDT.get("/pdt/data", (req, res) => {
    res.sendFile(path.join(__dirname, "PDT-PCT/data.js"));
});

appPDT.get("/pdt/data2", (req, res) => {
    res.sendFile(path.join(__dirname, "PDT-PCT/data2.js"));
});

appPDT.get("/pdt/data3", (req, res) => {
    res.sendFile(path.join(__dirname, "PDT-PCT/data3.js"));
});

appPDT.get("/pdt/data4", (req, res) => {
    res.sendFile(path.join(__dirname, "PDT-PCT/data4.js"));
});

// Start PDT Server
appPDT.listen(portPDT, () => {
    console.log(`📘 PDT-PCT server chạy tại: http://localhost:${portPDT}/pdt/home`);
});



// =====================================
// =========== SERVER SINH VIÊN ========
// =====================================
const appSV = express();
const portSV = 8084;

appSV.use(express.static(path.join(__dirname)));

appSV.use((req, res, next) => {
    console.log(`[SV ${new Date().toISOString()}] ${req.method} ${req.url}`);
    next();
});

// --- Định tuyến Sinh viên ---
appSV.get("/sv/home", (req, res) => {
    res.sendFile(path.join(__dirname, "Sinhvien/index.html"));
});

appSV.get("/sv/dangkybuoituvan", (req, res) => {
    res.sendFile(path.join(__dirname, "Sinhvien/SVHS_dangkybuoituvan.html"));
});

appSV.get("/sv/lichsudangky", (req, res) => {
    res.sendFile(path.join(__dirname, "Sinhvien/SVHS_lichsudangky.html"));
});

appSV.get("/sv/thongtincanhan", (req, res) => {
    res.sendFile(path.join(__dirname, "Sinhvien/SVHS_thongtincanhan.html"));
});

// JS Sinh viên
appSV.get("/sv/data", (req, res) => {
    res.sendFile(path.join(__dirname, "Sinhvien/data.js"));
});

appSV.get("/sv/data2", (req, res) => {
    res.sendFile(path.join(__dirname, "Sinhvien/data2.js"));
});

appSV.get("/sv/data3", (req, res) => {
    res.sendFile(path.join(__dirname, "Sinhvien/data3.js"));
});

appSV.get("/sv/data4", (req, res) => {
    res.sendFile(path.join(__dirname, "Sinhvien/data4.js"));
});

// thư mục Video M3U8
appSV.use('/Video_m3u8', express.static(path.join(__dirname, 'Sinhvien/Video_m3u8')));

// Start SV Server
appSV.listen(portSV, () => {
    console.log(`🎓 Sinh viên server chạy tại: http://localhost:${portSV}/sv/home`);
});



// =====================================
// =========== SERVER TUTOR ============
// =====================================
const appTutor = express();
const portTutor = 8085;

appTutor.use(express.static(path.join(__dirname)));

appTutor.use((req, res, next) => {
    console.log(`[Tutor ${new Date().toISOString()}] ${req.method} ${req.url}`);
    next();
});

// --- Định tuyến Tutor ---
appTutor.get("/tutor/home", (req, res) => {
    res.sendFile(path.join(__dirname, "Tutor/index.html"));
});

appTutor.get("/tutor/taobuoituvan", (req, res) => {
    res.sendFile(path.join(__dirname, "Tutor/tutor_taobuoituvan.html"));
});

appTutor.get("/tutor/thongtincanhan", (req, res) => {
    res.sendFile(path.join(__dirname, "Tutor/tutor_thongtincanhan.html"));
});

appTutor.get("/tutor/xemlichsu", (req, res) => {
    res.sendFile(path.join(__dirname, "Tutor/tutor_xemlichsu.html"));
});

// JS Tutor
appTutor.get("/tutor/data", (req, res) => {
    res.sendFile(path.join(__dirname, "Tutor/data.js"));
});

appTutor.get("/tutor/data2", (req, res) => {
    res.sendFile(path.join(__dirname, "Tutor/data2.js"));
});

// Start Tutor Server
appTutor.listen(portTutor, () => {
    console.log(`🧑‍🏫 Tutor server chạy tại: http://localhost:${portTutor}/tutor/home`);
});
