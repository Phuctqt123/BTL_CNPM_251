package com.example.BTL_CNPM.Service;

import java.util.ArrayList;
import java.util.List;// thay đổi
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static List<Map<String, Object>> toMap(String json) {
        if (json == null || json.isBlank()) {
            return new ArrayList<>();
        }

        List<Map<String, Object>> list = new ArrayList<>();

        try {
            // Thử parse như mảng JSON bình thường
            list = mapper.readValue(json, new TypeReference<List<Map<String, Object>>>() {});
        } catch (JsonProcessingException e) {
            // Nếu lỗi, có thể là nhiều object nối tiếp nhau (không có dấu [,])
            try {
                String[] lines = json.split("\\r?\\n");
                for (String line : lines) {
                    if (!line.isBlank()) {
                        Map<String, Object> map = mapper.readValue(line, new TypeReference<Map<String, Object>>() {});
                        list.add(map);
                    }
                }
            } catch (JsonProcessingException ex) {
                throw new RuntimeException("Lỗi parse JSON → List<Map>: " + json, ex);
            }
        }

        return list;
    }

    public static List<Map<String, Object>> toMap2(String json) {
        if (json == null || json.trim().isEmpty() || json.trim().equals("{}")) {
            return new ArrayList<>();
        }

        try {
            JsonNode root = mapper.readTree(json);

            // Kiểm tra lỗi từ DB
            if (root.has("error")) {
                System.err.println("DB error: " + root.get("error").asText());
                return new ArrayList<>();
            }

            JsonNode arrayNode;

            // Trường hợp 1: { "27": [...] } → lấy value đầu tiên
            if (root.isObject() && root.size() > 0) {
                arrayNode = root.elements().next();
            }
            // Trường hợp 2: JSON là mảng luôn → dùng trực tiếp
            else if (root.isArray()) {
                arrayNode = root;
            } else {
                return new ArrayList<>(); // không phải dạng mong muốn
            }

            if (!arrayNode.isArray()) {
                return new ArrayList<>();
            }

            return mapper.convertValue(arrayNode, new TypeReference<List<Map<String, Object>>>() {});

        } catch (JsonProcessingException e) {
            // Lỗi parse JSON (cú pháp sai, không phải JSON hợp lệ)
            System.err.println("JSON không hợp lệ: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            // Lỗi khi convert (rất hiếm, nhưng vẫn bắt)
            System.err.println("Lỗi convert JSON: " + e.getMessage());
        }

        // Trả về danh sách rỗng nếu có lỗi
        return new ArrayList<>();
    }


}

