package com.example.BTL_CNPM.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.core.type.TypeReference;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BTL_CNPM.Service.Notify_Service;
import com.fasterxml.jackson.databind.ObjectMapper;

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
}
