package com.example.BTL_CNPM.Service;

import java.util.HashMap;// thay đổi 
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static Map<String, Object> toMap(String json) {
        if (json == null || json.isBlank()) {
            return new HashMap<>(); // trả về map rỗng thay vì ném lỗi
        }

        try {
            return mapper.readValue(json, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Lỗi parse JSON → Map: " + json, e);
        }
    }
}

