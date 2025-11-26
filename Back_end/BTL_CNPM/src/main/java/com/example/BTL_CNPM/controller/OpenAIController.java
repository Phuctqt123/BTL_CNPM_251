package com.example.BTL_CNPM.controller;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;

import jakarta.annotation.PostConstruct;



@RestController
@RequestMapping("/api/openai")
@CrossOrigin(origins = "*")
public class OpenAIController {

    @Value("${openai.api.key}")
    private String apiKey;

    private OpenAIClient client;

    @PostConstruct
    public void init() {
        this.client = OpenAIOkHttpClient.builder()
                .apiKey(apiKey)
                .build();
    }

    @PostMapping("/chat")
    public String chatWithOpenAI(@RequestBody Map<String, String> payload) {
        try {
            String systemPrompt = payload.getOrDefault("system", "Bạn là trợ lý tên là 'Hai Mặt Một Lời', Bạn làm việc cho trường đại học Bách Khoa HCM (HCMUT), bạn đang trả lời cho câu hỏi cho sinh viên về các buổi tư vấn, hãy trả lời nghiêm túc và thân thiện.");
            String userText = payload.get("text");
            String id = payload.get("id");
            String danhsachbuoituvan = Database.apiStudentGetHistory(id);

            if (userText == null || userText.isEmpty()) {
                return "Text is required!";
            }

            String abcd = "Thời gian hiện tại là " + LocalDateTime.now().toString() +
                          " Danh sách lịch sử buổi tư vấn của sinh viên là: " + danhsachbuoituvan;
            String fullInput = "System: " + systemPrompt + "\nUser: " + "Câu hỏi của sinh viên là " + userText + abcd;

            ResponseCreateParams params = ResponseCreateParams.builder()
                    .input(fullInput)
                    .model("gpt-4o-mini")
                    .build();

            Response response = client.responses().create(params);

            if (response.output().isEmpty()) {
                return "No response from OpenAI";
            }

            return response.output().get(0).asMessage().content().get(0).outputText().get().text();

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
