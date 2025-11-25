package com.example.BTL_CNPM.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.BTL_CNPM.controller.Database;
import com.example.BTL_CNPM.domain.PDT_PCT;
import com.example.BTL_CNPM.domain.SinhVien;
import com.example.BTL_CNPM.domain.Tutor;
public class Notify_Service {
    public Map<String, Object> get_notify(String id) {
        Map<String, Object> result = new HashMap<>();
        String json;

        try {
            json = Database.apiGetGeneralNotice("ThongBao");
        } catch (Exception e) {
            result.put("notify", "Lỗi khi lấy thông báo: " + e.getMessage());
            return result;
        }

        // Parse JSON -> List<Map>
        List<Map<String, Object>> list = JSONUtil.toMap(json);

        // Lấy phần tử đầu tiên nếu có
        Map<String, Object> firstItem = list.isEmpty() ? 
                new HashMap<>() : list.get(0);

        String message = (String) firstItem.getOrDefault("Noi_dung", "Không có nội dung thông báo");

        String notifyResult;

        if (id.startsWith("SV")) {
            notifyResult = new SinhVien().Notify(message);
        } else if (id.startsWith("GV")) {
            notifyResult = new Tutor().Notify(message);
        } else if (id.startsWith("PDT") || id.startsWith("PCT")) {
            notifyResult = new PDT_PCT().Notify(message);
        } else {
            notifyResult = "Không xác định loại người dùng từ id: " + id;
        }

        result.put("notify", notifyResult);
        return result;
    }


    public boolean update_noidung(Map<String, Object> body ) {      
        String Key = body.get("Key").toString();
        String tenThongBao = "ThongBao";
        String noiDungMoi = body.get("noiDungMoi").toString();
        try {
            Database.apiUpdateGeneralNotice(Key, tenThongBao, noiDungMoi);
            return true;
        } catch (Exception e) {
            System.err.println("❌ Lỗi khi update nội dung thông báo: " + e.getMessage());
            return false;
        }
    }

}