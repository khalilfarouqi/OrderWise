package com.example.orderwise.slack.service;

import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SlackMessageService {
    private static final String WEBHOOK_URL = "https://hooks.slack.com/services/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";

    public void sendSlackMessage(String message) throws IOException {
        OkHttpClient client = new OkHttpClient();

        String jsonPayload = "{\"text\":\"" + message + "\"}";

        RequestBody body = RequestBody.create(
                jsonPayload, MediaType.get("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url(WEBHOOK_URL)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
        }
    }
}
