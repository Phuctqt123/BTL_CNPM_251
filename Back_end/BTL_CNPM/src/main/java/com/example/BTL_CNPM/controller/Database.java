package com.example.BTL_CNPM.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private static final String URL = "jdbc:postgresql://aws-0-ap-southeast-1.pooler.supabase.com:5432/postgres";
    private static final String USER = "postgres.ccgetqgeblzfniakoasx";
    private static final String PASS = "Phuctqt123@";

    // ================= Tutor APIs =================

    // 1. Lấy thông tin cá nhân giảng viên
    public static String apiTutorGetProfile(String gvKey) throws Exception{
        String sql = "{? = call api_tutor_get_profile(?)}";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.registerOutParameter(1, Types.OTHER);
            cs.setString(2, gvKey);
            cs.execute();
            Object result = cs.getObject(1);
            return result != null ? result.toString() : null;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    // 2. Tạo buổi tư vấn
    public static String apiTutorCreateSession(String gvKey, String tenBuoi, String hinhThuc,
                                                Timestamp thoiGianBD, Timestamp thoiGianKT,
                                                String ghiChu, String diaChi, String linkGgmeet,
                                                Integer slToiThieu, Integer slToiDa) throws Exception{
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
            throw new Exception(e.getMessage());
        }
    }

    // 3. Lấy lịch sử các buổi tư vấn của giảng viên
    public static String apiTutorGetHistory(String gvKey) throws Exception{
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
            throw new Exception(e.getMessage());
        }
    }

    // 4. Xem tài liệu buổi tư vấn (Tutor & Student)
    public static String apiGetDocumentsOfSession(int buoiId) throws Exception{
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
            throw new Exception(e.getMessage());
        }
    }

    // 5. Thêm tài liệu vào buổi tư vấn
    public static String apiTutorAddDocument(String gvKey, int buoiId, String filename) throws Exception {
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
            // QUAN TRỌNG: NÉM LẠI LỖI, KHÔNG TRẢ STRING
            throw new Exception("Lỗi database khi thêm tài liệu: " + e.getMessage(), e);
        }
    }


    // 6. Xóa tài liệu khỏi buổi tư vấn
    public static String apiTutorDeleteDocument(String gvKey, int buoiId, int taiLieuId) throws Exception {
        String sql = "{? = call api_tutor_delete_document(?,?,?)}";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
            CallableStatement cs = conn.prepareCall(sql)) {

            // Đăng ký tham số OUT
            cs.registerOutParameter(1, Types.OTHER);

            // Tham số IN
            cs.setString(2, gvKey);
            cs.setInt(3, buoiId);
            cs.setInt(4, taiLieuId);

            // Thực thi procedure
            cs.execute();

            Object result = cs.getObject(1);
            return result != null ? result.toString() : null;

        } catch (Exception e) {
            // Bắt lỗi và ném lại để service xử lý
            throw new Exception("Lỗi database khi xóa tài liệu: " + e.getMessage(), e);
        }
    }


    // 7. Hủy buổi tư vấn (Tutor hoặc Student)
    public static String apiCancelSession(String userKey, int buoiId) throws Exception{
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
            throw new Exception("Lỗi database khi hủy buổi tư vấn: " + e.getMessage(), e);
        }
    }

    // 8. Gửi đánh giá
    public static String apiSubmitRating(String nguoiDanhGia, int buoiId, String loaiDanhGia,
                                         int diemSo, String nguoiDuocDg, String noiDung) throws Exception{
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
            throw new Exception("Lỗi database khi gửi đánh giá: " + e.getMessage(), e);
        }
    }

    // ================= Student APIs =================

    // 9. Lấy trang chủ student
    public static String apiStudentHomepage() throws Exception{
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
            throw new Exception(e.getMessage());
        }
    }

    // 10. Lấy profile student
    public static String apiStudentGetProfile(String svKey) throws Exception{
        String sql = "{? = call api_student_get_profile(?)}";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.registerOutParameter(1, Types.OTHER);
            cs.setString(2, svKey);
            cs.execute();
            Object result = cs.getObject(1);
            return result != null ? result.toString() : null;

        } catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    // 11. Đăng ký buổi tư vấn
    
    public static String apiStudentRegisterSession(String svKey, int buoiId) throws Exception{
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
            throw new Exception(e.getMessage());
        }
    }

    // 12. Lấy lịch sử buổi tư vấn của student
    public static String apiStudentGetHistory(String svKey) throws Exception{
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
            throw new Exception(e.getMessage());
        }
    }

    // ================= PDT / Admin APIs =================
    // 16. Dashboard PDT
    public static String apiPdtDashboard() throws Exception{
        String sql = "{? = call api_pdt_dashboard()}";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.registerOutParameter(1, Types.OTHER);
            cs.execute();
            Object result = cs.getObject(1);
            return result != null ? result.toString() : null;

        } catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    // 17. Danh sách sinh viên
    public static String apiPdtListStudents() throws Exception{
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
            throw new Exception(e.getMessage());
        }
    }

    // 18. Danh sách buổi tư vấn
    public static String apiPdtListSessions() throws Exception{
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
            throw new Exception(e.getMessage());
        }
    }

    // 19. Chi tiết 1 buổi tư vấn
    public static String apiPdtSessionDetail(int buoiId) throws Exception{
        String sql = "{? = call api_pdt_session_detail(?)}";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.registerOutParameter(1, Types.OTHER);
            cs.setInt(2, buoiId);
            cs.execute();
            Object result = cs.getObject(1);
            return result != null ? result.toString() : null;

        } catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    public static String apiGetUpcomingSessions() throws Exception {
        // Chuỗi SQL để gọi hàm trả về SETOF record (cần sử dụng RETURN QUERY/TABLE)
        String sql = "SELECT * FROM api_get_upcoming_sessions()";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             // Sử dụng Statement hoặc PreparedStatement cho câu lệnh SELECT đơn giản
             java.sql.PreparedStatement ps = conn.prepareStatement(sql)) {

            // Thực thi truy vấn
            try (java.sql.ResultSet rs = ps.executeQuery()) {
                StringBuilder resultJson = new StringBuilder();
                resultJson.append("[");
                
                // Lặp qua các bản ghi (mỗi bản ghi là 1 JSONB Object)
                while (rs.next()) {
                    // Lấy kết quả (cột đầu tiên)
                    String jsonItem = rs.getString(1);
                    if (resultJson.length() > 1) {
                        resultJson.append(",");
                    }
                    resultJson.append(jsonItem);
                }
                resultJson.append("]");
                
                return resultJson.toString();

            }
        } catch (Exception e) {
            // Log lỗi hoặc xử lý lỗi chi tiết hơn tại đây
            throw new Exception("Lỗi khi gọi API api_get_upcoming_sessions: " + e.getMessage());
        }
    }

    // Tham khảo: 1. Hàm lấy thông báo chung
    public static String apiGetGeneralNotice(String tenThongBao) throws Exception {
        // Nếu p_ten_thong_bao là NULL, PostgreSQL sẽ dùng giá trị mặc định 'ThongBaoChung'
        String sql = "{? = call api_get_general_notice(?)}"; 
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
            CallableStatement cs = conn.prepareCall(sql)) {

            cs.registerOutParameter(1, Types.OTHER); // Trả về JSONB
            
            // Thiết lập tham số đầu vào (ten_thong_bao)
            if (tenThongBao != null) {
                cs.setString(2, tenThongBao);
            } else {
                // Nếu Java truyền null, PostgreSQL sẽ dùng default là 'ThongBaoChung'
                // Tuy nhiên, để gọi hàm có default params, ta nên truyền giá trị rõ ràng nếu không phải default
                cs.setNull(2, Types.VARCHAR); 
            }

            cs.execute();
            
            // Lấy kết quả JSONB
            Object result = cs.getObject(1);
            return result != null ? result.toString() : null;

        } catch(Exception e) {
            throw new Exception("Lỗi khi lấy thông báo chung: " + e.getMessage());
        }
    }

    // Tham khảo: 2. Hàm cập nhật thông báo chung (chỉ Giảng viên/Admin)
    public static String apiUpdateGeneralNotice(String gvKey, String tenThongBao, String noiDungMoi) throws Exception {
        String sql = "{? = call api_update_general_notice(?, ?, ?)}";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
            CallableStatement cs = conn.prepareCall(sql)) {

            cs.registerOutParameter(1, Types.OTHER); // Trả về JSONB (status)
            
            // Thiết lập các tham số đầu vào
            cs.setString(2, gvKey);        // p_gv_key
            cs.setString(3, tenThongBao);  // p_ten_thong_bao
            cs.setString(4, noiDungMoi);   // p_noi_dung_moi

            cs.execute();
            
            // Lấy kết quả JSONB
            Object result = cs.getObject(1);
            return result != null ? result.toString() : null;

        } catch(Exception e) {
            throw new Exception("Lỗi khi cập nhật thông báo chung: " + e.getMessage());
        }
    }
    /**
     * Trả về mảng String[] các email cần gửi thông báo (getmail = TRUE)
     * Dùng cực kỳ tiện trong vòng lặp gửi mail
     */
    public static String[] getSubscribedEmailArray() throws Exception {
        String sql = "SELECT email FROM get_subscribed_emails_list()";  // Dùng hàm bảng
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            java.util.List<String> emailList = new java.util.ArrayList<>();
            while (rs.next()) {
                String email = rs.getString("email");
                if (email != null && !email.trim().isEmpty()) {
                    emailList.add(email.trim());
                }
            }

            return emailList.toArray(new String[0]);

        } catch (SQLException e) {
            throw new Exception("Lỗi khi lấy danh sách email nhận thông báo: " + e.getMessage(), e);
        }
    }
    public static List<String> getStudentFeedbackLast30Days(String email) throws Exception {
        String sql = "SELECT * FROM api_get_student_feedback_by_email_last_30days(?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                List<String> feedbackList = new ArrayList<>();

                while (rs.next()) {
                    String jsonStr = rs.getString("result");
                    if (jsonStr != null && !jsonStr.isEmpty()) {
                        feedbackList.add(jsonStr);
                    }
                }

                return feedbackList;
            }

        } catch (SQLException e) {
            throw new Exception("Lỗi khi lấy feedback sinh viên: " + e.getMessage(), e);
        }
    }
    
    public static String getStudentsInSession(int buoiId) throws Exception {
        String sql = "SELECT api_get_students_in_session(?) AS result";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, buoiId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Lấy cột JSONB dưới dạng String
                    String jsonResult = rs.getString("result");

                    // Nếu có lỗi từ hàm SQL (ví dụ: buổi không tồn tại)
                    if (jsonResult != null && jsonResult.contains("\"error\"")) {
                        // Vẫn trả về nguyên bản để frontend xử lý
                        return jsonResult;
                    }

                    // Nếu thành công: jsonResult sẽ là dạng {"27": [...]}
                    return jsonResult != null ? jsonResult : "{}";
                }
            }
        } catch (SQLException e) {
            throw new Exception("Lỗi khi lấy danh sách sinh viên buổi tư vấn ID = " + buoiId + ": " + e.getMessage(), e);
        }

        return "{}"; // Trường hợp không có kết quả (không nên xảy ra)
    }

}
