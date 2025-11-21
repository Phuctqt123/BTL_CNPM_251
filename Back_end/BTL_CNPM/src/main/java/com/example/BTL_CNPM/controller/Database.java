package com.example.BTL_CNPM.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;

public class Database {

    private static final String URL = "jdbc:postgresql://aws-0-ap-southeast-1.pooler.supabase.com:5432/postgres";
    private static final String USER = "postgres.ccgetqgeblzfniakoasx";
    private static final String PASS = "Phuctqt123@";

    // ================= Tutor APIs =================

    // 1. Lấy thông tin cá nhân giảng viên
    public static String apiTutorGetProfile(String gvKey) {
        String sql = "{? = call api_tutor_get_profile(?)}";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.registerOutParameter(1, Types.OTHER);
            cs.setString(2, gvKey);
            cs.execute();
            Object result = cs.getObject(1);
            return result != null ? result.toString() : null;

        } catch (Exception e) {
            return "❌ Error: " + e.getMessage();
        }
    }

    // 2. Tạo buổi tư vấn
    public static String apiTutorCreateSession(String gvKey, String tenBuoi, String hinhThuc,
                                                Timestamp thoiGianBD, Timestamp thoiGianKT,
                                                String ghiChu, String diaChi, String linkGgmeet,
                                                Integer slToiThieu, Integer slToiDa) {
        String sql = "{? = call api_tutor_create_session(?,?,?,?,?,?,?,?,?,?)}";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.registerOutParameter(1, Types.OTHER);
            cs.setString(2, gvKey);
            cs.setString(3, tenBuoi);
            cs.setString(4, hinhThuc);
            cs.setTimestamp(5, thoiGianBD);
            cs.setTimestamp(6, thoiGianKT);
            cs.setString(7, ghiChu);
            cs.setString(8, diaChi);
            cs.setString(9, linkGgmeet);
            if (slToiThieu != null) cs.setInt(10, slToiThieu); else cs.setNull(10, Types.INTEGER);
            if (slToiDa != null) cs.setInt(11, slToiDa); else cs.setNull(11, Types.INTEGER);

            cs.execute();
            Object result = cs.getObject(1);
            return result != null ? result.toString() : null;

        } catch (Exception e) {
            return "❌ Error: " + e.getMessage();
        }
    }

    // 3. Lấy lịch sử các buổi tư vấn của giảng viên
    public static String apiTutorGetHistory(String gvKey) {
        String sql = "SELECT result FROM api_tutor_get_history(?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, gvKey);
            ResultSet rs = ps.executeQuery();
            StringBuilder sb = new StringBuilder();
            while(rs.next()) {
                Object value = rs.getObject("result");
                sb.append(value != null ? value.toString() : "NULL").append("\n");
            }
            return sb.toString();

        } catch (Exception e) {
            return "❌ Error: " + e.getMessage();
        }
    }

    // 4. Xem tài liệu buổi tư vấn (Tutor & Student)
    public static String apiGetDocumentsOfSession(int buoiId) {
        String sql = "SELECT result FROM api_get_documents_of_session(?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, buoiId);
            ResultSet rs = ps.executeQuery();
            StringBuilder sb = new StringBuilder();
            while(rs.next()) {
                Object value = rs.getObject("result");
                sb.append(value != null ? value.toString() : "NULL").append("\n");
            }
            return sb.toString();

        } catch (Exception e) {
            return "❌ Error: " + e.getMessage();
        }
    }

    // 5. Thêm tài liệu vào buổi tư vấn
    public static String apiTutorAddDocument(String gvKey, int buoiId, String filename) {
        String sql = "{? = call api_tutor_add_document(?,?,?)}";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.registerOutParameter(1, Types.OTHER);
            cs.setString(2, gvKey);
            cs.setInt(3, buoiId);
            cs.setString(4, filename);
            cs.execute();
            Object result = cs.getObject(1);
            return result != null ? result.toString() : null;

        } catch (Exception e) {
            return "❌ Error: " + e.getMessage();
        }
    }

    // 6. Xóa tài liệu khỏi buổi tư vấn
    public static String apiTutorDeleteDocument(String gvKey, int buoiId, int taiLieuId) {
        String sql = "{? = call api_tutor_delete_document(?,?,?)}";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.registerOutParameter(1, Types.OTHER);
            cs.setString(2, gvKey);
            cs.setInt(3, buoiId);
            cs.setInt(4, taiLieuId);
            cs.execute();
            Object result = cs.getObject(1);
            return result != null ? result.toString() : null;

        } catch (Exception e) {
            return "❌ Error: " + e.getMessage();
        }
    }

    // 7. Hủy buổi tư vấn (Tutor hoặc Student)
    public static String apiCancelSession(String userKey, int buoiId) {
        String sql = "{? = call api_cancel_session(?,?)}";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.registerOutParameter(1, Types.OTHER);
            cs.setString(2, userKey);
            cs.setInt(3, buoiId);
            cs.execute();
            Object result = cs.getObject(1);
            return result != null ? result.toString() : null;

        } catch (Exception e) {
            return "❌ Error: " + e.getMessage();
        }
    }

    // 8. Gửi đánh giá
    public static String apiSubmitRating(String nguoiDanhGia, int buoiId, String loaiDanhGia,
                                         int diemSo, String nguoiDuocDg, String noiDung) {
        String sql = "{? = call api_submit_rating(?,?,?,?,?,?)}";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.registerOutParameter(1, Types.OTHER);
            cs.setString(2, nguoiDanhGia);
            cs.setInt(3, buoiId);
            cs.setString(4, loaiDanhGia);
            cs.setInt(5, diemSo);
            if(nguoiDuocDg != null) cs.setString(6, nguoiDuocDg); else cs.setNull(6, Types.VARCHAR);
            if(noiDung != null) cs.setString(7, noiDung); else cs.setNull(7, Types.VARCHAR);
            cs.execute();
            Object result = cs.getObject(1);
            return result != null ? result.toString() : null;

        } catch (Exception e) {
            return "❌ Error: " + e.getMessage();
        }
    }

    // ================= Student APIs =================

    // 9. Lấy trang chủ student
    public static String apiStudentHomepage() {
        String sql = "SELECT result FROM api_student_homepage()";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            StringBuilder sb = new StringBuilder();
            while(rs.next()) {
                Object value = rs.getObject("result");
                sb.append(value != null ? value.toString() : "NULL").append("\n");
            }
            return sb.toString();
        } catch(Exception e) {
            return "❌ Error: " + e.getMessage();
        }
    }

    // 10. Lấy profile student
    public static String apiStudentGetProfile(String svKey) {
        String sql = "{? = call api_student_get_profile(?)}";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.registerOutParameter(1, Types.OTHER);
            cs.setString(2, svKey);
            cs.execute();
            Object result = cs.getObject(1);
            return result != null ? result.toString() : null;

        } catch(Exception e) {
            return "❌ Error: " + e.getMessage();
        }
    }

    // 11. Đăng ký buổi tư vấn
    public static String apiStudentRegisterSession(String svKey, int buoiId) {
        String sql = "{? = call api_student_register_session(?,?)}";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.registerOutParameter(1, Types.OTHER);
            cs.setString(2, svKey);
            cs.setInt(3, buoiId);
            cs.execute();
            Object result = cs.getObject(1);
            return result != null ? result.toString() : null;

        } catch(Exception e) {
            return "❌ Error: " + e.getMessage();
        }
    }

    // 12. Lấy lịch sử buổi tư vấn của student
    public static String apiStudentGetHistory(String svKey) {
        String sql = "SELECT result FROM api_student_get_history(?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, svKey);
            ResultSet rs = ps.executeQuery();
            StringBuilder sb = new StringBuilder();
            while(rs.next()) {
                Object value = rs.getObject("result");
                sb.append(value != null ? value.toString() : "NULL").append("\n");
            }
            return sb.toString();

        } catch(Exception e) {
            return "❌ Error: " + e.getMessage();
        }
    }

    // ================= PDT / Admin APIs =================

    // 16. Dashboard PDT
    public static String apiPdtDashboard() {
        String sql = "{? = call api_pdt_dashboard()}";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.registerOutParameter(1, Types.OTHER);
            cs.execute();
            Object result = cs.getObject(1);
            return result != null ? result.toString() : null;

        } catch(Exception e) {
            return "❌ Error: " + e.getMessage();
        }
    }

    // 17. Danh sách sinh viên
    public static String apiPdtListStudents() {
        String sql = "SELECT result FROM api_pdt_list_students()";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            StringBuilder sb = new StringBuilder();
            while(rs.next()) {
                Object value = rs.getObject("result");
                sb.append(value != null ? value.toString() : "NULL").append("\n");
            }
            return sb.toString();

        } catch(Exception e) {
            return "❌ Error: " + e.getMessage();
        }
    }

    // 18. Danh sách buổi tư vấn
    public static String apiPdtListSessions() {
        String sql = "SELECT result FROM api_pdt_list_sessions()";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            StringBuilder sb = new StringBuilder();
            while(rs.next()) {
                Object value = rs.getObject("result");
                sb.append(value != null ? value.toString() : "NULL").append("\n");
            }
            return sb.toString();

        } catch(Exception e) {
            return "❌ Error: " + e.getMessage();
        }
    }

    // 19. Chi tiết 1 buổi tư vấn
    public static String apiPdtSessionDetail(int buoiId) {
        String sql = "{? = call api_pdt_session_detail(?)}";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.registerOutParameter(1, Types.OTHER);
            cs.setInt(2, buoiId);
            cs.execute();
            Object result = cs.getObject(1);
            return result != null ? result.toString() : null;

        } catch(Exception e) {
            return "❌ Error: " + e.getMessage();
        }
    }
}
