package com.example.orderwise.slack.service;

import com.example.orderwise.slack.config.SlackAppProperties;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class SlackMessageService {

    public final SlackAppProperties slackAppProperties;

    public void sendSlackMessage(String message) throws IOException {
        OkHttpClient client = new OkHttpClient();

        String jsonPayload = "{\"text\":\"" + message + "\"}";

        RequestBody body = RequestBody.create(
                jsonPayload, MediaType.get("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url(slackAppProperties.getWebhookUrl())
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
        }
    }
}
