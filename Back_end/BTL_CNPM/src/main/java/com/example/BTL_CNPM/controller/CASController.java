package com.example.BTL_CNPM.controller;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cas")
@CrossOrigin(origins = "*")
public class CASController {

    private final String DB_URL = "jdbc:postgresql://aws-0-ap-southeast-1.pooler.supabase.com:5432/postgres";
    private final String DB_USER = "postgres.ccgetqgeblzfniakoasx";
    private final String DB_PASSWORD = "Phuctqt123@";

    // ===== POST /verify =====
    @PostMapping(value = "/verify", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> verify(
            @RequestHeader Map<String, String> headers,
            @RequestBody(required = false) Map<String, Object> body
    ) {

        String username = null;
        String password = null;

        // ===== Xử lý Basic Auth =====
        String authHeader = headers.get("authorization");
        if (authHeader != null && authHeader.startsWith("Basic ")) {
            try {
                String base64Credentials = authHeader.substring("Basic ".length());
                byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
                String credentials = new String(credDecoded, StandardCharsets.UTF_8);
                String[] values = credentials.split(":", 2);
                if (values.length == 2) {
                    username = values[0];
                    password = values[1];
                }
            } catch (Exception e) {
                System.err.println("❌ Basic Auth decode error: " + e.getMessage());
            }
        }
        // ===== Nếu không có Basic Auth, dùng body =====
        else if (body != null && body.get("username") != null && body.get("password") != null) {
            username = body.get("username").toString();
            password = body.get("password").toString();
        }

        // ===== Kiểm tra thông tin =====
        if (username == null || password == null) {
            System.out.println("❌ Thiếu username hoặc password");
            return Map.of("success", false);
        }

        // ===== Kết nối DB và xác thực =====
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT * FROM users WHERE Taikhoan = ? AND Matkhau = ?"
             )) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String taikhoan = rs.getString("Taikhoan");
                    String keyUser = rs.getString("Key_user");
                    String vaiTro = rs.getString("Vai_tro");

                    Map<String, Object> attributes = new HashMap<>();
                    attributes.put("username", List.of(taikhoan));
                    attributes.put("key_user", List.of(keyUser));
                    attributes.put("role", List.of(vaiTro));

                    Map<String, Object> response = new HashMap<>();
                    response.put("@class", "org.apereo.cas.authentication.principal.SimplePrincipal");
                    response.put("id", taikhoan);
                    response.put("attributes", attributes);

                    System.out.println("✅ Xác thực thành công cho: " + taikhoan);
                    return response;
                } else {
                    System.out.println("❌ Xác thực thất bại cho: " + username);
                    return Map.of("success", false);
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ DB Query Error: " + e.getMessage());
            return Map.of("success", false);
        }
    }
}
