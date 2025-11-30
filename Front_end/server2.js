const express = require("express");
const path = require("path");

const app = express();

// Lấy port từ Railway
const PORT = process.env.PORT || 8080;

// Serve static toàn bộ thư mục
app.use(express.static(path.join(__dirname)));

app.use((req, res, next) => {
    console.log(`[${new Date().toISOString()}] ${req.method} ${req.url}`);
    next();
});

//
// ================== PDT ==================
//
app.get("/pdt/home", (req, res) => {
    res.sendFile(path.join(__dirname, "PDT-PCT/index.html"));
});

app.get("/pdt/danhsachsinhvien", (req, res) => {
    res.sendFile(path.join(__dirname, "PDT-PCT/PDT_Xemdssinhvien.html"));
});

app.get("/pdt/danhsachtuvan", (req, res) => {
    res.sendFile(path.join(__dirname, "PDT-PCT/PDT_Xemdstuvan.html"));
});

app.get("/pdt/chitietbuoituvan", (req, res) => {
    res.sendFile(path.join(__dirname, "PDT-PCT/PDT_Xemthongtinchitietbuoituvan.html"));
});

// JS PDT
["data", "data2", "data3", "data4"].forEach((file) => {
    app.get(`/pdt/${file}`, (req, res) => {
        res.sendFile(path.join(__dirname, `PDT-PCT/${file}.js`));
    });
});

//
// ================== SINH VIÊN ==================
//
app.get("/sv/home", (req, res) => {
    res.sendFile(path.join(__dirname, "Sinhvien/index.html"));
});

app.get("/sv/dangkybuoituvan", (req, res) => {
    res.sendFile(path.join(__dirname, "Sinhvien/SVHS_dangkybuoituvan.html"));
});

app.get("/sv/lichsudangky", (req, res) => {
    res.sendFile(path.join(__dirname, "Sinhvien/SVHS_lichsudangky.html"));
});

app.get("/sv/thongtincanhan", (req, res) => {
    res.sendFile(path.join(__dirname, "Sinhvien/SVHS_thongtincanhan.html"));
});

// JS Sinh viên
["data", "data2", "data3", "data4"].forEach((file) => {
    app.get(`/sv/${file}`, (req, res) => {
        res.sendFile(path.join(__dirname, `Sinhvien/${file}.js`));
    });
});

// Video m3u8
app.use('/Video_m3u8', express.static(path.join(__dirname, 'Sinhvien/Video_m3u8')));

//
// ================== TUTOR ==================
//
app.get("/tutor/home", (req, res) => {
    res.sendFile(path.join(__dirname, "Tutor/index.html"));
});

app.get("/tutor/taobuoituvan", (req, res) => {
    res.sendFile(path.join(__dirname, "Tutor/tutor_taobuoituvan.html"));
});

app.get("/tutor/thongtincanhan", (req, res) => {
    res.sendFile(path.join(__dirname, "Tutor/tutor_thongtincanhan.html"));
});

app.get("/tutor/xemlichsu", (req, res) => {
    res.sendFile(path.join(__dirname, "Tutor/tutor_xemlichsu.html"));
});

app.get("/login", (req, res) => {
    res.sendFile(path.join(__dirname, "trangchu.html"));
});


// JS Tutor
["data", "data2"].forEach((file) => {
    app.get(`/tutor/${file}`, (req, res) => {
        res.sendFile(path.join(__dirname, `Tutor/${file}.js`));
    });
});

// Start server
app.listen(PORT, () => {
    console.log(`Server chạy tại: http://localhost:${PORT}`);
});
