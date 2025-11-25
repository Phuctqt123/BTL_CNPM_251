package com.example.BTL_CNPM.Service;

import java.util.ArrayList;
import java.util.List;// thay đổi
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
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


}

