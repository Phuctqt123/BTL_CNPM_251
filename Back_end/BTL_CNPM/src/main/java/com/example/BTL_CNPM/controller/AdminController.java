package com.example.BTL_CNPM.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.core.type.TypeReference;

import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.BTL_CNPM.Service.Notify_Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.http.*;
import java.net.URI;
import java.time.Duration;
import javax.net.ssl.*;
import java.security.*;


@RestController
@RequestMapping("/api/Admin")
@CrossOrigin(origins = "*")
public class AdminController {
    @PostMapping("/update_notify")
    public boolean update_notify(@RequestBody Map<String, Object> body) {
        Notify_Service service = new Notify_Service();
        return service.update_noidung(body);
    }
    @GetMapping("/notifications/{id}")
    public Map<String, Object> getNotifications(@PathVariable String id) {
        Notify_Service service = new Notify_Service();
        return service.get_notify(id);
    }
    @GetMapping("/get_listmail_n8n")
    public ResponseEntity<List<String>> get_listmail_n8n() {
        try {
            String[] emails = Database.getSubscribedEmailArray();
            return ResponseEntity.ok(Arrays.asList(emails));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(Collections.singletonList("ERROR: " + e.getMessage()));
        }
    }
    private final ObjectMapper mapper = new ObjectMapper();

    @GetMapping("/student_feedback/{email}")
    public ResponseEntity<List<Map<String, Object>>> getStudentFeedback(@PathVariable String email) {
        try {
            // Lấy danh sách JSON string từ Database
            List<String> feedbackStrings = Database.getStudentFeedbackLast30Days(email);

            // Chuyển từng JSON string thành Map<String,Object>
            List<Map<String, Object>> feedbacks = new ArrayList<>();
            for (String jsonStr : feedbackStrings) {
                Map<String, Object> map = mapper.readValue(
                        jsonStr, new TypeReference<Map<String, Object>>() {});
                feedbacks.add(map);
            }

            // Trả về JSON object cho client
            return ResponseEntity.ok(feedbacks);

        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(500)
                    .body(Collections.singletonList(error));
        }
    }

    @PostMapping("/userinfo")
    public ResponseEntity<Map<String, Object>> userInfo(@RequestBody Map<String, String> body) {
        String ticket = body.get("ticket");
        String service = body.get("service");

        System.out.println("[Backend] userinfo | ticket=" + ticket + " | service=" + service);

        if (ticket == null || service == null || ticket.isBlank() || service.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Missing ticket or service URL"));
        }

        
        final String CAS_HOST = "https://casserver-production.up.railway.app";   

        String url = CAS_HOST + "/cas/serviceValidate" +
                "?ticket=" + URLEncoder.encode(ticket, StandardCharsets.UTF_8) +
                "&service=" + URLEncoder.encode(service, StandardCharsets.UTF_8);

        System.out.println("[Backend] CAS validate URL: " + url);

        try {
            HttpClient client = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(10))
                    .sslContext(createTrustAllSslContext())   // bỏ qua SSL tự ký
                    .build();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(Duration.ofSeconds(20))
                    .GET()
                    .build();

            HttpResponse<String> resp = client.send(request, HttpResponse.BodyHandlers.ofString());
            String xml = resp.body();

            System.out.println("[Backend] CAS response (" + resp.statusCode() + "):\n" + xml);

            if (resp.statusCode() != 200) {
                return ResponseEntity.status(502).body(Map.of("error", "CAS server error " + resp.statusCode()));
            }

            if (xml.contains("<cas:authenticationFailure")) {
                String msg = extractTag(xml, "authenticationFailure");
                return ResponseEntity.status(401).body(Map.of("error", "Ticket không hợp lệ: " + msg));
            }

            Map<String, String> user = new HashMap<>();
            user.put("username", extractTag(xml, "username"));
            user.put("keyuser",  extractTag(xml, "keyuser"));
            user.put("role",     extractTag(xml, "role"));

            System.out.println("[Backend] Đăng nhập thành công → " + user);

            return ResponseEntity.ok(Map.of("success", true, "attributes", user));

        } catch (Exception e) {
            System.err.println("[Backend] Lỗi xác thực CAS: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("error", "Lỗi server"));
        }
    }

    // SSLContext chấp nhận mọi chứng chỉ (dùng cho CAS tự ký)
    private SSLContext createTrustAllSslContext() throws NoSuchAlgorithmException, KeyManagementException {
        TrustManager[] trustAllCerts = new TrustManager[] {
            new X509TrustManager() {
                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                    // Không làm gì
                }

                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                    // Không làm gì → chấp nhận mọi chứng chỉ
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[0];
                }
            }
        };

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
        return sslContext;
    }

    // Lấy nội dung trong tag <cas:tagName>....</cas:tagName>
    private String extractTag(String xml, String tagName) {
        String pattern = "<cas:" + Pattern.quote(tagName) + ">(.*?)</cas:" + Pattern.quote(tagName) + ">";
        Matcher matcher = Pattern.compile(pattern, Pattern.DOTALL).matcher(xml);
        return matcher.find() ? matcher.group(1).trim() : null;
    }

    
}
