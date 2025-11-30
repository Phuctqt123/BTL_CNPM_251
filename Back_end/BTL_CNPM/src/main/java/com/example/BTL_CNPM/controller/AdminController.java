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

import java.net.URLEncoder;


import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;



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
    public ResponseEntity<Map<String, Object>> getUserInfo(@RequestBody Map<String, String> body) {
        String ticket = body.get("ticket");
        String service = body.get("service");

        if (ticket == null || service == null) {
            return ResponseEntity.badRequest()
                    .body(Collections.singletonMap("error", "Missing ticket or service URL"));
        }

        try {
            // CAS URL
            String casHost = "https://casserver-production.up.railway.app";
            String validateUrl = casHost + "/cas/serviceValidate?service="
                    + URLEncoder.encode(service, "UTF-8")
                    + "&ticket=" + URLEncoder.encode(ticket, "UTF-8");

            System.out.println("[Backend-Java] Validating ST with CAS: " + validateUrl);

            // Không verify SSL (giống httpsAgent rejectUnauthorized:false)
            HttpComponentsClientHttpRequestFactory requestFactory =
                    new HttpComponentsClientHttpRequestFactory(
                            HttpClientBuilder.create()
                                    .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                                    .setSSLContext(new SSLContextBuilder()
                                            .loadTrustMaterial(null, (x, y) -> true)
                                            .build())
                                    .build()
                    );

            RestTemplate rest = new RestTemplate(requestFactory);

            ResponseEntity<String> response = rest.getForEntity(validateUrl, String.class);

            String xml = response.getBody();
            System.out.println("[Backend-Java] CAS status: " + response.getStatusCode());
            System.out.println("[Backend-Java] CAS response: " + xml);

            if (response.getStatusCodeValue() != 200) {
                return ResponseEntity.status(response.getStatusCodeValue())
                        .body(Collections.singletonMap("error", "CAS validation returned non-200 status"));
            }

            // Check authentication failure
            if (xml.contains("<cas:authenticationFailure")) {
                String reason = extractTag(xml, "authenticationFailure");
                return ResponseEntity.status(401)
                        .body(Collections.singletonMap("error", "ST validation failed: " + reason));
            }

            // Extract tags
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("username", extractTag(xml, "username"));
            attributes.put("keyuser", extractTag(xml, "keyuser"));
            attributes.put("role", extractTag(xml, "role"));

            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("attributes", attributes);

            return ResponseEntity.ok(result);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body(Collections.singletonMap("error", "CAS validation exception: " + e.getMessage()));
        }
    }
    
    
    private String extractTag(String xml, String tag) {
        Pattern pattern = Pattern.compile("<cas:" + tag + ">(.*?)</cas:" + tag + ">");
        Matcher matcher = pattern.matcher(xml);
        return matcher.find() ? matcher.group(1) : null;
    }

    
}
