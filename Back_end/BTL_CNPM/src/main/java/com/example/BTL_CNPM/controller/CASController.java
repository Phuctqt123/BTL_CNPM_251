package com.example.BTL_CNPM.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/cas")
@CrossOrigin(origins = "*")
public class CASController {

    private static final String DB_URL =
            "jdbc:postgresql://aws-0-ap-southeast-1.pooler.supabase.com:5432/postgres";

    private static final String DB_USER = "postgres.ccgetqgeblzfniakoasx";
    private static final String DB_PASS = "Phuctqt123@";

    private String readRawBody(HttpServletRequest request) throws IOException {
        StringBuilder raw = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                raw.append(line);
            }
        }
        return raw.toString();
    }

    @PostMapping(value = "/verify", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object verifyUser(
            @RequestHeader(value = "Authorization", required = false) String authorization,
            @RequestBody(required = false) Map<String, Object> body,
            HttpServletRequest request
    ) {
        System.out.println("=== Đang xử lý yêu cầu /verify ===");

        try {
            String rawBody = readRawBody(request);
            System.out.println("Raw Body: " + (rawBody.isEmpty() ? "Không có body" : rawBody));
        } catch (Exception e) {
            System.out.println("Không đọc được raw body: " + e.getMessage());
        }

        String username = null;
        String password = null;

        // ===== BASIC AUTH =====
        if (authorization != null && authorization.startsWith("Basic ")) {
            try {
                String base64 = authorization.substring("Basic ".length());
                String decoded = new String(Base64.getDecoder().decode(base64), StandardCharsets.UTF_8);
                String[] parts = decoded.split(":", 2);

                username = parts[0];
                password = parts.length > 1 ? parts[1] : null;

                System.out.println("Basic Auth: " + username + "/" + password);
            } catch (Exception e) {
                return false;
            }
        }
        // ===== BODY =====
        else if (body != null && body.containsKey("username") && body.containsKey("password")) {
            username = body.get("username").toString();
            password = body.get("password").toString();
        } else {
            return false;
        }

        if (username == null || password == null)
            return false;

        // ===== KẾT NỐI DB =====
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {

            String sql = "SELECT * FROM users WHERE Taikhoan = ? AND Matkhau = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String keyUser = rs.getString("key_user");
                String role = rs.getString("vai_tro");

                Map<String, Object> response = new HashMap<>();
                response.put("@class", "org.apereo.cas.authentication.principal.SimplePrincipal");
                response.put("id", username);

                Map<String, Object> attr = new HashMap<>();
                attr.put("@class", "java.util.HashMap");
                attr.put("username", new Object[]{"java.util.ArrayList", new Object[]{username}});
                attr.put("keyuser", new Object[]{"java.util.ArrayList", new Object[]{keyUser}});
                attr.put("role", new Object[]{"java.util.ArrayList", new Object[]{role}});

                response.put("attributes", attr);

                return response;
            }

            return false;

        } catch (SQLException e) {
            System.out.println("DB Error: " + e.getMessage());
            return false;
        }
    }
}
