package com.example.BTL_CNPM.controller;

import java.util.HashMap;  // << IMPORT QUAN TRỌNG
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @GetMapping("/info")
    public Map<String, Object> getInfo() {
        Map<String, Object> data = new HashMap<>();
        data.put("name", "Phúc");
        data.put("age", 20);
        return data;
    }

    @PostMapping("/add")
    public Map<String, Object> add(@RequestBody Map<String, Object> body) {
        Map<String, Object> data = new HashMap<>();
        data.put("xyz", "abc");
        return data;
    }
}
