package com.example.BTL_CNPM.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BTL_CNPM.Service.Booking_Service;
import com.example.BTL_CNPM.Service.TutorService;
import com.example.BTL_CNPM.Service.Notify_Service;

@RestController
@RequestMapping("/api/Admin")
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
}
